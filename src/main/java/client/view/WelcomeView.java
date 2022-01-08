package client.view;

import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class WelcomeView {
    public void WelcomePage() {
        MainController.panel.removeAll();
        JButton login = new JButton();
        try(FileReader reader = new FileReader("src/main/java/client/config/welcome.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            login.setText(properties.getProperty("login"));
            login.setBackground(Color.pink);
            login.setBounds(Integer.parseInt(properties.getProperty("l1")),
                    Integer.parseInt(properties.getProperty("l2")),Integer.parseInt(properties.getProperty("l3")),
                    Integer.parseInt(properties.getProperty("l4")));
            JButton register = new JButton();
            register.setText(properties.getProperty("register"));
            register.setBackground(Color.pink);
            register.setBounds(Integer.parseInt(properties.getProperty("r1")),
                    Integer.parseInt(properties.getProperty("r2")),Integer.parseInt(properties.getProperty("r3")),
                    Integer.parseInt(properties.getProperty("r4")));
            MainController.panel.setBackground(Color.lightGray);
            MainController.panel.add(login);
            MainController.panel.add(register);
            MainController.panel.revalidate();
            MainController.panel.repaint();
            register.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RegisterView registerView = new RegisterView();
                    registerView.registerPage();
                }
            });
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LoginView loginView = new LoginView();
                    loginView.LoginPage();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
