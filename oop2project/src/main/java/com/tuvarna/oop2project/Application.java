package com.tuvarna.oop2project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage loginStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 360);
        loginStage.setTitle("Hotel Manager V0.0.0");
        loginStage.setScene(scene);
        loginStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}