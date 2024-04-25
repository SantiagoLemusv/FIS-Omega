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
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorLogIn;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearCuentaGUI {
    private ControladorLogIn controladorLogIn;
    public CrearCuentaGUI(ControladorLogIn controladorLogIn) {
        this.controladorLogIn = controladorLogIn;
    }

    @FXML
    Button btnCrearCuentaM;
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
    public void mBtnCrearCuenta(){

    }
}
