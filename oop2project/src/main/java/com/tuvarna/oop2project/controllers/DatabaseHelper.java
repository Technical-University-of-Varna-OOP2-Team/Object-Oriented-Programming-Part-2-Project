package com.tuvarna.oop2project.controllers;
import java.sql.*;

public class DatabaseHelper
{

    private static final String DB_URL = "jdbc:mysql://localhost:3306/HotelManagement";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static String authenticateUser(String username, String password) throws SQLException {
        String role = null;

        String query = "SELECT role FROM Users WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        }

        return role;
    }
}