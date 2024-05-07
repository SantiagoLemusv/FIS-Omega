package omega.sgb.dominio;

import java.time.LocalDate;
import java.util.Date;

public class Multa  {
    //Atributos de instancia
    private Integer id;
    private Integer montoPagar;// Es igual a multaValorDia * número de días que el lector no hizo la devolución del libro
    private Date fechaEmision;//Cuando se genera la multa, que es cuando se vencio el prestamo
    private Pago pago;

    //Métodos constructores
    public Multa() {
    }

    public Multa(Integer id, Integer montoPagar, Date fechaEmision, Pago pago) {
        this.id = id;
        this.montoPagar = montoPagar;
        this.fechaEmision = fechaEmision;
        this.pago = pago;
    }

    public Multa(Integer montoPagar, Date fechaEmision, Pago pago) {
        this.montoPagar = montoPagar;
        this.fechaEmision = fechaEmision;
        this.pago = pago;
    }

    //Métodos de acceso

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Integer montoPagar) {
        this.montoPagar = montoPagar;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
