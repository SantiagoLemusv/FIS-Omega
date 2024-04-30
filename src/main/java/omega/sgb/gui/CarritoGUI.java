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
    private ControladorCarrito controladorCarrito = SingletonControladores.getInstanceCarrito();
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

    public void agregarImagenPrueba(ActionEvent event) throws SQLException, IOException {
        Blob imagenBlob = controladorCarrito.getImagenLibroById();

        imagenPrueba.setImage(SingletonControladores.getImagenProcesar().blobToImage(imagenBlob));

    }

}
