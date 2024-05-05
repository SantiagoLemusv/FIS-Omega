package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorCarrito;
import omega.sgb.control.ControladorLogIn;
import omega.sgb.integracion.Imagen;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class CarritoGUI {
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

