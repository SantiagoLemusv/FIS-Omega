package omega.sgb.gui;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorBusquedaLibro;

import java.sql.SQLException;

public class ResultadosBibliotecarioGUI {
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public ResultadosBibliotecarioGUI() throws SQLException {}
    public ResultadosBibliotecarioGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
}
