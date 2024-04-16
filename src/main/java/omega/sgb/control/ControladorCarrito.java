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

public class ControladorCarrito {
    private List<LibroFisico> librosFisicosTotal = new ArrayList<LibroFisico>();

    public void obtenerIdLibros() {
        List<Integer> idLibros = new ArrayList<Integer>();
        try (Connection connection = SQL.getConexion()) {
            String sql = "SELECT ID FROM LIBROFISICO";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Recorremos cada fila del resultado
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID"); // Obtenemos el valor entero de la columna "ID"
                        idLibros.add(id); // Añadimos el ID a la lista idLibros
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        llenarListaLibrosFisicos(idLibros);
    }

    public void llenarListaLibrosFisicos(List<Integer> idLibros) {

        try (Connection connection = SQL.getConexion()) {
            String sql = "SELECT UBICACION, NUMEROCLASIFICACION, LIBROVIRTUALID, ESTADOLIBROFISICOID FROM LIBROFISICO WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (int id : idLibros) { // Iterate through the idLibros list
                    // Set the ID parameter for the current iteration
                    preparedStatement.setInt(1, id);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            LibroFisico auxLibroFisico = new LibroFisico();
                            auxLibroFisico.setUbicacion(resultSet.getString("UBICACION"));
                            auxLibroFisico.setNumeroClasificacion(resultSet.getString("NUMEROCLASIFICACION"));
                            auxLibroFisico.setEstadoLibroFisicoId(resultSet.getInt("ESTADOLIBROFISICOID"));

                            // Add the LibroFisico object to the list
                            librosFisicosTotal.add(auxLibroFisico);
                        }
                    }
                }//cierra for
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

