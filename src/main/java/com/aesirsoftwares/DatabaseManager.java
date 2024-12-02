package com.aesirsoftwares;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:analyzer.db";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Criando tabelas caso não existam
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," + // Restringe duplicidade
                    "password TEXT NOT NULL" + // Senha armazenada com hash preferencialmente
                    ");";
            conn.createStatement().execute(createUsersTable);
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    // Salvar novo usuário no banco de dados
    public static boolean createUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insertSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Aqui você pode implementar hash na senha

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                System.err.println("Erro: Nome de usuário já existe.");
            } else {
                System.err.println("Erro ao criar usuário: " + e.getMessage());
            }
            return false;
        }
    }

    // Autenticar usuário
    public static boolean authenticateUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String querySQL = "SELECT password FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(querySQL);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // Aqui você pode comparar hash
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
        }
        return false;
    }
}
