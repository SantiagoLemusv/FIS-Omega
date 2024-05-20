package omega.sgb.control;

import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import org.junit.jupiter.api.Test;
import omega.sgb.SingletonControladores;
import omega.sgb.integracion.DataBaseConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class ControladorBusquedaLibroTest {

    private static final String URL = "jdbc:h2:./test";
    private static final String USUARIO = "omega";
    private static final String CONTRASENA = "";

    private ControladorBusquedaLibro controladorBusquedaLibro;

    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        Connection connectionH2 = DataBaseConnectionManager.getConnectionH2(URL, USUARIO, CONTRASENA);
        omega.sgb.integracion.InicializadorBD inicializarBD = new omega.sgb.integracion.InicializadorBD(
                connectionH2);
        inicializarBD.initDB();
        SingletonControladores.setConexionGeneral(connectionH2);
        controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();

    }

    //Retorna los libros coincidentes del título completo
    @Test
    void buscarLibroTituloExistenteTitulo() throws SQLException, IOException {

        String fragmentoTituloloLibroBuscado = "El padrino";

        controladorBusquedaLibro.buscarLibrosVirtuales(fragmentoTituloloLibroBuscado);
        List<LibroVirtual> librosVirtuales = new ArrayList<>();
        librosVirtuales = controladorBusquedaLibro.getListaLibrosVirtuales();

        //Revisa si la lista contiene al menos un libro
        assertFalse(librosVirtuales.isEmpty());
    }

    //Retorna los libros coincidentes del fragmento del título ingresado
    @Test
    void buscarLibroTituloExistenteTituloFragmento() throws SQLException, IOException {

        String fragmentoTituloloLibroBuscado = "el ";

        controladorBusquedaLibro.buscarLibrosVirtuales(fragmentoTituloloLibroBuscado);
        List<LibroVirtual> librosVirtuales = new ArrayList<>();
        librosVirtuales = controladorBusquedaLibro.getListaLibrosVirtuales();

        //Revisa si la lista contiene al menos un libro
        assertFalse(librosVirtuales.isEmpty());
    }

    //No retorna ningún libro al fragmento ingresado
    @Test
    void buscarLibrosTituloInexistente() throws SQLException{
        String fragmentoTituloloLibroBuscado = "Confieso que ";

        controladorBusquedaLibro.buscarLibrosVirtuales(fragmentoTituloloLibroBuscado);
        List<LibroVirtual> librosVirtuales = new ArrayList<>();
        librosVirtuales = controladorBusquedaLibro.getListaLibrosVirtuales();

        // Check if the list contains at least one book
        assertTrue(librosVirtuales.isEmpty(),"No se encotró coincidencias");
    }

    //Retorna todos los libros existentes al presionar buscar sin escribir un fragmento
    @Test
    void buscarLibroSinTitulo() throws SQLException, IOException {

        String fragmentoTituloloLibroBuscado = " ";

        controladorBusquedaLibro.buscarLibrosVirtuales(fragmentoTituloloLibroBuscado);
        List<LibroVirtual> librosVirtuales = new ArrayList<>();
        librosVirtuales = controladorBusquedaLibro.getListaLibrosVirtuales();

        // Check if the list contains at least one book
        assertFalse(librosVirtuales.isEmpty(),"Se encontró coincidencias");
    }

    //Validar una reserva con un usuario que no tiene conflictos
    @Test
    void reservarLibroFisicoUsuarioSinConflictos() throws Exception {
        LibroVirtual libroVirtual = new LibroVirtual(4,"9780321572604","El Padrino",2,"Mario Puzo",15,2500);
        LibroFisico libroFisico = new LibroFisico(5,"Piso 3","D234 Q57",libroVirtual, 1  );

        SingletonControladores.crearUsuarioActualLector();
        SingletonControladores.getUsuarioActual().setId(22);
        SingletonControladores.getUsuarioActual().setCedula(1000222333);
        SingletonControladores.getUsuarioActual().setNombre("Mario Mendoza Zambrano");
        SingletonControladores.getUsuarioActual().setContrasena("Fourier71");
        controladorBusquedaLibro.setLibroFisicoSeleccionado(libroFisico);
        String textoValidar = controladorBusquedaLibro.validarSiPuedoReservar();
        assertEquals("Reserva realizada exitosamente",textoValidar,"Reserva exitosa");
    }

    //Validar una reserva con un usuario que tiene conflictos
    @Test
    void reservarLibroFisicoUsuarioConConflictos() throws Exception {
        LibroVirtual libroVirtual = new LibroVirtual(4,"9780321572604","El Padrino",2,"Mario Puzo",15,2500);
        LibroFisico libroFisico = new LibroFisico(5,"Piso 3","D234 Q57",libroVirtual, 1  );

        SingletonControladores.crearUsuarioActualLector();
        SingletonControladores.getUsuarioActual().setId(5);
        SingletonControladores.getUsuarioActual().setCedula(1019982313);
        SingletonControladores.getUsuarioActual().setNombre("Ana Cecilia de Armas Caso");
        SingletonControladores.getUsuarioActual().setContrasena("Marilynmonroe24");
        controladorBusquedaLibro.setLibroFisicoSeleccionado(libroFisico);
        String textoValidar = controladorBusquedaLibro.validarSiPuedoReservar();
        assertNotEquals("Reserva realizada exitosamente",textoValidar,"Reserva no exitosa, el usuario tiene multas");
    }

    //Validar una reserva con un libro que está prestado
    @Test
    void reservarLibroFisicoPrestado() throws Exception {
        LibroVirtual libroVirtual = new LibroVirtual(4,"9780321572604","El Padrino",2,"Mario Puzo",15,2500);
        LibroFisico libroFisico = new LibroFisico(5,"Piso 3","D234 Q57",libroVirtual, 2  );

        SingletonControladores.crearUsuarioActualLector();
        SingletonControladores.getUsuarioActual().setId(22);
        SingletonControladores.getUsuarioActual().setCedula(1000222333);
        SingletonControladores.getUsuarioActual().setNombre("Mario Mendoza Zambrano");
        SingletonControladores.getUsuarioActual().setContrasena("Fourier71");
        controladorBusquedaLibro.setLibroFisicoSeleccionado(libroFisico);
        String textoValidar = controladorBusquedaLibro.validarSiPuedoReservar();
        assertNotEquals("Reserva realizada exitosamente",textoValidar,"Reserva no exitosa, el libro está prestado");
    }

    //Validar una reserva con un libro que está reservado
    @Test
    void reservarLibroFisicoReservado() throws Exception {
        LibroVirtual libroVirtual = new LibroVirtual(4,"9780321572604","El Padrino",2,"Mario Puzo",15,2500);
        LibroFisico libroFisico = new LibroFisico(5,"Piso 3","D234 Q57",libroVirtual, 3  );

        SingletonControladores.crearUsuarioActualLector();
        SingletonControladores.getUsuarioActual().setId(22);
        SingletonControladores.getUsuarioActual().setCedula(1000222333);
        SingletonControladores.getUsuarioActual().setNombre("Mario Mendoza Zambrano");
        SingletonControladores.getUsuarioActual().setContrasena("Fourier71");
        controladorBusquedaLibro.setLibroFisicoSeleccionado(libroFisico);
        String textoValidar = controladorBusquedaLibro.validarSiPuedoReservar();
        assertNotEquals("Reserva realizada exitosamente",textoValidar,"Reserva no exitosa, el libro está reservado");
    }


}