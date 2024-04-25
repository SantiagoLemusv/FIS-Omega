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
import omega.sgb.control.ControladorLogIn;
import omega.sgb.control.ControladorUsuario;
import omega.sgb.dominio.Persona;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainLectorGUI{
    private ControladorUsuario controladorUsuario;
    public MainLectorGUI(){}
    public MainLectorGUI(ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;
    }

    @FXML
    Label Titulo;

}
