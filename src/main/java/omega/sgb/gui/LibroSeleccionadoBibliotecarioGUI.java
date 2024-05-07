package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class LibroSeleccionadoBibliotecarioGUI {
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
    public LibroSeleccionadoBibliotecarioGUI() throws SQLException {
    }
    public LibroSeleccionadoBibliotecarioGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
    @FXML
    void initialize(){
        imageViewLibroSeleccionado.setImage(controladorBusquedaLibro.getLibroSeleccionado().getImagenLibro().getImage());
        lblTitulo.setText(controladorBusquedaLibro.getLibroSeleccionado().getTitulo());
        lblAutor.setText(controladorBusquedaLibro.getLibroSeleccionado().getAutor());
        lblISBN.setText(controladorBusquedaLibro.getLibroSeleccionado().getIsbn());
        String cant = String.valueOf(controladorBusquedaLibro.getLibroSeleccionado().getCantidadCopias());
        lblCantidad.setText(cant);

        ObservableList<String> observableUbicaciones = FXCollections.observableArrayList(
                controladorBusquedaLibro.combinarPisoConClasificacion(controladorBusquedaLibro.getLibroSeleccionado().getLibrosFisicosDisponibles()));
        cmbBoxCopias.setItems(observableUbicaciones);
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
