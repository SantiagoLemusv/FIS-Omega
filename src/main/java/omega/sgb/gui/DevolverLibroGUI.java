package omega.sgb.gui;

import javafx.event.ActionEvent;
import omega.sgb.SingletonPantallas;

import java.io.IOException;

public class DevolverLibroGUI {
    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoBibliotecarioViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroBibliotecarioViewSingleton(event);
    }
    public void mBtnCarrito(ActionEvent event) throws IOException {
        SingletonPantallas.toCarritoViewSingleton(event);
    }
    public void mBtnCerrarSesion(ActionEvent event) throws IOException {
        SingletonPantallas.toLogInViewSingleton(event);
    }
    public void mBtnDevolverLibro(ActionEvent event) throws IOException {
        SingletonPantallas.toDevolverLibroViewSingleton(event);
    }
}
