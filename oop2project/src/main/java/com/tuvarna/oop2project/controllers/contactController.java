package com.tuvarna.oop2project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class contactController {
    @FXML
    private Text contactText;
    public void redirectToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) contactText.getScene().getWindow();
        stage.close();
    }

}
