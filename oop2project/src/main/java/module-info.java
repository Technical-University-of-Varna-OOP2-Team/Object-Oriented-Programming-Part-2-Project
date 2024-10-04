module com.tuvarna.oop2project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tuvarna.oop2project to javafx.fxml;
    exports com.tuvarna.oop2project;
}