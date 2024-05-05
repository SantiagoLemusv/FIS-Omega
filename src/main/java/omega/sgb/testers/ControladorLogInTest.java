package omega.sgb.testers;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorLogIn;
import omega.sgb.integracion.SQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorLogInTest {
    private static final String URL = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/LAB"; //cambiar a la conexion de H2
    private static final String USUARIO = "is819920";//cambiar a la conexion de H2
    private static final String CONTRASENA = "Zyqb4HO0x1BG57S";//cambiar a la conexion de H2
    private ControladorLogIn controladorLogIn;
    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        InicializarBD inicializarBD = new InicializarBD(SQL.getConexion(URL, USUARIO, CONTRASENA, 0));
        inicializarBD.initDB();
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
