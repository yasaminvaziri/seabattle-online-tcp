package client.controller;

import client.holders.TokenHolder;
import client.stream.StreamUtil;
import client.view.ScoresView;
import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;
import shared.scores.Scores;

import javax.swing.*;

import java.io.FileReader;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class ScoresController {
    Response response;
    boolean end = false;
    public ScoresController() {
       getScores();
    }

    public void getScores() {
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
                StreamUtil.sendRequest(new Request(properties.getProperty("scores"), "", TokenHolder.token));

            }catch (Exception e ){
                e.printStackTrace();
            }
            //StreamUtil.sendRequest(new Request("scores", "", TokenHolder.token));
            response = StreamUtil.getResponse();
            SwingUtilities.invokeLater(()->{
                if (!end)
                    new ScoresView(GsonUtil.getGson().fromJson(response.data, Scores.class), this);
            });
            getScores();
        });
        thread.start();
    }
}
