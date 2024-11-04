package com.tuvarna.oop2project.controllers;

import com.tuvarna.oop2project.Application;
import com.tuvarna.oop2project.enums.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    @FXML
    private Label errorText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    protected void loginButtonClicked() {
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
            //TODO: add DB check for the user type
            UserType userType = null;
            try {
                userType.returnUser();
            } catch (Exception e){
                e.getStackTrace();
            }

        } else {
            errorText.setText("Invalid credentials");
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

    //TODO: Fix this
    public void goToContactPage() throws IOException {
        Stage contactScene = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("contact.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        contactScene.setTitle("Hotel Manager V0.0.0 - Contacts");
        contactScene.setScene(scene);
        contactScene.initModality(Modality.APPLICATION_MODAL);
        contactScene.setResizable(false);
        contactScene.show();
    }
}