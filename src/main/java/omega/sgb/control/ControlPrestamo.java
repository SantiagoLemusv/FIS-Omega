package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;

import java.util.ArrayList;
import java.util.List;

//No debe tener dependencias a las pantallas
public class ControlPrestamo {
    private List<LibroFisico> carrito= new ArrayList<>();
    private SingletonControladores sControl= SingletonControladores.getInstance();

    public void agregarLibroCarrito(LibroFisico libro){
        carrito.add(libro);
    }
    public void realizarPrestamo(){
    }


}
