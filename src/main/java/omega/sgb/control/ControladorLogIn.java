package omega.sgb.control;

import omega.sgb.SingletonControladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Boolean nuevoUsuarioCrear(String cedula, String contrasena) throws SQLException {
        Integer numCedula = Integer.parseInt(cedula);
        String sql = "SELECT ID, TIPOPERSONAID, NOMBRE FROM PERSONA WHERE CEDULA = ? AND CONTRASENA = ?";
        System.out.println("ejecutó crear usuario");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, numCedula);
            preparedStatement.setString(2, contrasena);
/*
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getInt("TIPOPERSONAID") == 1) {
                        SingletonControladores.crearUsuarioActualBibliotecario();
                    } else if (resultSet.getInt("TIPOPERSONAID") == 2) { .
                        SingletonControladores.crearUsuarioActualLector();
                    }
                    SingletonControladores.getUsuarioActual().setId(resultSet.getInt("ID"));
                    SingletonControladores.getUsuarioActual().setNombre(resultSet.getString("NOMBRE"));
                    SingletonControladores.getUsuarioActual().setCedula(numCedula);
                    SingletonControladores.getUsuarioActual().setContrasena(contrasena);
                    return true;
                }
            }*/
        }
        return false;
    }



}
