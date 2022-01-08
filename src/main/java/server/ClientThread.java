package server;

import server.apps.*;
import server.stream.StreamUtil;
import shared.networking.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class ClientThread extends Thread {
    Socket socket;

    public ClientThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        Auth auth = new Auth();
        GameAgent gameAgent = new GameAgent();
        Live live = new Live();
        Profile profile = new Profile();
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("error in getting inputStream");
        }
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("error in getting outputStream");
        }

        while(true) {
            Request request = null;
            try(FileReader reader = new FileReader("src/main/java/server/config/client.properties")) {
                Properties properties = new Properties();
                properties.load(reader);
                try {
                    request = StreamUtil.getRequest(dataInputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(properties.getProperty("er"));
                }
                if (request.type.equals(properties.getProperty("register"))) {
                    auth.register(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("login"))) {
                    auth.login(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("matchmaking"))) {
                    MatchMaking.addPlayerToMatchMaking(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("game"))) {
                    gameAgent.getGame(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("ready"))) {
                    gameAgent.Ready(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("layout"))) {
                    gameAgent.newArrangement(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("shot"))) {
                    gameAgent.shot(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("live"))) {
                    live.getGame(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("ll"))) {
                    live.getList(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("pro"))) {
                    profile.getProfile(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("scores"))) {
                    profile.getScores(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("online"))) {
                    profile.isOnline(request, dataOutputStream);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
