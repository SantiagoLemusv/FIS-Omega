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

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearCuentaGUI {

    private LoginGUI LC = new LoginGUI();
    public String css = this.getClass().getResource("/omega/sgb/gui/gui/app.css").toExternalForm();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button btnCrearCuentaM;
    @FXML
    PasswordField txtContrasena;
    @FXML
    PasswordField txtNombreCompleto;
    @FXML
    TextField txtCedula;
    @FXML
    PasswordField txtContrasenaConfirmar;
    @FXML
    Label lblAutenticacion;

    public String getTxtCedula(){
        return txtCedula.getText();
    }

    public String getTxtContrasena(){
        return txtContrasena.getText();
    }
    public String getTxtNombreCompleto(){
        return txtNombreCompleto.getText();
    }
    public String getTxtContrasenaConfirmar(){
        return txtContrasenaConfirmar.getText();
    }

    public void setLblAutenticacion(String texto){
        lblAutenticacion.setText(texto);
    }

    public void ToLoginView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/omega/sgb/gui/login-view.fxml"));
        root = loader.load();
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void CrearCuenta(ActionEvent event) throws IOException {

    }


    public boolean ValidarCedula(String cedula){
        String patron = "^[0-9]+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cedula);
        return matcher.matches();
    }


}
