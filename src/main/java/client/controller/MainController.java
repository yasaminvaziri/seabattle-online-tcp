package client.controller;

import client.holders.TokenHolder;
import client.stream.StreamUtil;
import client.view.WelcomeView;
import shared.networking.Request;

import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;

public class MainController {
    public static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel();
    public void mainController(){
        frame.add(panel);
        try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            frame.setSize(Integer.parseInt(properties.getProperty("frameW")), Integer.parseInt(properties.getProperty("frameH")));
        }catch (Exception e){
            e.printStackTrace();
        }
        //frame.setSize(4000,800);
        WelcomeView welcomeView = new WelcomeView();
        welcomeView.WelcomePage();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.setVisible(true);
    }
}
