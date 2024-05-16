package omega.sgb.dominio;

import java.util.ArrayList;
import java.util.List;

public class PersonaBibliotecario extends Persona{
    private List<LibroFisico> carrito ;

    public PersonaBibliotecario(){
        super();
        this.carrito = new ArrayList<>();
    }

    @Override
    public List<Multa> getMultas() {
        return null;
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
