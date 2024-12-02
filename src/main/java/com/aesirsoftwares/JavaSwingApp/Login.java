package com.aesirsoftwares.JavaSwingApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 250);
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
        loginButton.setBounds(10, 80, 100, 25);
        frame.add(loginButton);

        JButton createAccountButton = new JButton("Criar Conta");
        createAccountButton.setBounds(120, 80, 145, 25);
        frame.add(createAccountButton);

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(10, 110, 250, 25);
        frame.add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (DatabaseManager.authenticateUser(username, password)) {
                    statusLabel.setText("Login bem-sucedido!");
                    redirectToDashboard(username);
                } else {
                    statusLabel.setText("Nome de usuário ou senha incorretos.");
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Criar.main(null);
            }
        });

        frame.setVisible(true);
    }

    private static void redirectToDashboard(String username) {
        JFrame dashboard = new JFrame("Dashboard");
        dashboard.setSize(400, 300);
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setLayout(null);

        JLabel welcomeLabel = new JLabel("Bem-vindo, " + username + "!");
        welcomeLabel.setBounds(10, 10, 300, 25);
        dashboard.add(welcomeLabel);

        dashboard.setVisible(true);
    }
}
