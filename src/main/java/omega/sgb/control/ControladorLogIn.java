package omega.sgb.control;

import omega.sgb.SingletonControladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ControladorLogIn {
    private final Connection connection;
    public ControladorLogIn(Connection conexionGeneral) throws SQLException {
        this.connection = conexionGeneral;
    }

    public Boolean validarCredenciales(String cedula, String contrasena) throws SQLException {
        Integer numCedula = Integer.parseInt(cedula);
            String sql = "SELECT ID, TIPOPERSONAID, NOMBRE FROM PERSONA WHERE CEDULA = ? AND CONTRASENA = ?";
            System.out.println("ejecutó query");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, numCedula);
                preparedStatement.setString(2, contrasena);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        if (resultSet.getInt("TIPOPERSONAID") == 1) {
                            SingletonControladores.crearUsuarioActualBibliotecario();
                        } else if (resultSet.getInt("TIPOPERSONAID") == 2) {
                            SingletonControladores.crearUsuarioActualLector();
                        }
                        SingletonControladores.getUsuarioActual().setId(resultSet.getInt("ID"));
                        SingletonControladores.getUsuarioActual().setNombre(resultSet.getString("NOMBRE"));
                        SingletonControladores.getUsuarioActual().setCedula(numCedula);
                        SingletonControladores.getUsuarioActual().setContrasena(contrasena);
                        return true;
                    }
                }
            }
        return false;
    }

    public boolean nuevoUsuarioCrear(String cedula, String contrasena, String contrasenaConfirmar, String nombreCompleto) throws SQLException {
        Integer numCedula = Integer.parseInt(cedula);

        String sqlValidarExistencia = "SELECT COUNT(*) FROM PERSONA WHERE CEDULA = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlValidarExistencia)) {
            preparedStatement.setInt(1, numCedula);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                if(count != 0){
                    return false;
                }
                String sqlCrearUsuario = "INSERT INTO PERSONA (CEDULA, CONTRASENA, NOMBRE, TIPOPERSONAID) VALUES (?, ?, ?, 2)";
                try (PreparedStatement insertStatement = connection.prepareStatement(sqlCrearUsuario)) {
                    insertStatement.setInt(1, numCedula);
                    insertStatement.setString(2, contrasena);
                    insertStatement.setString(3, nombreCompleto);
                    int filasAfectadas = insertStatement.executeUpdate();
                    if (filasAfectadas < 1) {
                        return false;
                    }
                    SingletonControladores.crearUsuarioActualLector();
                    SingletonControladores.getUsuarioActual().setCedula(numCedula);
                    SingletonControladores.getUsuarioActual().setContrasena(contrasena);
                    SingletonControladores.getUsuarioActual().setNombre(nombreCompleto);
                    return true;
                }
            }
        }
    }

    //Implementar método para validar que el campo de cédula sólo acepte números
    public Boolean validarCaracteresValidos(String cedula){

        return null;
    }


}
