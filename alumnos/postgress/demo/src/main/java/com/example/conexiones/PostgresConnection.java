package com.example.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.aplicacion.ConfigLoader;

public class PostgresConnection implements DBConnection{

    /** 
     * @return Connection
     */
    // private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    // private static final String USER = "usuario";
    // private static final String PASSWORD = "usuario123";


    @Override
    public Connection getConnection() {
        /* Con archivo aplicacion.properties */
        String URL = ConfigLoader.get ("postgres.url");
        String BD = ConfigLoader.get("postgres.db");
        String USER = ConfigLoader.get ("postgres.user");
        String PASSWORD = ConfigLoader.get ("postgres.password");


        try {
            return DriverManager.getConnection(URL+BD, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error conectando a Postgres: " + e.getMessage());
            return null;
        }
    }
    public Connection getConnectionServer() {
        /* Con archivo aplicacion.properties */
        String URL = ConfigLoader.get ("postgres.urlInicial");
        String USER = ConfigLoader.get ("postgres.user");
        String PASSWORD = ConfigLoader.get ("postgres.password");


        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error conectando a Postgres: " + e.getMessage());
            return null;
        }
    }
}
