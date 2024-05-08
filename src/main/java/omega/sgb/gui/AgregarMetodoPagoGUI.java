package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorPago;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarMetodoPagoGUI implements Initializable {

    @FXML
    Label lblNombreU;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNombreU.setText(SingletonControladores.getUsuarioActual().getNombre());
    }
    private ControladorPago controladorPago = SingletonControladores.getInstanceControladorPago();
    public AgregarMetodoPagoGUI() throws SQLException {}
    public AgregarMetodoPagoGUI(ControladorPago controladorPago) throws SQLException {
        this.controladorPago = controladorPago;
    }

    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoLectorViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroLectorViewSingleton(event);
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
