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
        assertTrue(controladorLogIn.validarCaracteresValidos(cedula),"Cédula aceptada");
    }

    //Verifica para una entrada alfanumérica
    @Test
    void ValidarCaracteresInvalidos(){
        String cedula = "df120192734";
        assertFalse(controladorLogIn.validarCaracteresValidos(cedula),"Cédula no aceptada por incluir letras");
    }

    //Falla con una longitud menor a 8
    @Test
    void ValidarCaracteresLongitudMenor(){
        String cedula = "101998";
        assertFalse(controladorLogIn.validarCaracteresValidos(cedula),"Cédula no aceptada por no tener al menos 8 carácteres");
    }

    //Falla con una longitud mayor a 10
    @Test
    void ValidarCaracteresLongitudMayor(){
        String cedula = "1019983323231221";
        assertFalse(controladorLogIn.validarCaracteresValidos(cedula),"Cédula no aceptada por tener más de 10 carácteres");
    }


    //Valida con un usuario existente en la base de datos
    @Test
    void ValidarCredencialesUsuarioExistente() throws SQLException {
        String cedula = "1019983323";
        String contrasena = "lavidaesbella24";
        assertTrue(controladorLogIn.validarCredenciales(cedula,contrasena),"Autenticación exitosa");
    }

    //Valida con datos de usuario que no existen en la base de datos
    @Test
    void ValidarCredencialesUsuarioInexistente() throws SQLException {
        String cedula = "1019983328";
        String contrasena = "lavidaesbella25";
        assertFalse(controladorLogIn.validarCredenciales(cedula,contrasena),"Usuario no existe");
    }

    //Valida con un número de cédula de usuario existente, pero contraseña equivocada
    @Test
    void ValidarCredencialesCedulaExistente() throws SQLException {
        String cedula = "1019983323";
        String contrasena = "lavidaesbella25";
        assertFalse(controladorLogIn.validarCredenciales(cedula,contrasena),"Contraseña incorrecta");
    }

    //Valida con un número de cédula que no existe, pero la contraseña pertenece a un usuario existente
    @Test
    void ValidarCredencialesContrasenaExistente() throws SQLException {
        String cedula = "1019983321";
        String contrasena = "lavidaesbella24";
        assertFalse(controladorLogIn.validarCredenciales(cedula,contrasena),"Usuario no existe");
    }


    //Valida con datos aceptados de un usuario inexistente
    @Test
    void nuevoUsuarioInexistente() throws SQLException {
        String cedula = "123456789";
        String contrasena = "contrasena123";
        String contrasenaConfirmar = "contrasena123";
        String nombreCompleto = "Juan Pérez";
        boolean usuarioCreado = controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto);

        assertTrue(usuarioCreado,"Nuevo usuario creado");
    }

    //Valida al ingresar todos los datos de un usuario existente
    @Test
    void nuevoUsuarioExistente() throws SQLException {
        //La cuenta ya existe
        String cedula = "1019982313";
        String contrasena = "MarylinMonroe24";
        String contrasenaConfirmar = "MarylinMonroe24";
        String nombreCompleto = "Ana Cecilia de Armas Caso";
        boolean usuarioCreado = controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto);

        // Verificar el resultado
        assertFalse(usuarioCreado,"El usuario ya existe");
    }

    //Valida al no aceptar un usuario que tiene una cédula que ya existe en la base de datos, y los otros datos son diferentes
    @Test
    void nuevoUsuarioCedulaExistente() throws SQLException {
        String cedula = "1019982313";
        String contrasena = "Willsmith23";
        String contrasenaConfirmar = "Willsmith23";
        String nombreCompleto = "Elizabeth Simpson Boudie";
        boolean usuarioCreado = controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto);

        // Verificar el resultado"
        assertFalse(usuarioCreado,"El usuario ya existe");
    }

    //Valida al no aceptar un usuario que tiene una cédula con carácteres inválidos
    @Test
    void nuevoUsuarioCedulaInvalida() throws SQLException {
        String cedula = "3dghevbffhff";
        String contrasena = "Bradpitt55";
        String contrasenaConfirmar = "Bradpitt55";
        String nombreCompleto = "Monica Velandia Roble";

        assertFalse(controladorLogIn.validarCaracteresValidos(cedula));

        boolean usuarioCreado = controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto);
        // Verificar el resultado
        assertFalse(usuarioCreado, "No se pudo crear el usuairo, cédula contiene caracteres inválidos");
    }

    //Valida al no aceptar un usuario con la contraseña que no es la misma en ambos campos
    @Test
    void nuevoUsuarioContrasenaIncorrecta() throws SQLException {
        String cedula = "1019983388";
        String contrasena = "Tomcruise86";
        String contrasenaConfirmar = "Tomcruise87";
        String nombreCompleto = "Mario Valenzuela Oswaldo ";


        assertFalse(controladorLogIn.validarContrasena(contrasena, contrasenaConfirmar));

        boolean usuarioCreado = controladorLogIn.nuevoUsuarioCrear(cedula, contrasena, contrasenaConfirmar, nombreCompleto);
        // Verificar el resultado
        assertFalse(usuarioCreado, "No se pudo crear el usuario, las contraseñas no coinciden");
    }












}