package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.dominio.LibroFisico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidarLectorGUI {
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
            lblEstadoLector.setText("El lector tiene conflictos");
        }else{
            lblEstadoLector.setText("Estado lector correcto");
        }
    }
    public void mConfirmarPrestamo(ActionEvent event){
        if(lblEstadoLector.getText() == "Estado lector correcto"){
            LibroFisico libroPrueba = SingletonControladores.getInstancePrestamo().agregarLibroCarrito();//cambiar, esto es quemado
            List<LibroFisico> listaPrueba = new ArrayList<LibroFisico>();
            listaPrueba.add(libroPrueba);
            SingletonControladores.getInstancePrestamo().confirmarPrestamo(listaPrueba);

        }
    }
    public void mVolverCarrito(ActionEvent event) throws IOException {
        SingletonPantallas.toCarritoViewSingleton(event);
    }


}
