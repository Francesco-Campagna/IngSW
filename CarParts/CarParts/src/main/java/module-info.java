module com.example.carparts {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.carparts.Controller to javafx.fxml;
    exports com.example.carparts;
    exports com.example.carparts.Database;
    exports com.example.carparts.Handler;
}