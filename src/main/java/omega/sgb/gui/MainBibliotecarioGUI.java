package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import omega.sgb.SingletonPantallas;

import java.io.IOException;

public class MainBibliotecarioGUI {
    @FXML
    Button btnCarrito;

    public void mBtnCarrito(ActionEvent event) throws IOException {
        SingletonPantallas.toCarritoViewSingleton(event);
    }
}
