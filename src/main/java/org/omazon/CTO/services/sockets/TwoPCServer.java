package org.omazon.CTO.services.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishn_000 on 23.02.14.
 */
public class TwoPCServer {
    boolean closed;
    int inputFromAll;
    List<ClientThread> connectedClients;
    ServerSocket serverSocket;
    int server_port = 5555;

    public TwoPCServer() {
        connectedClients = new ArrayList<ClientThread>();
        closed = true;
        inputFromAll = 0;
    }

    public void startDBServerSocket() {

        try {
            //open server socket
            serverSocket = new ServerSocket(server_port);
            closed = false;
            System.out.println("WAITING FOR CLIENT SOCKET TO PARTICIPATE IN 2PC");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!closed) {
            try {
                //waiting for new connections
                Socket clientSocket = serverSocket.accept();
                ClientThread th = new ClientThread(this, clientSocket);

                //add connection to connection's list
                connectedClients.add(th);
                th.numberOfCurrentClient = connectedClients.indexOf(th) + 1;
                System.out.println("TOTAL CLIENTS ARE: " + connectedClients.size());

                th.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            serverSocket.close();
            closed = true;
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }
}
