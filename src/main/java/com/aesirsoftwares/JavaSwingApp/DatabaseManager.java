package com.aesirsoftwares.javaswingapp;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:analyzer.db";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String createEntriesTable = "CREATE TABLE IF NOT EXISTS entries (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "input TEXT NOT NULL," +
                    "type TEXT NOT NULL," +
                    "result TEXT NOT NULL" +
                    ");";
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," +
                    "token TEXT NOT NULL" +
                    ");";
            conn.createStatement().execute(createEntriesTable);
            conn.createStatement().execute(createUsersTable);
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    public static void saveEntry(String input, String type, String result) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insertSQL = "INSERT INTO entries (input, type, result) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, input);
            pstmt.setString(2, type);
            pstmt.setString(3, result);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Entrada salva com sucesso!");
            } else {
                System.out.println("Falha ao salvar a entrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco de dados: " + e.getMessage());
        }
    }

    public static void saveUser(String username, String token) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String upsertSQL = "INSERT INTO users (username, token) VALUES (?, ?) " +
                    "ON CONFLICT(username) DO UPDATE SET token = excluded.token";
            PreparedStatement pstmt = conn.prepareStatement(upsertSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, token);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário salvo/atualizado com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar/atualizar usuário: " + e.getMessage());
        }
    }

    public static String getUserToken(String username) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String querySQL = "SELECT token FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(querySQL);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("token");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar token de usuário: " + e.getMessage());
        }
        return null;
    }
}