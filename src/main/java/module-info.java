module com.example.sgb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sgb to javafx.fxml;
    exports com.example.sgb;
}