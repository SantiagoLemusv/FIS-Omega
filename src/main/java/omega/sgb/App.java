package omega.sgb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    //configuracion visual del CSS
    public String css = this.getClass().getResource("/omega/sgb/view/gui/app.css").toExternalForm();
    //public String css = this.getClass().getResource("/omega/sgb/view/gui/nada.css").toExternalForm();


    @Override
    public void start(Stage stage) throws IOException {
        //Carga y establece la vista principal del programa
        Parent root = FXMLLoader.load(getClass().getResource("/omega/sgb/view/login-view.fxml"));

        Scene scene = new Scene(root);

        //carga el estilo del css correspondiente
        scene.getStylesheets().add(css);
        stage.setTitle("SGB");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}