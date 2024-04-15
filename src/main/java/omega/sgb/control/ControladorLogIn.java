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

    public Boolean validarCredenciales() {
        String cedula = SingletonPantallas.getInstanceLogin().getTxtCedula();
        String contrasena = SingletonPantallas.getInstanceLogin().getTxtContrasena();
        Integer numCedula = Integer.parseInt(cedula);
        try (Connection connection = SQL.getConexion()) {
            String sql = "SELECT ID, TIPOPERSONAID, NOMBRE FROM PERSONA WHERE CEDULA = ? AND CONTRASENA = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cedula);
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


    public void mensajeAutenticacion(){
        SingletonPantallas.getInstanceLogin().setLblAutenticacion("El usuario o contraseña no son válidas");
    }

}
