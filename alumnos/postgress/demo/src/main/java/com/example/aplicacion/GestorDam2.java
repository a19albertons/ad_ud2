package com.example.aplicacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.conexiones.PostgresConnection;

public class GestorDam2 {
    

    /**
     * Inicia la clase
     */
    public GestorDam2(){
    
    }
    public void crearBD() {
        try (Connection conn = new PostgresConnection().getConnectionServer();
             Statement stmt = conn.createStatement()) {
            String db = ConfigLoader.get("postgres.db");
            String user = ConfigLoader.get("postgres.user");
            String comprobar = "SELECT 1 FROM pg_database WHERE datname = '" + db + "'";
            ResultSet rs = stmt.executeQuery(comprobar);
            if (!rs.next()) {
                stmt.executeUpdate("CREATE DATABASE "+db+" WITH OWNER = "+user+" ENCODING = 'UTF8' TEMPLATE = template0;");
                System.out.println("Base de datos "+db+" creada correctamente.");
            }
            

        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

}
