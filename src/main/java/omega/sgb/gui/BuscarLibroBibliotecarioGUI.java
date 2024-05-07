package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorBusquedaLibro;

import java.io.IOException;
import java.sql.SQLException;

public class BuscarLibroBibliotecarioGUI {
    @FXML
    TextField txtFieldTitulo;
    @FXML
    Button btnBuscarLibro;
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public BuscarLibroBibliotecarioGUI() throws SQLException {}
    public BuscarLibroBibliotecarioGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
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
    public void mBtnBuscarLibro(ActionEvent event) throws IOException{
        SingletonPantallas.toResultadosBibliotecarioViewSingleton(event);
    }
}

