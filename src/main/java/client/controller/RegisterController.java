package client.controller;

import client.holders.TokenHolder;
import client.stream.StreamUtil;
import client.view.RegisterView;
import client.view.WelcomeView;
import shared.gson.GsonUtil;
import shared.networking.AuthRequest;
import shared.networking.Request;
import shared.networking.Response;

import java.io.FileReader;
import java.util.Properties;

public class RegisterController {
    RegisterView registerView;
    public RegisterController(RegisterView registerView) {
        this.registerView = registerView;
    }
    public void okButtonPressed(String username, String password, String password2) {
        AuthRequest authRequest = new AuthRequest(username, password, password2);
        try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            StreamUtil.sendRequest(new Request(properties.getProperty("register"), GsonUtil.getGson().toJson(authRequest)));
            Response response = StreamUtil.getResponse();
            if (response.status.equals(properties.getProperty("created"))) {
                WelcomeView welcomeView = new WelcomeView();
                welcomeView.WelcomePage();
            }
            else {
                registerView.warn.setText(response.data);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        //StreamUtil.sendRequest(new Request("register", GsonUtil.getGson().toJson(authRequest)));
//        Response response = StreamUtil.getResponse();
//        if (response.status.equals("created")) {
//            WelcomeView welcomeView = new WelcomeView();
//            welcomeView.WelcomePage();
//        }
//        else {
//            System.out.println(response.data);
//        }
    }
}
