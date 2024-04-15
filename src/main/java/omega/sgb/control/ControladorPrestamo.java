package omega.sgb.control;

import omega.sgb.SingletonControladores;
import omega.sgb.dominio.LibroFisico;

import java.util.ArrayList;
import java.util.List;

//No debe tener dependencias a las pantallas
public class ControladorPrestamo {
    private List<LibroFisico> carrito= new ArrayList<>();

    public void agregarLibroCarrito(LibroFisico libro){
        carrito.add(libro);
    }
    public void realizarPrestamo(){
    }


}
