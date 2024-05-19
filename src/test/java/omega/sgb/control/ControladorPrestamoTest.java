package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.PersonaLector;
import omega.sgb.integracion.DataBaseConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ControladorPrestamoTest {

    private static final String URL = "jdbc:h2:./test";
    private static final String USUARIO = "omega";
    private static final String CONTRASENA = "";

    private ControladorPrestamo controladorPrestamo;

    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        Connection connectionH2 = DataBaseConnectionManager.getConnectionH2(URL, USUARIO, CONTRASENA);
        omega.sgb.integracion.InicializadorBD inicializarBD = new omega.sgb.integracion.InicializadorBD(
                connectionH2);
        inicializarBD.initDB();
        SingletonControladores.setConexionGeneral(connectionH2);
        controladorPrestamo = SingletonControladores.getInstanceControladorPrestamo();

    }



    @Test
    public void eliminarLibroCarritoExito(){

    }

    @Test
    public void eliminarLibroCarritoFallido(){

    }

    @Test
    public void confirmarPrestamoExitoso(){

    }

    @Test
    public void confirmarPrestamoFallido(){

    }



}