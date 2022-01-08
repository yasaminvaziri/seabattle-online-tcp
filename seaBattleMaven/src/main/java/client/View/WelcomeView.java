package client.view;

import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView {
    public void WelcomePage(){
        MainController.panel.removeAll();
        JButton login = new JButton();
        login.setText("LOGIN");
        login.setBackground(Color.pink);
        login.setBounds(100,100,100,30);
        JButton register = new JButton();
        register.setText("REGISTER");
        register.setBackground(Color.pink);
        register.setBounds(100,200,100,30);
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
    }
}
