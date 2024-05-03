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
    private static LoginGUI login;
    private static CrearCuentaGUI crearCuenta;
    private static BusquedaGUI busqueda;
    private static MainLectorGUI mainLector;
    private static MainBibliotecarioGUI mainBibliotecario;



    //Instancias singleton------------------------------------

    public static BusquedaGUI getInstanceBusqueda() {
        if (busqueda == null) {
            busqueda = new BusquedaGUI();
        }
        return busqueda;
    }

    public static CrearCuentaGUI getInstanceCrearCuenta() {
        if (crearCuenta == null) {
            crearCuenta = new CrearCuentaGUI();
        }
        return crearCuenta;
    }

    public static LoginGUI getInstanceLogin() {
        if (login == null) {
            login = new LoginGUI();
        }
        return login;
    }

    public static MainBibliotecarioGUI getInstanceMainBibliotecario() {
        if (mainBibliotecario == null) {
            mainBibliotecario = new MainBibliotecarioGUI();
        }
        return mainBibliotecario;
    }

    public static MainLectorGUI getInstanceMainLector() {
        if (mainLector == null) {
            mainLector = new MainLectorGUI();
        }
        return mainLector;
    }

    //Creaciones pantallas--------------------------------------------------
    public static void toCrearCuentaViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/crearCuentaView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }
    public static void toEstadoUsuarioViewSingleton(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual().getTipoPersonaId() == 1){ //Bibliotecario
            Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/estadoBibliotecarioView.fxml")));
            Scene InicioScene = new Scene(InicioParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(InicioScene);
            window.show();
        } else { //Lector
            Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/estadoLectorView.fxml")));
            Scene InicioScene = new Scene(InicioParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(InicioScene);
            window.show();
        }
    }
    public static void toBuscarLibroViewSingleton(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual().getTipoPersonaId() == 1){ //Bibliotecario
            Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/buscarLibroBibliotecarioView.fxml")));
            Scene InicioScene = new Scene(InicioParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(InicioScene);
            window.show();
        } else {
            Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/buscarLibroLectorView.fxml")));
            Scene InicioScene = new Scene(InicioParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(InicioScene);
            window.show();
        }
    }
    public static void toResultadoLibroViewSingleton(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual().getTipoPersonaId() == 1){ //Bibliotecario
            Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/resultadosBibliotecarioView.fxml")));
            Scene InicioScene = new Scene(InicioParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(InicioScene);
            window.show();
        } else {
            Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/resultadosLectorView.fxml")));
            Scene InicioScene = new Scene(InicioParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(InicioScene);
            window.show();
        }
    }
    public static void toDetallesLibroViewSingleton(ActionEvent event) throws IOException {
        if(SingletonControladores.getUsuarioActual().getTipoPersonaId() == 1){ //Bibliotecario
            Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/resultadoLibroView.fxml")));
            Scene InicioScene = new Scene(InicioParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(InicioScene);
            window.show();
        } else {
            Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/resultadoLibroView.fxml")));
            Scene InicioScene = new Scene(InicioParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.centerOnScreen();
            window.setScene(InicioScene);
            window.show();
        }
    }

    public static void toLogInViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/logInView.fxml")));
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
    public static void toCarritoViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/carritoView.fxml")));
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

    public static void toAgregarMetododePagoViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/agregarMetodoPagoView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }
}
