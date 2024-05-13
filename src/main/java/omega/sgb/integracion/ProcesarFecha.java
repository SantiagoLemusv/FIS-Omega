package omega.sgb.integracion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProcesarFecha {

    public Date getFechaActual() {
        // Obtener la fecha actual
        Date fechaActual = new Date();
        return fechaActual;
    }

    public int calcularDiferenciaDias(Date fechaLlegada) {
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Calcular la diferencia en milisegundos
        long diferenciaMilisegundos = fechaActual.getTime() - fechaLlegada.getTime();

        // Convertir milisegundos a días
        double dias = diferenciaMilisegundos / (1000 * 60 * 60 * 24);

        // Redondear hacia arriba para considerar el día de llegada completo
        int diasRedondeados = (int) Math.ceil(dias);

        return diasRedondeados+1;
    }

    public String formatearFecha(Date fecha) {
        // Crear objeto SimpleDateFormat para el formato deseado
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        // Formatear la fecha y guardarla en una cadena
        String fechaFormateada = formatoFecha.format(fecha);

        // Devolver la fecha formateada
        return fechaFormateada;
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

    public boolean haPasadoUnMinuto(Date fechaReferencia) {
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Calcular la diferencia en milisegundos
        long diferenciaEnMs = fechaActual.getTime() - fechaReferencia.getTime();

        // Un minuto en milisegundos es 60.000 ms
        long unMinutoEnMs = 60000;

        // Devolver true si ha pasado un minuto o más
        return diferenciaEnMs >= unMinutoEnMs;
    }

    public boolean haExpirado(Date fechaReferencia) {
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Calcular la diferencia en milisegundos
        long diferenciaEnMs = fechaActual.getTime() - fechaReferencia.getTime();

        // Si la diferencia es mayor o igual a 0, la fecha ha expirado
        return diferenciaEnMs >= 0;
    }

    public static Date fechaStringToFechaJava(String fechaString) throws ParseException {
        // Definimos el formato de la fecha de entrada (mes y año)
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/yyyy");

        // Obtenemos el calendario actual
        Calendar calendario = Calendar.getInstance();

        // Convertimos la cadena de fecha a un objeto Calendar
        Calendar fechaCalendar = calendario.getInstance();
        fechaCalendar.setTime(formatoFecha.parse(fechaString));

        // Establecemos el día al primer día del mes
        fechaCalendar.set(Calendar.DAY_OF_MONTH, 1);

        // Convertimos el objeto Calendar a un objeto Date
        Date fecha = fechaCalendar.getTime();

        // Devolvemos la fecha convertida
        return fecha;
    }

}
