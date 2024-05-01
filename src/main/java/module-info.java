module omega.sgb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens omega.sgb to javafx.fxml;
    exports omega.sgb;
    opens omega.sgb.control to javafx.fxml;
    exports omega.sgb.control;
    exports omega.sgb.integracion;
    opens omega.sgb.integracion to javafx.fxml;
    exports omega.sgb.gui;
    opens omega.sgb.gui to javafx.fxml;

}