package client.view;

import client.controller.LoginController;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.util.Properties;

public class LoginView {
    LoginController loginController;

    JTextField userText;
    JPasswordField pass;
    JPasswordField pass2;
    public JLabel warn;
    public void LoginPage() {
        loginController = new LoginController(this);
        MainController.panel.removeAll();
        JLabel username, password;
        username = new JLabel();
        try (FileReader reader = new FileReader("src/main/java/client/config/login.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

            username.setText(properties.getProperty("username"));
            username.setBounds(Integer.parseInt(properties.getProperty("u1")),
                    Integer.parseInt(properties.getProperty("u2")),Integer.parseInt(properties.getProperty("u3")),
                    Integer.parseInt(properties.getProperty("u4")));
            password = new JLabel();
            password.setText(properties.getProperty("password"));
            password.setBounds(Integer.parseInt(properties.getProperty("p1")),
                    Integer.parseInt(properties.getProperty("p2")),Integer.parseInt(properties.getProperty("p3")),
                    Integer.parseInt(properties.getProperty("p4")));
            userText = new JTextField();
            userText.setBackground(Color.LIGHT_GRAY);
            userText.setBounds(Integer.parseInt(properties.getProperty("ut1")),
                    Integer.parseInt(properties.getProperty("ut2")),Integer.parseInt(properties.getProperty("ut3")),
                    Integer.parseInt(properties.getProperty("ut4")));
            pass = new JPasswordField();
            pass.setBackground(Color.LIGHT_GRAY);
            pass.setBounds(Integer.parseInt(properties.getProperty("pt1")),
                    Integer.parseInt(properties.getProperty("pt2")),Integer.parseInt(properties.getProperty("pt3")),
                    Integer.parseInt(properties.getProperty("pt4")));
            pass2 = new JPasswordField();
            pass2.setBackground(Color.LIGHT_GRAY);
            pass2.setBounds(Integer.parseInt(properties.getProperty("pp1")),
                    Integer.parseInt(properties.getProperty("pp2")),Integer.parseInt(properties.getProperty("pp3")),
                    Integer.parseInt(properties.getProperty("pp4")));
            JButton ok = new JButton();
            ok.setText(properties.getProperty("ok"));
            ok.setBackground(Color.pink);
            ok.setBounds(Integer.parseInt(properties.getProperty("o1")),
                    Integer.parseInt(properties.getProperty("o2")),Integer.parseInt(properties.getProperty("o3")),
                    Integer.parseInt(properties.getProperty("o4")));
            warn = new JLabel();
            warn.setBounds(Integer.parseInt(properties.getProperty("w1")),
                    Integer.parseInt(properties.getProperty("w2")),Integer.parseInt(properties.getProperty("w3")),
                    Integer.parseInt(properties.getProperty("w4")));

            MainController.panel.add(username);
            MainController.panel.add(password);
            MainController.panel.add(userText);
            MainController.panel.add(pass);
            MainController.panel.add(warn);
            MainController.panel.add(ok);
            MainController.panel.revalidate();
            MainController.panel.repaint();

            ok.addActionListener(this::okButtonPressed);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void okButtonPressed(ActionEvent actionEvent) {
        loginController.okButtonPressed(userText.getText(), pass.getText());
    }
}
