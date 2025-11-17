package com.example.aplicacion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.conexiones.MySQLConnection;

public class GestorDam2 {

    /**
     * Inicia la clase
     */
    public GestorDam2() {

    }

    /**
     * Crea la base de datos dam2
     */
    public void crearBD() {
        try (Connection conn = new MySQLConnection().getConnectionServer();
                Statement stmt = conn.createStatement()) {
            String db = ConfigLoader.get("mysql.db");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + db);
            System.out.println("Base de datos '" + db + "' creada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

}
