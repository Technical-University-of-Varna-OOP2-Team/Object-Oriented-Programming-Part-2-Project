package com.tuvarna.oop2project.controllers;

import com.tuvarna.oop2project.Application;
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

    private static Stage stage;

    @FXML
    protected void loginButtonClicked() throws IOException {
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
            new Administrator(stage);

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

    private boolean authenticate(String username, String password) {
        // TODO: check the db for the existence of a give user+pass combo
        return "admin".equals(username) && "admin".equals(password);


    }

    //TODO: Fix this
    public void goToContactPage() throws IOException {
        Stage contactStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("contact.fxml"));
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