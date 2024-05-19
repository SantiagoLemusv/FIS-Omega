package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.*;
import omega.sgb.integracion.ConversorImagen;
import omega.sgb.integracion.ProcesarFecha;

import java.io.IOException;
import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorEstadoUsuario {
    private Connection connection;
    private ProcesarFecha procesarFecha;
    private ConversorImagen conversorImagen;
    public ControladorEstadoUsuario(Connection conexionGeneral, ProcesarFecha procesarFecha, ConversorImagen conversorImagen) {
        this.connection = conexionGeneral;
        this.procesarFecha = procesarFecha;
        this.conversorImagen = conversorImagen;
    }

    public void traerPrestamos(Persona usuarioactual){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM PRESTAMO WHERE PERSONAID = ? AND ESTADOPRESTAMOID != 3";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, usuarioactual.getId());

            ResultSet resultSet = statement.executeQuery();

            usuarioactual.getPrestamos().clear(); // Clear the list before adding new results

            while (resultSet.next()) {
                Prestamo prestamoAux = new Prestamo();
                prestamoAux.setId(resultSet.getInt("ID"));
                Date fechaSqlAux;
                fechaSqlAux = resultSet.getDate("FECHAPRESTAMO");
                prestamoAux.setFechaPrestamo(procesarFecha.fechaSqlToFechaJava(fechaSqlAux));
                fechaSqlAux = resultSet.getDate("FECHADEVOLUCION");
                prestamoAux.setFechaDevolucion(procesarFecha.fechaSqlToFechaJava(fechaSqlAux));
                prestamoAux.setPersona(usuarioactual);
                prestamoAux.setLibro(traerLibroFisico(resultSet.getInt("LIBROFISICOID")));
                prestamoAux.setEstadoPrestamoId(resultSet.getInt("ESTADOPRESTAMOID"));
                Integer idMulta = resultSet.getInt("MULTAID");
                prestamoAux.setMulta(traerMulta(idMulta,usuarioactual));
                usuarioactual.getPrestamos().add(prestamoAux);
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
                Blob imagenBlob = resultSet.getBlob("IMAGENLIBRO");
                libroVirtual.setImagenLibro(conversorImagen.blobToImageView(imagenBlob));

                return libroVirtual;
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Multa traerMulta(Integer idMulta, Persona usuario){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM MULTA WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idMulta);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Multa multaAux = new Multa();
                multaAux.setId(resultSet.getInt("ID"));
                multaAux.setMontoPagar(resultSet.getInt("MONTOPAGAR"));
                Date fechaSqlAux;
                fechaSqlAux = resultSet.getDate("FECHAEMISION");
                multaAux.setFechaEmision(procesarFecha.fechaSqlToFechaJava(fechaSqlAux));
                multaAux.setPago(null);
                multaAux.setDiasPasados(resultSet.getInt("DIASPASADOS"));

                usuario.getMultas().add(multaAux);
                return multaAux;
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> listaStringPrestamos(List<Prestamo> listaPrestamos){
        List<String> listaString = new ArrayList<>();
        for(Prestamo prestamoAct : listaPrestamos){
            String aux;
            aux = prestamoAct.getLibro().getLibroVirtual().getTitulo() + prestamoAct.getFechaDevolucion();
            listaString.add(aux);
        }
        return listaString;
    }

    public List<String> listaStringMulta(List<Prestamo> listaPrestamos){
        List<String> listaString = new ArrayList<>();
        for(Prestamo prestamoAct : listaPrestamos){
            if(prestamoAct.getMulta() != null){
                String aux;
                aux = prestamoAct.getLibro().getLibroVirtual().getTitulo() + " "+ prestamoAct.getMulta().getDiasPasados()+ " día(s) vencido";
                listaString.add(aux);
            }
        }
        return listaString;
    }

    public LibroFisico traerLibroReservado(){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT LIBROFISICOID FROM LIBROSRESERVADOS WHERE PERSONAID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, SingletonControladores.getUsuarioActual().getId());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LibroFisico libroReservado = new LibroFisico();
                libroReservado = traerLibroFisico(resultSet.getInt("LIBROFISICOID"));
                return libroReservado;
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void renovarPrestamo(Prestamo prestamoAct) throws SQLException {
        connection.setAutoCommit(false); // Deshabilitar confirmación automática

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE PRESTAMO SET FECHAPRESTAMO = ?, FECHADEVOLUCION = ? WHERE ID = ?")) {
            preparedStatement.setDate(1, procesarFecha.fechaJavaToFechaSql(procesarFecha.getFechaActual()));
            preparedStatement.setDate(2, procesarFecha.fechaJavaToFechaSql(procesarFecha.sumarDiasAFecha(procesarFecha.getFechaActual(), prestamoAct.getLibro().getLibroVirtual().getDuracionPrestamo())));
            preparedStatement.setInt(3, prestamoAct.getId());
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows != 1) {
                System.out.println("¡Error! No se actualizó ningún prestamo.");
            } else {
                System.out.println("Estado del prestamo actualizado exitosamente.");
            }
        } catch (SQLException e) {
            throw e; // Re-throw the exception for handling
        } finally {
            connection.commit(); // Commit the transaction if successful
        }

    }


}
