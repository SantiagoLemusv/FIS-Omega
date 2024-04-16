package omega.sgb.control;

import omega.sgb.SingletonPantallas;
import omega.sgb.SingletonControladores;
import omega.sgb.dominio.Persona;
import omega.sgb.integracion.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorLogIn {

    public Boolean validarCredenciales(String cedula, String contrasena) {
        Integer numCedula = Integer.parseInt(cedula);
        try (Connection connection = SQL.getConexion()) {
            String sql = "SELECT ID, TIPOPERSONAID, NOMBRE FROM PERSONA WHERE CEDULA = ? AND CONTRASENA = ?";
            System.out.println("ejecutó query");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, numCedula);
                preparedStatement.setString(2, contrasena);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        SingletonControladores.getUsuarioActual().setId(resultSet.getInt("ID"));
                        SingletonControladores.getUsuarioActual().setTipoPersonaId(resultSet.getInt("TIPOPERSONAID"));
                        SingletonControladores.getUsuarioActual().setNombre(resultSet.getString("NOMBRE"));
                        SingletonControladores.getUsuarioActual().setCedula(numCedula);
                        SingletonControladores.getUsuarioActual().setContrasenia(contrasena);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
