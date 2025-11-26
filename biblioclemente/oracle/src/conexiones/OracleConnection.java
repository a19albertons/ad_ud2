package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import aplicacion.ConfigLoader;

public class OracleConnection implements DBConnection{

    /** 
     * @return Connection
     */
    // private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    // private static final String USER = "usuario";
    // private static final String PASSWORD = "usuario123";


    @Override
    public Connection getConnection() {
        /* Con archivo aplicacion.properties */
        String URL = ConfigLoader.get ("oracle.url");
        String USER = ConfigLoader.get ("oracle.user");
        String PASSWORD = ConfigLoader.get ("oracle.password");


        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error conectando a Oracle: " + e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getSQLState());
            return null;
        }
    }
    public Connection getConnectionServer() {
        return this.getConnection();
    }
}
