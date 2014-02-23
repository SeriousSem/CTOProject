package src.db;

import java.io.*;
import java.net.Socket;

/**
 * Created by vishn_000 on 22.02.14.
 */
public class SocketDBDump {
    public static File getDump() {
        String PATH_TO_LOCAL_DUMP = "E:/dump.sql";
        try {
            System.out.println("CONNECT TO SERVER SOCKET");
            Socket clientSocket = new Socket("127.0.0.1", 3201);

            System.out.println("GETTING STREAM");
            InputStream is = clientSocket.getInputStream();

            //length of inputstream?? .available() doesn't work :'(
            byte[] mybytearray = new byte[1000000];

            File file = new File(PATH_TO_LOCAL_DUMP);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            System.out.println("WRITING FILE TO " + PATH_TO_LOCAL_DUMP);
            int bytesRead = is.read(mybytearray, 0, mybytearray.length);
            bos.write(mybytearray, 0, bytesRead);

            bos.close();
            clientSocket.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
