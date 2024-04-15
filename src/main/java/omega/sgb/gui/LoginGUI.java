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

    public String css = this.getClass().getResource("/omega/sgb/gui/gui/app.css").toExternalForm();
    //public Cuenta cuenta = new Cuenta();
    public Persona persona = new Persona();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label lblAutenticacion;
    @FXML
    Button btnIrInciarSesionM;
    @FXML
    TextField txtCedula;
    @FXML
    PasswordField txtContrasena;

    public String getTxtCedula(){
        return txtCedula.getText();
    }

    public String getTxtContrasena(){
        return txtContrasena.getText();
    }
    public void setLblAutenticacion(String texto){
        lblAutenticacion.setText(texto);
    }

    public void iniciarSesion(ActionEvent event) throws IOException {
        if(SingletonControladores.getInstanceLogIn().validarCredenciales()){
            toTipoPantalla(event);
        }
        else{
            SingletonControladores.getInstanceLogIn().mensajeAutenticacion();

        }

    }

    public void toTipoPantalla(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual().getId() == 1){
            toPantallaBibliotecario(event);
        }
        else if (SingletonControladores.getUsuarioActual().getId() == 2) {
            toPantallaLector(event);
        }

    }

    public void toPantallaCrearCuenta(ActionEvent event) throws IOException {
        App app = new App();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/omega/sgb/gui/crearcuenta-view.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/omega/sgb/view/nada.fxml"));
        root = loader.load();
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }



    public void toPantallaLector(ActionEvent event) throws IOException {
        App app = new App();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/omega/sgb/gui/mainlector-view.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/omega/sgb/view/nada.fxml"));
        root = loader.load();
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void toPantallaBibliotecario(ActionEvent event) throws IOException {
        App app = new App();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/omega/sgb/gui/mainbibliotecario-view.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/omega/sgb/view/nada.fxml"));
        root = loader.load();
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
