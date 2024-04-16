package omega.sgb;

import omega.sgb.control.ControladorLogIn;
import omega.sgb.control.ControladorPrestamo;
import omega.sgb.dominio.Persona;

public class SingletonControladores {
    private static SingletonControladores myself;
    private static Persona usuarioActual = new Persona();
    private static ControladorPrestamo controladorPrestamo;
    private static ControladorLogIn controladorLogIn;
    public static Persona getUsuarioActual() {
        return usuarioActual;
    }



    public static ControladorLogIn getInstanceLogIn() {
        if (controladorLogIn == null) {
            controladorLogIn = new ControladorLogIn();
        }
        return controladorLogIn;
    }
    public static ControladorPrestamo getInstancePrestamo() {
        if (controladorPrestamo == null) {
            controladorPrestamo = new ControladorPrestamo();
        }
        return controladorPrestamo;
    }
}
