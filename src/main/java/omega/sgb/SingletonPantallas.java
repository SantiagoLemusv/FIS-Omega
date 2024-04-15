package omega.sgb;

import omega.sgb.gui.*;

public class SingletonPantallas {
    private static LoginGUI login;
    private static CrearCuentaGUI crearCuenta;
    private static BusquedaGUI busqueda;
    private static MainLectorGUI mainLector;
    private static MainBibliotecarioGUI mainBibliotecario;


    public static BusquedaGUI getInstanceBusqueda() {
        if (busqueda == null) {
            busqueda = new BusquedaGUI();
        }
        return busqueda;
    }

    public static CrearCuentaGUI getInstanceCrearCuenta() {
        if (crearCuenta == null) {
            crearCuenta = new CrearCuentaGUI();
        }
        return crearCuenta;
    }

    public static LoginGUI getInstanceLogin() {
        if (login == null) {
            login = new LoginGUI();
        }
        return login;
    }

    public static MainBibliotecarioGUI getInstanceMainBibliotecario() {
        if (mainBibliotecario == null) {
            mainBibliotecario = new MainBibliotecarioGUI();
        }
        return mainBibliotecario;
    }

    public static MainLectorGUI getInstanceMainLector() {
        if (mainLector == null) {
            mainLector = new MainLectorGUI();
        }
        return mainLector;
    }
}
