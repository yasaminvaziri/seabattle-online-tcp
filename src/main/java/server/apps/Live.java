package server.apps;

import javafx.scene.control.PasswordField;
import server.stream.StreamUtil;
import shared.game.Game;
import shared.game.Player;
import shared.gson.GsonUtil;
import shared.live.StreamGame;
import shared.live.StreamGameList;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class Live {

    public void getGame(Request request, DataOutputStream dataOutputStream) {
        try(FileReader reader = new FileReader("src/main/java/server/config/game.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

        int gameID = request.gameID;
        if (Game.IdToGame.getOrDefault(gameID, null) == null) {
            try {
                StreamUtil.sendResponse(new Response(properties.getProperty("invalid_game_id"), properties.getProperty("denied")), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        Game game = Game.IdToGame.get(gameID);
        Player livePlayer = new Player(game.player1);
        try {
            StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(livePlayer), properties.getProperty("accepted")), dataOutputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getList(Request request, DataOutputStream dataOutputStream) {
        LinkedList<StreamGame> games = new LinkedList<>();
        for (Game game:Game.games)
            games.add(new StreamGame(game));
        StreamGameList streamGameList = new StreamGameList(games);
        try {
            StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(streamGameList), "accepted"), dataOutputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
