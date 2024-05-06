package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorBusquedaLibro;

import java.io.IOException;
import java.sql.SQLException;

public class ResultadoLibroBibliotecarioGUI {
    @FXML
    ImageView imageViewLibroSeleccionado;
    @FXML
    Label lblTitulo;
    @FXML
    Label lblAutor;
    @FXML
    Label lblISBN;
    @FXML
    Label lblCantidad;
    @FXML
    Label lblEstadoLibro;
    @FXML
    ComboBox cmbBoxCopias;
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public ResultadoLibroBibliotecarioGUI() throws SQLException {
    }
    public ResultadoLibroBibliotecarioGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
    @FXML
    void initialize(){
        imageViewLibroSeleccionado.setImage(controladorBusquedaLibro.getLibroSeleccionado().getImagenLibro().getImage());
        lblTitulo.setText(controladorBusquedaLibro.getLibroSeleccionado().getTitulo());
        lblAutor.setText(controladorBusquedaLibro.getLibroSeleccionado().getAutor());
        lblISBN.setText(controladorBusquedaLibro.getLibroSeleccionado().getIsbn());
        String cant = String.valueOf(controladorBusquedaLibro.getLibroSeleccionado().getCantidad());
        lblCantidad.setText(cant);
        lblEstadoLibro.setText("estado prueba");
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
}
