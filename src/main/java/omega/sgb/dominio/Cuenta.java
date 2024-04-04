package omega.sgb.dominio;

public class Cuenta {
    public static String Usuario;
    private static String Contraseña;
    public static String Nombre;
    private static String Apellido;
    private static String Cedula;
    public static String TipoCuenta;

    public Cuenta(){
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Cedula;
    }

    public void setCorreo(String correo) {
        Cedula = correo;
    }

    public String getTipoCuenta() {
        return TipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        TipoCuenta = tipoCuenta;
    }

    public Cuenta(String usuario, String contraseña, String nombre, String apellido, String correo, String tipoCuenta) {
        Usuario = usuario;
        Contraseña = contraseña;
        Nombre = nombre;
        Apellido = apellido;
        Cedula = correo;
        TipoCuenta = tipoCuenta;
    }

    public Cuenta(String nombre, String apellido, String correo, String contraseña){
        Contraseña = contraseña;
        Nombre = nombre;
        Apellido = apellido;
        Cedula = correo;
    }
}
