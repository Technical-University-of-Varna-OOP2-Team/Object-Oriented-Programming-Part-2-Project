package com.tuvarna.oop2project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

        // Simulated credential check (replace with actual login logic)
        if (authenticate(user, pass)) {
            errorText.setText("");
            loginText.setText("LOGIN SUCCESSFUL!");
            openAdminPage();
        } else {
            errorText.setText("Invalid credentials");
            loginText.setText("");
        }
    }

    private boolean authenticate(String username, String password) {
        // Implement actual authentication logic here
        //TODO: open admin page only if the admin logins
        return "admin".equals(username) && "admin".equals(password);
    }

    private void openAdminPage() {
        try {
            Parent adminPage = FXMLLoader.load(getClass().getResource("admin/adminPage.fxml"));
            Stage stage = (Stage) loginText.getScene().getWindow(); // Get the current stage

            Scene scene = new Scene(adminPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToContactPage(ActionEvent event) throws IOException {
        Parent contactPage = FXMLLoader.load(getClass().getResource("contact.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(contactPage);
        stage.setScene(scene);
        stage.show();
    }
}