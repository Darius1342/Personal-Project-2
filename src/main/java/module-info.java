module com.example.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires org.json;
    requires java.sql;


    opens application to javafx.fxml;
    exports application;
}