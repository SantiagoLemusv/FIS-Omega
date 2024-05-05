package omega.sgb.gui;

import omega.sgb.SingletonControladores;
import omega.sgb.control.ControladorBusquedaLibro;
import omega.sgb.control.ControladorLogIn;

import java.sql.SQLException;

public class BuscarLibroLectorGUI {
    private ControladorBusquedaLibro controladorBusquedaLibro = SingletonControladores.getInstanceControladorBusquedaLibro();
    public BuscarLibroLectorGUI() throws SQLException {}
    public BuscarLibroLectorGUI(ControladorBusquedaLibro controladorBusquedaLibro) throws SQLException {
        this.controladorBusquedaLibro = controladorBusquedaLibro;
    }
}
