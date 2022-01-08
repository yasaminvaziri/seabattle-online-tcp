package client.view;

import client.controller.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.util.Properties;

public class MenuView {
     JButton new_game, online_stream, info, scores;

    public MenuView() {
        MainController.frame.remove(MainController.panel);
        MainController.panel = new JPanel();
        try(FileReader reader = new FileReader("src/main/java/client/config/menu.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            MainController.panel.setSize(Integer.parseInt(properties.getProperty("p1")),
                    Integer.parseInt(properties.getProperty("p2")));
            MainController.frame.add(MainController.panel);

            new_game = new JButton();
            new_game.setText(properties.getProperty("n"));
            new_game.setBounds(Integer.parseInt(properties.getProperty("n1")),
                    Integer.parseInt(properties.getProperty("n2")), Integer.parseInt(properties.getProperty("n3")),
                    Integer.parseInt(properties.getProperty("n4")));
            new_game.setBackground(Color.pink);
            online_stream = new JButton();
            online_stream.setText(properties.getProperty("o"));
            online_stream.setBounds(Integer.parseInt(properties.getProperty("o1")),
                    Integer.parseInt(properties.getProperty("o2")), Integer.parseInt(properties.getProperty("o3")),
                    Integer.parseInt(properties.getProperty("o4")));
            online_stream.setBackground(Color.pink);
            info = new JButton();
            info.setText(properties.getProperty("i"));
            info.setBounds(Integer.parseInt(properties.getProperty("i1")),
                    Integer.parseInt(properties.getProperty("i2")), Integer.parseInt(properties.getProperty("i3")),
                    Integer.parseInt(properties.getProperty("i4")));
            info.setBackground(Color.pink);
            scores = new JButton();
            scores.setText(properties.getProperty("s"));
            scores.setBounds(Integer.parseInt(properties.getProperty("s1")),
                    Integer.parseInt(properties.getProperty("s2")), Integer.parseInt(properties.getProperty("s3")),
                    Integer.parseInt(properties.getProperty("s4")));
            scores.setBackground(Color.pink);
            MainController.panel.add(new_game);
            MainController.panel.add(online_stream);
            MainController.panel.add(scores);
            MainController.panel.add(info);
            MainController.panel.revalidate();
            MainController.panel.repaint();

            MainController.frame.revalidate();
            MainController.frame.repaint();
        }catch (Exception e){
            e.printStackTrace();
        }

        new_game.addActionListener(this::new_gamePressed);
        online_stream.addActionListener(this::online_stream_button_pressed);
        scores.addActionListener(this::score_button_pressed);
        info.addActionListener(this::info_button_pressed);
    }

    private void info_button_pressed(ActionEvent event) {
        new ProfileController();
    }

    private void score_button_pressed(ActionEvent event) {
        new ScoresController();
    }

    private void online_stream_button_pressed(ActionEvent actionEvent) {
        new StreamListController();
    }

    private void new_gamePressed(ActionEvent event) {
        new MatchMakingView();
    }
}
