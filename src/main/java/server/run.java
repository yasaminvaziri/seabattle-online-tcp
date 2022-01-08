package server;

import server.dataBase.Load;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

public class run {
    public static void main(String[] args) {
        try(FileReader reader = new FileReader("src/main/java/server/config/run.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

        try {
            ServerSocket serverSocket = new ServerSocket(Integer.valueOf(properties.getProperty("port")));
            while (true) {
                Load.loadUser();
                ClientThread clientThread = new ClientThread(serverSocket.accept());
                clientThread.start();
                System.out.println(properties.getProperty("accepted"));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println(properties.getProperty("er"));
        }
    }catch (Exception e){
            e.printStackTrace();
        }
}
}
