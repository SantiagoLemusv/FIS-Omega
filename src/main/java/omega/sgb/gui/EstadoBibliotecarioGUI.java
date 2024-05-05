package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorBusquedaLibro;
import omega.sgb.control.ControladorEstadoUsuario;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EstadoBibliotecarioGUI implements Initializable {
    private ControladorEstadoUsuario controladorEstadoUsuario = SingletonControladores.getInstanceControladorEstadoUsuario();
    public EstadoBibliotecarioGUI() throws SQLException {}
    public EstadoBibliotecarioGUI(ControladorEstadoUsuario controladorEstadoUsuario) throws SQLException {
        this.controladorEstadoUsuario = controladorEstadoUsuario;
    }

    @FXML
    Label txtCedula;
    @FXML
    Label txtTipoCuenta;
    @FXML
    Label txtNombre;
    @FXML
    Label txtNombreCompleto;
    @FXML
    ListView<String> ListPrestamos;
    @FXML
    ListView<String> ListMultas;
    @FXML
    Button btnMiPerfil;
    @FXML
    Button btnBuscar;
    @FXML
    Button btnCarrito;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtNombre.setText(SingletonControladores.getUsuarioActual().getNombre());

        txtCedula.setText(String.valueOf(SingletonControladores.getUsuarioActual().getCedula()));

        if(SingletonControladores.getUsuarioActual().getClass().toString().equals("PerosnaBibliotecario")){
            txtTipoCuenta.setText("Bibliotecario");
        }else {
            txtTipoCuenta.setText("Lector");
        }
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
