package server.apps;

import server.dataBase.Load;
import server.models.User;
import server.stream.StreamUtil;
import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;
import shared.scores.Scores;
import shared.UserInfo;

import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

public class Profile {

    public void getProfile(Request request, DataOutputStream dataOutputStream) {
        try(FileReader reader = new FileReader("src/main/java/server/config/pro.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

        if (Tokens.tokenMap.getOrDefault(request.token, null) == null) {
            try {
                StreamUtil.sendResponse(new Response(properties.getProperty("invalid_token"), properties.getProperty("denied")), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        User user = Tokens.tokenMap.get(request.token);

        try {
            StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(new UserInfo(user)), properties.getProperty("accepted")),
                    dataOutputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getScores(Request request, DataOutputStream dataOutputStream) {
        try(FileReader reader = new FileReader("src/main/java/server/config/pro.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

            try {
                StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(new Scores(Load.userList)), properties.getProperty("accepted")),
                        dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void isOnline(Request request, DataOutputStream dataOutputStream) {
        User user = Tokens.tokenMap.get(request.token);
        if (user == null)
            return;
        user.lastOnline = LocalDateTime.now();
    }

}
