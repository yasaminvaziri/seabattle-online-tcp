package client.view;

import client.controller.MainController;
import shared.UserInfo;

import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;

public class ProfileView {
    public ProfileView(UserInfo userInfo){
        JLabel usernameIs, winsIs, losesIs, pointsIs;
        JLabel username, wins, loses, points;
        MainController.frame.remove(MainController.panel);
        MainController.panel = new JPanel();
        try(FileReader reader = new FileReader("src/main/java/client/config/profile.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

            MainController.panel.setSize(Integer.parseInt(properties.getProperty("p1")),
                    Integer.parseInt(properties.getProperty("p2")));
            MainController.frame.add(MainController.panel);

            usernameIs = new JLabel();
            usernameIs.setText(properties.getProperty("username"));
            usernameIs.setBounds(Integer.parseInt(properties.getProperty("u1")),
                    Integer.parseInt(properties.getProperty("u2")), Integer.parseInt(properties.getProperty("u3")),
                    Integer.parseInt(properties.getProperty("u4")));

            username = new JLabel();
            username.setText(userInfo.username);
            username.setBounds(Integer.parseInt(properties.getProperty("uu1")),
                    Integer.parseInt(properties.getProperty("uu2")), Integer.parseInt(properties.getProperty("uu3")),
                    Integer.parseInt(properties.getProperty("uu4")));

            winsIs = new JLabel();
            winsIs.setText(properties.getProperty("wins"));
            winsIs.setBounds(Integer.parseInt(properties.getProperty("w1")),
                    Integer.parseInt(properties.getProperty("w2")), Integer.parseInt(properties.getProperty("w3")),
                    Integer.parseInt(properties.getProperty("w4")));

            wins = new JLabel();
            wins.setText(String.valueOf(userInfo.wins));
            wins.setBounds(Integer.parseInt(properties.getProperty("ww1")),
                    Integer.parseInt(properties.getProperty("ww2")), Integer.parseInt(properties.getProperty("ww3")),
                    Integer.parseInt(properties.getProperty("ww4")));

            losesIs = new JLabel();
            losesIs.setText(properties.getProperty("loses"));
            losesIs.setBounds(Integer.parseInt(properties.getProperty("l1")),
                    Integer.parseInt(properties.getProperty("l2")), Integer.parseInt(properties.getProperty("l3")),
                    Integer.parseInt(properties.getProperty("l4")));

            loses = new JLabel();
            loses.setText(String.valueOf(userInfo.loses));
            loses.setBounds(Integer.parseInt(properties.getProperty("ll1")),
                    Integer.parseInt(properties.getProperty("ll2")), Integer.parseInt(properties.getProperty("ll3")),
                    Integer.parseInt(properties.getProperty("ll4")));

            pointsIs = new JLabel();
            pointsIs.setText(properties.getProperty("points"));
            pointsIs.setBounds(Integer.parseInt(properties.getProperty("p11")),
                    Integer.parseInt(properties.getProperty("p22")), Integer.parseInt(properties.getProperty("p3")),
                    Integer.parseInt(properties.getProperty("p4")));

            points = new JLabel();
            points.setText(String.valueOf(userInfo.points));
            points.setBounds(Integer.parseInt(properties.getProperty("pp1")),
                    Integer.parseInt(properties.getProperty("pp2")), Integer.parseInt(properties.getProperty("pp3")),
                    Integer.parseInt(properties.getProperty("pp4")));

        MainController.panel.add(usernameIs);
        MainController.panel.add(username);
        MainController.panel.add(winsIs);
        MainController.panel.add(wins);
        MainController.panel.add(wins);
        MainController.panel.add(losesIs);
        MainController.panel.add(loses);
        MainController.panel.add(pointsIs);
        MainController.panel.add(points);
        MainController.panel.revalidate();
        MainController.panel.repaint();
        MainController.frame.revalidate();
        MainController.frame.repaint();

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
