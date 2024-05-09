package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorAgregarMetodoPago;
import omega.sgb.control.ControladorPago;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregarMetodoPagoGUI implements Initializable {

    //Campos ingresados por el usuario
    @FXML
    TextField txtNumeroTarjeta;
    @FXML
    TextField txtCVV;
    @FXML
    TextField txtFechaVencimiento;
    @FXML
    TextField txtNombreTitular;
    @FXML
    TextField txtNombre;
    @FXML
    ComboBox cbxTipoTarjeta;

    //Campos de error
    @FXML
    Label lblErrorNumeroTarjeta;
    @FXML
    Label lblErrorCVV;
    @FXML
    Label lblErrorFechaVencimiento;
    @FXML
    Label lblErrorNombreTitular;
    @FXML
    Label lblErrorNombre;
    @FXML
    Label lblErrorTipoTarjeta;

    //inicializables
    @FXML
    Label lblNombreU;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNombreU.setText(SingletonControladores.getUsuarioActual().getNombre());
        cbxTipoTarjeta.getItems();
    }
    private ControladorPago controladorPago = SingletonControladores.getInstanceControladorPago();
    public AgregarMetodoPagoGUI() throws SQLException {}
    public AgregarMetodoPagoGUI(ControladorPago controladorPago) throws SQLException {
        this.controladorPago = controladorPago;
    }

    public boolean agregarTarjeta(){
        if (validarDatos()){
            return true;
        }
        return false;
    }

    public boolean validarDatos(){

        String Numetotarjeta = txtNumeroTarjeta.getText();
        String CVV = txtCVV.getText();
        String FechaVencimiento = txtFechaVencimiento.getText();
        String NombreTitular = txtNombreTitular.getText();
        String Nombre = txtNombre.getText();



        if(validacionREGEX()) {
            System.out.println("TRUE");
            return true;
        }
        return false;
    }

    public boolean validacionREGEX(){
        //if(validarNumeroTarjeta(txtNumeroTarjeta.getText())&&validarCVV(txtCVV.getText())&&validarFecha(txtFechaVencimiento.getText())&&validarTitular(txtNombreTitular.getText())){
        if(validarCVV(txtCVV.getText())){
            return true;
        }
            return false;
    }

    public boolean validarNumeroTarjeta(String NumeroTarjeta){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(NumeroTarjeta);
        if(NumeroTarjeta.isEmpty()){
            lblErrorNumeroTarjeta.setText("No puede dejar este campo vacio");
            return false;
        }else if (!matcher.matches()) {
            lblErrorNumeroTarjeta.setText("Ingrese solo numeros");
            return false;
        }
        else if (NumeroTarjeta.length()>16) {
            lblErrorNumeroTarjeta.setText("El numero no puede tener mas de 16 digitos");
            return false;
        } else if (NumeroTarjeta.length()<16) {
            lblErrorNumeroTarjeta.setText("El numero no puede tener menos de 16 digitos");
            return false;
        } else if (matcher.matches()&&NumeroTarjeta.length()==16){
            lblErrorNumeroTarjeta.setText("");
            return true;
        }
        return false;
    }
    public boolean validarFecha(String Fecha){
        Pattern pattern = Pattern.compile("^(0[1-9]|1[0-2])\\/?([0-9]{2})$\n");
        Matcher matcher = pattern.matcher(Fecha);
        if(Fecha.isEmpty()){
            lblErrorFechaVencimiento.setText("No puede dejar este campo vacio");
            return false;
        }else if (!matcher.matches()) {
            lblErrorFechaVencimiento.setText("Ingrese una fecha");
            return false;
        }else if (matcher.matches()){
            lblErrorFechaVencimiento.setText("");
            return true;
        }
        return false;
    }
    public boolean validarCVV(String CVV){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(CVV);
        if(CVV.isEmpty()){
            lblErrorCVV.setText("No puede dejar este campo vacio");
            return false;
        }else if (!matcher.matches()) {
            lblErrorCVV.setText("Ingrese solo numeros");
            return false;
        } else if (CVV.length()!=3) {
            lblErrorCVV.setText("Ingrese 3 digitos");
            return false;
        } else if (matcher.matches()&&CVV.length()==3){
            lblErrorCVV.setText("");
            return true;
        }
        return false;
    }
    public boolean validarTitular(String Titular){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        Matcher matcher = pattern.matcher(Titular);
        if(Titular.isEmpty()){
            lblErrorNombreTitular.setText("No puede dejar este campo vacio");
            return false;
        }else if (!matcher.matches()) {
            lblErrorNombreTitular.setText("Ingrese solo letras");
            return false;
        } else if (matcher.matches()) {
            lblErrorNombreTitular.setText("");
            return true;
        }
        return false;
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
    public void mBtnAgregarNuevoMetodoPago(ActionEvent event) throws IOException{
        if(agregarTarjeta()) {
            SingletonPantallas.toPagoViewSingleton(event);
        }
    }
    public void mBtnCancelar(ActionEvent event) throws IOException{
        SingletonPantallas.toPagoViewSingleton(event);
    }
}
