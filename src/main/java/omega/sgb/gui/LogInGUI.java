package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorLogIn;
import omega.sgb.dominio.PersonaBibliotecario;
import omega.sgb.dominio.PersonaLector;

import java.io.IOException;
import java.sql.SQLException;

public class LogInGUI {

    private ControladorLogIn controladorLogIn = SingletonControladores.getInstanceControladorLogIn();
    public LogInGUI() throws SQLException {}
    public LogInGUI(ControladorLogIn controladorLogIn) throws SQLException {
        this.controladorLogIn = controladorLogIn;
    }

    @FXML
    Label lblAutenticacion;
    @FXML
    Button btnInciarSesion;
    @FXML
    TextField txtCedula;
    @FXML
    PasswordField txtContrasena;
    public void mBtnIniciarSesion(ActionEvent event) throws IOException, SQLException {
        String cedula = txtCedula.getText();
        String contrasena = txtContrasena.getText();
        if(controladorLogIn.validarCredenciales(cedula, contrasena)){
            lblAutenticacion.setText("El usuario es válido");
            mTipoPantalla(event);
        }
        else{
            lblAutenticacion.setText("Usuario o contraseña no válidos");
        }

    }
    public void mTipoPantalla(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual() instanceof PersonaBibliotecario){ //Bibliotecario
            SingletonPantallas.toEstadoBibliotecarioViewSingleton(event);
        } else if (SingletonControladores.getUsuarioActual() instanceof PersonaLector) { //Lector
            SingletonPantallas.toEstadoLectorViewSingleton(event);
        }else{
            lblAutenticacion.setText("El usuario no tiene tipo");
        }
    }
    public void mBtnCrearCuenta(ActionEvent event) throws IOException {
        SingletonPantallas.toCrearCuentaViewSingleton(event);
    }
}
