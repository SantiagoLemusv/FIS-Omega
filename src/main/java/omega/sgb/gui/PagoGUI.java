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
    Label txtValorTotal;
    @FXML
    Label txtMetododePago;
    @FXML
    ListView<String> ListMultas;
    @FXML
    ComboBox<String> cbxMetodoPago;
    @FXML
    Label lblEstadoPago;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEstadoPago.setVisible(false);
        txtNombre.setText(SingletonControladores.getUsuarioActual().getNombre());
        txtCedula.setText(String.valueOf(SingletonControladores.getUsuarioActual().getCedula()));
        txtTipoCuenta.setText("Lector");
        controladorEstadoUsuario.traerPrestamos(SingletonControladores.getUsuarioActual());
        controladorEstadoUsuario.traerTarjetas();
        ObservableList <String> tarjetasString = FXCollections.observableArrayList(
                controladorEstadoUsuario.listaStringTarjeta(SingletonControladores.getUsuarioActual().getTarjetas()));
        ObservableList<String> observableMultas = FXCollections.observableArrayList(
                controladorEstadoUsuario.listaStringMulta(SingletonControladores.getUsuarioActual().getPrestamos()));
        ListMultas.setItems(observableMultas);
        cbxMetodoPago.setItems(tarjetasString);
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

    public void mBtnConfirmarPago(ActionEvent event) throws IOException, SQLException {
        if(cbxMetodoPago.getSelectionModel().isEmpty() && ListMultas.getSelectionModel().isEmpty()){
            lblEstadoPago.setText("Debe seleccionar alguna multa y método de pago");
            lblEstadoPago.setVisible(true);
        }
        else if(cbxMetodoPago.getSelectionModel().isEmpty()){
            lblEstadoPago.setText("Seleccione un método de pago");
            lblEstadoPago.setVisible(true);
        }else if(ListMultas.getSelectionModel().isEmpty()){
            lblEstadoPago.setText("Seleccione una multa a pagar");
            lblEstadoPago.setVisible(true);
        }else{
            String datoMetodoPago = (String) cbxMetodoPago.getValue();
            String numeroTarjeta = controladorPago.numeroTarjetaMulta(datoMetodoPago);
            String datoMulta = ListMultas.getSelectionModel().getSelectedItem();
            String nombreLibro = controladorPago.nombreLibroMulta(datoMulta);

            if(controladorPago.validarDatos(numeroTarjeta, nombreLibro)){
                controladorPago.setTarjetaSeleccionada(numeroTarjeta);
                controladorPago.setPrestamoSeleccionado(nombreLibro);
                txtValorTotal.setText(String.valueOf(controladorPago.calcularPrecioMulta()));
                txtMetododePago.setText(controladorPago.metodoPago());
                controladorPago.pagarMulta();
            }
            SingletonPantallas.toEstadoLectorViewSingleton(event);
        }
    }
    public void mBtnNuevoMetodoPago(ActionEvent event) throws IOException{
        SingletonPantallas.toAgregarMetodoPagoViewSingleton(event);
    }
}
