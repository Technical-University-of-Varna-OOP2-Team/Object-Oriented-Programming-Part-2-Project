package com.tuvarna.oop2project;

import com.tuvarna.oop2project.controllers.loginController;
import com.tuvarna.oop2project.util.HibernateUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.tuvarna.oop2project.entity.Role;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage loginStage) throws IOException {
        loginController.setStage(loginStage);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        loginStage.setTitle("Hotel Manager V0.0.0");
        loginStage.setScene(scene);
        loginStage.setResizable(false);
        loginStage.centerOnScreen();
        loginStage.show();

        //To seed the database from the db_seed.sql file
        //DatabaseSeeder.main(null);

    }

    public static void main(String[] args) {
        // TEST CODE
       /*Session session = null;
        Transaction transaction = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Role role = new Role();
            role.setName("Administrator");

            session.save(role);

            transaction.commit();
            System.out.println("Role saved successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }*/
        launch();
    }
}