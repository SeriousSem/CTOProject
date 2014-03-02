package src.db.twoPC;

import src.db.DAO.DbConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by vishn_000 on 23.02.14.
 */
public class TwoPCClientSocket implements Runnable {
    private Socket clientSocket;
    private PrintStream os;
    private BufferedReader br;
    private DbConnector dbConnector;

    private static final String HOST = "127.0.0.1";
    private static final int PORT_NUMBER = 5555;

    private static final String NOT_SENT = "NOT_SENT";
    private static final String ABORT = "ABORT";
    private static final String COMMIT = "COMMIT";
    private static final String GLOBAL_ABORT = "GLOBAL_ABORT";
    private static final String GLOBAL_COMMIT = "GLOBAL_COMMIT";


    public TwoPCClientSocket(DbConnector dbConnector) {
        try {
            this.dbConnector = dbConnector;

            clientSocket = new Socket(HOST, PORT_NUMBER);

            os = new PrintStream(clientSocket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            new Thread(this).start();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("NO CONNECTION TO SERVER");
        }
    }

    public void run() {
        String responseLine;
        try {
            while ((responseLine = br.readLine()) != null) {
                System.out.println("GOT FROM SERVER: " + responseLine);
                switch (responseLine) {
                    case GLOBAL_COMMIT: {
                        //can execute query
                        dbConnector.commitQuery();
                        break;
                    }
                    case GLOBAL_ABORT: {
                        //somebody denied query. cannot execute
                        dbConnector.rollbackQuery();
                        break;
                    }
                    default: {
                        //in this case we have got a query. Now we should send COMMIT back, if query is ok or ABORT if query is bad
                        //we also should save a query and next time when we will get GLOBAL_COMMIT or GLOBAL_ABORT delete it or execute

                        if (dbConnector.executeUpdateWithoutSending(responseLine)) {
                            writeToServer(COMMIT);
                        } else {
                            writeToServer(ABORT);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }

    public boolean writeToServer(String textToSend) {
        if (clientSocket != null && os != null && br != null) {

            os.println(textToSend); //write to server socket

            return true;
        } else {
            System.out.println("SMTH GOES WRONG");
        }
        return false;
    }
}
