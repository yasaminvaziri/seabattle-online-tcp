package client.view;

import client.controller.MainController;
import client.controller.OnlineStreamController;
import client.controller.StreamListController;
import shared.live.StreamGame;
import shared.live.StreamGameList;

import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;


public class StreamListView {
    public StreamListView(StreamGameList streamGameList, StreamListController streamListController) {


        MainController.frame.remove(MainController.panel);
        MainController.panel = new JPanel();
        try(FileReader reader = new FileReader("src/main/java/client/config/stream.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            MainController.panel.setSize(Integer.parseInt(properties.getProperty("p1")),
                    Integer.parseInt(properties.getProperty("p2")));
            MainController.frame.add(MainController.panel);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("sc1")),
                    Integer.parseInt(properties.getProperty("sc2")),Integer.parseInt(properties.getProperty("sc3")),
                    Integer.parseInt(properties.getProperty("sc4")));
            for (StreamGame streamGame : streamGameList.games) {
                JLabel player1, player2, player1_successful_shots, player2_successful_shots,
                        player1_failed_shots, player2_failed_shots, player1_dead_ships,
                        player2_dead_ships;
                player1 = new JLabel();
                player1.setText(properties.getProperty("pl1") + streamGame.player1);
                player2 = new JLabel();
                player2.setText(properties.getProperty("pl2") + streamGame.player2);
                player1_successful_shots = new JLabel();
                player1_successful_shots.setText(properties.getProperty("ps1") + String.valueOf(streamGame.player1_successful_shots));
                player2_successful_shots = new JLabel();
                player2_successful_shots.setText(properties.getProperty("ps2") + String.valueOf(streamGame.player1_successful_shots));
                player1_failed_shots = new JLabel();
                player1_failed_shots.setText(properties.getProperty("pf1") + String.valueOf(streamGame.player1_failed_shots));
                player2_failed_shots = new JLabel();
                player2_failed_shots.setText(properties.getProperty("pf2") + String.valueOf(streamGame.player2_failed_shots));
                player1_dead_ships = new JLabel();
                player1_dead_ships.setText(properties.getProperty("pd1") + String.valueOf(streamGame.player1_dead_ships));
                player2_dead_ships = new JLabel();
                player2_dead_ships.setText(properties.getProperty("pd2") + String.valueOf(streamGame.player2_dead_ships));
                JButton watch = new JButton();
                watch.setText(properties.getProperty("watch"));

                panel.add(player1);
                panel.add(player1_successful_shots);
                panel.add(player1_failed_shots);
                panel.add(player1_dead_ships);
                panel.add(player2);
                panel.add(player2_successful_shots);
                panel.add(player2_failed_shots);
                panel.add(player2_dead_ships);
                panel.add(watch);

                watch.addActionListener((a) -> {
                    streamListController.end = true;
                    new OnlineStreamController(streamGame.gameID);
                });
            }
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            MainController.panel.add(scrollPane);
            MainController.panel.revalidate();
            MainController.panel.repaint();
            MainController.frame.revalidate();
            MainController.frame.repaint();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
