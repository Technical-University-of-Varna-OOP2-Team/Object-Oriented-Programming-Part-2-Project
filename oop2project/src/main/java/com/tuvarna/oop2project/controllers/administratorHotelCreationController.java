package com.tuvarna.oop2project.controllers;

import com.tuvarna.oop2project.Application;
import com.tuvarna.oop2project.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

            showHotelLayout(hotelName, floorsCount, roomsPerFloor, address, ownerId);

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Floors count and rooms per floor must be numeric.");
        }
    }

    private void showHotelLayout(String hotelName, int floorsCount, int roomsPerFloor, String address, int ownerId) {
        Stage popupWindow = new Stage();
        popupWindow.setTitle("Hotel Layout");

        VBox vBox = new VBox(10);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int floor = 1; floor <= floorsCount; floor++) {
            for (int room = 1; room <= roomsPerFloor; room++) {
                String roomNumber = floor + String.format("%02d", room);; // Concatenate floor and room number
                Label roomLabel = new Label(roomNumber);
                gridPane.add(roomLabel, room - 1, floor - 1); // Add label to grid (columns, rows)
            }
        }
        VBox roomLayoutBox = new VBox(10);
        roomLayoutBox.getChildren().add(gridPane);  // Add GridPane to VBox

        // Create "OK" button to confirm the layout
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            // Confirm the layout and proceed with hotel creation
            popupWindow.close();
            insertHotelToDatabase(hotelName, floorsCount, roomsPerFloor, address, ownerId);
        });

        // Create "Abort" button to cancel the operation
        Button abortButton = new Button("Abort");
        abortButton.setOnAction(event -> {
            // Close the popup without submitting
            popupWindow.close();
        });

        // Create an HBox for the buttons and add it to the VBox
        HBox buttonBox = new HBox(10); // Horizontal spacing between buttons
        buttonBox.getChildren().addAll(okButton, abortButton);

        // Add the room layout and buttons to the main VBox
        vBox.getChildren().addAll(roomLayoutBox, buttonBox);  // First add the rooms, then the buttons

        // Set the scene and show the popup window
        Scene scene = new Scene(vBox, 400, 400); // Set the size of the popup window
        popupWindow.setScene(scene);
        popupWindow.show();
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
