package omega.sgb;

import omega.sgb.control.*;
import omega.sgb.dominio.Persona;
import omega.sgb.dominio.PersonaBibliotecario;
import omega.sgb.dominio.PersonaLector;
import omega.sgb.integracion.ConversorImagen;
import omega.sgb.integracion.ProcesarFecha;

import java.sql.Connection;
import java.sql.SQLException;

public class SingletonControladores {
    private static SingletonControladores myself;
    private static Connection conexionGeneral = null;
    private static ConversorImagen conversorImagen = new ConversorImagen();
    private static ProcesarFecha procesarFecha = new ProcesarFecha();
    private static Persona usuarioActual;
    private static ControladorActualizarApp controladorActualizarApp;
    private static ControladorAgregarMetodoPago controladorAgregarMetodoPago;
    private static ControladorBusquedaLibro controladorBusquedaLibro;
    private static ControladorCarrito controladorCarrito;
    private static ControladorEstadoUsuario controladorEstadoUsuario;
    private static ControladorLogIn controladorLogIn;
    private static ControladorPago controladorPago;
    private static ControladorPrestamo controladorPrestamo;

    public static void setConexionGeneral(Connection conexionGeneral) {
        SingletonControladores.conexionGeneral = conexionGeneral;
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
    public static ControladorActualizarApp getInstanceControladorActualizarApp() throws SQLException {
        if (controladorActualizarApp == null) {
            controladorActualizarApp = new ControladorActualizarApp(conexionGeneral, procesarFecha);
        }
        return controladorActualizarApp;
    }


    public static ControladorBusquedaLibro getInstanceControladorBusquedaLibro() throws SQLException {
        if (controladorBusquedaLibro == null) {
            controladorBusquedaLibro = new ControladorBusquedaLibro(conexionGeneral, conversorImagen, procesarFecha);
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
            controladorEstadoUsuario = new ControladorEstadoUsuario(conexionGeneral, procesarFecha, conversorImagen);
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
            controladorPago = new ControladorPago(conexionGeneral, procesarFecha);
        }
        return controladorPago;
    }

    public static ControladorPrestamo getInstanceControladorPrestamo() throws SQLException {
        if (controladorPrestamo == null) {
            controladorPrestamo = new ControladorPrestamo(conexionGeneral, procesarFecha);
        }
        return controladorPrestamo;
    }
    public static ControladorAgregarMetodoPago getInstanceControladorAgregarMetodoPago() throws SQLException {
        if (controladorAgregarMetodoPago == null) {
            controladorAgregarMetodoPago = new ControladorAgregarMetodoPago(conexionGeneral,procesarFecha);
        }
        return controladorAgregarMetodoPago;
    }
}