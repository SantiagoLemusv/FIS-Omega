package omega.sgb.dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Persona {
    //Atributos de instancia
    private Integer id;
    private Integer cedula;
    private String nombre;
    private String contrasena;

    //Métodos constructores
    public Persona() {
    }

    public Persona(Integer id, Integer cedula, String nombre, String contrasena) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public Persona(Integer cedula, String nombre, String contrasena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
    //Métodos de acceso

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public abstract List<Multa> getMultas();

    // Getters y setters para el atributo 'carrito'
    public abstract List<LibroFisico> getCarrito();

    // Getters y setters para el atributo 'prestamos'
    public abstract List<Prestamo> getPrestamos();

    public abstract List<Tarjeta> getTarjetas();
}

