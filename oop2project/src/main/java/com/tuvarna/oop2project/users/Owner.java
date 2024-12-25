package com.tuvarna.oop2project.users;

import com.tuvarna.oop2project.Application;
import com.tuvarna.oop2project.controllers.loginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Owner extends User{

    public Owner(Stage stage) throws IOException
    {
        // Load the Owner page (FXML)
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/Owner/ownerPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Owner Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
