package com.tuvarna.oop2project.controllers;

import com.tuvarna.oop2project.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;


import java.io.IOException;

public class administratorCreateOwnerController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    private static final String URL = "jdbc:mysql://localhost:3306/HotelManagement";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    @FXML
    public void createOwner() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill both username and password fields.");
            return;
        }

        insertOwnerIntoDatabase(username, password);
    }

    private void insertOwnerIntoDatabase(String username, String password) {
        int roleId = getRoleIdByName("Owner");

        if (roleId == -1) {
            showAlert("Error", "Role 'Owner' not found in the database.");
            return;
        }

        if (isUsernameTaken(username)) {
            showAlert("Error", "Username is already taken. Please choose another username.");
            return;
        }
        String query = "INSERT INTO account (username, password, role_id) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameters
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, roleId);

            // Execute update
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Success", "Owner created successfully!");
            } else {
                showAlert("Error", "Failed to create owner.");
            }

        } catch (SQLException e) {
            showAlert("Database Error", "An error occurred while connecting to the database: " + e.getMessage());
        }
    }

    private boolean isUsernameTaken(String username) {
        String checkQuery = "SELECT COUNT(*) FROM account WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(checkQuery)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Returns true if the username is taken
                }
            }

        } catch (SQLException e) {
            showAlert("Database Error", "An error occurred while checking the username: " + e.getMessage());
        }
        return false; // Returns false if no errors or if username is not taken
    }


    private int getRoleIdByName(String roleName) {
        String query = "SELECT id FROM role WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, roleName);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {
            showAlert("Database Error", "An error occurred while fetching the role: " + e.getMessage());
        }

        return -1;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void goToAdminMainPage() throws IOException {
        Stage stage = loginController.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/administrator/administratorMainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Manager V0.0.0 - ADMINISTRATOR MAIN PAGE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void goToAdminHotelCreationPage() throws IOException {
        Stage stage = loginController.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/administrator/administratorHotelCreation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Manager V0.0.0 - ADMINISTRATOR HOTEL CREATION");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void goToAdminHotelManagementPage() throws IOException {
        Stage stage = loginController.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/administrator/administratorHotelManagement.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Manager V0.0.0 - ADMINISTRATOR HOTEL MANAGEMENT");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
