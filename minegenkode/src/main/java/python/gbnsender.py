#%%writefile gbnsender.py

import socket
from threading import Thread
from threading import Lock
import sys
import time
import binascii


PORT = 5000 
SENDER_IP = "10.20.30.2"
RECEIVER_IP = "10.20.40.1"
RECV_BUFFER = 1024
# Create a UDP socket
UDP_SOCKET = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
UDP_SOCKET.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) 
# Bind the UDP socket to SENDER_IP and PORT
UDP_SOCKET.bind((SENDER_IP, PORT))


# Send a packet over unreliable data transprot(UDP)
def udt_send(pkt):
    print(f"send pkt{int.from_bytes(pkt[0:5], byteorder = 'little', signed = True)}")
    #Send "pkt" to the receiver
    UDP_SOCKET.sendto(pkt, (RECEIVER_IP, PORT))

  
# Creates a packet in bytes from seqnum, data and checksum
def make_pkt(seqnum, data, checksum):
    seqnum_bytes = seqnum.to_bytes(5, byteorder = 'little', signed = True)
    data_bytes = data.encode('utf-8')
    checksum_bytes = checksum.to_bytes(5, byteorder = 'little', signed = True)
    return seqnum_bytes+checksum_bytes+data_bytes


# Computes cyclic redundancy check (CRC), the 32-bit checksum of (seqnum + data)
def compute_checksum(seqnum, data):
    seqnum_bytes = seqnum.to_bytes(5, byteorder = 'little', signed = True)
    data_bytes = data.encode('utf-8')
    checksum = binascii.crc32(seqnum_bytes+data_bytes)
    return checksum

# Get acknowledgement number from a received packet
def getacknum(rcvpkt):
    acknum = int.from_bytes(rcvpkt[0:5], byteorder = 'little', signed = True)
    return acknum

#check if a received packet is not corrupted
def notcorrupt(rcvpkt):
    acknum = int.from_bytes(rcvpkt[0:5], byteorder = 'little', signed = True)
    checksum = int.from_bytes(rcvpkt[5:10], byteorder = 'little', signed = True)
    ACK = rcvpkt[10:].decode('utf-8')
    computed_checksum = compute_checksum(acknum, ACK)
    return (checksum == computed_checksum)


#receive packets from UDP socket
def rdt_rcv():
    # Receive a rcvpkt (acknowedgement) from the receiver
    rcvpkt, address = UDP_SOCKET.recvfrom(RECV_BUFFER)
    print(f"rcv ACK{int.from_bytes(rcvpkt[0:5], byteorder = 'little', signed = True)}")
    return rcvpkt


#End transmission and exit the main thread
def end_transmission():
    global thread_lock
    empty_data = ''
    checksum = compute_checksum(nextseqnum, empty_data)
    empty_pkt = make_pkt(nextseqnum, empty_data, checksum)
    UDP_SOCKET.sendto(empty_pkt, (RECEIVER_IP  ,PORT))
    print('end transmission')
    thread_lock.acquire()
    sys.exit()


# class that provides: starting, stopping, and checking a timer
class Timer(object):
    def __init__(self, timeout_interval):
        self._start_time = 0.0
        self._timeout_interval = timeout_interval
    def start(self):
        self._start_time = time.time()
    def stop(self):
        self._start_time = time.time()
    def running(self):
        return self._start_time > 0.0
    def timeout(self):
        return ((time.time() - self._start_time) >= self._timeout_interval)


#Figure1, circle 1: Initial state of GBN sender
#We start with 0 to be compatible with python (0 based indexing)
base = 0 
nextseqnum = 0

#GBN parameters
N = 4
#===================
# CHANGE TIMEOUT_INTERVAL TO SEE EFFECTS OF POOR NETWORK CONDITIONS
#===================
TIMEOUT_INTERVAL = 3

timer = Timer(TIMEOUT_INTERVAL)
thread_lock = Lock()



#Main thread sending packets, 
def send():
    global thread_lock
    global base
    global timer
    global N
    global nextseqnum

    #read text file (send_data.txt) and initialize empty sndpkt list
    with open('/home/ttm4200/work_dir/senddata.txt') as f:
        data_lines = f.readlines()
    sndpkt = [None]*len(data_lines) 

    #start a thread to receive ACK
    Thread(target=receive, daemon=True).start()

    #loop until you send all data
    while base < len(data_lines):
        thread_lock.acquire()
        N = min(N, len(data_lines) - base)

        #Figure1, circle 2:
        if (nextseqnum < base + N):
            data = data_lines[nextseqnum]
            checksum = compute_checksum(nextseqnum, data)

            # make packet using make_pkt function
            pkt = make_pkt(nextseqnum, data, checksum)

            # send the packet using udt_send function
            udt_send(pkt)

            # start the timer if the base equal nextseqnum
            if (base == nextseqnum):
                timer.start()
            

            # increment nextseqnum
            nextseqnum += 1         

        else:
            #refuse_data
            pass

        # Figure1, circle 3: if timeout, resend all packets (from the base)
        if timer.timeout():
            print(f"pkt{base} timeout")
            # Hint: reset the nextseqnum to the base, so it will go through 
            # the loop again and send packets from base
            nextseqnum = base

        thread_lock.release()
    end_transmission()


# Daemon thread  for  receiving acknowledgment
def receive():
    global thread_lock
    global base
    global timer
    global nextseqnum
    while True:
        rcvpkt = rdt_rcv()
        thread_lock.acquire()
        #Figure1, circle 4:
        if rcvpkt  and notcorrupt(rcvpkt):
            #get the acknum (getacknum) and set the base to it plus one
            base = getacknum(rcvpkt)+1
            
            #stop the time if base equeal nextseqnum, otherwise start it
            if (base == nextseqnum):
                timer.stop()
            else:
                timer.start()
            
        ##Figure1, circle 4:    
        else:
            #if the are no received packet or corruped packets, stay in the loop
            pass

        thread_lock.release()


if __name__ == '__main__':
    send()
    UDP_SOCKET.close()
