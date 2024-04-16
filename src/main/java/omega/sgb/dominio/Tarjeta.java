package omega.sgb.dominio;

import java.time.LocalDate;

public class Tarjeta {
    //Atributos de instancia
    private Integer id;
    private Integer numero;
    private String fechaVencimiento;
    private String entidadBancaria;
    private String titular;
    private Integer tipoTarjetaId;//Debito (1), credito (2)
    private Persona persona;

    //Métodos constructores
    public Tarjeta() {
    }

    public Tarjeta(Integer id, Integer numero, String fechaVencimiento, String entidadBancaria, String titular, Integer tipoTarjetaId, Persona persona) {
        this.id = id;
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.entidadBancaria = entidadBancaria;
        this.titular = titular;
        this.tipoTarjetaId = tipoTarjetaId;
        this.persona = persona;
    }

    public Tarjeta(Integer numero, String fechaVencimiento, String entidadBancaria, String titular, Integer tipoTarjetaId, Persona persona) {
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.entidadBancaria = entidadBancaria;
        this.titular = titular;
        this.tipoTarjetaId = tipoTarjetaId;
        this.persona = persona;
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

    public String getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(String entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Integer getTipoTarjetaId() {
        return tipoTarjetaId;
    }

    public void setTipoTarjetaId(Integer tipoTarjetaId) {
        this.tipoTarjetaId = tipoTarjetaId;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
