package com.tuvarna.oop2project;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class DatabaseSeeder {

    public static void main(String[] args) {
        String sqlFilePath = "db_seed.sql";
        try {
            String sql = loadSqlFromResources(sqlFilePath);
            executeSqlScript(sql);
            System.out.println("Database seeding completed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String loadSqlFromResources(String fileName) throws Exception {
        InputStream inputStream = DatabaseSeeder.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        // Read the file as a string
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
    private static void executeSqlScript(String sql) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String[] statements = sql.split(";"); // Split SQL script by semicolons
            for (String statement : statements) {
                if (statement.trim().length() > 0) {
                    try (PreparedStatement stmt = conn.prepareStatement(statement.trim())) {
                        stmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
