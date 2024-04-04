module omega.sgb {
    requires javafx.controls;
    requires javafx.fxml;


    opens omega.sgb to javafx.fxml;
    exports omega.sgb;
    opens omega.sgb.control to javafx.fxml;
    exports omega.sgb.control;

}