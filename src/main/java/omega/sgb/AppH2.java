package omega.sgb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import omega.sgb.integracion.SQL;

import java.io.IOException;

public class AppH2 extends Application {
    // Datos de conexión a la base de datos (modifica según tu configuración)
    private static final String URL = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/LAB"; //cambiar a la conexion de H2
    private static final String USUARIO = "is819920";//cambiar a la conexion de H2
    private static final String CONTRASENA = "Zyqb4HO0x1BG57S";//cambiar a la conexion de H2

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Ejecuta metodo");
        FXMLLoader fxmlLoader = new FXMLLoader(AppH2.class.getResource("/omega/sgb/gui/logInView.fxml"));
        System.out.println("Carga pantalla");
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SingletonControladores.setConexionGeneral(SQL.getConexion(URL,USUARIO,CONTRASENA));
        launch();
    }
}