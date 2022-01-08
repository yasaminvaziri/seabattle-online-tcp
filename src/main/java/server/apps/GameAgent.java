package server.apps;

import javafx.util.Pair;
import server.dataBase.Load;
import server.dataBase.Save;
import server.models.User;
import server.stream.StreamUtil;
import shared.game.*;
import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Properties;

public class GameAgent {


    public Pair<User, Game> validate(Request request, DataOutputStream dataOutputStream) {

        if (Tokens.tokenMap.getOrDefault(request.token, null) == null) {
            try {
                StreamUtil.sendResponse(new Response("Invalid Token", "denied"), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
        User user = Tokens.tokenMap.get(request.token);
        int gameID = request.gameID;
        if (Game.IdToGame.getOrDefault(gameID, null) == null) {
            try {
                StreamUtil.sendResponse(new Response("Invalid gameID", "denied"), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
        Game game = Game.IdToGame.get(gameID);
        checkForEndTurnTime(game);

        return new Pair<>(user, game);

    }

    public void getGame(Request request, DataOutputStream dataOutputStream) {

        Pair<User, Game> validated_data = validate(request, dataOutputStream);
        if (validated_data == null)
            return;
        Game game = validated_data.getValue();
        User user = validated_data.getKey();
        if (game.player1.username.equals(user.username)) {
            try {
                StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(game.player1), "accepted"), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if (game.player2.username.equals(user.username)) {
            try {
                StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(game.player2), "accepted"), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    public void Ready(Request request, DataOutputStream dataOutputStream) {
        Pair<User, Game> validated_data = validate(request, dataOutputStream);
        if (validated_data == null)
            return;
        Game game = validated_data.getValue();
        User user = validated_data.getKey();
        Player player = null;
        if (game.player1.username.equals(user.username)) {
            player = game.player1;
            if (game.player1.state.equals("arranging"))
                game.player1.state = "ready";
        }
        else {
            player = game.player2;
            if (game.player2.state.equals("arranging"))
                game.player2.state = "ready";
        }
        checkForBothReady(game);
        try {
            StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(player), "accepted"), dataOutputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void checkForBothReady(Game game) {
        if (game.player1.state.equals("ready") && game.player2.state.equals("ready")) {
            game.player1.state = "play";
            game.player2.state = "wait";
            game.player1.endTurn = game.player2.endTurn = LocalDateTime.now().plusSeconds(25);
        }
    }

    public void checkForEndTurnTime(Game game) {
        try(FileReader reader = new FileReader("src/main/java/server/config/game.properties")) {
            Properties properties = new Properties();
            properties.load(reader);


        if (game.player1.endTurn.isBefore(LocalDateTime.now())) {
            if (game.player1.state.equals(properties.getProperty("arranging"))) {
                game.player1.state = properties.getProperty("ready");
            } else if (game.player1.state.equals(properties.getProperty("play")) || game.player1.state.equals(properties.getProperty("wait"))) {
                String tmp = game.player1.state;
                game.player1.state = game.player2.state;
                game.player2.state = tmp;
                game.player1.endTurn = game.player2.endTurn = LocalDateTime.now().plusSeconds(25);
            }
        }
        if (game.player2.endTurn.isBefore(LocalDateTime.now())) {
            if (game.player2.state.equals(properties.getProperty("arranging"))) {
                game.player2.state = properties.getProperty("ready");
            } else if (game.player2.state.equals(properties.getProperty("play")) || game.player2.state.equals(properties.getProperty("wait"))) {
                String tmp = game.player1.state;
                game.player1.state = game.player2.state;
                game.player2.state = tmp;
                game.player1.endTurn = game.player2.endTurn = LocalDateTime.now().plusSeconds(25);
            }
        }
        checkForBothReady(game);
    }catch (Exception e){
        e.printStackTrace();
        }
    }

    public void newArrangement(Request request, DataOutputStream dataOutputStream) {
        try(FileReader reader = new FileReader("src/main/java/server/config/game.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
        Pair<User, Game> validated_data = validate(request, dataOutputStream);
        if (validated_data == null)
            return;
        Game game = validated_data.getValue();
        User user = validated_data.getKey();
        Player player = null;
        if (game.player1.username.equals(user.username)) {
            player = game.player1;
        } else {
            player = game.player2;
        }
        if (player.mapChoices.size() != 0) {
            player.shipArrangement = new ShipArrangement(player.mapChoices);
            player.mapChoices.remove(Integer.valueOf(player.shipArrangement.mapNumber));
            player.endTurn = player.endTurn.plusSeconds(10);
        }
        try {
            StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(player), properties.getProperty("accepted")), dataOutputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void shot(Request request, DataOutputStream dataOutputStream) {
        try(FileReader reader = new FileReader("src/main/java/server/config/game.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

        Pair<User, Game> validated_data = validate(request, dataOutputStream);
        if (validated_data == null)
            return;
        Game game = validated_data.getValue();
        User user = validated_data.getKey();
        Player player = null, enemy = null;
        if (game.player1.username.equals(user.username)) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        if (!player.state.equals(properties.getProperty("play"))) {
            try {
                StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(player), properties.getProperty("denied")), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return;
        }
        Coordinate shot = GsonUtil.getGson().fromJson(request.data, Coordinate.class);
        if (!findCoordinate(player.successful_shots, shot) && !findCoordinate(player.failed_shots, shot)) {
            for (Ship ship : enemy.shipArrangement.ships) {
                if (findCoordinate(ship.coordinates, shot)) {
                    player.successful_shots.add(shot);
                    enemy.enemy_successful_shots.add(shot);
                    boolean ShipDead = true;
                    for (Coordinate coordinate : ship.coordinates) {
                        if (!findCoordinate(player.successful_shots, coordinate))
                            ShipDead = false;
                    }
                    if (ShipDead) {
                        player.enemy_dead_ships.add(ship);
                        enemy.dead_ships.add(ship);
                        for (int i = 0; i < 10; i++)
                            for (int j = 0; j < 10; j++) {
                                for (Coordinate coordinate : ship.coordinates) {
                                    if (Math.abs(coordinate.x - i) + Math.abs(coordinate.y - j) == 1 ||
                                            (Math.abs(coordinate.x - i) == 1 && Math.abs(coordinate.y - j) == 1)) {
                                        if (!findCoordinate(player.failed_shots, new Coordinate(i, j))
                                                && !findCoordinate(player.successful_shots, new Coordinate(i, j))) {
                                            player.failed_shots.add(new Coordinate(i, j));
                                            enemy.enemy_failed_shots.add(new Coordinate(i, j));
                                        }
                                    }
                                }
                            }
                        if (player.enemy_dead_ships.size() == enemy.shipArrangement.ships.size()) {
                            player.state = properties.getProperty("win");
                            enemy.state = properties.getProperty("lose");
                            User playerUser = Load.loadUser(player.username);
                            User enemyUser = Load.loadUser(enemy.username);
                            playerUser.wins++;
                            enemyUser.loses++;
                            Save.saveUser(playerUser);
                            Save.saveUser(enemyUser);
                        }
                    }
                    try {
                        StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(player), properties.getProperty("accepted")), dataOutputStream);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    return;
                }
            }
            player.failed_shots.add(shot);
            enemy.enemy_failed_shots.add(shot);
            String tmp = game.player1.state;
            game.player1.state = game.player2.state;
            game.player2.state = tmp;
            game.player1.endTurn = game.player2.endTurn = LocalDateTime.now().plusSeconds(25);
            try {
                StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(player), properties.getProperty("accepted")), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        try {
            StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(player), properties.getProperty("accepted")), dataOutputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean findCoordinate(LinkedList<Coordinate> coordinates, Coordinate coordinate) {
        for (Coordinate tmp:coordinates)
            if (tmp.x == coordinate.x && tmp.y == coordinate.y)
                return true;
        return false;
    }
}
