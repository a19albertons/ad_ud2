package conexiones;

import java.sql.Connection;

/**
 * Interfaz que unifica la forma de obtener las conexiones de los diferentes conectores
 */
public interface DBConnection {
    Connection getConnection();
    Connection getConnectionServer();
}
