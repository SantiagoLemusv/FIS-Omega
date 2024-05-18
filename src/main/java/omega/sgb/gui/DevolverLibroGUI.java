package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorActualizarApp;
import omega.sgb.control.ControladorEstadoUsuario;
import omega.sgb.control.ControladorPrestamo;
import omega.sgb.dominio.Prestamo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DevolverLibroGUI implements Initializable {
    private ControladorPrestamo controladorPrestamo = SingletonControladores.getInstanceControladorPrestamo();
    private ControladorEstadoUsuario controladorEstadoUsuario = SingletonControladores.getInstanceControladorEstadoUsuario();
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

            List<Prestamo> prestamosSinMulta = new ArrayList<>();
            for(Prestamo p : controladorPrestamo.getLectorActual().getPrestamos()){
                if(p.getMulta() == null){
                    prestamosSinMulta.add(p);
                    System.out.println("prestamo sin multaaaaaaaa");
                    System.out.println(p);
                }
            }
            ObservableList<Prestamo> obsStringPrestamos = FXCollections.observableArrayList(prestamosSinMulta);
            listViewLibrosPrestados.setItems(obsStringPrestamos);
            listViewLibrosPrestados.setVisible(true);
        }
    }

    public void mBtnDevolver() throws SQLException {
        controladorPrestamo.actualizarEstadoPrestamo((Prestamo) listViewLibrosPrestados.getSelectionModel().getSelectedItem());
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
