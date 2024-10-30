package com.tuvarna.oop2project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    public void redirectToHotelCreation(ActionEvent event) throws IOException {
        Parent contactPage = FXMLLoader.load(getClass().getResource("hotelCreation.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(contactPage);
        stage.setScene(scene);
        stage.show();
    }
}
