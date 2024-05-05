package omega.sgb.test;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorLogIn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorLogInTester {
    private InicializarBD   inicializarBD = SingletonControladores.getInicializarBD();
    private ControladorLogIn controladorLogIn;
    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        inicializarBD.initDB();
        controladorLogIn = SingletonControladores.getInstanceLogIn();
    }
    //Verifica que sean números, con un mínimo de 8 y máximo 10
    @Test
    void ValidarCaracteresValidosExito(){
        String cedula = "101998323";
        //assertTrue(controladorLogIn.validarCaracteresValidos(cedula));
    }


    @Test
    void ValidarCaracteresValidosFallido(){
        String cedula = "df120192734";
        //assertFalse(controladorLogIn.validarCaracteresValidos(cedula));
    }

    @Test
    void ValidarCredencialesExito() throws SQLException {
        String cedula = "1019983323";
        String contrasena = "lavidaesbella24";
        assertTrue(controladorLogIn.validarCredenciales(cedula,contrasena));
    }

    @Test
    void ValidarCredencialesFallido() throws SQLException {
        String cedula = "1019983328";
        String contrasena = "lavidaesbella25";
        assertFalse(controladorLogIn.validarCredenciales(cedula,contrasena));
    }




}
