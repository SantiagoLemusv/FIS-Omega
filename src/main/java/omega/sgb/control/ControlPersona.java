package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.Persona;
import omega.sgb.integracion.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlPersona {
    private static SingletonControladores myself;
    private Persona persona;

    public Integer validarCredenciales(String usuario, String contrasena) {
        try (Connection connection = SQL.getConexion()) {
            String sql = "SELECT ID FROM PERSONA WHERE CEDULA = ? AND CONTRASENA = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contrasena);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("ID");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
