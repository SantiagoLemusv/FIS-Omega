package omega.sgb;

import omega.sgb.gui.*;

public class SingletonPantallas {
    private LoginGUI login;
    private CrearCuentaGUI crearCuenta;
    private BusquedaGUI busqueda;
    private MainLectorGUI mainLector;
    private MainBibliotecarioGUI mainBibliotecario;

    private static SingletonPantallas myself;

    private SingletonPantallas(){
        crearCuenta=new CrearCuentaGUI();
        // Las demas pantallas o lo que se desee inicializar
    }

    public static SingletonPantallas getInstance() {
        if (myself == null) {
            myself = new SingletonPantallas();
        }
        return myself;
    }

}
