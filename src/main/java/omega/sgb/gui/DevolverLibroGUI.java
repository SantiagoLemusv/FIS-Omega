package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorActualizarApp;
import omega.sgb.control.ControladorEstadoUsuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DevolverLibroGUI implements Initializable {
    private ControladorActualizarApp controladorActualizarApp = SingletonControladores.getInstanceControladorActualizarApp();
    private ControladorEstadoUsuario controladorEstadoUsuario = SingletonControladores.getInstanceControladorEstadoUsuario();
    public EstadoLectorGUI() throws SQLException {}
    public EstadoLectorGUI(ControladorEstadoUsuario controladorEstadoUsuario) throws SQLException {
        this.controladorEstadoUsuario = controladorEstadoUsuario;
    }

    @FXML
    TextField txtFieldCedulaLector;
    @FXML
    Button btnConsultarLector;
    @FXML
    ListView listViewLibrosPrestados;
    @FXML
    Button btnDevolverLibro;
    @FXML
    Label lblListaPrestamos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }
    public void mBtnConsultarLector(ActionEvent event){

    }
}
