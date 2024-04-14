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
    private SingletonPantallas sPantallas = SingletonPantallas.getInstance();

    public Boolean validarCredenciales() {
        String cedula = sPantallas.getLogin().getTxtCedula();
        String contrasena = sPantallas.getLogin().getTxtContrasena();
        Integer numCedula = Integer.parseInt(cedula);
        try (Connection connection = SQL.getConexion()) {
            String sql = "SELECT ID, TIPOPERSONAID, NOMBRE FROM PERSONA WHERE CEDULA = ? AND CONTRASENA = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, cedula);
                preparedStatement.setString(2, contrasena);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        sPantallas.getsControladores().getUsuarioActual().setId(resultSet.getInt("ID"));
                        sPantallas.getsControladores().getUsuarioActual().setTipoPersonaId(resultSet.getInt("TIPOPERSONAID"));
                        sPantallas.getsControladores().getUsuarioActual().setNombre(resultSet.getString("NOMBRE"));
                        sPantallas.getsControladores().getUsuarioActual().setCedula(numCedula);
                        sPantallas.getsControladores().getUsuarioActual().setContrasenia(contrasena);
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
        sPantallas.getLogin().setLblAutenticacion("El usuario o contraseña no son válidas");
    }

}
