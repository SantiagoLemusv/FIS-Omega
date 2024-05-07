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
    public void mBtnCrearCuenta(ActionEvent event) throws SQLException, IOException {
        String cedula = txtCedula.getText();
        String contrasena = txtContrasena.getText();
        String nombreCompleto = txtNombreCompleto.getText();
        String contrasenaConfirmar = txtContrasenaConfirmar.getText();
        if(controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto)){
            lblAutenticacion.setText("El usuario se ha registrado");
            SingletonPantallas.toEstadoLectorViewSingleton(event);
        }else{
            lblAutenticacion.setText("El usuario no se pudo registrar");
        }
    }
}
