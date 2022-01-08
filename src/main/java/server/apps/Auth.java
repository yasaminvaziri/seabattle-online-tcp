package server.apps;

import server.dataBase.Load;
import server.dataBase.Save;
import server.models.User;
import server.stream.StreamUtil;
import shared.gson.GsonUtil;
import shared.networking.AuthRequest;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Auth {
    public void register(Request request, DataOutputStream dataOutputStream) {
        AuthRequest authRequest = GsonUtil.getGson().fromJson(request.data, AuthRequest.class);
        try(FileReader reader = new FileReader("src/main/java/server/config/auth.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

            if (!authRequest.password.equals(authRequest.password2)) {
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("pdm"), properties.getProperty("id")),
                            dataOutputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            else if (authRequest.username.equals("") || authRequest.password.equals("")) {
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("f"), properties.getProperty("id")),
                            dataOutputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            else if (Load.UserExists(authRequest.username)) {
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("tu"), properties.getProperty("id")),
                            dataOutputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            User user = new User(authRequest.username, authRequest.password);
            Save.saveUser(user);
            try {
                StreamUtil.sendResponse(new Response("", properties.getProperty("created")),
                        dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println(properties.getProperty("csr"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void login(Request request, DataOutputStream dataOutputStream) {
        AuthRequest authRequest = GsonUtil.getGson().fromJson(request.data, AuthRequest.class);
        try(FileReader reader = new FileReader("src/main/java/server/config/auth.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

            if (!Load.UserExists(authRequest.username)) {
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("ud"), "invalid_data"), dataOutputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            User user = Load.loadUser(authRequest.username);
            if (!user.password.equals(authRequest.password)) {
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("ip"), properties.getProperty("id")), dataOutputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            String token = Tokens.generateToken(user);
            try {
                StreamUtil.sendResponse(new Response(token, properties.getProperty("accepted")), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println(properties.getProperty("csr"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
