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
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorLogIn;
import omega.sgb.control.ControladorPrestamo;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.Multa;
import omega.sgb.dominio.PersonaLector;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ValidarLectorGUI implements Initializable {
    private ControladorPrestamo controladorPrestamo = SingletonControladores.getInstanceControladorPrestamo();
    public ValidarLectorGUI() throws SQLException {}

    @FXML
    TextField txtCedulaLector;
    @FXML
    Button btnConsultarLector;
    @FXML
    Label lblEstadoLector;
    @FXML
    Label lblConflictosLector;
    @FXML
    Button btnConfirmarPrestamo;
    @FXML
    Label lblEstadoPrestamo;
    @FXML
    Button btnVolver;
    @FXML
    Label lblMulta;
    @FXML
    ListView listViewConflictos;
    @FXML
    ListView listViewLibros;
    @FXML
    Label lblNombreU;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNombreU.setText(SingletonControladores.getUsuarioActual().getNombre());
        ObservableList<LibroFisico> obsCarrito = FXCollections.observableArrayList(SingletonControladores.getUsuarioActual().getCarrito());
        listViewLibros.setItems(obsCarrito);
    }
    public void mConsultarLector(ActionEvent event) throws SQLException {
        String cedulaLector = txtCedulaLector.getText();
        Integer numCedula = Integer.parseInt(cedulaLector);
        lblMulta.setVisible(false);
        listViewConflictos.setVisible(false);
        controladorPrestamo.setLectorActual(numCedula);
        if(controladorPrestamo.consultarLector()){
            ObservableList<String> obsMultas = FXCollections.observableArrayList(controladorPrestamo.getControladorEstadoUsuario().listaStringMulta(controladorPrestamo.getLectorActual().getPrestamos()));
            lblEstadoLector.setText("El lector tiene conflictos");
            lblMulta.setVisible(true);
            listViewConflictos.setVisible(true);
            listViewConflictos.setItems(obsMultas);

        }else{
            lblEstadoLector.setText("Estado lector correcto");
        }
    }
    public void mConfirmarPrestamo(ActionEvent event) throws SQLException {
        if(lblEstadoLector.getText() == "Estado lector correcto"){
            List<LibroFisico> listalibros = SingletonControladores.getUsuarioActual().getCarrito();
            controladorPrestamo.confirmarPrestamo(listalibros);
            listalibros.clear();

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

    public void mBtnDevolverLibro(ActionEvent event) throws IOException{
        SingletonPantallas.toDevolverLibroViewSingleton(event);
    }
}
