package omega.sgb;

import omega.sgb.control.ControlPrestamo;
import omega.sgb.dominio.Persona;

public class SingletonControladores {
    private static SingletonControladores myself;
    private Persona usuarioActual;
    private final ControlPrestamo prestamo;

    public SingletonControladores(){
        prestamo=new ControlPrestamo();
    }


    public static SingletonControladores getInstance() {
        if (myself == null) {
            myself = new SingletonControladores();
        }
        return myself;
    }
}
