package omega.sgb.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    ImageView imagenPrueba;
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

    public ObservableList<LibroVirtual> quemarLibros(){
        LibroVirtual libro1 = new LibroVirtual(1, "978-1234567890", "El señor de los anillos", 10, "J.R.R. Tolkien", 14, 5, null);
        LibroVirtual libro2 = new LibroVirtual(2, "978-0316734937", "Cien años de soledad", 8, "Gabriel García Márquez", 7, 3, null);
        LibroVirtual libro3 = new LibroVirtual(3, "978-0062515849", "Orgullo y prejuicio", 12, "Jane Austen", 21, 2, null);
        ObservableList<LibroVirtual> lista = FXCollections.observableArrayList();
        lista.add(libro1);
        lista.add(libro2);
        lista.add(libro3);
        return lista;
    }

    public void mInicializarTablaLibros(){
        System.out.println("boton lupita bibliotecario");
        listaLibrosFisicos.addAll(controladorBusquedaLibro.getListaLibrosVirtuales());
        if(listaLibrosFisicos.isEmpty()){
            System.out.println("lista vacia");
        }else{
            System.out.println("lista con datos");
        }
        imagenPrueba.setImage(listaLibrosFisicos.get(0).getImagenLibro());
        colPortada.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tableViewResultadosLibros.setItems(listaLibrosFisicos);
    }

}