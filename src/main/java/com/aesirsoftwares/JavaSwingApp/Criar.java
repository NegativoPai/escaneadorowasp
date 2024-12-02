package com.aesirsoftwares.JavaSwingApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Criar {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Criar Conta");
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

        JButton createButton = new JButton("Criar Conta");
        createButton.setBounds(10, 100, 150, 25);
        frame.add(createButton);

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(10, 130, 250, 25);
        frame.add(statusLabel);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (!username.isEmpty() && !password.isEmpty()) {
                    if (DatabaseManager.createUser(username, password)) {
                        statusLabel.setText("Conta criada com sucesso!");
                        frame.dispose();
                        Login.main(null);
                    } else {
                        statusLabel.setText("Usuário já existe.");
                    }
                } else {
                    statusLabel.setText("Preencha todos os campos.");
                }
            }
        });

        frame.setVisible(true);
    }
}
