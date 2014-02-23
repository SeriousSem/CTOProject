package org.omazon.CTO.services.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vishn_000 on 22.02.14.
 */
public class DBSender {

    String PATH_TO_SERVER_DB = "E:/MariaDB/bin/mysqldump";

    /**
     * make dump of database
     *
     * @return database file
     */
    public File makeDump(String dbName) {
        String DUMP_PATH = "E:/" + dbName + ".sql";
        try {
            Runtime runtime = Runtime.getRuntime();
            System.out.println("MAKING DUMP");
            String commandToExecute = PATH_TO_SERVER_DB + " -u root -proot " + dbName + " -r " + DUMP_PATH;

            Process runtimeProcess = Runtime.getRuntime().exec(commandToExecute);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("DUMP CREATED SUCCESSFULLY");
                File dump = new File(DUMP_PATH);
                return dump;
            } else {
                System.out.println("COULD NOT CREATE THE DUMP");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void openSocketToSendDump() {
        try {
            ServerSocket serverSocket = new ServerSocket(3201);
            while (true) {

                System.out.println("WAITING FOR CLIENT SOCKET TO SEND DUMP");
                Socket sock = serverSocket.accept();

                File dbDump = makeDump("ctodb");
                byte[] byteArray = new byte[(int) dbDump.length()];
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(dbDump));
                bis.read(byteArray, 0, byteArray.length);

                OutputStream os = sock.getOutputStream();
                os.write(byteArray, 0, byteArray.length);

                os.flush();
                sock.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }
}
