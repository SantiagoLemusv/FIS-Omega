package omega.sgb.dominio;

public class Prestamo {
    private Integer id;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private Integer personaId;
    private Integer estadoPrestamoId;

    //Métodos constructores
    public Prestamo() {
    }

    public Prestamo(Integer id, String fechaPrestamo, String fechaDevolucion, Integer personaId, Integer estadoPrestamoId) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.personaId = personaId;
        this.estadoPrestamoId = estadoPrestamoId;
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

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public Integer getEstadoPrestamoId() {
        return estadoPrestamoId;
    }

    public void setEstadoPrestamoId(Integer estadoPrestamoId) {
        this.estadoPrestamoId = estadoPrestamoId;
    }
}
