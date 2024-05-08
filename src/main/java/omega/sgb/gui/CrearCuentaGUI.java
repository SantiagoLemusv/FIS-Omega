package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorLogIn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearCuentaGUI {
    private ControladorLogIn controladorLogIn = SingletonControladores.getInstanceControladorLogIn();
    public CrearCuentaGUI() throws SQLException {}
    public CrearCuentaGUI(ControladorLogIn controladorLogIn) throws SQLException {
        this.controladorLogIn = controladorLogIn;
    }

    @FXML
    Button btnCrearCuenta;
    @FXML
    TextField txtNombreCompleto;
    @FXML
    TextField txtCedula;
    @FXML
    PasswordField txtContrasenaConfirmar;
    @FXML
    PasswordField txtContrasena;
    @FXML
    Label lblAutenticacion;
    @FXML
    Label lblErrorNombreCompleto;

    @FXML
    Label lblErrorCedula;

    @FXML
    Label lblErrorContrasena;

    @FXML
    Label lblErrorConfirmarContrasena;


    public String getTxtCedula() {
        return txtCedula.getText();
    }

    public String getTxtContrasena() {
        return txtContrasena.getText();
    }

    public String getTxtNombreCompleto() {
        return txtNombreCompleto.getText();
    }

    public String getTxtContrasenaConfirmar() {
        return txtContrasenaConfirmar.getText();
    }

    public void setLblAutenticacion(String texto) {
        lblAutenticacion.setText(texto);
    }

    public void mBtnLoginView(ActionEvent event) throws IOException {
        SingletonPantallas.toLogInViewSingleton(event);
    }

    private boolean validarCampos (){
        String cedula = txtCedula.getText();
        String contrasena = txtContrasena.getText();
        String nombreCompleto = txtNombreCompleto.getText();
        String contrasenaConfirmar = txtContrasenaConfirmar.getText();

        //patrones de verificacion
        Pattern patternContrasena = Pattern.compile("^(?=.*[A-Z])(?=.*\\d).*$");
        Pattern patternNumerico = Pattern.compile("^[0-9]*$");
        Pattern patternAlfabetico = Pattern.compile("^[a-zA-Z ]*$");
        Matcher matcherContrasena = patternContrasena.matcher("");
        Matcher matcherNumerico = patternNumerico.matcher("");
        Matcher matcherAlfabetico = patternAlfabetico.matcher("");

        //Limpiar campos y variables necesarias
        lblErrorNombreCompleto.setText("");
        lblErrorCedula.setText("");
        lblErrorContrasena.setText("");
        lblErrorConfirmarContrasena.setText("");
        boolean cumple=true;

        //Revisa campos vacios y patrones
        //Revison nombre
        if(nombreCompleto.isEmpty()){
            cumple= false;
            lblErrorNombreCompleto.setText("Por favor llene este campo");
        } else{
            matcherAlfabetico.reset(nombreCompleto);
        } if (!matcherAlfabetico.matches()) {
            cumple= false;
            lblErrorNombreCompleto.setText("El nombre no debe contener caracteres especiales ni numeros");
        }

        //Revision cedula
        if(cedula.isEmpty()){
            cumple= false;
            lblErrorCedula.setText("Por favor llene este campo");
        } else{
            matcherNumerico.reset(cedula);
        } if (!matcherNumerico.matches()) {
            cumple= false;
            lblErrorCedula.setText("El campo solo debe contener números");
        }

        //Revision contrasena
        if(contrasena.isEmpty()){
            cumple= false;
            lblErrorContrasena.setText("Por favor llene este campo");
        } else{
            if (contrasena.length()<8) {
                cumple= false;
                lblErrorContrasena.setText("La contraseña debe contener al menos 8 caracteres");
            } else{
                matcherContrasena.reset(contrasena);
                if (!matcherContrasena.matches()) {
                    cumple = false;
                    lblErrorContrasena.setText("La contraseña debe contener mayusculas y números");
                }
            }
        }

        //Revision confirmarcontrasena
        if (contrasenaConfirmar.isEmpty()) {
            cumple=false;
            lblErrorConfirmarContrasena.setText("Por favor llene este campo");
        } else if (!contrasena.equals(contrasenaConfirmar)) {
            cumple=false;
            lblErrorConfirmarContrasena.setText("La contraseña no coincide");
        }

        return cumple;
    }
    public void mBtnCrearCuenta(ActionEvent event) throws SQLException, IOException {
        String cedula = txtCedula.getText();
        String contrasena = txtContrasena.getText();
        String nombreCompleto = txtNombreCompleto.getText();
        String contrasenaConfirmar = txtContrasenaConfirmar.getText();


         //Confirmacion del registro
        if(validarCampos()){
            controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto);
            lblAutenticacion.setText("El usuario se ha registrado");
            SingletonPantallas.toEstadoLectorViewSingleton(event);
        }else{
            lblAutenticacion.setText("El usuario no se pudo registrar");
        }

    }
}
