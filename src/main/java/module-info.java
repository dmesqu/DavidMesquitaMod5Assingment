module org.example.davidmesquitamod5assingment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.davidmesquitamod5assingment to javafx.fxml;
    exports org.example.davidmesquitamod5assingment;
}