package omega.sgb.gui;

import javafx.event.ActionEvent;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;

import java.io.IOException;

public class BusquedaGUI {

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
        SingletonPantallas.toLogInViewSingleton(event);
    }
    public void mBtnIraPago(ActionEvent event) throws IOException {
        SingletonPantallas.toPagoViewSingleton(event);
    }
    public void mBtnBuscar(ActionEvent event) throws IOException {
        SingletonPantallas.toResultadoLibroViewSingleton(event);
    }
}
