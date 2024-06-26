package omega.sgb.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorBusquedaLibro;
import omega.sgb.dominio.LibroVirtual;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResultadosBibliotecarioGUI implements Initializable {
    @FXML
    TableView<LibroVirtual> tableViewResultadosLibros;
    @FXML
    TableColumn<LibroVirtual, ImageView> colPortada;
    @FXML
    TableColumn<LibroVirtual, String> colTitulo;
    @FXML
    TableColumn<LibroVirtual, String> colAutor;
    @FXML
    TableColumn<LibroVirtual, Integer> colCopiasDisponibles;
    @FXML
    TableColumn<LibroVirtual, Integer> colCopiasTotales;
    @FXML
    TextField txtFieldTitulo;
    @FXML
    Button btnVerDetalles;
    @FXML
    Label lblLibroDisponible;
    @FXML
    Label lblNombreU;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(controladorBusquedaLibro.getTituloLibroBusquedaGrande() != null){
            txtFieldTitulo.setText(controladorBusquedaLibro.getTituloLibroBusquedaGrande());
            mBtnLupa();
        }
        lblNombreU.setText(SingletonControladores.getUsuarioActual().getNombre());
    }
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    private ObservableList<LibroVirtual> observableLibrosVirtuales = FXCollections.observableArrayList();
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
    public void mBtnVerDetalles(ActionEvent event) throws IOException {
        lblLibroDisponible.setVisible(false);
        LibroVirtual libroSeleccionado = tableViewResultadosLibros.getSelectionModel().getSelectedItem();
        controladorBusquedaLibro.setLibroVirtualSeleccionado(libroSeleccionado);
        SingletonPantallas.toLibroSeleccionadoBibliotecarioViewSingleton(event);
    }

    public void mBtnLupa(){
        tableViewResultadosLibros.getItems().clear();
        controladorBusquedaLibro.buscarLibrosVirtuales(txtFieldTitulo.getText());
        observableLibrosVirtuales.addAll(controladorBusquedaLibro.getListaLibrosVirtuales());
        colPortada.setCellValueFactory(new PropertyValueFactory<>("imagenLibro"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colCopiasDisponibles.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getLibrosFisicosDisponibles().size()).asObject());
        colCopiasTotales.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getLibrosFisicosTotales().size()).asObject());
        tableViewResultadosLibros.setItems(observableLibrosVirtuales);
    }

    public void mBtnDevolverLibro(ActionEvent event) throws IOException{
        SingletonPantallas.toDevolverLibroViewSingleton(event);
    }
}