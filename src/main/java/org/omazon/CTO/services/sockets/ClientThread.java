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
    //private boolean initiator;
    int numberOfCurrentClient;

    private static final String NOT_SENT = "NOT_SENT";
    private static final String ABORT = "ABORT";
    private static final String COMMIT = "COMMIT";
    private static final String GLOBAL_ABORT = "GLOBAL_ABORT";
    private static final String GLOBAL_COMMIT = "GLOBAL_COMMIT";


    public ClientThread(TwoPCServer dbServer, Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.dbServer = dbServer;
        // initiator = false;
    }

    public void run() {
        try {
            //get data from client socket
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //write data to client socket
            os = new PrintStream(clientSocket.getOutputStream());

            while (!dbServer.closed) {
                String line = br.readLine();
                System.out.println("#------------------------- THIS IS CLIENT №: " + numberOfCurrentClient + "  -----------------------------#");
                System.out.println("GOT LINE: " + line);

                switch (line) {
                    case ABORT: {
                        if (dbServer.connectedClients.contains(this)) {
                            System.out.println("ABORT RECEIVED. SEND ABORT TO ALL CLIENTS.");
                            dbServer.inputFromAll = 0;
                            clientsIterator(GLOBAL_ABORT, true);
                        }
                        break;
                    }
                    case COMMIT: {
                        System.out.println("COMMIT RECEIVED");
                        if (dbServer.connectedClients.contains(this)) {
                            System.out.println("UPVOTE THIS");
                            dbServer.inputFromAll++;
                        }
                        break;
                    }
                    default: {
                        System.out.println("RECEIVED NEW QUERY: " + line);
                        dbServer.inputFromAll = 1;
                        // initiator = true;
                        clientsIterator(line, false);
                        break;
                    }
                }
                if (dbServer.inputFromAll == dbServer.connectedClients.size()) {
                    System.out.println("ALL CLIENTS VOTED FOR COMMIT. SEND GLOBAL COMMIT.");
                    //initiator = false;
                    clientsIterator(GLOBAL_COMMIT, true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }

    private void clientsIterator(String command, boolean sendToThisThread) throws IOException {
        for (ClientThread clientThread : dbServer.connectedClients) {
            if (sendToThisThread || clientThread != this) {
                clientThread.os.println(command);
            }
        }
    }
}
