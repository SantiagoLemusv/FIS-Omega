package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.dominio.Multa;
import omega.sgb.dominio.Prestamo;
import omega.sgb.integracion.ProcesarFecha;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorEstadoUsuario {
    private Connection connection;
    private ProcesarFecha procesarFecha;
    private List<Prestamo> listaPrestamos;
    private List<Multa> listaMultas;
    public ControladorEstadoUsuario(Connection conexionGeneral, ProcesarFecha procesarFecha) {
        this.connection = conexionGeneral;
        this.procesarFecha = procesarFecha;
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
                prestamoAux.setId(resultSet.getInt("ID"));
                Date fechaSqlAux;
                fechaSqlAux = resultSet.getDate("FECHAPRESTAMO");
                prestamoAux.setFechaPrestamo(procesarFecha.fechaSqlToFechaJava(fechaSqlAux));
                fechaSqlAux = resultSet.getDate("FECHADEVOLUCION");
                prestamoAux.setFechaDevolucion(procesarFecha.fechaSqlToFechaJava(fechaSqlAux));
                prestamoAux.setPersona(SingletonControladores.getUsuarioActual());
                prestamoAux.setLibro(traerLibroFisico(resultSet.getInt("LIBROFISICOID")));
                prestamoAux.setEstadoPrestamoId(resultSet.getInt("ESTADOPRESTAMOID"));
                Integer idMulta = resultSet.getInt("MULTAID");

                /////////////////////////////////////////////
                if(idMulta != null){

                    /////////////////////////////////////////////
                }
                listaPrestamos.add(prestamoAux);
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LibroFisico traerLibroFisico (Integer idLibroFisico){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM LIBROFISICO WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idLibroFisico);

            ResultSet resultSet = statement.executeQuery();

            SingletonControladores.getUsuarioActual().getPrestamos().clear(); // Clear the list before adding new results

            if (resultSet.next()) {
                LibroFisico libroFisico = new LibroFisico();
                libroFisico.setId(resultSet.getInt("ID"));
                libroFisico.setUbicacion(resultSet.getString("UBICACION"));
                libroFisico.setNumeroClasificacion(resultSet.getString("NUMEROCLASIFICACION"));
                //libroFisico.setLibroVirtual(traerLibroVirtual(resultSet.getInt("LIBROVIRTUALID")));
                libroFisico.setEstadoLibroFisicoId(resultSet.getInt("ESTADOLIBROFISICOID"));
                return libroFisico;
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public LibroVirtual traerLibroVirtual (Integer idLibroVirtual){

    }*/

    public void traerMultas(){
        List<Multa> listaMultas = new ArrayList<>();


    }
}
