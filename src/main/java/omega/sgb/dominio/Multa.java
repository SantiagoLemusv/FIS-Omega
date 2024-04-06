package omega.sgb.dominio;

public class Multa  {
    private Integer id;
    private Float montoPagar;
    private String fechaEmision;
    private Integer prestamoId;

    //Métodos constructores
    public Multa() {
    }

    public Multa(Integer id, Float montoPagar, String fechaEmision, Integer prestamoId) {
        this.id = id;
        this.montoPagar = montoPagar;
        this.fechaEmision = fechaEmision;
        this.prestamoId = prestamoId;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Float montoPagar) {
        this.montoPagar = montoPagar;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Integer getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Integer prestamoId) {
        this.prestamoId = prestamoId;
    }
}
