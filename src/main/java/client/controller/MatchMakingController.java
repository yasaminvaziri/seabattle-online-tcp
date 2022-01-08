package client.controller;


import client.holders.GameIDHolder;
import client.holders.TokenHolder;
import client.stream.StreamUtil;
import client.view.MenuView;
import shared.game.Player;
import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;

public class MatchMakingController {
    public static Response MatchMakingResponse;
    public void sendMatchMakingRequest() {
        try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            StreamUtil.sendRequest(new Request(properties.getProperty("matchMaking"), "", TokenHolder.token));
            //System.out.println("sent MatchMaking request");
            Thread thread = new Thread(()->{
                MatchMakingResponse = StreamUtil.getResponse();
                SwingUtilities.invokeLater(()-> {
                    if (MatchMakingResponse.status.equals(properties.getProperty("accepted"))) {
                        Player player = GsonUtil.getGson().fromJson(MatchMakingResponse.data, Player.class);
                        GameIDHolder.gameID = player.ID;
                        new GameController();
                    }
                    else {
                        new MenuView();
                    }
                });
            });
            thread.start();

        }catch (Exception e){
            e.printStackTrace();
        }
//        StreamUtil.sendRequest(new Request("matchmaking", "", TokenHolder.token));
//        System.out.println("sent MatchMaking request");
//        Thread thread = new Thread(()->{
//            MatchMakingResponse = StreamUtil.getResponse();
//            SwingUtilities.invokeLater(()-> {
//                if (MatchMakingResponse.status.equals("accepted")) {
//                    Player player = GsonUtil.getGson().fromJson(MatchMakingResponse.data, Player.class);
//                    GameIDHolder.gameID = player.ID;
//                    new GameController();
//                }
//                else {
//                    new MenuView();
//                }
//            });
//        });
//        thread.start();
    }
}
