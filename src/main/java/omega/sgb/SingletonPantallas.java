package omega.sgb;

import omega.sgb.gui.*;

public class SingletonPantallas {
    private LoginGUI login;
    private CrearCuentaGUI crearCuenta;
    private BusquedaGUI busqueda;
    private MainLectorGUI mainLector;
    private MainBibliotecarioGUI mainBibliotecario;

    private static SingletonPantallas myself;
    private static SingletonControladores sControladores = SingletonControladores.getInstance();


    public LoginGUI getLogin() {
        return login;
    }

    public void setLogin(LoginGUI login) {
        this.login = login;
    }

    public CrearCuentaGUI getCrearCuenta() {
        return crearCuenta;
    }

    public void setCrearCuenta(CrearCuentaGUI crearCuenta) {
        this.crearCuenta = crearCuenta;
    }

    public BusquedaGUI getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(BusquedaGUI busqueda) {
        this.busqueda = busqueda;
    }

    public MainLectorGUI getMainLector() {
        return mainLector;
    }

    public void setMainLector(MainLectorGUI mainLector) {
        this.mainLector = mainLector;
    }

    public MainBibliotecarioGUI getMainBibliotecario() {
        return mainBibliotecario;
    }

    public void setMainBibliotecario(MainBibliotecarioGUI mainBibliotecario) {
        this.mainBibliotecario = mainBibliotecario;
    }


    public static SingletonControladores getsControladores() {
        return sControladores;
    }

    public static void setsControladores(SingletonControladores sControladores) {
        SingletonPantallas.sControladores = sControladores;
    }

    public SingletonPantallas(){

    }

    public SingletonPantallas(LoginGUI login, CrearCuentaGUI crearCuenta, BusquedaGUI busqueda, MainLectorGUI mainLector, MainBibliotecarioGUI mainBibliotecario) {
        this.login = login;
        this.crearCuenta = crearCuenta;
        this.busqueda = busqueda;
        this.mainLector = mainLector;
        this.mainBibliotecario = mainBibliotecario;
    }

    public static SingletonPantallas getInstance() {
        if (myself == null) {
            myself = new SingletonPantallas();
        }
        return myself;
    }

}
