package client.controller;

import client.holders.TokenHolder;
import client.stream.StreamUtil;
import client.view.OnlineStreamView;
import shared.game.Player;
import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import javax.swing.*;

import java.io.FileReader;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class OnlineStreamController {
    public boolean end = false;
    public Response StreamResponse;
    int gameId;
    public OnlineStreamController(int id) {
        getStream();
        gameId = id;
    }
    public void getStream() {
        if (end)
            return;
        Thread thread = new Thread(()->{
            try {
                sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
                Properties properties = new Properties();
                properties.load(reader);
                StreamUtil.sendRequest(new Request(properties.getProperty("live"), "", TokenHolder.token, gameId));
            }catch (Exception e){
                e.printStackTrace();
            }
            //StreamUtil.sendRequest(new Request("live", "", TokenHolder.token, gameId));
            StreamResponse = StreamUtil.getResponse();
            SwingUtilities.invokeLater(()->{
                if (!end)
                    new OnlineStreamView(GsonUtil.getGson().fromJson(StreamResponse.data, Player.class), this);
            });
            getStream();
        });
        thread.start();
    }
}
