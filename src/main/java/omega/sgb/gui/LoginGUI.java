package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import omega.sgb.App;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.dominio.Persona;

import java.io.IOException;

public class LoginGUI {

    @FXML
    Label lblAutenticacion;
    @FXML
    Button btnIrInciarSesionM;
    @FXML
    TextField txtCedula;
    @FXML
    PasswordField txtContrasena;
    public void mBtnIniciarSesion(ActionEvent event) throws IOException {
        String cedula = txtCedula.getText();
        String contrasena = txtContrasena.getText();
        if(SingletonControladores.getInstanceLogIn().validarCredenciales(cedula, contrasena)){
            lblAutenticacion.setText("El usuario es válido");
            mTipoPantalla(event);
        }
        else{
            lblAutenticacion.setText("Usuario o contraseña no válidos");
        }

    }
    public void mTipoPantalla(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual().getTipoPersonaId() == 1){ //Bibliotecario
            SingletonPantallas.toMainBibliotecarioViewSingleton(event);
        } else if (SingletonControladores.getUsuarioActual().getTipoPersonaId() == 2) { //Lector
            SingletonPantallas.toMainLectorViewSingleton(event);
        }else{
            lblAutenticacion.setText("El usuario no tiene tipo");
        }
    }
    public void mBtnCrearCuenta(ActionEvent event) throws IOException {
        SingletonPantallas.toCrearCuentaViewSingleton(event);
    }
}
