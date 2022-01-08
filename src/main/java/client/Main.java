package client;

import client.controller.MainController;
import client.stream.StreamUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try(FileReader reader = new FileReader("src/main/java/client/config/main.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

        try {
            Socket socket = new Socket(properties.getProperty("localhost"), Integer.valueOf(properties.getProperty("port")));
            try {
                StreamUtil.dataInputStream = new DataInputStream(socket.getInputStream());
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println(properties.getProperty("cl"));
            }
            try {
                StreamUtil.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println(properties.getProperty("cl"));
            }

            MainController mainController = new MainController();
            mainController.mainController();

        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println(properties.getProperty("cs"));
        }
    }catch (Exception e){
            e.printStackTrace();
        }

    }
}
