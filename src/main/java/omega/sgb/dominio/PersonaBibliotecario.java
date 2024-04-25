package omega.sgb.dominio;

import java.util.ArrayList;
import java.util.List;

public class PersonaBibliotecario extends Persona{
    private List<LibroFisico> carrito = new ArrayList<LibroFisico>();

    public PersonaBibliotecario(){
        super();
        this.carrito = new ArrayList<>();
    }

    public PersonaBibliotecario(Integer id, Integer cedula, String nombre, String contrasena, List<LibroFisico> carrito) {
        super(id, cedula, nombre, contrasena);
        this.carrito = carrito;
    }

    // Getters y setters para el atributo 'carrito'
    @Override
    public List<LibroFisico> getCarrito() {
        return carrito;
    }

    @Override
    public List<Prestamo> getPrestamos() {
        return null;
    }

}
