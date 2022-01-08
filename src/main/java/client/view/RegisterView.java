package client.view;

import client.controller.MainController;
import client.controller.RegisterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.util.Properties;

public class RegisterView {
    RegisterController registerController;
    JTextField userText;
    JPasswordField pass, pass2;
    public JLabel warn;
    public void registerPage(){
        registerController = new RegisterController(this);
        MainController.panel.removeAll();
        JLabel username, password, password2;
        username = new JLabel();
        try(FileReader reader = new FileReader("src/main/java/client/config/register.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            username.setText(properties.getProperty("username"));
            username.setBounds(Integer.parseInt(properties.getProperty("u1")),
                    Integer.parseInt(properties.getProperty("u2")), Integer.parseInt(properties.getProperty("u3")),
                    Integer.parseInt(properties.getProperty("u4")));
            password = new JLabel();
            password.setText(properties.getProperty("password"));
            password.setBounds(Integer.parseInt(properties.getProperty("p1")),
                    Integer.parseInt(properties.getProperty("p2")), Integer.parseInt(properties.getProperty("p3")),
                    Integer.parseInt(properties.getProperty("p4")));
            password2 = new JLabel();
            password2.setText(properties.getProperty("pa"));
            password2.setBounds(Integer.parseInt(properties.getProperty("pa1")),
                    Integer.parseInt(properties.getProperty("pa2")), Integer.parseInt(properties.getProperty("pa3")),
                    Integer.parseInt(properties.getProperty("pa4")));
            userText = new JTextField();
            userText.setBackground(Color.LIGHT_GRAY);
            userText.setBounds(Integer.parseInt(properties.getProperty("ut1")),
                    Integer.parseInt(properties.getProperty("ut2")), Integer.parseInt(properties.getProperty("ut3")),
                    Integer.parseInt(properties.getProperty("ut4")));
            pass = new JPasswordField();
            pass.setBackground(Color.LIGHT_GRAY);
            pass.setBounds(Integer.parseInt(properties.getProperty("pt1")),
                    Integer.parseInt(properties.getProperty("pt2")), Integer.parseInt(properties.getProperty("pt3")),
                    Integer.parseInt(properties.getProperty("pt4")));
            pass2 = new JPasswordField();
            pass2.setBackground(Color.LIGHT_GRAY);
            pass2.setBounds(Integer.parseInt(properties.getProperty("ppt1")),
                    Integer.parseInt(properties.getProperty("ppt2")), Integer.parseInt(properties.getProperty("ppt3")),
                    Integer.parseInt(properties.getProperty("ppt4")));
            JButton ok = new JButton();
            ok.setText(properties.getProperty("ok"));
            ok.setBackground(Color.pink);
            ok.setBounds(Integer.parseInt(properties.getProperty("o1")),
                    Integer.parseInt(properties.getProperty("o2")), Integer.parseInt(properties.getProperty("o3")),
                    Integer.parseInt(properties.getProperty("o4")));
            warn = new JLabel();
            warn.setBounds(Integer.parseInt(properties.getProperty("w1")),
                    Integer.parseInt(properties.getProperty("w2")), Integer.parseInt(properties.getProperty("w3")),
                    Integer.parseInt(properties.getProperty("w4")));
            MainController.panel.add(username);
            MainController.panel.add(password);
            MainController.panel.add(password2);
            MainController.panel.add(userText);
            MainController.panel.add(pass);
            MainController.panel.add(pass2);
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
        registerController.okButtonPressed(userText.getText(), pass.getText(), pass2.getText());
    }
}
