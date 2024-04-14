package omega.sgb.dominio;

import java.time.LocalDate;

public class Prestamo {
    //Atributos de instancia
    private Integer id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Persona persona;
    private Integer estadoPrestamoId;//Activo (1), vencido (2)
    private LibroFisico libro;
    private Multa multa;

    //Métodos constructores
    public Prestamo() {
    }

    public Prestamo(Integer id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Persona persona, Integer estadoPrestamoId, LibroFisico libro, Multa multa) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.persona = persona;
        this.estadoPrestamoId = estadoPrestamoId;
        this.libro = libro;
        this.multa = multa;
    }

    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion, Persona persona, Integer estadoPrestamoId, LibroFisico libro, Multa multa) {
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

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
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
