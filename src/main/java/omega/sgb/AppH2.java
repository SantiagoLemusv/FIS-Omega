package omega.sgb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import omega.sgb.integracion.SQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppH2 extends Application {
    private static final String URL = "jdbc:h2:~/test";
    private static final String USUARIO = "omega";
    private static final String CONTRASENA = "";


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
        SingletonControladores.setConexionGeneral(SQL.getConexion(URL,USUARIO,CONTRASENA,0));
        launch();
    }
}