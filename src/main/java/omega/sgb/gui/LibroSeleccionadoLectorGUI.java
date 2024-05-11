package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorBusquedaLibro;
import omega.sgb.dominio.LibroFisico;

import java.io.IOException;
import java.sql.SQLException;

public class LibroSeleccionadoLectorGUI {
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
    Label lblEstadoReserva;
    @FXML
    TextField txtFieldTituloLibro;
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public LibroSeleccionadoLectorGUI() throws SQLException {
    }
    public LibroSeleccionadoLectorGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
    @FXML
    void initialize(){
        imageViewLibroSeleccionado.setImage(controladorBusquedaLibro.getLibroVirtualSeleccionado().getImagenLibro().getImage());
        lblTitulo.setText(controladorBusquedaLibro.getLibroVirtualSeleccionado().getTitulo());
        lblAutor.setText(controladorBusquedaLibro.getLibroVirtualSeleccionado().getAutor());
        lblISBN.setText(controladorBusquedaLibro.getLibroVirtualSeleccionado().getIsbn());
        String cant = String.valueOf(controladorBusquedaLibro.getLibroVirtualSeleccionado().getCantidadCopias());
        lblCantidad.setText(cant);
        ObservableList<String> observableUbicaciones = FXCollections.observableArrayList(
                controladorBusquedaLibro.combinarPisoConClasificacion(controladorBusquedaLibro.getLibroVirtualSeleccionado().getLibrosFisicosTotales()));
        cmbBoxCopias.setItems(observableUbicaciones);
    }
    public void mCmbBox(ActionEvent event){
        String ubicacionCompleta = (String) cmbBoxCopias.getValue();
        String[] splitStrings = ubicacionCompleta.split(",");
        String valueAfterComma = splitStrings[1];

        controladorBusquedaLibro.setLibroFisicoSeleccionado(valueAfterComma);
        if(controladorBusquedaLibro.getLibroFisicoSeleccionado().getEstadoLibroFisicoId().equals(1)){
            lblEstadoLibro.setText("Libro disponible");
        }else if (controladorBusquedaLibro.getLibroFisicoSeleccionado().getEstadoLibroFisicoId().equals(2)) {
            lblEstadoLibro.setText("Libro prestado");
        }else if (controladorBusquedaLibro.getLibroFisicoSeleccionado().getEstadoLibroFisicoId().equals(3)) {
            lblEstadoLibro.setText("Libro reservado");
        }
    }

    public void mBtnReservar(ActionEvent event) throws IOException, SQLException {
        if(controladorBusquedaLibro.traerLibroReservado() != null){
            lblEstadoReserva.setText("Ya tiene un libro reservado");
            System.out.println("ya tiene libro reservado");

        }else if(controladorBusquedaLibro.getLibroFisicoSeleccionado().getEstadoLibroFisicoId().equals(1)){
            lblEstadoReserva.setText("Reserva realizada exitosamente");
            System.out.println("llama a reservar libro");
            controladorBusquedaLibro.reservarLibro(controladorBusquedaLibro.getLibroFisicoSeleccionado());
        }else if (controladorBusquedaLibro.getLibroFisicoSeleccionado().getEstadoLibroFisicoId().equals(2)) {
            lblEstadoReserva.setText("Este libro no está disponible");
        }else if (controladorBusquedaLibro.getLibroFisicoSeleccionado().getEstadoLibroFisicoId().equals(3)) {
            lblEstadoReserva.setText("Este libro ya está reservado");
        }
    }
    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoBibliotecarioViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroLectorViewSingleton(event);
    }
    public void mBtnCerrarSesion(ActionEvent event) throws IOException {
        SingletonPantallas.toLogInViewSingleton(event);
    }

    public void mBtnLupa(ActionEvent event) throws IOException {
        controladorBusquedaLibro.setTituloLibroBusquedaGrande(txtFieldTituloLibro.getText());
        SingletonPantallas.toResultadosLectorViewSingleton(event);
    }
}
