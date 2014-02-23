package org.omazon.CTO.services.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by vishn_000 on 23.02.14.
 */
public class ClientThread extends Thread {
    private BufferedReader br;
    private PrintStream os;
    private Socket clientSocket;
    private TwoPCServer dbServer;
    private String data;

    private static final String NOT_SENT = "NOT_SENT";
    private static final String ABORT = "ABORT";
    private static final String COMMIT = "COMMIT";
    private static final String GLOBAL_ABORT = "GLOBAL_ABORT";
    private static final String GLOBAL_COMMIT = "GLOBAL_COMMIT";


    public ClientThread(TwoPCServer dbServer, Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.dbServer = dbServer;
        this.data = NOT_SENT;
    }

    public void run() {
        try {
            //get data from client socket
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //write data to client socket
            os = new PrintStream(clientSocket.getOutputStream());

            while (!dbServer.closed) {
                this.data = br.readLine();
                System.out.println("GOT LINE: " + data);

                switch (data) {
                    case ABORT: {
                        System.out.println("ABORT RECEIVED. SEND ABORT TO ALL CLIENTS.");
                        clientsIterator(GLOBAL_ABORT);
                        break;
                    }
                    case COMMIT: {
                        System.out.println("COMMIT RECEIVED");
                        if (dbServer.connectedClients.contains(this)) {
                            System.out.println("DID ALL CLIENTS VOTE?");
                            dbServer.inputFromAll = 0;
                            for (ClientThread clientThread : dbServer.connectedClients) {
                                if (COMMIT.equalsIgnoreCase(clientThread.data)) {
                                    dbServer.inputFromAll++;
                                } else {
                                    System.out.println("SOMEBODY VOTED: " + clientThread.data);
                                    break;
                                }
                            }
                            if (dbServer.inputFromAll == dbServer.connectedClients.size()) {
                                System.out.println("ALL CLIENTS VOTED FOR COMMIT. SEND GLOBAL COMMIT.");
                                clientsIterator(GLOBAL_COMMIT);
                            }
                        }
                        break;
                    }
                    default: {
                        System.out.println("RECEIVED NEW QUERY: " + data);
                        clientsIterator(data);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }

    private void clientsIterator(String command) throws IOException {
        for (ClientThread clientThread : dbServer.connectedClients) {
            clientThread.os.println(command);
        }
    }
}
