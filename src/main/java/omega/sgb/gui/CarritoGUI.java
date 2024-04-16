package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import omega.sgb.SingletonPantallas;

import java.io.IOException;

public class CarritoGUI {
    @FXML
    Button btnSolicitar;

    public void mBtnFinalizarPrestamo(ActionEvent event) throws IOException {
        SingletonPantallas.toValidarLectorViewSingleton(event);
    }
}
