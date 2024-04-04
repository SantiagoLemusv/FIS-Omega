package omega.sgb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearCuentaGUI {

    private LoginGUI LC = new LoginGUI();
    public String css = this.getClass().getResource("/omega/sgb/gui/gui/app.css").toExternalForm();

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label LabelCC;
    @FXML
    TextField Usuariotxt;
    @FXML
    PasswordField Contraseñatxt;
    @FXML
    PasswordField ContraseñatxtConfirm;
    @FXML
    TextField Nombretxt;
    @FXML
    TextField Apellidotxt;
    @FXML
    TextField Cedulatxt;

    public void ToLoginView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/omega/sgb/gui/login-view.fxml"));
        root = loader.load();
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void CrearCuenta(ActionEvent event) throws IOException {

        Boolean Crear = true;
        Boolean run = true;
        String Usuario = Usuariotxt.getText();
        String Contraseña = Contraseñatxt.getText();
        String Nombre = Nombretxt.getText();
        String Apellido = Apellidotxt.getText();
        String Cedula = Cedulatxt.getText();
        String TipoCuenta = "Lector";

        Cedula();
        Contraseña();
        Usuario();
        Apellido();
        Nombre();

        if(Boolean.valueOf(Nombre() == false || Apellido() == false || Usuario() == false || Contraseña() == false  || Cedula() == false || !TipoCuenta.equals("Lector"))) {
            run = false;
            String hexColor = "#830009";
            Color color = Color.web(hexColor);
            LabelCC.setTextFill(color);
            System.out.println("RUN="+Boolean.valueOf(run));
        }
        System.out.println("RUN="+Boolean.valueOf(run));
        if(run == true){
            LabelCC.setTextFill(Color.GREEN);
            LabelCC.setText("Cuenta creada!");
            /* Codigo a implementar con la clase SQL -> Conexion a la base de datos
            SQL.Crear_Cuenta(Usuario,Contraseña,Nombre,Apellido,Correo,TipoCuenta);
            */
        }
    }

    public boolean ValidarUsuario(String Usuario){
        String patron = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(Usuario);
        return matcher.matches();
    }
    public boolean ValidarCedula(String Correo){
        String patron = "^[0-9]+$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(Correo);
        return matcher.matches();
    }
    public boolean Usuario(){
        String Usuario = Usuariotxt.getText();
        if(Usuario.equals("")){
            LabelCC.setText("Ingrese un nombre de usuario.");
            return false;
        }
        else if(ValidarUsuario(Usuario)==false){
            LabelCC.setText("El nombre de usuario solo puede contener letras y numeros.");
            return false;
        }
        else if(ValidarUsuario(Usuario)==true) {
            return true;
        }
        /* Codigo a implementar con la clase SQL -> Conexion a la base de datos
        else if(ValidarUsuario(Usuario)==true && SQL.ValidarUsuarioSQL(Usuario)==false){
            return true;
        }
        else if(SQL.ValidarUsuarioSQL(Usuario)==true){
            LabelCC.setText("El nombre de usuario ingresado ya esta en uso.");
            return false;
        }
         */
        else{
            LabelCC.setText("ERROR");
            return false;
        }
    }
    public boolean Contraseña(){
        String Contraseña = Contraseñatxt.getText();
        String Confirm = ContraseñatxtConfirm.getText();

        if(Contraseña.equals("")){
            LabelCC.setText("Ingrese su contraseña.");
            return false;
        }
        else if(!Contraseña.equals(Confirm)){
            LabelCC.setText("Las contraseñas no coinciden. Intente de nuevo.");
            return false;
        }
        else if(Contraseña.equals(Confirm)){
            return true;
        }
        else{
            LabelCC.setText("Error inesperado");
            return false;
        }
    }
    public boolean Nombre(){
        if(Nombretxt.getText().equals("")){
            LabelCC.setText("Ingrese su nombre.");
            return false;
        }
        else if(Nombretxt.getText().matches(".*\\s+.*")){
            LabelCC.setText("Ingrese solo un nombre.");
            return false;
        }
        else{
            return true;
        }
    }
    public boolean Apellido(){
        if(Apellidotxt.getText().equals("")){
            LabelCC.setText("Ingrese su apellido.");
            return false;
        }
        else if(Apellidotxt.getText().matches(".*\\s+.*")){
            LabelCC.setText("Ingrese solo un apellido.");
            return false;
        }
        else{
            return true;
        }
    }
    public boolean Cedula(){
        String Cedula = Cedulatxt.getText();
        if(Cedula.equals("")){
            LabelCC.setText("Ingrese una cedula valida.");
            return false;
        }
        else if(ValidarCedula(Cedula)==false){
            LabelCC.setText("La cedula solo puede contener numeros.");
            return false;
        }
        else if(ValidarCedula(Cedula)==true){
            return true;
        }

        else{
            LabelCC.setText("Ingrese una cedula valida.");
            return true;
        }
    }
}
