package omega.sgb;

import omega.sgb.control.ControladorLogIn;
import omega.sgb.control.ControladorPrestamo;
import omega.sgb.dominio.Persona;

public class SingletonControladores {
    private static SingletonControladores myself;
    private static SingletonPantallas sPantallas = SingletonPantallas.getInstance();
    private Persona usuarioActual;
    private ControladorPrestamo controladorPrestamo;
    private ControladorLogIn controladorLogIn;

    public static SingletonPantallas getsPantallas() {
        return sPantallas;
    }

    public static void setsPantallas(SingletonPantallas sPantallas) {
        SingletonControladores.sPantallas = sPantallas;
    }

    public Persona getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Persona usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public ControladorPrestamo getControladorPrestamo() {
        return controladorPrestamo;
    }

    public void setControladorPrestamo(ControladorPrestamo controladorPrestamo) {
        this.controladorPrestamo = controladorPrestamo;
    }

    public ControladorLogIn getControladorLogIn() {
        return controladorLogIn;
    }

    public void setControladorLogIn(ControladorLogIn controladorLogIn) {
        this.controladorLogIn = controladorLogIn;
    }

    public SingletonControladores(){

    }

    public SingletonControladores(Persona usuarioActual, ControladorPrestamo controladorPrestamo, ControladorLogIn controladorLogIn) {
        this.usuarioActual = usuarioActual;
        this.controladorPrestamo = controladorPrestamo;
        this.controladorLogIn = controladorLogIn;
    }

    public static SingletonControladores getInstance() {
        if (myself == null) {
            myself = new SingletonControladores();
        }
        return myself;
    }
}
