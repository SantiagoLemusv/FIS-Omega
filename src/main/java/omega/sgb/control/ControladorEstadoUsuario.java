package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.Multa;
import omega.sgb.dominio.Prestamo;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorEstadoUsuario {
    private Connection connection;
    public ControladorEstadoUsuario(Connection conexionGeneral) {
        this.connection = conexionGeneral;
    }

    public void traerPrestamos(){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM PRESTAMO WHERE PERSONAID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, SingletonControladores.getUsuarioActual().getId());

            ResultSet resultSet = statement.executeQuery();

            SingletonControladores.getUsuarioActual().getPrestamos().clear(); // Clear the list before adding new results

            while (resultSet.next()) {
                Prestamo prestamoAux = new Prestamo();
                Date fechaSqlAux;
                fechaSqlAux = resultSet.getDate("FECHAPRESTAMO");
                prestamoAux.setFechaPrestamo(fechaSqlAux);
                libroAux.setId(resultSet.getInt("ID"));
                libroAux.setUbicacion(resultSet.getString("UBICACION"));
                libroAux.setNumeroClasificacion(resultSet.getString("NUMEROCLASIFICACION"));
                libroAux.setLibroVirtual(libroVirtual);
                libroAux.setEstadoLibroFisicoId(resultSet.getInt("ESTADOLIBROFISICOID"));

                listaLibrosFisicos.add(libroAux);
            }

            // Close resources
            resultSet.close();
            statement.close();

            System.out.println("Búsqueda libros fisicos finalizada.");
            if (listaLibrosFisicos.isEmpty()) {
                System.out.println("Ningún libro fisico encontrado con el título especificado.");
            } else {
                System.out.println(listaLibrosVirtuales.size() + " libros fisicos encontrados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPrestamos;
    }

    public List<Multa> traerMultas(){
        List<Multa> listaMultas = new ArrayList<>();

        return listaMultas;
    }
}
