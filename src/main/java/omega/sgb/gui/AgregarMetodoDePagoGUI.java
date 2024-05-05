package omega.sgb.gui;

import javafx.event.ActionEvent;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorLogIn;
import omega.sgb.control.ControladorPago;

import java.io.IOException;
import java.sql.SQLException;

public class AgregarMetodoDePagoGUI {
    private ControladorPago controladorPago = SingletonControladores.getInstanceControladorPago();
    public AgregarMetodoDePagoGUI() throws SQLException {}
    public AgregarMetodoDePagoGUI(ControladorPago controladorPago) throws SQLException {
        this.controladorPago = controladorPago;
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
