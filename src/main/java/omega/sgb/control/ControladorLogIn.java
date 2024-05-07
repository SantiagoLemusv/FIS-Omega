package omega.sgb.control;

import omega.sgb.SingletonPantallas;
import omega.sgb.SingletonControladores;
import omega.sgb.dominio.Persona;
import omega.sgb.integracion.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorLogIn {
    private Connection connection;
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
        boolean camposValidos = validarCampos(cedula, contrasena, contrasenaConfirmar, nombreCompleto);

        if(!camposValidos){
            return false;
        }

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

    private boolean validarCampos(String cedula, String contrasena, String contrasenaConfirmar,String nombreCompleto) {
        // Validar campos vacíos
        if (cedula.isEmpty() || contrasena.isEmpty() || nombreCompleto.isEmpty()) {
            return false;
        }

        // Validar longitud de la contraseña (mínimo 8 caracteres)
        if (contrasena.length() < 8) {
            return false;
        }

        //Validar que la contraseña se escribio correctamente
        if(!contrasena.equals(contrasenaConfirmar)){
            return false;
        }

        // Validar caracteres incorrectos en la contraseña
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(contrasena);
        if (!matcher.matches()) {
            return false;
        }

        // Otras validaciones adicionales...

        return true;
    }
}
