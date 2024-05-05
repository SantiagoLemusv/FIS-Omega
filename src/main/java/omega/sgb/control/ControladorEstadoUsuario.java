package omega.sgb.control;

import java.sql.Connection;

public class ControladorEstadoUsuario {
    private Connection connection;
    public ControladorEstadoUsuario(Connection conexionGeneral) {
        this.connection = conexionGeneral;
    }
}
