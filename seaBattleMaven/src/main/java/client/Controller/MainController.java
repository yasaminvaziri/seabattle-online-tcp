package client.controller;

import client.view.WelcomeView;

import javax.swing.*;

public class MainController {
    public static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel();
    public void mainController(){
        frame.add(panel);
        frame.setSize(1000,1000);
        WelcomeView welcomeView = new WelcomeView();
        welcomeView.WelcomePage();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.setVisible(true);
    }
}
