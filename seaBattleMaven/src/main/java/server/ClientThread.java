package server;

import server.stream.StreamUtil;
import shared.networking.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    Socket socket;

    public ClientThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        while(true) {
            Request request = null;
            try {
                request = StreamUtil.getRequest(dataInputStream);
            }catch (IOException e) {
                e.printStackTrace();
            }
            if (request.type == "register") {

            }

        }
    }


}
