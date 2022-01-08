package client.controller;

import client.holders.TokenHolder;
import client.view.LoginView;
import client.stream.StreamUtil;
import client.view.MenuView;
import shared.gson.GsonUtil;
import shared.networking.AuthRequest;
import shared.networking.Request;
import shared.networking.Response;

import java.io.FileReader;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class LoginController {
    public LoginView loginView;
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    public void okButtonPressed(String username, String password) {
        AuthRequest authRequest = new AuthRequest(username, password, password);
        try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            StreamUtil.sendRequest(new Request(properties.getProperty("login"), GsonUtil.getGson().toJson(authRequest)));
            Response response = StreamUtil.getResponse();
            if (response.status.equals(properties.getProperty("accepted"))) {
                TokenHolder.token = response.data;
                sendOnlineRequest();
                new MenuView();
            }
            else {
                loginView.warn.setText(response.data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //StreamUtil.sendRequest(new Request("login", GsonUtil.getGson().toJson(authRequest)));
//        Response response = StreamUtil.getResponse();
//        if (response.status.equals("accepted")) {
//            TokenHolder.token = response.data;
//            sendOnlineRequest();
//            new MenuView();
//        }
//        else {
//            loginView.warn.setText(response.data);
//        }
    }


    public void sendOnlineRequest() {
        Thread thread = new Thread(()-> {
            try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
                Properties properties = new Properties();
                properties.load(reader);
                StreamUtil.sendRequest(new Request(properties.getProperty("online"), "", TokenHolder.token));
            }catch (Exception e){
                e.printStackTrace();
            }
            //StreamUtil.sendRequest(new Request("online", "", TokenHolder.token));
            try {
                sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            sendOnlineRequest();
        });
        thread.start();
    }
}
