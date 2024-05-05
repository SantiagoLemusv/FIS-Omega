package omega.sgb;

import omega.sgb.control.*;
import omega.sgb.dominio.Persona;
import omega.sgb.dominio.PersonaBibliotecario;
import omega.sgb.dominio.PersonaLector;
import omega.sgb.integracion.Imagen;

import java.sql.Connection;
import java.sql.SQLException;

public class SingletonControladores {
    private static SingletonControladores myself;
    private static Connection conexionGeneral = null;
    private static Imagen procesadorImagen = new Imagen();
    private static Persona usuarioActual;


    private static ControladorBusquedaLibro controladorBusquedaLibro;
    private static ControladorCarrito controladorCarrito;
    private static ControladorEstadoUsuario controladorEstadoUsuario;
    private static ControladorLogIn controladorLogIn;
    private static ControladorPago controladorPago;
    private static ControladorPrestamo controladorPrestamo;

    public static void setConexionGeneral(Connection conexionGeneral) {
        SingletonControladores.conexionGeneral = conexionGeneral;
    }

    public static void setProcesadorImagen(Imagen procesadorImagen) {
        SingletonControladores.procesadorImagen = procesadorImagen;
    }


    //Crear el tipo de persona necesario---------------------------------------------------------
    public static void crearUsuarioActualLector() {
        usuarioActual = new PersonaLector();
    }

    public static void crearUsuarioActualBibliotecario() {
        usuarioActual = new PersonaBibliotecario();
    }

    //Traer persona necesaria------------------------------------------------------------------------
    public static Persona getUsuarioActual() {
        return usuarioActual;
    }

    //Traer Controladores-------------------------------------------------------------------------
    public static ControladorBusquedaLibro getInstanceControladorBusquedaLibro() throws SQLException {
        if (controladorBusquedaLibro == null) {
            controladorBusquedaLibro = new ControladorBusquedaLibro(conexionGeneral);
        }
        return controladorBusquedaLibro;
    }

    public static ControladorCarrito getInstanceControladorCarrito() throws SQLException {
        if (controladorCarrito == null) {
            controladorCarrito = new ControladorCarrito(conexionGeneral);
        }
        return controladorCarrito;
    }

    public static ControladorEstadoUsuario getInstanceControladorEstadoUsuario() throws SQLException {
        if (controladorEstadoUsuario == null) {
            controladorEstadoUsuario = new ControladorEstadoUsuario(conexionGeneral);
        }
        return controladorEstadoUsuario;
    }

    public static ControladorLogIn getInstanceControladorLogIn() throws SQLException {
        if (controladorLogIn == null) {
            controladorLogIn = new ControladorLogIn(conexionGeneral);
        }
        return controladorLogIn;
    }

    public static ControladorPago getInstanceControladorPago() throws SQLException {
        if (controladorPago == null) {
            controladorPago = new ControladorPago(conexionGeneral);
        }
        return controladorPago;
    }

    public static ControladorPrestamo getInstanceControladorPrestamo() throws SQLException {
        if (controladorPrestamo == null) {
            controladorPrestamo = new ControladorPrestamo(conexionGeneral);
        }
        return controladorPrestamo;
    }
}