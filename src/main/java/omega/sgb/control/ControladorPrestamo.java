package omega.sgb.control;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import omega.sgb.SingletonControladores;
import omega.sgb.SingletonPantallas;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.dominio.Prestamo;
import omega.sgb.integracion.FechaActual;
import omega.sgb.integracion.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//No debe tener dependencias a las pantallas
public class ControladorPrestamo {

    private Connection connection;
    public ControladorPrestamo(Connection conexionGeneral) throws SQLException {
        this.connection = conexionGeneral;
    }

    public Boolean consultarLector(String cedula) throws SQLException {
        System.out.println("ENTRA CONSULTAR");
        Integer numCedula = Integer.parseInt(cedula);
            String sql =
                    "SELECT PR.ESTADOPRESTAMOID"+
                    "FROM PRESTAMO PR"+
                    "INNER JOIN PERSONA PE ON PE.ID = PR.PERSONAID"+
                    "WHERE PE.CEDULA = 1019983323 AND PR.ESTADOPRESTAMOID = 2";

            System.out.println("ejecutó query prestamo");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, numCedula);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    }
                }
            }

        return false;
    }

    public LibroFisico agregarLibroCarrito(){//cambiar, no deberia retornar un libro
        //el usuario mete los libros que quiera en carritoLibros
        LibroVirtual libroVirtualTemp1 = new LibroVirtual();
        libroVirtualTemp1.setId(1);
        libroVirtualTemp1.setIsbn("1541675453");
        libroVirtualTemp1.setTitulo("Lady Masacre");
        libroVirtualTemp1.setCantidad(1);
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
    public void confirmarPrestamo(List<LibroFisico> carritoLibros){
        for(LibroFisico libroAct : carritoLibros){
            Prestamo prestamoAux = crearPrestamo(libroAct);
            SingletonControladores.getUsuarioActual().getPrestamos().add(prestamoAux);
            actualizarPrestamoBD(prestamoAux);
        }

    }
    public Prestamo crearPrestamo(LibroFisico libroFisico){
        Prestamo prestamoAux = new Prestamo();
        prestamoAux.setFechaPrestamo(FechaActual.getFecha());
        prestamoAux.setFechaDevolucion(FechaActual.sumarDiasFecha(FechaActual.getFecha(), libroFisico.getLibroVirtual().getDuracionPrestamo()));
        prestamoAux.setPersona(SingletonControladores.getUsuarioActual());
        prestamoAux.setEstadoPrestamoId(1);
        prestamoAux.setLibro(libroFisico);
        prestamoAux.setMulta(null);
        return prestamoAux;
    }


    public void actualizarPrestamoBD(Prestamo prestamoAct) {
        Connection connection = null; // Declare connection variable outside try-catch block
        try {
            connection = SQL.getConexion(); // Get connection within try block
            connection.setAutoCommit(false); // ... (remaining code within try block)

            // Actualizar tabla EstadoPrestamo
            String updateEstadoPrestamoSql = "INSERT INTO PRESTAMO (FECHAPRESTAMO, FECHADEVOLUCION, PERSONAID, LIBROFISICOID, " +
                                                                    "ESTADOPRESTAMOID, MULTAID)" +
                                             "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateEstadoPrestamoSql)) {
                preparedStatement.setString(1, prestamoAct.getFechaPrestamo());
                preparedStatement.setString(2, prestamoAct.getFechaDevolucion());
                preparedStatement.setInt(3, prestamoAct.getPersona().getId());
                preparedStatement.setInt(4, prestamoAct.getLibro().getId());
                preparedStatement.setInt(5, prestamoAct.getEstadoPrestamoId()); //1 activo, 2 vencido, 3 devuelto
                preparedStatement.setInt(6, 0); // Set MULTAID correctly
                preparedStatement.executeUpdate();
            }

            // (Opcional) Actualizar tablas adicionales según su lógica - modifique la consulta y los parámetros en consecuencia
            // String updateAnotherTableSql = "...";
            // ... (prepare y ejecute la actualización para otra tabla)

            connection.commit(); // Confirma los cambios si todas las actualizaciones son exitosas
            System.out.println("Préstamo actualizado exitosamente");
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback if connection exists
                }
                System.out.println("Error al actualizar préstamo. Se revierte la transacción.");
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close(); // Close connection in finally block
                } catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }


}
