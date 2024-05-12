package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.dominio.Multa;
import omega.sgb.integracion.ProcesarFecha;

import java.io.IOException;
import java.sql.*;
import java.util.Date;


public class ControladorActualizarApp {
    private final Connection connection;
    private final ProcesarFecha procesarFecha;
    public ControladorActualizarApp(Connection conexionGeneral, ProcesarFecha procesarFecha) throws SQLException {
        this.connection = conexionGeneral;
        this.procesarFecha = procesarFecha;
    }

    //Borrar reservas cada minuto------------------------------------------------------------------

    public void validarReservasVencidas() throws SQLException {
        String sql = "SELECT * FROM LIBROSRESERVADOS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    java.sql.Date fechaSqlAux;
                    fechaSqlAux = resultSet.getDate("FECHARESERVA");
                    Date fechaJavaAux = procesarFecha.fechaSqlToFechaJava(fechaSqlAux);
                    if(procesarFecha.haPasadoUnMinuto(fechaJavaAux)){
                        cambiarEstadoLibro(resultSet.getInt("LIBROFISICOID"), 1);
                        eliminarReserva(resultSet.getInt("ID"));
                    }
                }
            }
        }
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

    //Buscar prestamos vencidos y generar multa------------------------------------------------------------
    public void validarPrestamosVencidos() throws SQLException {
        String sql = "SELECT * FROM PRESTAMO";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    java.sql.Date fechaSqlAux;
                    fechaSqlAux = resultSet.getDate("FECHADEVOLUCION");
                    Date fechaJavaAux = procesarFecha.fechaSqlToFechaJava(fechaSqlAux);
                    Integer multaId = resultSet.getInt("MULTAID");
                    if(procesarFecha.haExpirado(fechaJavaAux) && multaId == 0){
                        LibroFisico libroActual = traerLibroFisico(resultSet.getInt("LIBROFISICOID"));
                        asignarMultaEnPrestamo(resultSet.getInt("ID"), generarMultaParaPrestamo(libroActual.getLibroVirtual().getMultaValorDia()));
                    }
                }
            }
        }
    }

    public Integer generarMultaParaPrestamo(Integer valorMultaPorDia) throws SQLException {
        Multa nuevaMulta = new Multa(valorMultaPorDia, procesarFecha.getFechaActual(), null);

        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO MULTA (MONTOPAGAR, FECHAEMISION, PAGOID, DIASPASADOS) VALUES (?, ?, NULL, ?)")) {
            preparedStatement.setInt(1, nuevaMulta.getMontoPagar());
            preparedStatement.setDate(2, procesarFecha.fechaJavaToFechaSql(nuevaMulta.getFechaEmision()));
            preparedStatement.setInt(3, 1);
            preparedStatement.executeUpdate(); // Ejecutar la sentencia preparada

        } catch (SQLException e) {
            throw e; // Volver a lanzar la excepción para su manejo
        } finally {
            connection.commit(); // Confirmar la transacción si es exitosa
        }

        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT ID FROM MULTA WHERE MONTOPAGAR = ? AND FECHAEMISION = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, nuevaMulta.getMontoPagar());
            statement.setDate(2, procesarFecha.fechaJavaToFechaSql(nuevaMulta.getFechaEmision()));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void asignarMultaEnPrestamo(Integer idPrestamo, Integer idMulta) throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE PRESTAMO SET MULTAID = ?, ESTADOPRESTAMOID = 2 WHERE ID = ?")) {
            preparedStatement.setInt(1, idMulta);
            preparedStatement.setInt(2, idPrestamo);
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows != 1) {
                System.out.println("¡Error! No se actualizó ningún prestamo.");
            } else {
                System.out.println("La asignacion de multa a prestamo se realizo correctamente");
            }
        } catch (SQLException e) {
            throw e; // Re-throw the exception for handling
        } finally {
            connection.commit(); // Commit the transaction if successful
        }

    }


    //Buscar multas pasadas de hoy sin pagar y actualizar monto a pagar---------------------------------------
    public void validarNuevoCostoDeMultas() throws SQLException {
        String sql = "SELECT * FROM MULTA";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    java.sql.Date fechaSqlAux;
                    fechaSqlAux = resultSet.getDate("FECHAEMISION");
                    Date fechaJavaAux = procesarFecha.fechaSqlToFechaJava(fechaSqlAux);
                    Integer multaId = resultSet.getInt("ID");
                    Integer numDiasAtrasados = procesarFecha.calcularDiferenciaDias(fechaJavaAux);
                    if(numDiasAtrasados != resultSet.getInt("DIASPASADOS")){
                        LibroFisico libroActual = traerLibroFisico(buscarLibroIdEnPrestamoConMulta(multaId));
                        Integer nuevoCostoMulta = libroActual.getLibroVirtual().getMultaValorDia() * numDiasAtrasados;
                        setNuevoCostoMulta(multaId, nuevoCostoMulta, numDiasAtrasados);
                    }
                }
            }
        }
    }

    public Integer buscarLibroIdEnPrestamoConMulta(Integer idMulta){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT LIBROFISICOID FROM PRESTAMO WHERE MULTAID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idMulta);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("LIBROFISICOID");
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setNuevoCostoMulta(Integer idMulta, Integer nuevoCosto, Integer diasPasados) throws SQLException {
        System.out.println("idMulta "+ idMulta);
        System.out.println("nuevo costo "+nuevoCosto);
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE MULTA SET MONTOPAGAR = ?, DIASPASADOS = ? WHERE ID = ?")) {
            preparedStatement.setInt(1, nuevoCosto);
            preparedStatement.setInt(2, diasPasados);
            preparedStatement.setInt(3, idMulta);
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows != 1) {
                System.out.println("¡Error! No se actualizó el valor de niunguna multa.");
            } else {
                System.out.println("La asignacion de el nuevo costo de la multa fue exitosa");
            }
        } catch (SQLException e) {
            throw e; // Re-throw the exception for handling
        } finally {
            connection.commit(); // Commit the transaction if successful
        }
    }

    //Generales-----------------------------------------------------------------------------------------------
    public LibroFisico traerLibroFisico (Integer idLibroFisico){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM LIBROFISICO WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idLibroFisico);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LibroFisico libroFisico = new LibroFisico();
                libroFisico.setId(resultSet.getInt("ID"));
                libroFisico.setUbicacion(resultSet.getString("UBICACION"));
                libroFisico.setNumeroClasificacion(resultSet.getString("NUMEROCLASIFICACION"));
                libroFisico.setLibroVirtual(traerLibroVirtual(resultSet.getInt("LIBROVIRTUALID")));
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

    public LibroVirtual traerLibroVirtual (Integer idLibroVirtual){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM LIBROVIRTUAL WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idLibroVirtual);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LibroVirtual libroVirtual = new LibroVirtual();
                libroVirtual.setId(resultSet.getInt("ID"));
                libroVirtual.setIsbn(resultSet.getString("ISBN"));
                libroVirtual.setTitulo(resultSet.getString("TITULO"));
                libroVirtual.setCantidadCopias(resultSet.getInt("CANTIDAD"));
                libroVirtual.setAutor(resultSet.getString("AUTOR"));
                libroVirtual.setMultaValorDia(resultSet.getInt("MULTAVALORDIA"));
                libroVirtual.setDuracionPrestamo(resultSet.getInt("DURACIONPRESTAMO"));

                // Get the image from the blob (assuming conversion logic remains the same)
                /*Blob imagenBlob = resultSet.getBlob("IMAGENLIBRO");
                libroVirtual.setImagenLibro(conversorImagen.blobToImageView(imagenBlob));*/

                return libroVirtual;
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
