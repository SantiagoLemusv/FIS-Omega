package omega.sgb.control;

import omega.sgb.integracion.ProcesarFecha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class ControladorActualizarApp {
    private final Connection connection;
    private final ProcesarFecha procesarFecha;
    public ControladorActualizarApp(Connection conexionGeneral, ProcesarFecha procesarFecha) throws SQLException {
        this.connection = conexionGeneral;
        this.procesarFecha = procesarFecha;
    }

    public void validarReservasVencidas() throws SQLException {
        String sql = "SELECT * FROM LIBROSRESERVADOS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    java.sql.Date fechaSqlAux;
                    fechaSqlAux = resultSet.getDate("FECHARESERVA");
                    Date fechaJavaAux = procesarFecha.fechaSqlToFechaJava(fechaSqlAux);
                    if(haPasadoUnMinuto(fechaJavaAux)){
                        cambiarEstadoLibro(resultSet.getInt("LIBROFISICOID"), 1);
                        eliminarReserva(resultSet.getInt("ID"));
                    }
                }
            }
        }
    }

    public static boolean haPasadoUnMinuto(Date fechaReferencia) {
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Calcular la diferencia en milisegundos
        long diferenciaEnMs = fechaActual.getTime() - fechaReferencia.getTime();

        // Un minuto en milisegundos es 60.000 ms
        long unMinutoEnMs = 60000;

        // Devolver true si ha pasado un minuto o más
        return diferenciaEnMs >= unMinutoEnMs;
    }

    public void cambiarEstadoLibro(Integer idLibroFisico, Integer estadoId) throws SQLException {
        System.out.println("entra a cambiar estado con "+estadoId);
        connection.setAutoCommit(false); // Deshabilitar confirmación automática

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE LIBROFISICO SET ESTADOLIBROFISICOID = ? WHERE ID = ?")) {
            preparedStatement.setInt(1, estadoId);
            preparedStatement.setInt(2, idLibroFisico);
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows != 1) {
                System.out.println("¡Error! No se actualizó ningún libro físico.");
            } else {
                System.out.println("Estado del libro físico actualizado exitosamente.");
            }
        } catch (SQLException e) {
            throw e; // Re-throw the exception for handling
        } finally {
            connection.commit(); // Commit the transaction if successful
        }
    }

    public void eliminarReserva(Integer idReserva) throws SQLException {
        System.out.println("Eliminando reserva con ID: " + idReserva);
        connection.setAutoCommit(false); // Deshabilitar confirmación automática

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM LIBROSRESERVADOS WHERE ID = ?")) {
            preparedStatement.setInt(1, idReserva);
            int filasEliminadas = preparedStatement.executeUpdate();

            if (filasEliminadas != 1) {
                System.out.println("¡Advertencia! No se eliminó ninguna reserva.");
            } else {
                System.out.println("Reserva eliminada exitosamente.");
            }
        } catch (SQLException e) {
            throw e; // Re-lanzar la excepción para su manejo
        } finally {
            connection.commit(); // Confirmar la transacción si es exitosa
        }
    }


}
