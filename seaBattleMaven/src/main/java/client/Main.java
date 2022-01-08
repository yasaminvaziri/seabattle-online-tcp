package client;

import client.controller.MainController;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
           Socket socket = new Socket("127.0.0.1", 8080);



            MainController mainController = new MainController();
            mainController.mainController();

        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("can not connect to server");
        }

    }
}
