package omega.sgb.dominio;

import java.util.ArrayList;
import java.util.List;

public class PersonaLector extends Persona{
    private List<Prestamo> prestamos = new ArrayList<Prestamo>();


    public PersonaLector(){
        super();
        this.prestamos = new ArrayList<>();
    }


    // Constructor con atributos específicos
    public PersonaLector(Integer id, Integer cedula, String nombre, String contrasena, List<Prestamo> prestamos) {
        super(id, cedula, nombre, contrasena);
        this.prestamos = prestamos;
    }

    // Getters y setters para el atributo 'prestamos'
    @Override
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    @Override
    public List<LibroFisico> getCarrito() {
        return null;
    }
}
