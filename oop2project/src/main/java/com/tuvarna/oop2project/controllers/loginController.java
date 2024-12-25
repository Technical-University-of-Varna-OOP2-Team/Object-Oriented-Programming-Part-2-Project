package com.tuvarna.oop2project.controllers;

import com.tuvarna.oop2project.Application;
import com.tuvarna.oop2project.DatabaseConnection;
import com.tuvarna.oop2project.enums.UserType;
import com.tuvarna.oop2project.users.Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginController {
    @FXML
    private Label errorText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private static Stage stage;

    @FXML
    protected void loginButtonClicked() throws IOException, SQLException
    {
        if (username == null || password == null || errorText == null) {
            throw new IllegalStateException("FXML components not initialized properly");
        }

        String user = username.getText().trim();
        String pass = password.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            errorText.setText("You must fill the fields!");
            return;
        }

        if (authenticate(user, pass)) {
            errorText.setText("");
            //new Administrator(stage);

            //TODO: add DB check for the user type
            /*UserType userType = null;
            try {
                userType.returnUser(stage);
            } catch (Exception e){
                e.getStackTrace();
            }*/

        } else {
            errorText.setText("Invalid credentials");
        }
    }

    private boolean authenticate(String username, String password) throws SQLException
    {
        String query = "SELECT role_id FROM account WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int roleId = rs.getInt("role_id");
                UserType userType = getUserTypeByRoleId(roleId);

                if (userType != null) {
                    // Create and show the corresponding user type page
                    userType.returnUser(stage);
                    return true;
                }
            }
        } catch (IOException e) {
            // Handle any IOException that occurs while loading the user page
            errorText.setText("Error loading user page: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
        // TODO: check the db for the existence of a give user+pass combo
        //return "admin".equals(username) && "admin".equals(password);


    }
    private String getRoleNameById(int roleId) throws SQLException {
        String query = "SELECT name FROM role WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roleId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("name");  // Assume the column is called role_name
            } else {
                return null;  // No role found for the given role_id
            }
        }
    }

    private UserType getUserTypeByRoleId(int roleId) {
        try {
            String roleName = getRoleNameById(roleId);

            if (roleName == null) {
                return null; // No role found for the given ID
            }

            switch (roleName.toUpperCase()) {
                case "ADMINISTRATOR":
                    return UserType.ADMINISTRATOR;
                case "OWNER":
                    return UserType.OWNER;
                case "MANAGER":
                    return UserType.MANAGER;
                case "RECEPTIONIST":
                    return UserType.RECEPTIONIST;
                default:
                    return null; // Handle the case where the role doesn't match any user type
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null if the database query fails
        }
    }

    //TODO: Fix this
    public void goToContactPage() throws IOException {
        Stage contactStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/contact.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        contactStage.setTitle("Hotel Manager V0.0.0 - Contacts");
        contactStage.setScene(scene);
        contactStage.initModality(Modality.APPLICATION_MODAL);
        contactStage.setResizable(false);
        contactStage.centerOnScreen();
        contactStage.show();
    }

    public static void setStage(Stage stage){
        loginController.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }
}