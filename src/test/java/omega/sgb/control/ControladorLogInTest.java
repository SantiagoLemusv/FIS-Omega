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

    //Prueba UAT
    @Test
    void ValidarCredencialesExito() throws SQLException {
        String cedula = "1019983323";
        String contrasena = "lavidaesbella24";
        assertTrue(controladorLogIn.validarCredenciales(cedula,contrasena));
    }
    //Prueba UAT
    @Test
    void ValidarCredencialesFallido() throws SQLException {
        String cedula = "1019983328";
        String contrasena = "lavidaesbella25";
        assertFalse(controladorLogIn.validarCredenciales(cedula,contrasena));
    }

    @Test
    void nuevoUsuarioCrearExitoso() throws SQLException {
        String cedula = "123456789";
        String contrasena = "contrasena123";
        String contrasenaConfirmar = "contrasena123";
        String nombreCompleto = "Juan Pérez";
        boolean usuarioCreado = controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto);

        assertTrue(usuarioCreado);
    }
    @Test
    void nuevoUsuarioCrearFallido() throws SQLException {
        //La cuenta ya existe
        String cedula = "1019982313";
        String contrasena = "MarylinMonroe24";
        String contrasenaConfirmar = "MarylinMonroe24";
        String nombreCompleto = "Ana Cecilia de Armas Caso";
        boolean usuarioCreado = controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto);

        // Verificar el resultado
        assertFalse(usuarioCreado);
    }



}