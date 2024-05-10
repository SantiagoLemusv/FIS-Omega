package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorEstadoUsuario;
import omega.sgb.dominio.LibroFisico;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EstadoLectorGUI implements Initializable {
    private ControladorEstadoUsuario controladorEstadoUsuario = SingletonControladores.getInstanceControladorEstadoUsuario();
    public EstadoLectorGUI() throws SQLException {}
    public EstadoLectorGUI(ControladorEstadoUsuario controladorEstadoUsuario) throws SQLException {
        this.controladorEstadoUsuario = controladorEstadoUsuario;
    }
    @FXML
    Label txtCedula;
    @FXML
    Label txtTipoCuenta;
    @FXML
    Label txtNombre;
    @FXML
    ListView<String> listViewPrestamos;
    @FXML
    ListView<String> listViewMultas;
    @FXML
    Label lblLibroSeleccionado;
    @FXML
    ImageView imageViewLibroReservado;
    @FXML
    Label lblTituloLibro;
    @FXML
    Label lblAutorLibro;
    @FXML
    Label lblEstadoLibro;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(controladorEstadoUsuario.traerLibroReservado() != null){
            lblLibroSeleccionado.setText("Tiene un libro seleccionado");
            LibroFisico libroReservado = controladorEstadoUsuario.traerLibroReservado();
            imageViewLibroReservado.setImage(libroReservado.getLibroVirtual().getImagenLibro().getImage());
            lblTituloLibro.setText(libroReservado.getLibroVirtual().getTitulo());
            lblAutorLibro.setText(libroReservado.getLibroVirtual().getAutor());
            if(libroReservado.getEstadoLibroFisicoId() == 3) {
                lblEstadoLibro.setText("Libro reservado");
            }
        }else{
            lblLibroSeleccionado.setText("No tiene libros reservados");
        }
        txtNombre.setText(SingletonControladores.getUsuarioActual().getNombre());
        txtCedula.setText(String.valueOf(SingletonControladores.getUsuarioActual().getCedula()));
        txtTipoCuenta.setText("Lector");
        controladorEstadoUsuario.traerPrestamos();
        ObservableList<String> observablePrestamos = FXCollections.observableArrayList(
                controladorEstadoUsuario.listaStringPrestamos(SingletonControladores.getUsuarioActual().getPrestamos()));

        ObservableList<String> observableMultas = FXCollections.observableArrayList(
                controladorEstadoUsuario.listaStringMulta(SingletonControladores.getUsuarioActual().getPrestamos()));

        listViewPrestamos.setItems(observablePrestamos);
        listViewMultas.setItems(observableMultas);

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
    public void mBtnIrPago(ActionEvent event) throws IOException {
        SingletonPantallas.toPagoViewSingleton(event);
    }

}
