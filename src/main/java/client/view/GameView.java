package client.view;

import client.controller.GameController;
import client.controller.MainController;
import client.holders.GameIDHolder;

import shared.game.Player;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

public class GameView {
    GameController gameController;
    public GameView(Player player, GameController gameController) {
        this.gameController = gameController;
        try(FileReader reader = new FileReader("src/main/java/client/config/game.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

            if (player.state.equals(properties.getProperty("win")) || player.state.equals(properties.getProperty("lose"))) {
                GameIDHolder.gameID = -1;
                new EndedGameView(player);
                // new MenuView();
                System.out.println(player.state);

                return;
            }

            GamePanel gamePanel = new GamePanel(player);
            JPanel panel = gamePanel;
            panel.setSize(Integer.parseInt(properties.getProperty("p1")), Integer.parseInt(properties.getProperty("p2")));
            JLabel player1, player2;
            player1 = new JLabel();
            player2 = new JLabel();
            player1.setBounds(Integer.parseInt(properties.getProperty("pl1")),
                    Integer.parseInt(properties.getProperty("pl2")),Integer.parseInt(properties.getProperty("pl3")),
                    Integer.parseInt(properties.getProperty("pl4")));
            player2.setBounds(Integer.parseInt(properties.getProperty("pll1")),
                    Integer.parseInt(properties.getProperty("pll2")),Integer.parseInt(properties.getProperty("pll3")),
                    Integer.parseInt(properties.getProperty("pll4")));;
            player1.setText(player.username);
            player2.setText(player.enemy_username);
            MainController.frame.remove(MainController.panel);
            panel.add(player1);
            panel.add(player2);
            MainController.frame.add(panel);
            MainController.frame.revalidate();
            MainController.frame.repaint();
            MainController.panel = panel;

            if (player.state.equals(properties.getProperty("arranging"))) {
                JButton reset, ready;
                reset = new JButton();
                reset.setText(properties.getProperty("reset"));
                reset.setBackground(Color.pink);
                ready = new JButton();
                ready.setText(properties.getProperty("ready"));
                ready.setBackground(Color.pink);

                reset.setBounds(Integer.parseInt(properties.getProperty("r1")),
                        Integer.parseInt(properties.getProperty("r2")),Integer.parseInt(properties.getProperty("r3")),
                        Integer.parseInt(properties.getProperty("r4")));;
                ready.setBounds(Integer.parseInt(properties.getProperty("rr1")),
                        Integer.parseInt(properties.getProperty("rr2")),Integer.parseInt(properties.getProperty("rr3")),
                        Integer.parseInt(properties.getProperty("rr4")));;
                panel.add(ready);
                panel.add(reset);


                reset.addActionListener(this::resetPressed);
                ready.addActionListener(this::readyPressed);
            }
            if (!player.state.equals(properties.getProperty("r"))) {
                long seconds_left = LocalDateTime.from(LocalDateTime.now()).until(player.endTurn, ChronoUnit.SECONDS);
                JLabel time_left;
                time_left = new JLabel();
                time_left.setText(Long.toString(seconds_left));
                time_left.setBounds(Integer.parseInt(properties.getProperty("t1")),
                        Integer.parseInt(properties.getProperty("t2")),Integer.parseInt(properties.getProperty("t3")),
                        Integer.parseInt(properties.getProperty("t4")));;
                panel.add(time_left);

            }
            panel.revalidate();
            panel.repaint();
            if (player.state.equals(properties.getProperty("play"))) {
                gamePanel.addMouseListener(gameController);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void readyPressed(ActionEvent event) {
        gameController.readyButtonPressed();
    }

    private void resetPressed(ActionEvent event) {
        gameController.reLayoutButtonPressed();
    }


}



