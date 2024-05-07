package omega.sgb.dominio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Pago {
    //Atributos de instancia
    private Integer id;
    private Date fechaEmision;//Cuando se realizo el pago
    private Integer montoTotal;//Suma de los montos de las multas
    private Tarjeta tarjeta;
    private List<Multa> multas;

    //Métodos constructores
    public Pago() {
    }

    public Pago(Integer id, Date fechaEmision, Integer montoTotal, Tarjeta tarjeta, List<Multa> multas) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.tarjeta = tarjeta;
        this.multas = multas;
    }

    public Pago(Date fechaEmision, Integer montoTotal, Tarjeta tarjeta, List<Multa> multas) {
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.tarjeta = tarjeta;
        this.multas = multas;
    }

    //Métodos de acceso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fechaEmision;
    }

    public void setFecha(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }
}
