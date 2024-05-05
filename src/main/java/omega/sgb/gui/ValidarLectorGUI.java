package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorLogIn;
import omega.sgb.control.ControladorPrestamo;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.PersonaLector;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValidarLectorGUI {
    private ControladorPrestamo controladorPrestamo = SingletonControladores.getInstanceControladorPrestamo();
    private PersonaLector lectorActual = new PersonaLector();
    public ValidarLectorGUI() throws SQLException {}
    public ValidarLectorGUI(ControladorPrestamo controladorPrestamo) throws SQLException {
        this.controladorPrestamo = controladorPrestamo;
    }

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
    ListView listViewLibros;

    public void mConsultarLector(ActionEvent event) throws SQLException {
        String cedulaLector = txtCedulaLector.getText();
        Integer numCedula = Integer.parseInt(cedulaLector);
        lectorActual.setCedula(numCedula);
        if(controladorPrestamo.consultarLector(lectorActual)){
            lblEstadoLector.setText("El lector tiene conflictos");
        }else{
            lblEstadoLector.setText("Estado lector correcto");
            lectorActual.setId(controladorPrestamo.getIdLectorActual(numCedula));
        }
    }
    public void mConfirmarPrestamo(ActionEvent event) throws SQLException {
        if(lblEstadoLector.getText() == "Estado lector correcto"){
            LibroFisico libroPrueba = controladorPrestamo.agregarLibroCarrito();//cambiar, esto es quemado
            List<LibroFisico> listaPrueba = new ArrayList<LibroFisico>();
            listaPrueba.add(libroPrueba);
            controladorPrestamo.confirmarPrestamo(listaPrueba, lectorActual);

        }
    }
    public void mVolverCarrito(ActionEvent event) throws IOException {
        SingletonPantallas.toCarritoViewSingleton(event);
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
