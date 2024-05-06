package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorBusquedaLibro;
import omega.sgb.dominio.LibroVirtual;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.sql.SQLException;

public class ResultadosBibliotecarioGUI {
    @FXML
    TableView<LibroVirtual> tableViewResultadosLibros;
    @FXML
    TableColumn<LibroVirtual, ImageView> colPortada;
    @FXML
    TableColumn<LibroVirtual, String> colTitulo;
    @FXML
    TableColumn<LibroVirtual, String> colAutor;
    @FXML
    TextField txtFieldTitulo;
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    private ObservableList<LibroVirtual> listaLibrosFisicos = FXCollections.observableArrayList();
    public ResultadosBibliotecarioGUI() throws SQLException {}
    public ResultadosBibliotecarioGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }

    public void mBtnMiPerfil(ActionEvent event) throws IOException {
        SingletonPantallas.toEstadoBibliotecarioViewSingleton(event);
    }
    public void mBtnBusqueda(ActionEvent event) throws IOException {
        SingletonPantallas.toBuscarLibroBibliotecarioViewSingleton(event);
    }
    public void mBtnCarrito(ActionEvent event) throws IOException {
        SingletonPantallas.toCarritoViewSingleton(event);
    }
    public void mBtnCerrarSesion(ActionEvent event) throws IOException {
        SingletonPantallas.toLogInViewSingleton(event);
    }
    public void mBtnVerDetalles(ActionEvent event){

    }

    public void mInicializarTablaLibros(){
        System.out.println(txtFieldTitulo.getText());
        tableViewResultadosLibros.getItems().clear();
        controladorBusquedaLibro.buscarLibros(txtFieldTitulo.getText());
        listaLibrosFisicos.addAll(controladorBusquedaLibro.getListaLibrosVirtuales());
        if(listaLibrosFisicos.isEmpty()){
            System.out.println("lista vacia");
        }else{
            System.out.println("lista con datos");
        }
        colPortada.setCellValueFactory(new PropertyValueFactory<>("imagenLibro"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tableViewResultadosLibros.setItems(listaLibrosFisicos);
    }

}