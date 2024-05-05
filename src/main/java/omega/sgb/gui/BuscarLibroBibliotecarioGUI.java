package omega.sgb.gui;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorBusquedaLibro;

import java.sql.SQLException;

public class BuscarLibroBibliotecarioGUI {
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public BuscarLibroBibliotecarioGUI() throws SQLException {}
    public BuscarLibroBibliotecarioGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
}
