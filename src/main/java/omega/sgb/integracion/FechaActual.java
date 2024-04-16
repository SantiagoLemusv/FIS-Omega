package omega.sgb.integracion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class FechaActual {
    public static String getFecha() {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format the date to dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);
        String retorno = formattedDate;
        return retorno;
    }
    public static String sumarDiasFecha(String fechaString, Integer dias) {
        // Convertir la fecha string a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);

        // Sumar los días especificados
        LocalDate fechaSumada = fecha.plusDays(dias);

        // Formatear la fecha sumada a string con el formato deseado
        String fechaSumadaString = fechaSumada.format(formatter);

        return fechaSumadaString;
    }
}