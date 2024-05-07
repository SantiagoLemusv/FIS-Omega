package omega.sgb.integracion;

import java.sql.*;


public class DataBaseConnectionManager {
    // Método para obtener una conexión a la base de datos
    public static Connection getConnectionOracle(String url, String usuario, String contrasena, int cont) {
        try {
            return DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            while(cont<3){
                System.out.println("Fallo en la conexión");
                cont=cont+1;
                getConnectionOracle(url, usuario, contrasena, cont);
            }
        }
        return null;
    }

    public static Connection getConnectionH2(String url, String usuario, String contrasena) throws SQLException {
        // return dataSource.getConnection();
        return DriverManager.getConnection(url, usuario, contrasena);
    }

}
