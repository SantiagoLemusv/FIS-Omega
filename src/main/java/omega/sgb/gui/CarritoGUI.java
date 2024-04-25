package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorCarrito;
import omega.sgb.control.ControladorLogIn;

import java.io.IOException;

public class CarritoGUI {
    private ControladorCarrito controladorCarrito;
    public CarritoGUI(){}
    public CarritoGUI(ControladorCarrito controladorCarrito) {
        this.controladorCarrito = controladorCarrito;
    }

    @FXML
    Button btnSolicitar;

    public void mBtnFinalizarPrestamo(ActionEvent event) throws IOException {
        SingletonPantallas.toValidarLectorViewSingleton(event);
    }
}
