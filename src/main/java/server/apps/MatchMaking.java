package server.apps;

import server.models.User;
import server.stream.StreamUtil;
import shared.game.Game;
import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import javafx.util.Pair;

public class MatchMaking {
    public static LinkedList<Pair<Request, DataOutputStream> > MatchLobby = new LinkedList<>();
    public static synchronized void addPlayerToMatchMaking(Request request, DataOutputStream dataOutputStream) {
        try(FileReader reader = new FileReader("src/main/java/server/config/match.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
        if (Tokens.tokenMap.getOrDefault(request.token, null) == null) {
            try {
                StreamUtil.sendResponse(new Response(properties.getProperty("invalid"), properties.getProperty("denied")), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return;
        }
        System.out.println("matchmaking request");
        MatchLobby.add(new Pair<>(request, dataOutputStream));
        if (MatchLobby.size() > 1)
            MakeMatches();
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized void MakeMatches() {
        if (MatchLobby.size() < 2)
            return;
        Pair<Request, DataOutputStream> player1 = MatchLobby.getFirst();
        MatchLobby.removeFirst();
        Pair<Request, DataOutputStream> player2 = MatchLobby.getFirst();
        MatchLobby.removeFirst();
        User player1User = Tokens.tokenMap.get(player1.getKey().token);
        User player2User = Tokens.tokenMap.get(player2.getKey().token);
        Game game = new Game(player1User.username, player2User.username);
        try {
            StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(game.player1), "accepted"), player1.getValue());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(game.player2), "accepted"), player2.getValue());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        if (MatchLobby.size() > 1)
            MakeMatches();
    }
}
