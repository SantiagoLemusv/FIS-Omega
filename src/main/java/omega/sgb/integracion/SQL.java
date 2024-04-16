package omega.sgb.integracion;

import java.sql.*;


public class SQL {
    // Datos de conexión a la base de datos (modifica según tu configuración)
    private static final String URL = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/LAB";
    private static final String USUARIO = "is819920";
    private static final String CONTRASENA = "Zyqb4HO0x1BG57S";

    // Método para obtener una conexión a la base de datos
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
