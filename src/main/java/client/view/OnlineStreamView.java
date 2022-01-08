package client.view;

import client.controller.MainController;
import client.controller.OnlineStreamController;
import shared.game.Player;

import javax.swing.*;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

public class OnlineStreamView {
    OnlineStreamController onlineStreamController;
    public OnlineStreamView(Player player, OnlineStreamController onlineStreamController) {
        this.onlineStreamController = onlineStreamController;
        GamePanel gamePanel = new GamePanel(player);
        JPanel panel = gamePanel;
        try(FileReader reader = new FileReader("src/main/java/client/config/online.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

            panel.setSize(Integer.parseInt(properties.getProperty("p1")),
                    Integer.parseInt(properties.getProperty("p2")));
            MainController.frame.remove(MainController.panel);
            MainController.frame.add(panel);
            MainController.frame.revalidate();
            MainController.frame.repaint();
            MainController.panel = panel;


            if (!player.state.equals(properties.getProperty("ready")) &&
                    !player.state.equals(properties.getProperty("arranging"))) {
                long seconds_left = LocalDateTime.from(LocalDateTime.now()).until(player.endTurn, ChronoUnit.SECONDS);
                JLabel time_left;
                time_left = new JLabel();
                time_left.setText(Long.toString(seconds_left));
                time_left.setBounds(Integer.parseInt(properties.getProperty("t1")),
                        Integer.parseInt(properties.getProperty("t2")),Integer.parseInt(properties.getProperty("t3")),
                        Integer.parseInt(properties.getProperty("t4")));
                panel.add(time_left);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        panel.revalidate();
        panel.repaint();
    }
}
