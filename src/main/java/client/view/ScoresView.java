package client.view;

import client.controller.MainController;
import client.controller.ScoresController;
import shared.scores.Scores;
import shared.UserInfo;


import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;

public class ScoresView {
    ScoresController scoresController;
    public ScoresView(Scores scores, ScoresController scoresController) {

        this.scoresController = scoresController;

        MainController.frame.remove(MainController.panel);
        MainController.panel = new JPanel();
        try(FileReader reader = new FileReader("src/main/java/client/config/scores.properties")) {
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

            for (UserInfo score : scores.scores) {
                JLabel player, point, online;
                player = new JLabel();
                player.setText(score.username);
                point = new JLabel();
                point.setText(String.valueOf(score.points));
                online = new JLabel();
                if (score.online) {
                    online.setText(properties.getProperty("online"));
                } else
                    online.setText(properties.getProperty("offline"));

                panel.add(player);
                panel.add(point);
                panel.add(online);
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
