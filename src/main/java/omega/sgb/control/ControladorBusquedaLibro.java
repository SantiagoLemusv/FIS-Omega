package omega.sgb.control;

import java.sql.Connection;

public class ControladorBusquedaLibro {
    private Connection connection;
    public ControladorBusquedaLibro(Connection conexionGeneral) {
        this.connection = conexionGeneral;
    }
}
