package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.Multa;
import omega.sgb.dominio.PersonaLector;
import omega.sgb.dominio.Prestamo;
import omega.sgb.integracion.DataBaseConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControladorEstadoUsuarioTest {

    private static final String URL = "jdbc:h2:./test";
    private static final String USUARIO = "omega";
    private static final String CONTRASENA = "";

    private ControladorEstadoUsuario controladorEstadoUsuario;

    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        Connection connectionH2 = DataBaseConnectionManager.getConnectionH2(URL, USUARIO, CONTRASENA);
        omega.sgb.integracion.InicializadorBD inicializarBD = new omega.sgb.integracion.InicializadorBD(
                connectionH2);
        inicializarBD.initDB();
        SingletonControladores.setConexionGeneral(connectionH2);
        controladorEstadoUsuario = SingletonControladores.getInstanceControladorEstadoUsuario();

    }

    //Validar que se retorna la lista de préstamos, multas y el libro reservado de un lector
    @Test
    void obtenerEstadoCompletoLectorExitoso1(){
        SingletonControladores.crearUsuarioActualLector();
        SingletonControladores.getUsuarioActual().setId(5);
        SingletonControladores.getUsuarioActual().setCedula(1019982313);
        SingletonControladores.getUsuarioActual().setNombre("Ana Cecilia de Armas Caso");
        SingletonControladores.getUsuarioActual().setContrasena("MarilynMonroe24");
        controladorEstadoUsuario.traerPrestamos(SingletonControladores.getUsuarioActual());
        List<Prestamo> prestamoLista = SingletonControladores.getUsuarioActual().getPrestamos();
        List<Multa> multaLista = SingletonControladores.getUsuarioActual().getMultas();
        assertFalse(prestamoLista.isEmpty());

        assertFalse(multaLista.isEmpty());

        LibroFisico libroResrvado = controladorEstadoUsuario.traerLibroReservado();
        assertNotNull(libroResrvado);

    }
    //Validar que no retorna la lista de préstamos, multas y el libro reservado de un lector que no tiene ninguno de esos atributos
    @Test
    void obtenerEstadoCompletoLectorExitoso2(){
        SingletonControladores.crearUsuarioActualLector();
        SingletonControladores.getUsuarioActual().setId(22);
        SingletonControladores.getUsuarioActual().setCedula(1000222333);
        SingletonControladores.getUsuarioActual().setNombre("Mario Mendoza Zambrano");
        SingletonControladores.getUsuarioActual().setContrasena("Fourier71");
        controladorEstadoUsuario.traerPrestamos(SingletonControladores.getUsuarioActual());
        List<Prestamo> prestamoLista = SingletonControladores.getUsuarioActual().getPrestamos();
        List<Multa> multaLista = SingletonControladores.getUsuarioActual().getMultas();
        assertFalse(prestamoLista.isEmpty());

        assertFalse(multaLista.isEmpty());

        LibroFisico libroResrvado = controladorEstadoUsuario.traerLibroReservado();
        assertNotNull(libroResrvado);

    }

    //Valida para los usuarios bibliotecario no mostrar lista de préstamo, multas y libros reservados
    @Test
    void obtenerEstadoCompletoLectorFallido(){
        SingletonControladores.crearUsuarioActualLector();
        SingletonControladores.getUsuarioActual().setId(41);
        SingletonControladores.getUsuarioActual().setCedula(1013259208);
        SingletonControladores.getUsuarioActual().setNombre("Juan David Ramírez Juzga");
        SingletonControladores.getUsuarioActual().setContrasena("luna");
        controladorEstadoUsuario.traerPrestamos(SingletonControladores.getUsuarioActual());
        List<Prestamo> prestamoLista = SingletonControladores.getUsuarioActual().getPrestamos();
        List<Multa> multaLista = SingletonControladores.getUsuarioActual().getMultas();
        assertTrue(prestamoLista.isEmpty());

        assertTrue(multaLista.isEmpty());

        LibroFisico libroResrvado = controladorEstadoUsuario.traerLibroReservado();
        assertNull(libroResrvado);

    }

    //Valida que un lector con un préstamo activo pueda renovar su préstamo
    @Test
    void renovarPrestamoExitoso(){
        //Prestamo prestamoActual = new Prestamo(('2024-05-19'), '2024-05-24', 21, 19, 1, NULL);
        //controladorEstadoUsuario.renovarPrestamo(prestamoActual);



    }

    //Valida que un lector con un préstamo vencido no pueda renovar su préstamo
    @Test
    void renovarPrestamoFallido(){

    }
}