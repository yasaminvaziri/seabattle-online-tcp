package server;

import java.io.IOException;
import java.net.ServerSocket;

public class run {
    public static void main(String []args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {

            }



        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
