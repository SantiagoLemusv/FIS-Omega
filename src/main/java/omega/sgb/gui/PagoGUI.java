package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorEstadoUsuario;
import omega.sgb.control.ControladorPago;
import omega.sgb.dominio.Tarjeta;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PagoGUI implements Initializable {
    private ControladorPago controladorPago = SingletonControladores.getInstanceControladorPago();
    private ControladorEstadoUsuario controladorEstadoUsuario = SingletonControladores.getInstanceControladorEstadoUsuario();

    public PagoGUI() throws SQLException {}
    public PagoGUI(ControladorPago controladorPago) throws SQLException {
        this.controladorPago = controladorPago;
    }
    @FXML
    Label txtCedula;
    @FXML
    Label txtTipoCuenta;
    @FXML
    Label txtNombre;
    @FXML
    ListView<String> ListPrestamos;
    @FXML
    ListView<String> ListMultas;
    @FXML
    ComboBox<String> cbxMetodoPago;
    List<String> Tarjetas;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtNombre.setText(SingletonControladores.getUsuarioActual().getNombre());

        txtCedula.setText(String.valueOf(SingletonControladores.getUsuarioActual().getCedula()));

        if(SingletonControladores.getUsuarioActual().getClass().toString().equals("PerosnaBibliotecario")){
            txtTipoCuenta.setText("Bibliotecario");
        }else {
            txtTipoCuenta.setText("Lector");
        }


        controladorEstadoUsuario.traerTarjetas();

        ObservableList <String> tarjetasString = FXCollections.observableArrayList(
                controladorEstadoUsuario.listaStringTarjeta(SingletonControladores.getUsuarioActual().getTarjetas()));
        cbxMetodoPago.setItems(tarjetasString);
        /*
        for(Prestamo p : SingletonControladores.getUsuarioActual().getPrestamos()){
            if(p.getMulta() == null){
                prestamosSinMulta.add(p);
            }
        }
         */
    }

    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoLectorViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroLectorViewSingleton(event);
    }
    public void mBtnCerrarSesion(ActionEvent event) throws IOException {
        SingletonPantallas.toLogInViewSingleton(event);
    }
    public void mBtnIrPago(ActionEvent event) throws IOException {
        SingletonPantallas.toPagoViewSingleton(event);
    }
    public void mBtnConfirmarPago(ActionEvent event) throws IOException{
        /*
        Codigo que se asegure que se selecciona un metodo de pago, que hay multas por pagar etc.
         */
        SingletonPantallas.toEstadoLectorViewSingleton(event);
    }
    public void mBtnNuevoMetodoPago(ActionEvent event) throws IOException{
        SingletonPantallas.toAgregarMetodoPagoViewSingleton(event);
    }
}
