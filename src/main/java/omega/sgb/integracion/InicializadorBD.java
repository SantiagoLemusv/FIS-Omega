package omega.sgb.integracion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;
public class InicializadorBD {
    private Connection connection;

    public InicializadorBD(Connection connection) {
        this.connection = connection;
    }

    public void initDB() throws FileNotFoundException, SQLException {
        String sqlFile = this.getClass().getResource("/data.sql").getFile();
        RunScript.execute(connection, new FileReader(sqlFile));
    }

}

