package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgregarMetododePagoGUI {

    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoUsuarioViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroViewSingleton(event);
    }
    public void mBtnCerrarSesion(ActionEvent event) throws IOException {
        SingletonPantallas.toLogInViewSingleton(event);
    }
    public void mBtnAgregarNuevoMetodoPago(ActionEvent event) throws IOException{
        /*
        Cogido que confirma que los valores ingresados esten bien, la tarjeta no este repetida, etc etc.
         */
        SingletonPantallas.toPagoViewSingleton(event);
    }
    public void mBtnCancelar(ActionEvent event) throws IOException{
        SingletonPantallas.toPagoViewSingleton(event);
    }
}
