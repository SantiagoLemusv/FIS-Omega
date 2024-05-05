package omega.sgb.gui;

import javafx.event.ActionEvent;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorPago;

import java.io.IOException;
import java.sql.SQLException;

public class AgregarMetodoPagoGUI {
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
