package com.tuvarna.oop2project.controllers;

import com.tuvarna.oop2project.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class administratorMainPageController {

    public void goToAdminCreateOwnerPage() throws IOException {
        Stage stage = loginController.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("administrator/administratorCreateOwner.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Manager V0.0.0 - ADMINISTRATOR CREATE OWNER");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public void goToAdminHotelCreationPage() throws IOException {
        Stage stage = loginController.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("administrator/administratorHotelCreation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Manager V0.0.0 - ADMINISTRATOR HOTEL CREATION");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public void goToAdminHotelManagementPage() throws IOException {
        Stage stage = loginController.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("administrator/administratorHotelManagement.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Manager V0.0.0 - ADMINISTRATOR HOTEL MANAGEMENT");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }
}
