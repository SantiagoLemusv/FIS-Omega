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
    @FXML
    TextField txtFieldTituloLibro;
    @FXML
    Label lblAgregado;
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public LibroSeleccionadoBibliotecarioGUI() throws SQLException {
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

    public void mBtnAgregar(){
        boolean agregado = false;
        if(lblEstadoLibro.getText().equals("Libro disponible")){
            agregado= controladorBusquedaLibro.agregarLibro();
        }

        if(agregado){
            lblAgregado.setText("Libro agregado");
        }else
            lblAgregado.setText("El libro ya fue agregado");
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

    public void mBtnLupa(ActionEvent event) throws IOException {
        controladorBusquedaLibro.setTituloLibroBusquedaGrande(txtFieldTituloLibro.getText());
        SingletonPantallas.toResultadosBibliotecarioViewSingleton(event);
    }

    public void mBtnDevolverLibro(ActionEvent event) throws IOException{
        SingletonPantallas.toDevolverLibroViewSingleton(event);
    }
}
