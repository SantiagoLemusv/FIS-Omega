package omega.sgb.gui;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorEstadoUsuario;

import java.sql.SQLException;

public class EstadoLectorGUI {
    private ControladorEstadoUsuario controladorEstadoUsuario = SingletonControladores.getInstanceControladorEstadoUsuario();
    public EstadoLectorGUI() throws SQLException {}
    public EstadoLectorGUI(ControladorEstadoUsuario controladorEstadoUsuario) throws SQLException {
        this.controladorEstadoUsuario = controladorEstadoUsuario;
    }
}
