package omega.sgb.control;
import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.integracion.ConversorImagen;
import omega.sgb.integracion.ProcesarFecha;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControladorBusquedaLibro {
    private Connection connection;
    private ConversorImagen conversorImagen;
    private ProcesarFecha procesarFecha;
    private String tituloLibroBusquedaGrande;
    List<LibroVirtual> listaLibrosVirtuales = new ArrayList<>();
    LibroVirtual libroVirtualSeleccionado = new LibroVirtual();
    LibroFisico libroFisicoSeleccionado = new LibroFisico();

    public ControladorBusquedaLibro(Connection conexionGeneral, ConversorImagen conversorImagen, ProcesarFecha procesarFecha) {
        this.connection = conexionGeneral;
        this.conversorImagen = conversorImagen;
        this.procesarFecha = procesarFecha;
    }

    public String getTituloLibroBusquedaGrande() {
        return tituloLibroBusquedaGrande;
    }

    public void setTituloLibroBusquedaGrande(String tituloLibroBusquedaGrande) {
        this.tituloLibroBusquedaGrande = tituloLibroBusquedaGrande;
    }

    public List<LibroVirtual> getListaLibrosVirtuales() {
        return this.listaLibrosVirtuales;
    }

    public LibroVirtual getLibroVirtualSeleccionado() {
        return libroVirtualSeleccionado;
    }

    public void setLibroVirtualSeleccionado(LibroVirtual libroSeleccionado) {
        this.libroVirtualSeleccionado = libroSeleccionado;
    }

    public LibroFisico getLibroFisicoSeleccionado() {
        return libroFisicoSeleccionado;
    }

    public void setLibroFisicoSeleccionado(String clasificacion) {
        LibroFisico libroNuevo = new LibroFisico();
        for(LibroFisico libroFisico : libroVirtualSeleccionado.getLibrosFisicosTotales()){
            if(libroFisico.getNumeroClasificacion().equals(clasificacion)){
                libroNuevo = libroFisico;
            }
        }
        this.libroFisicoSeleccionado = libroNuevo;
    }

    public void setLibroFisicoSeleccionado(LibroFisico libroFisico){
        this.libroFisicoSeleccionado = libroFisico;
    }

    public void buscarLibrosVirtuales(String tituloLibro) {
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM LIBROVIRTUAL WHERE LOWER(TITULO) LIKE LOWER(?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set the search term parameter using a case-insensitive search
            statement.setString(1, tituloLibro + "%");

            ResultSet resultSet = statement.executeQuery();

            listaLibrosVirtuales.clear(); // Clear the list before adding new results

            while (resultSet.next()) {
                // Create a LibroVirtual object and populate data
                LibroVirtual libroAux = new LibroVirtual();
                libroAux.setId(resultSet.getInt("ID"));
                libroAux.setIsbn(resultSet.getString("ISBN"));
                libroAux.setTitulo(resultSet.getString("TITULO"));
                libroAux.setCantidadCopias(resultSet.getInt("CANTIDAD"));
                libroAux.setAutor(resultSet.getString("AUTOR"));
                libroAux.setMultaValorDia(resultSet.getInt("MULTAVALORDIA"));
                libroAux.setDuracionPrestamo(resultSet.getInt("DURACIONPRESTAMO"));

                // Get the image from the blob (assuming conversion logic remains the same)
                Blob imagenBlob = resultSet.getBlob("IMAGENLIBRO");
                libroAux.setImagenLibro(conversorImagen.blobToImageView(imagenBlob));
                libroAux.setLibrosFisicosDisponibles(traerLibrosFisicos(libroAux, 1));
                libroAux.setLibrosFisicosAgotados(traerLibrosFisicos(libroAux, 2));
                libroAux.setLibrosFisicosReservados(traerLibrosFisicos(libroAux, 3));
                libroAux.setLibrosFisicosTotales();
                listaLibrosVirtuales.add(libroAux);
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<LibroFisico> traerLibrosFisicos(LibroVirtual libroVirtual, Integer disponibilidad) {
        List<LibroFisico> listaLibrosFisicos = new ArrayList<>();
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM LIBROFISICO WHERE LIBROVIRTUALID = ? AND ESTADOLIBROFISICOID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, libroVirtual.getId());
            statement.setInt(2, disponibilidad);

            ResultSet resultSet = statement.executeQuery();

            listaLibrosFisicos.clear(); // Clear the list before adding new results

            while (resultSet.next()) {
                // Create a LibroVirtual object and populate data
                LibroFisico libroAux = new LibroFisico();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibrosFisicos;
    }

    public List<String> getPisosLibrosFisicos(List<LibroFisico> librosFisicos){
        List<String> pisosLibrosFisicos = new ArrayList<>();
        for(LibroFisico libroActual : librosFisicos){
            pisosLibrosFisicos.add(libroActual.getUbicacion());
        }
        return pisosLibrosFisicos;
    }

    public List<String> getNumClasificacionLibrosFisicos(List<LibroFisico> librosFisicos){
        List<String> numClasificacionLibrosFisicos = new ArrayList<>();
        for(LibroFisico libroActual : librosFisicos){
            numClasificacionLibrosFisicos.add(libroActual.getNumeroClasificacion());
        }
        return numClasificacionLibrosFisicos;
    }

    public List<String> combinarPisoConClasificacion (List<LibroFisico> librosFisicos){
        List<String> listaPisos = getPisosLibrosFisicos(librosFisicos);
        List<String> listaClasificacion = getNumClasificacionLibrosFisicos(librosFisicos);
        List<String> listaCombinada = new ArrayList<>();
        for (int i = 0; i < listaPisos.size() && i < listaClasificacion.size(); i++) {
            String combinado = listaPisos.get(i) + "," + listaClasificacion.get(i);
            listaCombinada.add(combinado);
        }
        return listaCombinada;
    }

    //Proceso reservar libro---------------------------------------------------------
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

    public void reservarLibro(LibroFisico libroParaReservar) throws SQLException {
        if (getLibroFisicoSeleccionado() == null) {
            System.out.println("libro para reservar es null");
            return;
        }

        connection.setAutoCommit(false); // Deshabilitar confirmación automática

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO LIBROSRESERVADOS (PERSONAID, LIBROFISICOID, FECHARESERVA) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, SingletonControladores.getUsuarioActual().getId());
            preparedStatement.setInt(2, libroParaReservar.getId());
            preparedStatement.setDate(3, procesarFecha.fechaJavaToFechaSql(procesarFecha.getFechaActual()));
            preparedStatement.executeUpdate(); // Ejecutar la sentencia preparada
        } catch (SQLException e) {
            throw e; // Volver a lanzar la excepción para su manejo
        } finally {
            connection.commit(); // Confirmar la transacción si es exitosa
            System.out.println("Reserva realizada exitosamente");
            cambiarEstadoLibro(libroParaReservar, 3);
        }
    }

    public void cambiarEstadoLibro(LibroFisico libroFisico, Integer estadoId) throws SQLException {
        if (libroFisico == null) {
            throw new IllegalArgumentException("LibroFisico no puede ser null");
        }
        System.out.println("entra a cambiar estado con "+estadoId);
        connection.setAutoCommit(false); // Deshabilitar confirmación automática

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE LIBROFISICO SET ESTADOLIBROFISICOID = ? WHERE ID = ?")) {
            preparedStatement.setInt(1, estadoId);
            preparedStatement.setInt(2, libroFisico.getId());
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

    public String validarSiPuedoReservar() throws SQLException {
        String mensaje = "";

        if (!SingletonControladores.getUsuarioActual().getMultas().isEmpty()) {
            mensaje = "Usted tiene multas activas";
        } else if (traerLibroReservado() != null) {
            mensaje = "Usted ya tiene un libro reservado";
        } else if(getLibroFisicoSeleccionado().getEstadoLibroFisicoId().equals(2)){
            mensaje = "Este libro ya está prestado";
        } else if(getLibroFisicoSeleccionado().getEstadoLibroFisicoId().equals(3)) {
            mensaje = "Este libro ya está reservado";
        }
        if (mensaje.isEmpty()) {
            reservarLibro(getLibroFisicoSeleccionado());
            mensaje = "Reserva realizada exitosamente";
        }

        return mensaje;
    }


}