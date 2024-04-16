package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import omega.sgb.dominio.Persona;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainLectorGUI implements Initializable {
    public String css = this.getClass().getResource("/omega/sgb/gui/gui/app.css").toExternalForm();
    private Stage stage;
    private Scene scene;
    private Parent root;

    Persona cuenta = new Persona();

    @FXML
    Label Titulo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String Nombre = cuenta.getNombre();
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
        cuenta.setCedula(null);
        cuenta.setNombre(null);
        cuenta.setContrasenia(null);
        cuenta.setTipoPersonaId(null);

        Integer usr = cuenta.getCedula();
        String psw = cuenta.getContrasenia();
        String usn = cuenta.getNombre();
        Integer tip = cuenta.getTipoPersonaId();
        System.out.println("\n\nlogout");
        System.out.println(usr+"<-usr value");
        System.out.println(psw+"<-psw value");
        System.out.println(usn+"<-usn value");
        System.out.println(tip+"<-tip value");



    }
}
