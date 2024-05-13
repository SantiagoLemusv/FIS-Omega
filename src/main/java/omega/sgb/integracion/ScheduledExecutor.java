package omega.sgb.integracion;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorActualizarApp;
import java.util.concurrent.Executors;

import java.sql.SQLException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    private static final ControladorActualizarApp controladorActualizarApp;

    static {
        try {
            controladorActualizarApp = SingletonControladores.getInstanceControladorActualizarApp();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final int INTERVALO_EJECUCION = 10; // Segundos

    public ScheduledExecutor() throws SQLException {
    }

    public static void main(String[] args) {
        // Crear un ScheduledExecutorService con un solo hilo
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Crear la tarea que se ejecutará cada 10 segundos
        Runnable tarea = () -> {
            System.out.println("Ejecutando función cada 10 segundos...");

            try {
                System.out.println("llama reservas");
                controladorActualizarApp.validarReservasVencidas();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println("llama prestamos ");
                controladorActualizarApp.validarPrestamosVencidos();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println("llama multas");
                controladorActualizarApp.validarNuevoCostoDeMultas();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        // Programar la ejecución de la tarea cada 10 segundos
        executor.scheduleAtFixedRate(tarea, 0, INTERVALO_EJECUCION, TimeUnit.SECONDS);
    }
}
