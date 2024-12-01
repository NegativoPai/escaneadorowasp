package com.aesirsoftwares;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseManager{
    private static final String DB_URL = "jdbc:sqlite:analyzer.db";

    static{
        try (Connection conn = DriverManager.getConnection(DB_URL)){
            String createTableSQL = "CREATE TABLE IF NOT EXISTS entries (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "input TEXT NOT NULL," + "type TEXT NOT NULL," + "result TEXT NOT NULL" + ");";
conn.createStatement().execute(createTableSQL);
        } catch (Exception e)
{
        
System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
    }
}

public static void saveEntry(String input, String type, String result){
    try (Connection conn = DriverManager.getConnection(DB_URL)) {
        String insertSQL = "INSERT INTO entries (input, type, result) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSQL);

        pstmt.setString(1, input);

        pstmt.setString(2, type);

        pstmt.setString(3, result);

        pstmt.executeUpdate();
    } catch (Exception e)
{

System.err.println("Erro ao salvar no banco de dados: " + e.getMessage());
}
}
}