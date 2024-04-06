package omega.sgb.dominio;

public class Tarjeta {
    private Integer id;
    private Integer numero;
    private String fechaVencimiento;
    private Integer cVV;
    private String entidadBancaria;
    private Integer personaId;
    private Integer tipoTarjetaId;

    //Métodos constructores
    public Tarjeta() {
    }

    public Tarjeta(Integer id, Integer numero, String fechaVencimiento, Integer cVV, String entidadBancaria, Integer personaId, Integer tipoTarjetaId) {
        this.id = id;
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.cVV = cVV;
        this.entidadBancaria = entidadBancaria;
        this.personaId = personaId;
        this.tipoTarjetaId = tipoTarjetaId;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getcVV() {
        return cVV;
    }

    public void setcVV(Integer cVV) {
        this.cVV = cVV;
    }

    public String getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(String entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public Integer getTipoTarjetaId() {
        return tipoTarjetaId;
    }

    public void setTipoTarjetaId(Integer tipoTarjetaId) {
        this.tipoTarjetaId = tipoTarjetaId;
    }
}
