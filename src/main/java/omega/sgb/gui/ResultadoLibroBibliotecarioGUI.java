package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorBusquedaLibro;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ResultadoLibroBibliotecarioGUI implements Initializable {
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
    @FXML
    Label lblNombreU;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNombreU.setText(SingletonControladores.getUsuarioActual().getNombre());
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
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public ResultadoLibroBibliotecarioGUI() throws SQLException {
    }
    public ResultadoLibroBibliotecarioGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
    @FXML
    void initialize(){

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
