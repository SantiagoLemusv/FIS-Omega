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

    public List<Prestamo> traerPrestamos(Persona usuarioactual) {
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM PRESTAMO WHERE PERSONAID = ? AND ESTADOPRESTAMOID != 3";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, usuarioactual.getId());

            ResultSet resultSet = statement.executeQuery();

            usuarioactual.getPrestamos().clear(); // Clear the list before adding new results
            usuarioactual.getTarjetas().clear();
            usuarioactual.getMultas().clear();

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
                prestamoAux.setMulta(traerMulta(idMulta, usuarioactual));
                usuarioactual.getPrestamos().add(prestamoAux);
            }
            return usuarioactual.getPrestamos();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LibroFisico traerLibroFisico(Integer idLibroFisico) {
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

    public LibroVirtual traerLibroVirtual(Integer idLibroVirtual) {
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

    public Multa traerMulta(Integer idMulta, Persona usuario) {
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
                Integer idPago = resultSet.getInt("PAGOID");
                multaAux.setPago(traerPago(idPago, usuario));
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

    public List<String> listaStringPrestamos(List<Prestamo> listaPrestamos) {
        List<String> listaString = new ArrayList<>();
        for (Prestamo prestamoAct : listaPrestamos) {
            String aux;
            aux = prestamoAct.getLibro().getLibroVirtual().getTitulo() + prestamoAct.getFechaDevolucion();
            listaString.add(aux);
        }
        return listaString;
    }

    public List<String> listaStringMulta(List<Prestamo> listaPrestamos) {
        List<String> listaString = new ArrayList<>();
        for (Prestamo prestamoAct : listaPrestamos) {
            if (prestamoAct.getMulta() != null && prestamoAct.getMulta().getPago() == null) {
                String aux;
                aux = prestamoAct.getLibro().getLibroVirtual().getTitulo() + ", " + prestamoAct.getMulta().getDiasPasados() + " día(s) vencido";
                listaString.add(aux);
            }
        }
        return listaString;
    }

    public List<String> listaStringTarjeta(List<Tarjeta> listaTarjetas) {
        List<String> listaString = new ArrayList<>();
        for (Tarjeta i : listaTarjetas) {
            String aux;
            String numero = i.getNumero().toString();
            aux = i.getEntidadBancaria() + ": " + numero;
            listaString.add(aux);
            System.out.println(aux);
        }
        return listaString;
    }

    public void traerTarjetas() {
        Persona persona = SingletonControladores.getUsuarioActual();
        persona.getTarjetas().clear();
        try {
            String sql = "SELECT * FROM TARJETA WHERE PERSONAID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("ID PERSONA GET TARJETA: " + persona.getId());
            statement.setInt(1, persona.getId());
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Ejecuto query en traer tarjetas");
            while (resultSet.next()) {
                System.out.println("entro al while");
                Tarjeta tarjetaAux = new Tarjeta();
                tarjetaAux.setId(resultSet.getInt("ID"));
                tarjetaAux.setNumero(resultSet.getLong("NUMERO"));
                System.out.println("NUMERO TARJETA" + (resultSet.getInt("ID")));
                Date fechaSqlAux;
                fechaSqlAux = resultSet.getDate("FECHAVENCIMIENTO");
                tarjetaAux.setFechaVencimiento(procesarFecha.fechaSqlToFechaJava(fechaSqlAux));
                tarjetaAux.setEntidadBancaria(resultSet.getString("ENTIDADBANCARIA"));
                tarjetaAux.setTipoTarjetaId(resultSet.getInt("TIPOTARJETAID"));
                tarjetaAux.setPersona(persona);
                System.out.println(tarjetaAux);
                persona.getTarjetas().add(tarjetaAux);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LibroFisico traerLibroReservado() {
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
        if (prestamoAct.getMulta() != null) {
            return;
        }

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

    public Tarjeta traerTarjeta(Integer idTarjeta, Persona usuario) {
        try {
            String sqlObtenerTarjeta = "SELECT * FROM TARJETA T WHERE T.ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlObtenerTarjeta);
            preparedStatement.setInt(1, idTarjeta);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Tarjeta tarjetaAux = new Tarjeta();
                tarjetaAux.setId(resultSet.getInt("ID"));
                tarjetaAux.setNumero(resultSet.getLong("NUMERO"));
                Date fechaSqlAux;
                fechaSqlAux = resultSet.getDate("FECHAVENCIMIENTO");
                tarjetaAux.setFechaVencimiento(procesarFecha.fechaSqlToFechaJava(fechaSqlAux));
                tarjetaAux.setEntidadBancaria(resultSet.getString("ENTIDADBANCARIA"));
                tarjetaAux.setTipoTarjetaId(resultSet.getInt("TIPOTARJETAID"));
                tarjetaAux.setPersona(usuario);
                tarjetaAux.setTitular(resultSet.getString("TITULAR"));
                usuario.getTarjetas().add(tarjetaAux);
                return tarjetaAux;
            }
            // Close resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pago traerPago(Integer idPago, Persona usuario){
        try{
            String sqlObtenerPago = "SELECT * FROM PAGO P WHERE P.ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlObtenerPago);
            preparedStatement.setInt(1, idPago);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Pago pago = new Pago();
                pago.setId(resultSet.getInt("ID"));
                pago.setMontoTotal(resultSet.getInt("MONTOTOTAL"));
                Date fechaSqlAux;
                fechaSqlAux = resultSet.getDate("FECHAEMISION");
                pago.setFecha(procesarFecha.fechaSqlToFechaJava(fechaSqlAux));
                Integer idTarjeta = resultSet.getInt("TARJETAID");
                pago.setTarjeta(traerTarjeta(idTarjeta, usuario));
                return pago;
            }
            // Close resources
            resultSet.close();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
