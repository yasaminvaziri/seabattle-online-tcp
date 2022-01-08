package client.controller;

import client.holders.GameIDHolder;
import client.holders.TokenHolder;
import client.stream.StreamUtil;
import client.view.GameView;
import shared.game.Coordinate;
import shared.game.Player;
import shared.gson.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileReader;
import java.util.Properties;

import static java.lang.Thread.sleep;


public class GameController implements MouseListener {
    public final int left_starting_x = 100, right_starting_x = 840, starting_y = 30, size = 70;

    public static Response gameResponse;

    public GameController() {
        getGame(GameIDHolder.gameID);
    }
    public void getGame(int gameID) {
        if (gameID != GameIDHolder.gameID)
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
                StreamUtil.sendRequest(new Request(properties.getProperty("game"), "", TokenHolder.token, gameID));
            }catch (Exception e){
                e.printStackTrace();
            }
            //StreamUtil.sendRequest(new Request("game", "", TokenHolder.token, gameID));
            gameResponse = StreamUtil.getResponse();
            SwingUtilities.invokeLater(()->{
                new GameView(GsonUtil.getGson().fromJson(gameResponse.data, Player.class), this);
            });
            getGame(gameID);
        });
        thread.start();
    }

    public void readyButtonPressed() {
        try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            StreamUtil.sendRequest(new Request(properties.getProperty("ready"), "", TokenHolder.token, GameIDHolder.gameID));
        }catch (Exception e){
            e.printStackTrace();
        }
        //StreamUtil.sendRequest(new Request("ready", "", TokenHolder.token, GameIDHolder.gameID));
        gameResponse = StreamUtil.getResponse();
        SwingUtilities.invokeLater(()->{
            new GameView(GsonUtil.getGson().fromJson(gameResponse.data, Player.class), this);
        });
    }

    public void reLayoutButtonPressed() {
        try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            StreamUtil.sendRequest(new Request(properties.getProperty("layout"), "", TokenHolder.token, GameIDHolder.gameID));
        }catch (Exception e){
            e.printStackTrace();
        }
        //StreamUtil.sendRequest(new Request("layout", "", TokenHolder.token, GameIDHolder.gameID));
        gameResponse = StreamUtil.getResponse();
        SwingUtilities.invokeLater(()->{
            new GameView(GsonUtil.getGson().fromJson(gameResponse.data, Player.class), this);
        });
    }

    public synchronized void  Shot(Coordinate coordinate) {
        try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            StreamUtil.sendRequest(new Request("shot", GsonUtil.getGson().toJson(coordinate), TokenHolder.token, GameIDHolder.gameID));
        }catch (Exception e){
            e.printStackTrace();
        }
        //StreamUtil.sendRequest(new Request("shot", GsonUtil.getGson().toJson(coordinate), TokenHolder.token, GameIDHolder.gameID));
        gameResponse = StreamUtil.getResponse();
        SwingUtilities.invokeLater(()->{
            new GameView(GsonUtil.getGson().fromJson(gameResponse.data, Player.class), this);
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        if (right_starting_x < x && x < right_starting_x + size * 10 &&
                y > starting_y && y < starting_y + size * 10) {
            Coordinate coordinate = new Coordinate((x - right_starting_x) / size, (y - starting_y) / size);
            Shot(coordinate);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
