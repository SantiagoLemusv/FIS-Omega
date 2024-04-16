package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import omega.sgb.SingletonControladores;

public class PrestamoGUI {
    @FXML
    TextField txtCedulaLector;
    @FXML
    Button btnConsultarLector;
    @FXML
    Label lblEstadoLector;
    @FXML
    Label lblConflictosLector;
    @FXML
    Button btnConfirmarPrestamo;
    @FXML
    Label lblEstadoPrestamo;
    @FXML
    Button btnVolver;
    @FXML
    ListView listViewLibros;

    public void mConsultarLector(ActionEvent event){
        String cedulaLector = txtCedulaLector.getText();
        if(SingletonControladores.getInstancePrestamo().consultarLector(cedulaLector)){
            lblEstadoLector.setText("Estado lector correcto");
        }else{
            lblEstadoLector.setText("El lector tiene conflictos");
        }
    }
    public void mConfirmarPrestamo(ActionEvent event){
        if(lblEstadoLector.getText() == "Estado lector correcto"){

        }
    }
    public void mVolverCarrito(ActionEvent event){

    }


}
