package client.view;

import client.controller.MainController;
import shared.game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.util.Properties;

public class EndedGameView {
    public EndedGameView(Player player){
        MainController.frame.remove(MainController.panel);
        MainController.panel = new JPanel();
        MainController.frame.add(MainController.panel);
        JLabel status = new JLabel();
        JButton menu = new JButton();
        try(FileReader reader = new FileReader("src/main/java/client/config/endedgame.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            menu.setBounds(Integer.parseInt(properties.getProperty("m1")),Integer.parseInt(properties.getProperty("m2")),
                    Integer.parseInt(properties.getProperty("m3")),Integer.parseInt(properties.getProperty("m4")));
            menu.setBackground(Color.pink);
            status.setBounds(Integer.parseInt(properties.getProperty("s1")),Integer.parseInt(properties.getProperty("s2")),
                    Integer.parseInt(properties.getProperty("s3")),Integer.parseInt(properties.getProperty("s4")));
            if (player.state.equals(properties.getProperty("win"))){
                status.setText(properties.getProperty("winner"));
            }
            else if (player.state.equals(properties.getProperty("lose"))){
                status.setText(properties.getProperty("loser"));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
//        menu.setBounds(1500,200,100,30);
//        menu.setBackground(Color.pink);
//        status.setBounds(1500,100,100,30);
//        if (player.state.equals("win")){
//            status.setText("WINNER");
//        }
//        else if (player.state.equals("lose")){
//            status.setText("LOSER");
//        }

        MainController.panel.add(menu);
        MainController.panel.add(status);
        MainController.panel.revalidate();
        MainController.panel.repaint();

        MainController.frame.revalidate();
        MainController.frame.repaint();

        menu.addActionListener(this::menuPressedButton);


    }

    private void menuPressedButton(ActionEvent event) {
        new MenuView();
    }

}
