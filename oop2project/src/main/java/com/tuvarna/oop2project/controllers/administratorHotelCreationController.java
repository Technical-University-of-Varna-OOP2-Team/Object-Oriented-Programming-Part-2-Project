package com.tuvarna.oop2project.controllers;

import com.tuvarna.oop2project.Application;
import com.tuvarna.oop2project.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.*;

public class administratorHotelCreationController {

    @FXML
    private ComboBox<String> ownerComboBox;
    @FXML
    private TextField hotelNameField;
    @FXML
    private TextField floorsCountField;
    @FXML
    private TextField roomsPerFloorField;
    @FXML
    private TextField addressField;

    public void initialize() {
        loadOwnersToComboBox();
    }

    private void loadOwnersToComboBox() {
        String query = "SELECT username FROM account WHERE role_id = (SELECT id FROM role WHERE name = 'Owner')";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ObservableList<String> owners = FXCollections.observableArrayList();

            while (rs.next()) {
                owners.add(rs.getString("username"));
            }

            ownerComboBox.setItems(owners);
        } catch (SQLException e) {
            showAlert("Database Error", "Error fetching owners: " + e.getMessage());
        }
    }

    @FXML
    private void submitHotel() {
        String selectedOwner = ownerComboBox.getSelectionModel().getSelectedItem();
        if (selectedOwner == null) {
            showAlert("Input Error", "Please select an owner.");
            return;
        }

        String hotelName = hotelNameField.getText();
        String floorsCountStr = floorsCountField.getText();
        String roomsPerFloorStr = roomsPerFloorField.getText();
        String address = addressField.getText();

        if (hotelName.isEmpty() || floorsCountStr.isEmpty() || roomsPerFloorStr.isEmpty() || address.isEmpty()) {
            showAlert("Input Error", "Please fill in all fields.");
            return;
        }

        try {
            int floorsCount = Integer.parseInt(floorsCountStr);
            int roomsPerFloor = Integer.parseInt(roomsPerFloorStr);

            int ownerId = getOwnerIdByUsername(selectedOwner);

            if (ownerId == -1) {
                showAlert("Database Error", "Owner not found in the database.");
                return;
            }

            insertHotelToDatabase(hotelName, floorsCount, roomsPerFloor, address, ownerId);

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Floors count and rooms per floor must be numeric.");
        }
    }

    private int getOwnerIdByUsername(String username) {
        String query = "SELECT id FROM account WHERE username = ? AND role_id = (SELECT id FROM role WHERE name = 'Owner')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Error fetching owner ID: " + e.getMessage());
        }
        return -1;
    }

    // Insert hotel data into the database
    private void insertHotelToDatabase(String hotelName, int floorsCount, int roomsPerFloor, String address, int ownerId) {
        String query = "INSERT INTO hotel (name, floors, roomsPerFloor, address, owner_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, hotelName);
            stmt.setInt(2, floorsCount);
            stmt.setInt(3, roomsPerFloor);
            stmt.setString(4, address);
            stmt.setInt(5, ownerId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Success", "Hotel created successfully!");
            } else {
                showAlert("Error", "Failed to create hotel.");
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Error inserting hotel data: " + e.getMessage());
        }
    }

    // Show an alert
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

    public void goToAdminCreateOwnerPage() throws IOException {
        Stage stage = loginController.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/administrator/administratorCreateOwner.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Manager V0.0.0 - ADMINISTRATOR CREATE OWNER");
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
