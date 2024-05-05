package omega.sgb.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorLogIn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class ControladorLogInTester {
    private InicializarBD   inicializarBD = SingletonControladores.getInicializarBD();
    private ControladorLogIn controladorLogIn;
    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        inicializarBD.initDB();
        controladorLogIn = SingletonControladores.getInstanceLogIn();
    }

    @Test
    void ValidarCredenciales() throws SQLException {
        String cedula = "1019983323";
        String contrasena = "lavidaesbella24";
        assertTrue(controladorLogIn.validarCredenciales(cedula,contrasena));
    }



}
