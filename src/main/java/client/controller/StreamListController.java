package client.controller;

import client.holders.TokenHolder;
import client.stream.StreamUtil;
import client.view.StreamListView;
import shared.gson.GsonUtil;
import shared.live.StreamGameList;
import shared.networking.Request;
import shared.networking.Response;

import javax.swing.*;

import java.io.FileReader;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class StreamListController {
    public boolean end = false;
    public static Response listResponse;
    public StreamListController() {
        getList();
    }
    public void getList() {
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
                StreamUtil.sendRequest(new Request(properties.getProperty("live-list"), "", TokenHolder.token));

            }catch (Exception e ){
                e.printStackTrace();
            }
            //StreamUtil.sendRequest(new Request("live-list", "", TokenHolder.token));
            listResponse = StreamUtil.getResponse();
            SwingUtilities.invokeLater(()->{
                if (!end)
                    new StreamListView(GsonUtil.getGson().fromJson(listResponse.data, StreamGameList.class), this);
            });
            getList();
        });
        thread.start();
    }
}
