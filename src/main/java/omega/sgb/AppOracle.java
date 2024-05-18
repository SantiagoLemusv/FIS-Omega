package omega.sgb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import omega.sgb.control.ControladorActualizarApp;
import omega.sgb.integracion.DataBaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


public class AppOracle extends Application {
    // Datos de conexión a la base de datos (modifica según tu configuración)
    private static final String URL = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/LAB";
    private static final String USUARIO = "is819920";
    private static final String CONTRASENA = "Zyqb4HO0x1BG57S";
    private static ControladorActualizarApp controladorActualizarApp;
    private static final Connection connection = DataBaseConnectionManager.getConnectionOracle(URL, USUARIO, CONTRASENA, 0);


    @Override
    public void start(Stage stage) throws IOException, SQLException {
        SingletonControladores.setConexionGeneral(connection);
        controladorActualizarApp = SingletonControladores.getInstanceControladorActualizarApp();

        Timeline temporizador = new Timeline(new KeyFrame(Duration.seconds(10), evento -> {
            try {
                controladorActualizarApp.validarReservasVencidas();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                controladorActualizarApp.validarPrestamosVencidos();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                controladorActualizarApp.validarNuevoCostoDeMultas();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }));
        temporizador.setCycleCount(Timeline.INDEFINITE);
        temporizador.play();

        FXMLLoader fxmlLoader = new FXMLLoader(AppOracle.class.getResource("/omega/sgb/gui/logInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("SGB  :)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SingletonControladores.setConexionGeneral(connection);
        launch();
    }
}