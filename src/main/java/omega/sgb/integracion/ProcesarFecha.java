package omega.sgb.integracion;

import java.util.Calendar;
import java.util.Date;

public class ProcesarFecha {

    public Date getFecha() {
        // Obtener la fecha actual
        Date fechaActual = new Date();
        return fechaActual;
    }

    public Date sumarDiasAFecha(Date fecha, int numeroDias) {
        // Crear un objeto Calendar a partir de la fecha recibida
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);

        // Sumar el número de días especificado al calendario
        calendario.add(Calendar.DAY_OF_MONTH, numeroDias);

        // Obtener la nueva fecha con los días sumados
        Date fechaSumada = calendario.getTime();

        // Devolver la fecha sumada
        return fechaSumada;
    }

    public java.sql.Date fechaJavaToFechaSql(java.util.Date fechaUtil) {
        if (fechaUtil == null) {
            return null;
        }
        long tiempoEnMilisegundos = fechaUtil.getTime();
        return new java.sql.Date(tiempoEnMilisegundos);
    }

    public java.util.Date fechaSqlToFechaJava(java.sql.Date fechaSql) {
        if (fechaSql == null) {
            return null;
        }
        return new java.util.Date(fechaSql.getTime());
    }


}