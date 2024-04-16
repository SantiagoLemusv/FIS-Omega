package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.integracion.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//No debe tener dependencias a las pantallas
public class ControladorPrestamo {
    private List<LibroFisico> carrito= new ArrayList<>();

    public Boolean consultarLector(String cedula) {
        try (Connection connection = SQL.getConexion()) {
            String sql = "SELECT ESTADOPRESTAMOID FROM ESTADOPRESTAMO WHERE AND CONTRASENA = ?";
            System.out.println("ejecutó query");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, numCedula);
                preparedStatement.setString(2, contrasena);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void agregarLibroCarrito(LibroFisico libro){
        carrito.add(libro);
    }
    public void confirmarPrestamo(){
    }


}
