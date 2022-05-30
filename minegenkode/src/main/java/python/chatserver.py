#%%writefile chatserver.py


# Import necessary modules
import socket
from threading import Thread
import json
import sys
import hashlib
import time


password_dic = {}
# Get the IP address of the server using socket 
# Don't write the IP address directly.
SERVER_IP  = socket.gethostbyname(socket.gethostname())

# We will use a fixed port number
PORT = 5000 

# buffer size for receiving messages
RECV_BUFFER = 1024
# Dictionary for storing active Users
ACTIVE_USERS ={}  # Maps the name to the IP address

# Dictionary for storing active sockets
SOCKETS = {}  # Maps the socket to the name

# Create a server socket (TCP socket)
SERVER_SOCKET = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# bind the "SERVER_SOCKET" to the SERVER_IP and PORT
SERVER_SOCKET.bind((SERVER_IP, PORT))




# Main thread: accepting connections from clients and making
# a child thread for every client
def accept_incoming_connections():
    while True:
        # make the SERVER_SOCKET accept  connection from a client
        client_socket, client_address = SERVER_SOCKET.accept()
        try:
            #recieve the first message from the client_socket
            name = client_socket.recv(RECV_BUFFER)
            name= name.decode("utf-8")
            if name in password_dic:
                send_pass = "Skriv inn passord"
                send_pass = send_pass.encode()
                client_socket.send(send_pass)
                passord = client_socket.recv(RECV_BUFFER)
                #passord = passord.decode("utf-8")
                hash1 = hashlib.md5(passord).hexdigest()
                print(hash1)
                print(password_dic[name])
                if password_dic[name] == hash1:
                    client_socket.send("Du er naa logget inn!".encode("utf8"))
                else:
                    client_socket.send("Feil brukernavn og/eller passord. ".encode("utf8"))
                    client_socket.close()
                    
            else:
                client_socket.send("Enter your password".encode("utf8"))
                passord1 = client_socket.recv(RECV_BUFFER)
                hash2 = hashlib.md5(passord1).hexdigest()
                password_dic[name] = hash2
            
                    

            #Storing the client name and its socket
            #print(password_dic)
            SOCKETS[client_socket] = name
            ACTIVE_USERS[name] = client_address[0]
            print(f"{name} with IP address:{client_address[0]}, has connected\n")
            # Updating all clients of the current active users
            time.sleep(0.5)
            broadcast_active_users()

        except:
            # close the client_socket if an error occurs
            client_socket.close()
            print('Error connecting to a client')

        #Starting a thread for the connected client
        Thread(target=handle_client, args=(client_socket,)).start()


#Thread for handling connection to every client
def handle_client(client_socket):

    while True:
        try:
            # receive a message from the client_socket
            msg = client_socket.recv(RECV_BUFFER)
            msg = msg.decode("utf-8")

        except:
            # handle broken socket connection (e.g. the client pressed ctrl+c)
            client_socket.close()
            del(ACTIVE_USERS[SOCKETS[client_socket]])
            del SOCKETS[client_socket]
            broadcast_active_users()
            sys.exit()

        # len(msg)==0 when exiting wiht KeyboardInterrupt (ctrl+c)
        if len(msg)==0:
            client_socket.close()
            del(ACTIVE_USERS[SOCKETS[client_socket]])
            del(SOCKETS[client_socket])
            broadcast_active_users()
            sys.exit()
        else:
            # Attaching the name of sending client to msg
            msg = f"{SOCKETS[client_socket]}: {msg} "

            # Send the received msg to every client, except this client 
            broadcast_msg(client_socket, msg)


# Function to send a message to every client, except the sending client
def broadcast_msg(sending_client,msg): 
    msg = msg.encode("utf-8")
    for client_socket in list(SOCKETS):
        # exclude sending msg to the sending_client
        if client_socket !=  sending_client: 
            try:
                # Send the msg to client_socket
                #====fill in here====
                client_socket.send(msg)

            except:
                client_socket.close()
                del(ACTIVE_USERS[SOCKETS[client_socket]])
                del (SOCKETS[client_socket])
                broadcast_active_users()


# Function to broadcast the current active users to all clients 
def broadcast_active_users():
    active_users ='!'+json.dumps(ACTIVE_USERS)
    active_users = active_users.encode("utf-8")
    for client_socket in list(SOCKETS):
        try:
            # send "active_users" to client_socket
            #====fill in here====
            client_socket.send(active_users)

        except:
            client_socket.close()
            del(ACTIVE_USERS[SOCKETS[client_socket]])
            del (SOCKETS[client_socket])


if __name__ == "__main__":
    # Make the server listen for TCP connection requests
    # (5 maximum number of queued connections)
    SERVER_SOCKET.listen(5) 
    print("Waiting for connection...")
    # starting the main thread and joining all child threads
    connection_threads = Thread(target=accept_incoming_connections)
    connection_threads.start()  
    connection_threads.join()
    # after existing from the main thread, close SERVER_SOCKET
    SERVER_SOCKET.close()