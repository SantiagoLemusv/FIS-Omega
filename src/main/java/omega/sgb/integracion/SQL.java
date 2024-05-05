package omega.sgb.integracion;

import java.sql.*;


public class SQL {
    // Método para obtener una conexión a la base de datos
    public static Connection getConexion(String url, String usuario, String contrasena, int cont) {
        try {
            return DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            while(cont<3){
                System.out.println("Fallo en la conexión");
                cont=cont+1;
                getConexion(url, usuario, contrasena, cont);
            }

        }
        return null;
    }

}
