package omega.sgb.dominio;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    //Atributos de instancia
    private Integer id;
    private Integer cedula;
    private String nombre;
    private String contrasena;
    private Integer tipoPersonaId;//Bibliotecario (1), lector (2)
    private List<Prestamo> prestamos = new ArrayList<Prestamo>();
    private List<LibroFisico> carrito = new ArrayList<LibroFisico>();

    //Métodos constructores
    public Persona() {
    }

    public Persona(Integer id, Integer cedula, String nombre, String contrasena, Integer tipoPersonaId, List<Prestamo> prestamos) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipoPersonaId = tipoPersonaId;
        this.prestamos = prestamos;
    }

    public Persona(Integer cedula, String nombre, String contrasena, Integer tipoPersonaId, List<Prestamo> prestamos) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipoPersonaId = tipoPersonaId;
        this.prestamos = prestamos;
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

    public String getContrasenia() {
        return contrasena;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasena = contrasena;
    }

    public Integer getTipoPersonaId() {
        return tipoPersonaId;
    }

    public void setTipoPersonaId(Integer tipoPersonaId) {
        this.tipoPersonaId = tipoPersonaId;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<LibroFisico> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<LibroFisico> carrito) {
        this.carrito = carrito;
    }
}

