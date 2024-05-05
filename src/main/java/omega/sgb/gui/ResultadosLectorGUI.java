package omega.sgb.gui;
import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorBusquedaLibro;

import java.sql.SQLException;

public class ResultadosLectorGUI {
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public ResultadosLectorGUI() throws SQLException {}
    public ResultadosLectorGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
}
