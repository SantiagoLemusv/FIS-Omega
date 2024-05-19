package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.*;
import omega.sgb.integracion.DataBaseConnectionManager;
import omega.sgb.integracion.ProcesarFecha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControladorEstadoUsuarioTest {

    private static final String URL = "jdbc:h2:./test";
    private static final String USUARIO = "omega";
    private static final String CONTRASENA = "";
    private ProcesarFecha procesarFecha = new ProcesarFecha();

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
    void obtenerEstadoCompletoLectorConMultas(){
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
    void obtenerEstadoCompletoLectorSinMultas(){
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
    void obtenerEstadoCompletoBibliotecario(){
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
    void renovarPrestamoActivo() throws ParseException, SQLException {
        String fechaString = "2024 May 15";
        String fechaDevolucion = "2024 May 19";
        String fechaVerificar  = "2024 May 23";


        SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd");
        Date fechaDate = format.parse(fechaString);
        Date fechaDev = format.parse(fechaDevolucion);
        Date fechaVer = format.parse(fechaVerificar);
        Persona personaActual = new PersonaLector (21, 1000999888, "Miguel Angel Marquez Posso", "Fourier78");
        LibroVirtual libroVirtualActual = new LibroVirtual(2, "0674979857", "Tratado de medicina interna", 2, "Goldman Cecil", 4000, 5);
        LibroFisico libroFisicoActual = new LibroFisico(19, "Piso 9", "D789 Q14", libroVirtualActual, 1);
        Prestamo prestamoActual = new Prestamo(101,fechaDate, fechaDev, personaActual, 1, libroFisicoActual, null);

        controladorEstadoUsuario.renovarPrestamo(prestamoActual);
        List<Prestamo> prestamoList = controladorEstadoUsuario.traerPrestamos(personaActual);

        Prestamo prestamoVerificar = new Prestamo();
        for(Prestamo p: prestamoList){
            if(p.getLibro().getLibroVirtual().getTitulo().equals("Tratado de medicina interna")){
                prestamoVerificar = p;
            }
        }
        assertEquals(procesarFecha.getTodaysMidnightDate(),prestamoVerificar.getFechaPrestamo());
    }

    //Valida que un lector con un préstamo vencido no pueda renovar su préstamo
    @Test
    void renovarPrestamoVencido() throws ParseException, SQLException {
        String fechaString = "2024 May 15";
        String fechaDevolucion = "2024 May 19";
        String fechaVerificar  = "2024 May 23";


        SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd");
        Date fechaDate = format.parse(fechaString);
        Date fechaDev = format.parse(fechaDevolucion);
        Date fechaVer = format.parse(fechaVerificar);
        Persona personaActual = new PersonaLector (5, 1019982313, "Ana Cecilia de Armas Caso", "MarilynMonroe24");
        LibroVirtual libroVirtualActual = new LibroVirtual(2, "0674979857", "Tratado de medicina interna", 2, "Goldman Cecil", 4000, 5);
        LibroFisico libroFisicoActual = new LibroFisico(19, "Piso 9", "D789 Q14", libroVirtualActual, 1);
        Prestamo prestamoActual = new Prestamo(101,fechaDate, fechaDev, personaActual, 1, libroFisicoActual, null);

        controladorEstadoUsuario.renovarPrestamo(prestamoActual);
        List<Prestamo> prestamoList = controladorEstadoUsuario.traerPrestamos(personaActual);

        Prestamo prestamoVerificar = new Prestamo();
        for(Prestamo p: prestamoList){
            if(p.getLibro().getLibroVirtual().getTitulo().equals("Tratado de medicina interna")){
                prestamoVerificar = p;
            }
        }
        assertNull(prestamoVerificar.getFechaPrestamo());

    }

}