package omega.sgb.control;

import omega.sgb.dominio.LibroFisico;
import omega.sgb.dominio.LibroVirtual;
import omega.sgb.integracion.InicializadorBD;
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

class ControladorCarritoTest {

    private static final String URL = "jdbc:h2:./test";
    private static final String USUARIO = "omega";
    private static final String CONTRASENA = "";

    private ControladorCarrito controladorCarrito;

    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        Connection connectionH2 = DataBaseConnectionManager.getConnectionH2(URL, USUARIO, CONTRASENA);
        InicializadorBD inicializarBD = new InicializadorBD(
                connectionH2);
        inicializarBD.initDB();
        SingletonControladores.setConexionGeneral(connectionH2);
        controladorCarrito = SingletonControladores.getInstanceControladorCarrito();

    }

    @Test
    void agregarLibroLista(){
        boolean resultado = false;
        LibroVirtual libroVirtual = new LibroVirtual(9, "9780735619678", "Derecho Penal", 3, "Luis Carlos Perez", 4000, 5);
        LibroFisico libroFisicoNuevo = new LibroFisico(15, "Piso 8", "D678 Q01", libroVirtual, 1);
        SingletonControladores.crearUsuarioActualLector();
        SingletonControladores.getUsuarioActual().setId(22);
        SingletonControladores.getUsuarioActual().setCedula(1000222333);
        SingletonControladores.getUsuarioActual().setNombre("Mario Mendoza Zambrano");
        SingletonControladores.getUsuarioActual().setContrasena("Fourier71");
        List<LibroFisico> libroFisicos = SingletonControladores.getUsuarioActual().getCarrito();

        if(libroFisicoNuevo.getEstadoLibroFisicoId() == 1) {
            resultado = controladorCarrito.agregarLibro(libroFisicoNuevo);
        }

        assertTrue(resultado,"El libro fua agregado correctamente");
    }







}