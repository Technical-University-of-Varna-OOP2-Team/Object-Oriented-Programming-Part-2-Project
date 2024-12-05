module com.tuvarna.oop2project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    opens com.tuvarna.oop2project to javafx.fxml;
    exports com.tuvarna.oop2project;
    exports com.tuvarna.oop2project.controllers;
    opens com.tuvarna.oop2project.controllers to javafx.fxml;
}