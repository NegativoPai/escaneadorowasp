package com.aesirsoftwares.JavaSwingApp;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:analyzer.db";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Cria a tabela `users` se não existir
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," +  // Nome de usuário único
                    "password TEXT NOT NULL," +        // Senha do usuário
                    "token TEXT DEFAULT NULL" +        // Token opcional
                    ");";
            conn.createStatement().execute(createUsersTable);

            // Confere e ajusta a estrutura da tabela, se necessário
            ensureTableStructure(conn);

        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    // Método para garantir que a tabela tenha a estrutura correta
    private static void ensureTableStructure(Connection conn) throws SQLException {
        String checkTableSQL = "PRAGMA table_info(users)";
        ResultSet rs = conn.createStatement().executeQuery(checkTableSQL);

        boolean hasTokenColumn = false;

        while (rs.next()) {
            String columnName = rs.getString("name");
            if ("token".equals(columnName)) {
                hasTokenColumn = true;
            }
        }

        // Adiciona a coluna `token` se estiver ausente
        if (!hasTokenColumn) {
            String alterTableSQL = "ALTER TABLE users ADD COLUMN token TEXT DEFAULT NULL";
            conn.createStatement().execute(alterTableSQL);
            System.out.println("Coluna `token` adicionada à tabela `users`.");
        }
    }

    // Método para criar um novo usuário
    public static boolean createUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insertSQL = "INSERT INTO users (username, password, token) VALUES (?, ?, NULL)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            pstmt.executeUpdate();
            System.out.println("Usuário criado com sucesso: " + username);
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

    // Método para autenticar um usuário
    public static boolean authenticateUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String querySQL = "SELECT password FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(querySQL);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // Validação da senha
            } else {
                System.err.println("Erro: Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
        }
        return false;
    }

    // Método para atualizar o token de um usuário
    public static boolean updateToken(String username, String token) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String updateSQL = "UPDATE users SET token = ? WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);
            pstmt.setString(1, token);
            pstmt.setString(2, username);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar token: " + e.getMessage());
        }
        return false;
    }

    // Método para buscar o token de um usuário
    public static String getToken(String username) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String querySQL = "SELECT token FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(querySQL);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("token");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar token: " + e.getMessage());
        }
        return null;
    }
}
