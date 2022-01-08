package client.view;

import client.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    public void LoginPage(){
        MainController.panel.removeAll();
        JLabel username, password, password2;
        username = new JLabel();
        username.setText("username");
        username.setBounds(50,100,100,30);
        password = new JLabel();
        password.setText("password");
        password.setBounds(50,200,100,30);
        password2 = new JLabel();
        password2.setText("password again");
        password2.setBounds(50,300,100,30);
        JTextField userText = new JTextField();
        userText.setBackground(Color.LIGHT_GRAY);
        userText.setBounds(180,100,150,30);
        JPasswordField pass = new JPasswordField();
        pass.setBackground(Color.LIGHT_GRAY);
        pass.setBounds(180,200,150,30);
        JPasswordField pass2 = new JPasswordField();
        pass2.setBackground(Color.LIGHT_GRAY);
        pass2.setBounds(180,300,150,30);
        JButton ok = new JButton();
        ok.setText("OK");
        ok.setBackground(Color.pink);
        ok.setBounds(200,400,100,30);
        MainController.panel.add(username);
        MainController.panel.add(password);
        MainController.panel.add(password2);
        MainController.panel.add(userText);
        MainController.panel.add(pass);
        MainController.panel.add(pass2);
        MainController.panel.add(ok);
        MainController.panel.revalidate();
        MainController.panel.repaint();
    }
}
