package com.tuvarna.oop2project.controllers;

import com.tuvarna.oop2project.enums.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {
    @FXML
    private Label loginText;
    @FXML
    private Label errorText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    protected void loginButtonClicked() {
        if (username == null || password == null || errorText == null || loginText == null) {
            throw new IllegalStateException("FXML components not initialized properly");
        }

        String user = username.getText().trim();
        String pass = password.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            errorText.setText("You must fill the fields!");
            loginText.setText("");
            return;
        }

        if (authenticate(user, pass)) {
            errorText.setText("");
            loginText.setText("LOGIN SUCCESSFUL!");
            //TODO: add DB check for the user type
            UserType userType = null;
            try {
                userType.returnUser();
            } catch (Exception e){
                e.getStackTrace();
            }

        } else {
            errorText.setText("Invalid credentials");
            loginText.setText("");
        }
    }

    private boolean authenticate(String username, String password) {
        // TODO: check the db for the existence of a give user+pass combo
        return "admin".equals(username) && "admin".equals(password);


    }

    private void closeCurrentStage() {
        // Get the current stage from the username field
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
    }
}