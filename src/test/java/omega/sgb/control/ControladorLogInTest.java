package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.integracion.DataBaseConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ControladorLogInTest {
    private static final String URL = "jdbc:h2:./test";
    private static final String USUARIO = "omega";
    private static final String CONTRASENA = "";

    private ControladorLogIn controladorLogIn;

    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        Connection connectionH2 = DataBaseConnectionManager.getConnectionH2(URL, USUARIO, CONTRASENA);
        omega.sgb.integracion.InicializadorBD inicializarBD = new omega.sgb.integracion.InicializadorBD(
                connectionH2);
        inicializarBD.initDB();
        SingletonControladores.setConexionGeneral(connectionH2);
        controladorLogIn = SingletonControladores.getInstanceControladorLogIn();

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