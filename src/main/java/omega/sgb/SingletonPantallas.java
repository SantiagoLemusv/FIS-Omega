package omega.sgb;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import omega.sgb.gui.*;

import java.io.IOException;

import java.sql.SQLException;
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
    private static ResultadoLibroBibliotecarioGUI resultadoLibroBibliotecarioGUI;
    private static ResultadosBibliotecarioGUI resultadosBibliotecarioGUI;
    private static ResultadosLectorGUI resultadosLectorGUI;
    private static ValidarLectorGUI validarLectorGUI;



    //Instancias singleton GUI------------------------------------
    public static AgregarMetodoPagoGUI getInstanceAgregarMetodoDePagoGUI() throws SQLException {
        if (agregarMetodoDePagoGUI == null) {
            agregarMetodoDePagoGUI = new AgregarMetodoPagoGUI(SingletonControladores.getInstanceControladorPago());
        }
        return agregarMetodoDePagoGUI;
    }

    public static BuscarLibroBibliotecarioGUI getInstanceBuscarLibroBibliotecarioGUI() throws SQLException {
        if (buscarLibroBibliotecarioGUI == null) {
            buscarLibroBibliotecarioGUI = new BuscarLibroBibliotecarioGUI(SingletonControladores.getInstanceControladorBusquedaLibro());
        }
        return buscarLibroBibliotecarioGUI;
    }

    public static BuscarLibroLectorGUI getInstanceBuscarLibroLectorGUI() throws SQLException {
        if (buscarLibroLectorGUI == null) {
            buscarLibroLectorGUI = new BuscarLibroLectorGUI(SingletonControladores.getInstanceControladorBusquedaLibro());
        }
        return buscarLibroLectorGUI;
    }

    public static CarritoGUI getInstanceCarritoGUI() throws SQLException {
        if (carritoGui == null) {
            carritoGui = new CarritoGUI(SingletonControladores.getInstanceControladorCarrito());
        }
        return carritoGui;
    }

    public static CrearCuentaGUI crearCuentaGUI() throws SQLException {
        if (crearCuentaGUI == null) {
            crearCuentaGUI = new CrearCuentaGUI(SingletonControladores.getInstanceControladorLogIn());
        }
        return crearCuentaGUI;
    }

    public static EstadoBibliotecarioGUI getInstanceEstadoBibliotecarioGUI() throws SQLException {
        if (estadoBibliotecarioGUI == null) {
            estadoBibliotecarioGUI = new EstadoBibliotecarioGUI(SingletonControladores.getInstanceControladorEstadoUsuario());
        }
        return estadoBibliotecarioGUI;
    }

    public static EstadoLectorGUI getInstanceEstadoLectorGUI() throws SQLException {
        if (estadoLectorGUI == null) {
            estadoLectorGUI = new EstadoLectorGUI(SingletonControladores.getInstanceControladorEstadoUsuario());
        }
        return estadoLectorGUI;
    }

    public static LogInGUI getInstanceLogInGUI() throws SQLException {
        if (logInGUI == null) {
            logInGUI = new LogInGUI(SingletonControladores.getInstanceControladorLogIn());
        }
        return logInGUI;
    }

    public static PagoGUI getInstancePagoGUI() throws SQLException {
        if (pagoGUI == null) {
            pagoGUI = new PagoGUI(SingletonControladores.getInstanceControladorPago());
        }
        return pagoGUI;
    }

    public static ResultadoLibroBibliotecarioGUI getInstanceResultadoLibroGUI() throws SQLException {
        if (resultadoLibroBibliotecarioGUI == null) {
            resultadoLibroBibliotecarioGUI = new ResultadoLibroBibliotecarioGUI(SingletonControladores.getInstanceControladorBusquedaLibro());
        }
        return resultadoLibroBibliotecarioGUI;
    }

    public static ResultadosBibliotecarioGUI getInstanceResultadosBibliotecarioGUI() throws SQLException {
        if (resultadosBibliotecarioGUI == null) {
            resultadosBibliotecarioGUI = new ResultadosBibliotecarioGUI(SingletonControladores.getInstanceControladorBusquedaLibro());
        }
        return resultadosBibliotecarioGUI;
    }

    public static ResultadosLectorGUI getInstanceResultadosLectorGUI() throws SQLException {
        if (resultadosLectorGUI == null) {
            resultadosLectorGUI = new ResultadosLectorGUI(SingletonControladores.getInstanceControladorBusquedaLibro());
        }
        return resultadosLectorGUI;
    }

    public static ValidarLectorGUI getInstanceValidarLectorGUI() throws SQLException {
        if (validarLectorGUI == null) {
            validarLectorGUI = new ValidarLectorGUI(SingletonControladores.getInstanceControladorPrestamo());
        }
        return validarLectorGUI;
    }



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

    public static void toResultadoLibroBibliotecarioViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/resultadoLibroBibliotecarioView.fxml")));
        Scene InicioScene = new Scene(InicioParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.centerOnScreen();
        window.setScene(InicioScene);
        window.show();
    }

    public static void toResultadoLibroLectorViewSingleton(ActionEvent event) throws IOException {
        Parent InicioParent = FXMLLoader.load(Objects.requireNonNull(SingletonPantallas.class.getResource("/omega/sgb/gui/resultadoLibroLectorView.fxml")));
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
