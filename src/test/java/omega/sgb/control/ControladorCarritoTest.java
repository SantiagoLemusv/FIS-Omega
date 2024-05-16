package omega.sgb.control;

import org.junit.jupiter.api.Test;
import omega.sgb.SingletonControladores;
import omega.sgb.integracion.DataBaseConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControladorCarritoTest {

    private static final String URL = "jdbc:h2:./test";
    private static final String USUARIO = "omega";
    private static final String CONTRASENA = "";

    private ControladorCarrito controladorCarrito;

    @BeforeEach
    void init() throws SQLException, FileNotFoundException {
        Connection connectionH2 = DataBaseConnectionManager.getConnectionH2(URL, USUARIO, CONTRASENA);
        omega.sgb.integracion.InicializadorBD inicializarBD = new omega.sgb.integracion.InicializadorBD(
                connectionH2);
        inicializarBD.initDB();
        SingletonControladores.setConexionGeneral(connectionH2);
        controladorCarrito = SingletonControladores.getInstanceControladorCarrito();

    }

}