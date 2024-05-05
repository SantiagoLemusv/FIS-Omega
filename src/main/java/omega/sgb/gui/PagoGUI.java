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

public class PagoGUI implements Initializable {
    @FXML
    Label txtCedula;
    @FXML
    Label txtTipoCuenta;
    @FXML
    Label txtNombre;
    @FXML
    ListView<String> ListPrestamos;
    @FXML
    ListView<String> ListMultas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtNombre.setText(SingletonControladores.getUsuarioActual().getNombre());

        txtCedula.setText(String.valueOf(SingletonControladores.getUsuarioActual().getCedula()));

        if(SingletonControladores.getUsuarioActual().getClass().equals("PersonaBibliotecario")){
            txtTipoCuenta.setText("Bibliotecario");
        }else {
            txtTipoCuenta.setText("Lector");
        }
    }

    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoUsuarioViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroViewSingleton(event);
    }
    public void mBtnCerrarSesion(ActionEvent event) throws IOException {
        SingletonPantallas.toLogInViewSingleton(event);
    }
    public void mBtnIraPago(ActionEvent event) throws IOException {
        SingletonPantallas.toPagoViewSingleton(event);
    }
    public void mBtnConfPago(ActionEvent event) throws IOException{
        /*
        Codigo que se asegure que se selecciona un metodo de pago, que hay multas por pagar etc.
         */
        SingletonPantallas.toEstadoUsuarioViewSingleton(event);
    }
    public void mBtnNuevoMetodoPago(ActionEvent event) throws IOException{
        SingletonPantallas.toAgregarMetododePagoViewSingleton(event);
    }
}