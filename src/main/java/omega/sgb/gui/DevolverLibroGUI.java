package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import omega.sgb.control.ControladorPrestamo;
import omega.sgb.dominio.Prestamo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DevolverLibroGUI implements Initializable {
    private ControladorPrestamo controladorPrestamo;
    private ControladorEstadoUsuario controladorEstadoUsuario;
    public DevolverLibroGUI() throws SQLException {}
    public DevolverLibroGUI(ControladorPrestamo controladorPrestamo, ControladorEstadoUsuario controladorEstadoUsuario) throws SQLException {
        this.controladorPrestamo = controladorPrestamo;
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
        lblListaPrestamos.setVisible(false);
        listViewLibrosPrestados.setVisible(false);
    }
    public void mBtnConsultarLector(ActionEvent event) throws SQLException {
        Integer numCedula = Integer.parseInt(txtFieldCedulaLector.getText());
        controladorPrestamo.setLectorActual(numCedula);
        controladorEstadoUsuario.traerPrestamos(controladorPrestamo.getLectorActual());
        if(controladorPrestamo.getLectorActual().getPrestamos().isEmpty()){
            lblListaPrestamos.setText("Este usuario no tiene prestamos asignados");
            lblListaPrestamos.setVisible(true);
        }else{
            lblListaPrestamos.setText("Lista de prestamos");
            lblListaPrestamos.setVisible(true);
            ObservableList<Prestamo> obsStringPrestamos = FXCollections.observableArrayList(
                    controladorPrestamo.getLectorActual().getPrestamos());
            listViewLibrosPrestados.setItems(obsStringPrestamos);
            listViewLibrosPrestados.setVisible(true);
        }
    }

    public void mBtnDevolver(ActionEvent event){

    }
}
