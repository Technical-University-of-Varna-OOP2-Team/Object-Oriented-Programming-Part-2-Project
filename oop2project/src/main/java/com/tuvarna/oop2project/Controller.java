package com.tuvarna.oop2project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
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

        // Simulated credential check (replace with actual login logic)
        if (authenticate(user, pass)) {
            errorText.setText("");
            loginText.setText("LOGIN SUCCESSFUL!");
        } else {
            errorText.setText("Invalid credentials");
            loginText.setText("");
        }
    }

    private boolean authenticate(String username, String password) {
        // Implement actual authentication logic here
        return "admin".equals(username) && "admin".equals(password);
    }
}