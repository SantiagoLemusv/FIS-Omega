package omega.sgb.dominio;

import java.time.LocalDate;
import java.util.List;

public class Pago {
    //Atributos de instancia
    private Integer id;
    private String fecha;//Cuando se realizo el pago
    private Integer montoTotal;//Suma de los montos de las multas
    private Tarjeta tarjeta;
    private List<Multa> multas;

    //Métodos constructores
    public Pago() {
    }

    public Pago(Integer id, String fecha, Integer montoTotal, Tarjeta tarjeta, List<Multa> multas) {
        this.id = id;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.tarjeta = tarjeta;
        this.multas = multas;
    }

    public Pago(String fecha, Integer montoTotal, Tarjeta tarjeta, List<Multa> multas) {
        this.fecha = fecha;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
