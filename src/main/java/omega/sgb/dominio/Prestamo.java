package omega.sgb.dominio;

import java.time.LocalDate;

public class Prestamo {
    //Atributos de instancia
    private Integer id;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private Persona persona;
    private Integer estadoPrestamoId;//Activo (1), vencido (2)
    private LibroFisico libro;
    private Multa multa;

    //Métodos constructores
    public Prestamo() {
    }

    public Prestamo(Integer id, String fechaPrestamo, String fechaDevolucion, Persona persona, Integer estadoPrestamoId, LibroFisico libro, Multa multa) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.persona = persona;
        this.estadoPrestamoId = estadoPrestamoId;
        this.libro = libro;
        this.multa = multa;
    }

    public Prestamo(String fechaPrestamo, String fechaDevolucion, Persona persona, Integer estadoPrestamoId, LibroFisico libro, Multa multa) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.persona = persona;
        this.estadoPrestamoId = estadoPrestamoId;
        this.libro = libro;
        this.multa = multa;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getEstadoPrestamoId() {
        return estadoPrestamoId;
    }

    public void setEstadoPrestamoId(Integer estadoPrestamoId) {
        this.estadoPrestamoId = estadoPrestamoId;
    }

    public LibroFisico getLibro() {
        return libro;
    }

    public void setLibro(LibroFisico libro) {
        this.libro = libro;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }
}
