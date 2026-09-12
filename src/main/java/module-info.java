module com.example.ayed_practica1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ayed_practica1 to javafx.fxml;
    exports com.example.ayed_practica1;
}