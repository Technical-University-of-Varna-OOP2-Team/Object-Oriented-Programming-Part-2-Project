package com.tuvarna.oop2project.users;

import com.tuvarna.oop2project.Application;
import com.tuvarna.oop2project.controllers.loginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Administrator extends User{

    public Administrator (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/administrator/administratorMainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Manager V0.0.0 - ADMINISTRATOR MAIN PAGE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
