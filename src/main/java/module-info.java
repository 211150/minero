module com.mina.minero {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mina.minero to javafx.fxml;
    exports com.mina.minero;
    exports com.mina.minero.controller;
    opens com.mina.minero.controller to javafx.fxml;
}