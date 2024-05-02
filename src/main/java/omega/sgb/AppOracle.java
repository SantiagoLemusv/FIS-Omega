package omega.sgb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import omega.sgb.integracion.SQL;

import java.io.IOException;

public class AppOracle extends Application {
    // Datos de conexión a la base de datos (modifica según tu configuración)
    private static final String URL = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/LAB";
    private static final String USUARIO = "is819920";
    private static final String CONTRASENA = "Zyqb4HO0x1BG57S";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppOracle.class.getResource("/omega/sgb/gui/logInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SingletonControladores.setConexionGeneral(SQL.getConexion(URL,USUARIO,CONTRASENA,0));
        launch();
    }
}