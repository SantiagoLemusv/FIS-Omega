package omega.sgb;

import omega.sgb.control.*;
import omega.sgb.dominio.Persona;
import omega.sgb.dominio.PersonaBibliotecario;
import omega.sgb.dominio.PersonaLector;
import omega.sgb.integracion.Imagen;
import omega.sgb.integracion.SQL;
import omega.sgb.test.InicializarBD;

import java.sql.Connection;
import java.sql.SQLException;

public class SingletonControladores {
    private static Connection conexionGeneral = null;
    private static Imagen imagenProcesar = new Imagen();
    private static SingletonControladores myself;
    private static Persona usuarioActual;

    private static ControladorPrestamo controladorPrestamo;
    private static ControladorLogIn controladorLogIn;
    private static ControladorCarrito controladorCarrito;
    private static ControladorBusquedaLibro controladorBusquedaLibro;
    private static ControladorUsuario controladorUsuario;
    private static InicializarBD inicializarBD;

    public static void setConexionGeneral(Connection conexionGeneral) {
        SingletonControladores.conexionGeneral = conexionGeneral;
    }

    public static Imagen getImagenProcesar() {
        return imagenProcesar;
    }

    public static void setImagenProcesar(Imagen imagenProcesar) {
        SingletonControladores.imagenProcesar = imagenProcesar;
    }

    //Crear el tipo de persona necesario
    public static void crearUsuarioActualLector(){
        usuarioActual = new PersonaLector();
    }
    public static void crearUsuarioActualBibliotecario(){
        usuarioActual = new PersonaBibliotecario();
    }

    //Traer persona necesaria
    public static Persona getUsuarioActual() {
        return usuarioActual;
    }

    //Traer Controladores
    public static ControladorLogIn getInstanceLogIn() throws SQLException {
        if (controladorLogIn == null) {
            controladorLogIn = new ControladorLogIn(conexionGeneral);
        }
        return controladorLogIn;
    }

    public static ControladorPrestamo getInstancePrestamo() throws SQLException {
        if (controladorPrestamo == null) {
            controladorPrestamo = new ControladorPrestamo(conexionGeneral);
        }
        return controladorPrestamo;
    }

    public static ControladorCarrito getInstanceCarrito() throws SQLException {
        if (controladorCarrito == null) {
            controladorCarrito = new ControladorCarrito(conexionGeneral);
        }
        return controladorCarrito;
    }

    public static ControladorBusquedaLibro getInstanceBusquedaLibro() throws SQLException {
        if (controladorBusquedaLibro == null) {
            controladorBusquedaLibro = new ControladorBusquedaLibro(conexionGeneral);
        }
        return controladorBusquedaLibro;
    }

    public static ControladorUsuario getInstanceUsuario() throws SQLException {
        if (controladorUsuario == null) {
            controladorUsuario = new ControladorUsuario(conexionGeneral);
        }
        return controladorUsuario;
    }

    public static InicializarBD getInicializarBD (){
        if(inicializarBD == null) {
            inicializarBD = new InicializarBD(conexionGeneral);
        }
        return inicializarBD;

    }
}
