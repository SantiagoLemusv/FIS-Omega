package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorLogIn;
import omega.sgb.control.ControladorUsuario;

import java.io.IOException;

public class MainBibliotecarioGUI {
    private ControladorUsuario controladorUsuario;
    public MainBibliotecarioGUI(){}
    public MainBibliotecarioGUI(ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;
    }

    @FXML
    Button btnCarrito;

    public void mBtnCarrito(ActionEvent event) throws IOException {
        SingletonPantallas.toCarritoViewSingleton(event);
    }
}
