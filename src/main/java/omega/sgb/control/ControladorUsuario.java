package omega.sgb.control;

import java.sql.Connection;

public class ControladorUsuario {
    private Connection connection;
    public ControladorUsuario(Connection conexionGeneral) {
        this.connection = conexionGeneral;
    }
}
