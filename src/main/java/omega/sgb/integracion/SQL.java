package omega.sgb.integracion;

import java.sql.*;


public class SQL {

    // Método para obtener una conexión a la base de datos
    public static Connection getConexion(String url, String usuario, String contrasena) {
        try {
            return DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            getConexion(url, usuario, contrasena);
        }
        return null;
    }

}
