package client.controller;

import client.holders.TokenHolder;
import client.stream.StreamUtil;
import client.view.ProfileView;
import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;
import shared.UserInfo;

import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;

public class ProfileController {
    Response response;
    public ProfileController(){
        try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            Thread thread = new Thread(()->{
                StreamUtil.sendRequest(new Request(properties.getProperty("profile"), "" , TokenHolder.token));
                response = StreamUtil.getResponse();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new ProfileView(GsonUtil.getGson().fromJson(response.data, UserInfo.class));
                    }
                });
            });
            thread.start();

        }catch (Exception e ){
            e.printStackTrace();
        }

//        Thread thread = new Thread(()->{
//            StreamUtil.sendRequest(new Request("profile", "" , TokenHolder.token));
//            response = StreamUtil.getResponse();
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    new ProfileView(GsonUtil.getGson().fromJson(response.data, UserInfo.class));
//                }
//            });
//        });
//        thread.start();
    }
}
