package client.view;

import client.controller.MainController;
import client.controller.MatchMakingController;

import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;


public class MatchMakingView {
    MatchMakingController matchMakingController;
    public JLabel warn;
    public MatchMakingView(){
        //graphic design for match making
        MainController.panel.removeAll();
        warn = new JLabel();
        try(FileReader reader = new FileReader("src/main/java/client/config/matchMaking.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            warn.setBounds(Integer.parseInt(properties.getProperty("w1")),
                    Integer.parseInt(properties.getProperty("w2")),Integer.parseInt(properties.getProperty("w3")),
                    Integer.parseInt(properties.getProperty("w4")));
            warn.setText(properties.getProperty("searching"));
        }catch (Exception e){
            e.printStackTrace();
        }
        MainController.panel.add(warn);
        MainController.panel.revalidate();
        MainController.panel.repaint();
        //end of graphic scope
        matchMakingController = new MatchMakingController();
        matchMakingController.sendMatchMakingRequest();
    }
}
