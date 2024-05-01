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
        int id = 1;
        int personaid = 1;
        String Nombre = "Prueba Nombre";


        Integer numCedula = Integer.parseInt(cedula);
        SingletonControladores.getUsuarioActual().setId(id);
        SingletonControladores.getUsuarioActual().setTipoPersonaId(personaid);
        SingletonControladores.getUsuarioActual().setNombre(Nombre);
        SingletonControladores.getUsuarioActual().setCedula(numCedula);
        SingletonControladores.getUsuarioActual().setContrasenia(contrasena);

        mTipoPantalla(event);
        /*
        if(SingletonControladores.getInstanceLogIn().validarCredenciales(cedula, contrasena)){
            System.out.println("ENTRO");
            lblAutenticacion.setText("El usuario es válido");
            mTipoPantalla(event);
        }
        else{
            lblAutenticacion.setText("Usuario o contraseña no válidos");
        }
         */

        //SingletonPantallas.toEstadoUsuarioViewSingleton(event);
         /*
        SingletonPantallas.toEstadoUsuarioViewSingleton(event);
        SingletonPantallas.toMainBibliotecarioViewSingleton(event);
        SingletonPantallas.toMainLectorViewSingleton(event);
          */
    }
    public void mTipoPantalla(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual().getTipoPersonaId() == 1){ //Bibliotecario
            SingletonPantallas.toEstadoUsuarioViewSingleton(event);
        } else if (SingletonControladores.getUsuarioActual().getTipoPersonaId() == 2) { //Lector
            SingletonPantallas.toEstadoUsuarioViewSingleton(event);
        }else{
            lblAutenticacion.setText("El usuario no tiene tipo");
        }
    }
    public void mBtnCrearCuenta(ActionEvent event) throws IOException {
        SingletonPantallas.toCrearCuentaViewSingleton(event);
    }
}
