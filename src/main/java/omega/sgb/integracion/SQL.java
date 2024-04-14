package omega.sgb.integracion;

import java.sql.*;


public class SQL {

    public static Connection getConexion(){
        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                + "database=SGB_Omega_BD"
                + "user=x"
                + "password=1232;"
                + "loginTimeOut=30;";

        try{
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } catch(SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }




}
