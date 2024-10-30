package com.tuvarna.oop2project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactController {
    public void redirectToLogin(ActionEvent event) throws IOException {
        Parent contactPage = FXMLLoader.load(getClass().getResource("login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(contactPage);
        stage.setScene(scene);
        stage.show();
    }

}
