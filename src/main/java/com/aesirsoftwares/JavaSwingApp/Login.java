package com.aesirsoftwares.javaswingapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10, 20, 80, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        frame.add(userText);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 50, 80, 25);
        frame.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        frame.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 150, 25);
        frame.add(loginButton);

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(10, 110, 250, 25);
        frame.add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if ("admin".equals(username) && "1234".equals(password)) {
                    statusLabel.setText("Login bem-sucedido!");

                    DatabaseManager.saveEntry(username, "login", "sucesso");
                } else {
                    statusLabel.setText("Login falhou!");
                    DatabaseManager.saveEntry(username, "login", "falha");
                }
            }
        });

        frame.setVisible(true);
    }
}
