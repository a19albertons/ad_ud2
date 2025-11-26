package aplicacion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import conexiones.OracleConnection;

public class GestorBiblio {
    // String bd = ConfigLoader.get("oracle.db");

    public void crearDB() {
        try (Connection conn = new OracleConnection().getConnectionServer();
                Statement acciones = conn.createStatement()) {

            System.out.println("base de datos creada correctamente");

        } catch (SQLException e) {
            System.out.println("Error desconocido creando la base de datos:");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }

        catch (Exception e) {
            System.out.println("Error desconocido creando la base de datos:");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    // public void crearUsuario() {
    // String userdb = ConfigLoader.get("oracle.userdb");
    // String passworddb = ConfigLoader.get("oracle.passworddb");
    // String bd = ConfigLoader.get("oracle.db");

    // try (Connection conn = new OracleConnection().getConnectionServer();
    // Statement acciones = conn.createStatement()) {
    // String sql = "CREATE USER " + userdb + " IDENTIFIED BY " + passworddb+"
    // default tablespace "+bd;
    // acciones.execute(sql);

    // } catch (SQLException e) {
    // System.out.println("Error desconocido creando la base de datos:");
    // System.out.println(e.getMessage());
    // System.out.println(e.getCause());
    // } catch (Exception e) {
    // System.out.println("Error desconocido creando la base de datos:");
    // System.out.println(e.getMessage());
    // System.out.println(e.getCause());
    // }
    // }

}