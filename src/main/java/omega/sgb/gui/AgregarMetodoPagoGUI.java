package omega.sgb.gui;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregarMetodoPagoGUI implements Initializable {

    ControladorAgregarMetodoPago controlador = SingletonControladores.getInstanceControladorAgregarMetodoPago();

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
    ComboBox<String> cbxTipoTarjeta;

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

    public AgregarMetodoPagoGUI() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNombreU.setText(SingletonControladores.getUsuarioActual().getNombre());
        cbxTipoTarjeta.setItems(FXCollections.observableArrayList("Debito", "Credito"));
    }

    public boolean agregarTarjeta() throws SQLException {
        if (validacionREGEX()) {
            System.out.println("TRUE");
            return true;
        }
        return false;
    }

    //Validacion de valores ingresados por el usuario
    public boolean validacionREGEX() {
        if (validarNumeroTarjeta(txtNumeroTarjeta.getText()) &&
                validarCVV(txtCVV.getText()) &&
                validarFecha(txtFechaVencimiento.getText()) &&
                validarTitular(txtNombreTitular.getText()) &&
                validarNombreE(txtNombre.getText()) &&
                validarTipo()) {
            return true;
        }
        return false;
    }

    public boolean validarNumeroTarjeta(String NumeroTarjeta) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(NumeroTarjeta);
        if (NumeroTarjeta.isEmpty()) {
            lblErrorNumeroTarjeta.setText("No puede dejar este campo vacio");
            return false;
        } else if (!matcher.matches()) {
            lblErrorNumeroTarjeta.setText("Ingrese solo numeros");
            return false;
        } else if (NumeroTarjeta.length() > 16) {
            lblErrorNumeroTarjeta.setText("El numero no puede tener mas de 16 digitos");
            return false;
        } else if (NumeroTarjeta.length() < 16) {
            lblErrorNumeroTarjeta.setText("El numero no puede tener menos de 16 digitos");
            return false;
        } else if (matcher.matches() && NumeroTarjeta.length() == 16) {
            lblErrorNumeroTarjeta.setText("");
            return true;
        }
        return false;
    }

    public boolean validarCVV(String CVV) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(CVV);
        if (CVV.isEmpty()) {
            lblErrorCVV.setText("No puede dejar este campo vacio");
            return false;
        } else if (!matcher.matches()) {
            lblErrorCVV.setText("Ingrese solo numeros");
            return false;
        } else if (CVV.length() != 3) {
            lblErrorCVV.setText("Ingrese 3 digitos");
            return false;
        } else if (matcher.matches() && CVV.length() == 3) {
            lblErrorCVV.setText("");
            return true;
        }
        return false;
    }

    public boolean validarTitular(String Titular) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        Matcher matcher = pattern.matcher(Titular);
        if (Titular.isEmpty()) {
            lblErrorNombreTitular.setText("No puede dejar este campo vacio");
            return false;
        } else if (!matcher.matches()) {
            lblErrorNombreTitular.setText("Ingrese solo letras");
            return false;
        } else if (matcher.matches()) {
            lblErrorNombreTitular.setText("");
            return true;
        }
        return false;
    }

    public boolean validarNombreE(String Titular) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        Matcher matcher = pattern.matcher(Titular);
        if (Titular.isEmpty()) {
            lblErrorNombre.setText("No puede dejar este campo vacio");
            return false;
        } else if (!matcher.matches()) {
            lblErrorNombre.setText("Ingrese solo letras");
            return false;
        } else if (matcher.matches()) {
            lblErrorNombre.setText("");
            return true;
        }
        return false;
    }

    public boolean validarTipo() {
        String tipo = cbxTipoTarjeta.getValue();
        System.out.println("\n" + tipo);
        if (tipo == null) {
            lblErrorTipoTarjeta.setText("Seleccione algun tipo");
            return false;
        } else {
            lblErrorTipoTarjeta.setText("");
            return true;
        }
    }

    public boolean validarFecha(String Fecha){

    Pattern pattern = Pattern.compile("(0[1-9]|1[0-2])/(\\d{2})");
    Matcher matcher = pattern.matcher(Fecha);
        if(Fecha.isEmpty())

    {
        lblErrorFechaVencimiento.setText("No puede dejar este campo vacio");
        return false;
    }else if(!matcher.matches())

    {
        lblErrorFechaVencimiento.setText("Ingrese una fecha valida");
        return false;
    }else if(matcher.matches())

    {


        lblErrorFechaVencimiento.setText("");
        return true;
    }
        return false;
}
    //Botones
    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoLectorViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroLectorViewSingleton(event);
    }
    public void mBtnCerrarSesion(ActionEvent event) throws IOException {
        SingletonPantallas.toLogInViewSingleton(event);
    }
    public void mBtnAgregarNuevoMetodoPago(ActionEvent event) throws IOException, SQLException, ParseException {
        String Numetotarjeta = txtNumeroTarjeta.getText();
        String CVV = txtCVV.getText();
        String FechaVencimiento = txtFechaVencimiento.getText();
        String NombreTitular = txtNombreTitular.getText();
        String NombreEntidad = txtNombre.getText();
        String Tipo = cbxTipoTarjeta.getValue();
        if(agregarTarjeta()) {
            controlador.agregarMetodoPago(Numetotarjeta,FechaVencimiento,NombreEntidad,Tipo,NombreTitular,CVV);
            SingletonPantallas.toPagoViewSingleton(event);
        }
    }
    public void mBtnCancelar(ActionEvent event) throws IOException{
        SingletonPantallas.toPagoViewSingleton(event);}
}