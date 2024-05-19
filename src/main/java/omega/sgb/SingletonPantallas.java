package omega.sgb;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import omega.sgb.gui.*;

import java.io.IOException;

import java.util.Objects;

public class SingletonPantallas {
    private static AgregarMetodoPagoGUI agregarMetodoDePagoGUI;
    private static BuscarLibroBibliotecarioGUI buscarLibroBibliotecarioGUI;
    private static BuscarLibroLectorGUI buscarLibroLectorGUI;
    private static CarritoGUI carritoGui;
    private static CrearCuentaGUI crearCuentaGUI;
    private static EstadoBibliotecarioGUI estadoBibliotecarioGUI;
    private static EstadoLectorGUI estadoLectorGUI;
    private static LogInGUI logInGUI;
    private static PagoGUI pagoGUI;
    private static LibroSeleccionadoBibliotecarioGUI libroSeleccionadoBibliotecarioGUI;
    private static ResultadosBibliotecarioGUI resultadosBibliotecarioGUI;
    private static ResultadosLectorGUI resultadosLectorGUI;
    private static ValidarLectorGUI validarLectorGUI;

    //Creaciones pantallas--------------------------------------------------
    public static void toAgregarMetodoPagoViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/agregarMetodoPagoView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toBuscarLibroBibliotecarioViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/buscarLibroBibliotecarioView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toBuscarLibroLectorViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/buscarLibroLectorView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toDevolverLibroViewSingleton(ActionEvent event) throws IOException{
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/devolverLibroView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toCarritoViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/carritoView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toCrearCuentaViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/crearCuentaView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toEstadoBibliotecarioViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/estadoBibliotecarioView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toEstadoLectorViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/estadoLectorView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toLogInViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/logInView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toPagoViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/pagoView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toLibroSeleccionadoBibliotecarioViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/libroSeleccionadoBibliotecarioView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toLibroSeleccionadoLectorViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/libroSeleccionadoLectorView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toResultadosBibliotecarioViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/resultadosBibliotecarioView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toResultadosLectorViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/resultadosLectorView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toValidarLectorViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/validarLectorView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }
}
