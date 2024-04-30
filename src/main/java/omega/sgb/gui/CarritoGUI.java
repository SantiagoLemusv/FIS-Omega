package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;

import java.io.IOException;

public class CarritoGUI {
    @FXML
    Button btnSolicitar;

    public void mBtnMenuPrincipal(ActionEvent event) throws IOException {
        mTipoPantalla(event);
    }
    public void mTipoPantalla(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual().getTipoPersonaId() == 1){ //Bibliotecario
            SingletonPantallas.toMainBibliotecarioViewSingleton(event);
        } else if (SingletonControladores.getUsuarioActual().getTipoPersonaId() == 2) { //Lector
            SingletonPantallas.toMainLectorViewSingleton(event);
        }
    }

    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoUsuarioViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroViewSingleton(event);
    }
    public void mBtnCarrito(ActionEvent event) throws IOException {
        SingletonPantallas.toCarritoViewSingleton(event);
    }
    public void mBtnCerrarSesion(ActionEvent event) throws IOException {

    }



    public void mBtnFinalizarPrestamo(ActionEvent event) throws IOException {
        SingletonPantallas.toValidarLectorViewSingleton(event);
    }
}
