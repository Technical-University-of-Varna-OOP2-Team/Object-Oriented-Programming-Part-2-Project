package com.tuvarna.oop2project.enums;

import com.tuvarna.oop2project.users.*;
import javafx.stage.Stage;

import java.io.IOException;

public enum UserType {
    ADMINISTRATOR{
        @Override
        public User returnUser(Stage stage) throws IOException {
            return new Administrator(stage);
        }
    },
    OWNER{
        @Override
        public User returnUser(Stage stage) throws IOException {
            return new Owner(stage);
        }
    },
    MANAGER{
        @Override
        public User returnUser(Stage stage) throws IOException {
            return new Manager(stage);
        }
    },
    RECEPTIONIST{
        @Override
        public User returnUser(Stage stage) throws IOException {
            return new Receptionist(stage);
        }
    };

    public abstract User returnUser(Stage stage) throws IOException;

}
