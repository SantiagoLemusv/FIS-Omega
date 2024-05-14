package omega.sgb.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.control.ControladorCarrito;
import omega.sgb.dominio.LibroFisico;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CarritoGUI implements Initializable {
    @FXML
    Label lblNombreU;
    @FXML
    TableView<LibroFisico> tableViewCarrito;
    @FXML
    TableColumn<LibroFisico, ImageView> colPortada;
    @FXML
    TableColumn<LibroFisico, String> colTitulo;
    @FXML
    TableColumn<LibroFisico, String> colIsbn;



    private ControladorCarrito controladorCarrito = SingletonControladores.getInstanceControladorCarrito();
    private ObservableList<LibroFisico> observableLibrosFisicos = FXCollections.observableArrayList();


    public CarritoGUI() throws SQLException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNombreU.setText(SingletonControladores.getUsuarioActual().getNombre());
        cargarLibros();

    }

    private void cargarLibros() {
        tableViewCarrito.getItems().clear();
        System.out.println(SingletonControladores.getUsuarioActual().getCarrito().size());
        observableLibrosFisicos.addAll(SingletonControladores.getUsuarioActual().getCarrito());
        System.out.println(observableLibrosFisicos);
        // codigo para mostrar la portada
        colTitulo.setCellValueFactory(cellData -> {
            // Obtener el libro fisico para la fila actual
            LibroFisico libroFisico = cellData.getValue();

            // Devolver el título del libro fisico
            return new SimpleStringProperty(libroFisico.getLibroVirtual().getTitulo());
        });
        colIsbn.setCellValueFactory(cellData -> {
            // Obtener el libro fisico para la fila actual
            LibroFisico libroFisico = cellData.getValue();

            // Devolver el título del libro fisico
            return new SimpleStringProperty(libroFisico.getLibroVirtual().getIsbn());
        });
        tableViewCarrito.setItems(observableLibrosFisicos);
    }

    public void mBtnFinalizarPrestamo(ActionEvent event) throws IOException {
        SingletonPantallas.toValidarLectorViewSingleton(event);
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

    public void mBtnEliminarLibro(ActionEvent event) throws IOException {
        LibroFisico libroFisicoSeleccionado = tableViewCarrito.getSelectionModel().getSelectedItem();
        SingletonControladores.getUsuarioActual().getCarrito().remove(libroFisicoSeleccionado);
        mBtnCarrito(event);
    }
}

