package omega.sgb.testers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;
public class InicializarBD {
    private Connection connection;

    public InicializarBD(Connection connection) {
        this.connection = connection;
    }

    public void initDB() throws FileNotFoundException, SQLException {
        String sqlFile = this.getClass().getResource("/data.sql").getFile();
        RunScript.execute(connection, new FileReader(sqlFile));
    }

}

