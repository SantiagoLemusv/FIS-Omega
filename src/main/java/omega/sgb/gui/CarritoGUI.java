package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorCarrito;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CarritoGUI implements Initializable {
    @FXML
    Label lblNombreU;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNombreU.setText(SingletonControladores.getUsuarioActual().getNombre());
    }
    private ControladorCarrito controladorCarrito = SingletonControladores.getInstanceControladorCarrito();
    public CarritoGUI() throws SQLException {}
    public CarritoGUI(ControladorCarrito controladorCarrito) throws SQLException {
        this.controladorCarrito = controladorCarrito;
    }

    @FXML
    Button btnSolicitar;
    @FXML
    ImageView imagenPrueba;

    public void mBtnFinalizarPrestamo(ActionEvent event) throws IOException {
        SingletonPantallas.toValidarLectorViewSingleton(event);
    }

    /*public void agregarImagenPrueba(ActionEvent event) throws SQLException, IOException {
        Blob imagenBlob = controladorCarrito.getImagenLibroById();

        imagenPrueba.setImage(SingletonControladores.getImagenProcesar().blobToImage(imagenBlob));

    }*/

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
        public void mBtnEliminarLibro(ActionEvent event) throws IOException {
            //al libro seleccionado con el click lo elimina
        }
    }

