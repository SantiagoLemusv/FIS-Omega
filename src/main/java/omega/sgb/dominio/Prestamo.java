package omega.sgb.dominio;

import java.time.LocalDate;
import java.util.Date;

public class Prestamo {
    //Atributos de instancia
    private Integer id;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Persona persona;
    private Integer estadoPrestamoId;//Activo (1), vencido (2)
    private LibroFisico libro;
    private Multa multa;
    //Logica prestamo. Si son 15 dias de prestamo cuenta desde el dia que lo pide
    //Ejemplo, si lo pidio el 1 del mes, debe devolverlo maximo el 15 del mes
    //A partir del 16, el sistema crea la multa

    //Métodos constructores
    public Prestamo() {
    }

    public Prestamo(Integer id, Date fechaPrestamo, Date fechaDevolucion, Persona persona, Integer estadoPrestamoId, LibroFisico libro, Multa multa) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.persona = persona;
        this.estadoPrestamoId = estadoPrestamoId;
        this.libro = libro;
        this.multa = multa;
    }

    public Prestamo(Date fechaPrestamo, Date fechaDevolucion, Persona persona, Integer estadoPrestamoId, LibroFisico libro, Multa multa) {
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

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
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
