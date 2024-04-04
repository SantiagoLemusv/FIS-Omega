package omega.sgb.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import omega.sgb.dominio.Cuenta;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlMainLector implements Initializable {
    public String css = this.getClass().getResource("/omega/sgb/view/gui/app.css").toExternalForm();
    private Stage stage;
    private Scene scene;
    private Parent root;

    Cuenta cuenta = new Cuenta();

    @FXML
    Label Titulo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String Nombre = Cuenta.Nombre;
        Titulo.setText("Bienvenido "+Nombre+".");
    }

    public void BuscarLibros(ActionEvent event) throws IOException{
    //    FXMLLoader loader = new FXMLLoader(getClass().getResource("nada.fxml"));
    //    root = loader.load();
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public void CerrarSesion(ActionEvent event) throws IOException {
        cuenta.setUsuario(null);
        cuenta.setNombre(null);
        cuenta.setApellido(null);
        cuenta.setContraseña(null);
        cuenta.setCorreo(null);
        cuenta.setTipoCuenta(null);

        String usr = cuenta.getUsuario();
        String psw = cuenta.getContraseña();
        String usn = cuenta.getNombre();
        String snm = cuenta.getApellido();
        String cor = cuenta.getCorreo();
        String tip = cuenta.getTipoCuenta();
        System.out.println("\n\nlogout");
        System.out.println(usr+"<-usr value");
        System.out.println(psw+"<-psw value");
        System.out.println(usn+"<-usn value");
        System.out.println(snm+"<-snm value");
        System.out.println(cor+"<-cor value");
        System.out.println(tip+"<-tip value");


        if((usr == null && psw == null && usn == null && snm == null && cor == null && tip == null)){
            System.out.println("Values nulled");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            root = loader.load();
            stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
            scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            System.out.println("Logout true\n\n");
        }
    }
}
