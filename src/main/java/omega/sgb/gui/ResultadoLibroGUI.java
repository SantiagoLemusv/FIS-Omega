package omega.sgb.gui;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorBusquedaLibro;

import java.sql.SQLException;

public class ResultadoLibroGUI {

    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public ResultadoLibroGUI() throws SQLException {}
    public ResultadoLibroGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
}
