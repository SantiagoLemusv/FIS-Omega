package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.dominio.PersonaLector;
import omega.sgb.dominio.Prestamo;
import omega.sgb.integracion.ProcesarFecha;


import java.sql.*;
import java.util.List;

//No debe tener dependencias a las pantallas
public class ControladorPrestamo {
    private PersonaLector lectorActual = new PersonaLector();
    private ProcesarFecha procesarFecha;
    private Connection connection;
    private ControladorActualizarApp controladorActualizarApp ;
    private ControladorBusquedaLibro controladorBusquedaLibro ;
    private ControladorEstadoUsuario controladorEstadoUsuario ;

    public ControladorPrestamo(Connection conexionGeneral, ProcesarFecha procesarFecha, ControladorActualizarApp controladorActualizarApp, ControladorBusquedaLibro controladorBusquedaLibro, ControladorEstadoUsuario controladorEstadoUsuario) {
        this.connection = conexionGeneral;
        this.procesarFecha = procesarFecha;
        this.controladorActualizarApp=controladorActualizarApp;
        this.controladorBusquedaLibro= controladorBusquedaLibro;
        this.controladorEstadoUsuario=controladorEstadoUsuario;
    }

    public PersonaLector getLectorActual() {
        return lectorActual;
    }

    public ControladorEstadoUsuario getControladorEstadoUsuario() {
        return controladorEstadoUsuario;
    }

    public void setLectorActual(Integer lectorActualCedula) throws SQLException {
        String sql =
                "SELECT * "+
                        "FROM PERSONA "+
                        "WHERE CEDULA = ?";

        System.out.println("ejecutó query cedula");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            System.out.println("cedula"+lectorActualCedula);
            preparedStatement.setInt(1, lectorActualCedula);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lectorActual.setId(resultSet.getInt("ID"));
                    lectorActual.setCedula(lectorActualCedula);
                    controladorEstadoUsuario.traerPrestamos(lectorActual);

                }
            }
        }
    }

    public boolean consultarLector() throws SQLException {
        System.out.println("ENTRA CONSULTAR");
        String sql =
                "SELECT PR.ESTADOPRESTAMOID "+
                        "FROM PRESTAMO PR "+
                        "INNER JOIN PERSONA PE ON PE.ID = PR.PERSONAID "+
                        "WHERE PE.CEDULA = ? AND PR.ESTADOPRESTAMOID = 2";

        System.out.println("ejecutó query prestamo");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            System.out.println("cedula"+lectorActual.getCedula());
            preparedStatement.setInt(1, lectorActual.getCedula());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Lector tiene conflictos");
                    return true;
                }
            }
        }
        System.out.println("Lector no tiene problemas");
        return false;
    }

    public LibroFisico agregarLibroCarrito(){
        //el usuario mete los libros que quiera en carritoLibros
        LibroVirtual libroVirtualTemp1 = new LibroVirtual();
        libroVirtualTemp1.setId(1);
        libroVirtualTemp1.setIsbn("1541675453");
        libroVirtualTemp1.setTitulo("Lady Masacre");
        libroVirtualTemp1.setCantidadCopias(1);
        libroVirtualTemp1.setAutor("Mario Mendoza");
        libroVirtualTemp1.setMultaValorDia(2500);
        libroVirtualTemp1.setDuracionPrestamo(15);

        LibroFisico libroTemp1 = new LibroFisico();
        libroTemp1.setId(1);
        libroTemp1.setUbicacion("piso 0");
        libroTemp1.setNumeroClasificacion("D934 Q23");
        libroTemp1.setLibroVirtual(libroVirtualTemp1);
        libroTemp1.setEstadoLibroFisicoId(1); //1 disponible, 2 prestado
        SingletonControladores.getUsuarioActual().getCarrito().add(libroTemp1);
        return libroTemp1;
    }

    public void confirmarPrestamo(List<LibroFisico> carritoLibros) throws SQLException {
        for(LibroFisico libroAct : carritoLibros){
            Prestamo prestamoAux = crearPrestamo(libroAct, lectorActual);
            lectorActual.getPrestamos().add(prestamoAux);
            controladorActualizarApp.cambiarEstadoLibro(libroAct.getId(),2);
            actualizarPrestamoBD(prestamoAux);
        }

    }
    public Prestamo crearPrestamo(LibroFisico libroFisico, PersonaLector lectorActual){
        Prestamo prestamoAux = new Prestamo();
        prestamoAux.setFechaPrestamo(procesarFecha.getFechaActual());
        prestamoAux.setFechaDevolucion(procesarFecha.sumarDiasAFecha(procesarFecha.getFechaActual(), libroFisico.getLibroVirtual().getDuracionPrestamo()));
        prestamoAux.setPersona(lectorActual);
        prestamoAux.setEstadoPrestamoId(1);
        prestamoAux.setLibro(libroFisico);
        prestamoAux.setMulta(null);
        return prestamoAux;
    }


    public void actualizarPrestamoBD(Prestamo prestamoAct) throws SQLException {
        // Remove the line creating a new connection
        // Connection connection = null;
        System.out.println("Prestamo:");
        System.out.println(prestamoAct.getFechaPrestamo());
        System.out.println(prestamoAct.getFechaDevolucion());
        System.out.println(prestamoAct.getPersona().getId());
        System.out.println(prestamoAct.getLibro().getId());
        System.out.println(prestamoAct.getEstadoPrestamoId());
        //System.out.println(prestamoAct.getMulta().getId());

        connection.setAutoCommit(false); // ... (remaining code within try block)

        // Actualizar tabla EstadoPrestamo
        String updateEstadoPrestamoSql = "INSERT INTO PRESTAMO (FECHAPRESTAMO, FECHADEVOLUCION, PERSONAID, LIBROFISICOID, " +
                "ESTADOPRESTAMOID, MULTAID) " +
                "VALUES (?, ?, ?, ?, ?, NULL)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateEstadoPrestamoSql)) {
            preparedStatement.setDate(1, procesarFecha.fechaJavaToFechaSql(prestamoAct.getFechaPrestamo()));
            preparedStatement.setDate(2, procesarFecha.fechaJavaToFechaSql(prestamoAct.getFechaDevolucion()));
            preparedStatement.setInt(3, lectorActual.getId());
            preparedStatement.setInt(4, prestamoAct.getLibro().getId());
            preparedStatement.setInt(5, prestamoAct.getEstadoPrestamoId()); //1 activo, 2 vencido, 3 devuelto
            preparedStatement.executeUpdate();
        }

        connection.commit(); // Confirma los cambios si todas las actualizaciones son exitosas
        System.out.println("Préstamo actualizado exitosamente");

    }

    //metodos devolver libro
    public void actualizarEstadoPrestamo(Prestamo prestamoAct) throws SQLException {
        connection.setAutoCommit(false); // Deshabilitar confirmación automática

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE PRESTAMO SET ESTADOPRESTAMOID = ? WHERE ID = ?")) {
            preparedStatement.setInt(1, 3);
            preparedStatement.setInt(2, prestamoAct.getId());
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

        actualizarLibroDevuelto(prestamoAct);
    }


    public void actualizarLibroDevuelto(Prestamo prestamoAct){
        try {
            // Prepare the SQL with a placeholder for the title search term
            String sql = "SELECT * FROM PRESTAMO WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, prestamoAct.getId());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                controladorBusquedaLibro.cambiarEstadoLibro(controladorBusquedaLibro.traerLibroFisico(resultSet.getInt("LIBROFISICOID")), 1);
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validarCedulaLector() {


    }
}
