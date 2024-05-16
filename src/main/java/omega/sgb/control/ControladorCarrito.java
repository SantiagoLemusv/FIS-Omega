package omega.sgb.control;

import omega.sgb.SingletonControladores;

import omega.sgb.dominio.LibroFisico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorCarrito {
    private final Connection connection;
    public ControladorCarrito(Connection conexionGeneral){
        this.connection = conexionGeneral;
    }
    private List<LibroFisico> librosFisicosTotal = new ArrayList<LibroFisico>();

    public void obtenerIdLibros() throws SQLException {
        List<Integer> idLibros = new ArrayList<Integer>();
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
        llenarListaLibrosFisicos(idLibros);
    }

    public void llenarListaLibrosFisicos(List<Integer> idLibros) throws SQLException {
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
    }

    public Blob getImagenLibroById() throws SQLException {
        String sql =
                "SELECT IMG.IMAGEN " +
                        "FROM IMAGENLIBRO IMG " +
                        "INNER JOIN LIBROVIRTUAL LV ON IMG.ID = LV.IMAGENID " +
                        "WHERE LV.ID = 4";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBlob("IMAGEN"); // Get the BLOB data
                }
            }
        }
        return null; // Return null if no image found
    }

    public boolean agregarLibro(LibroFisico libroSeleccionado){
        List<LibroFisico> carrito= SingletonControladores.getUsuarioActual().getCarrito();
        if(!carrito.contains(libroSeleccionado)){
            carrito.add(libroSeleccionado);
            return true;
        }else
        return false;
    }



}

