import socket
from threading import Thread
import sys
import json

# get the IP address of the chatserver (www.ttm4200.com) 
# using your DNS server and socket methods
SERVER_IP = "10.20.30.3"

# Get the IP address of the client using socket methods
CLIENT_IP =  socket.gethostbyname(socket.gethostname())

PORT = 5000 
RECV_BUFFER = 1024

# Create a TCP socket 
TCP_SOCKET = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Create a UDP socket 
UDP_SOCKET = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Make the UDP socket reusable
UDP_SOCKET.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) 

# Bind the UDP socket to the CLIENT_IP and PORT
UDP_SOCKET.bind((CLIENT_IP, PORT))

# Dictionary for storing active users
ACTIVE_USERS = None

# Helping function to update active users
def update_active_users(msg):
    global ACTIVE_USERS
    ACTIVE_USERS = json.loads(msg[1:])


# Helping function to get the name of an active user from its IP
def get_name_from_ip_address(ip_address):
    name_list = [name for name, ip in ACTIVE_USERS.items() if ip == ip_address]
    name = name_list[0] if len(name_list) else 'Non-registered'
    return name 


# Main thread: connect to the server, then keep listening to receives msgs, 
# and send whenever there is an input
def connect_and_listen():
    try:
        # Connect TCP_SOCKET to the chat server
        TCP_SOCKET.connect((SERVER_IP, PORT))

        name = input("First enter your name to register it in the server: ")
        name = name.encode("utf-8")

        # send the "name" to the server
        TCP_SOCKET.send(name)

    except:
        print("could not connect to chat server...")
        sys.exit()

    print(""" Connection Established! 
    Now you can enter '#ls' to list active users,
    '@<user1>: msg' send msg to a specific user,
    or just write your msg  to send  to all users
    *************************************************""")
    
    # Start a thread to recieve msgs on TCP socket (server connection)
    Thread(target=receive_tcp).start()
    # Start a thread to recieve msgs on udp socket (msgs from other clients, p2p)
    Thread(target=receive_udp).start()
    # Start a thread to send the message when there is input. it will send either
    # to the server (TCP socket) or a specific client using its IP (UDP socket)
    Thread(target=send).start()


def receive_tcp():
    while True:
        try:
            # Store the message received from the server
            msg = TCP_SOCKET.recv(RECV_BUFFER)
            msg = msg.decode("utf-8")

            if msg[0]== '!':
                update_active_users(msg)
            else:
                print(f"incoming msg from chatserver ({msg})")

        except OSError:  # Possibly client has left the chat.
            TCP_SOCKET.close()
            sys.exit()


def receive_udp():
    while True:
        # Receive a message from any other client (p2p chat)
        msg, address = UDP_SOCKET.recvfrom(RECV_BUFFER)

        ip_address= address[0]
        sending_client = get_name_from_ip_address(ip_address)
        print (f"Incoming msg (P2P) from {sending_client}:{msg.decode('utf-8')}")


def send():
    while True:
        # Get input from the user
        usr_input = input(">")

        # List current active chat users
        if usr_input =='#ls':
            print(ACTIVE_USERS)

        # Send a msg to a single client (p2p) if it starts with "@"
        elif (len(usr_input) > 0) and (usr_input[0] == '@'):
            # Extract the name of the receiving_client and the msg
            temp = usr_input.split(':')
            receiving_client = temp[0][1:].strip()
            msg=':'.join(temp[1:])
            msg = msg.encode("utf-8")

            # Check if the receiving_client is in the active users
            #  (registered int the server)
            if receiving_client in ACTIVE_USERS:
                # Get the ip_address of the receiving client
                ip_address =ACTIVE_USERS[receiving_client]
                # Send "msg" to the receiving client
                UDP_SOCKET.sendto(msg, (ip_address, PORT))
            else:
                print(f"{receiving_client} is not registered")
        # Send a msg to all clients (chatroom), 
        # if it doesn't start with a specical character
        elif len(usr_input) > 0 :
            msg=usr_input
            msg = msg.encode("utf-8")
            # Send "msg" to the server (chatroom)
            TCP_SOCKET.send(msg)


if __name__ == "__main__":
    connection_threads = Thread(target=connect_and_listen)
    connection_threads.start()  # Starts the infinite loop.
    connection_threads.join(