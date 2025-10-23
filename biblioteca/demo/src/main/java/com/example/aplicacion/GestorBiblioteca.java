package com.example.aplicacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class GestorBiblioteca {
    private Connection conexion;
    GestorBiblioteca(Boolean rehacer) throws SQLException {
        // Gestion nombre base de datos y servidor por variable local
        String baseDatosNombre = "biblioAlberN";
        String serverYPuerto = "localhost:3306";
        // Conexión de dios
        Connection root = DriverManager.getConnection("jdbc:mysql://" + serverYPuerto + "/", "root", "root");
        Statement baseDatosRoot = root.createStatement();
        if (rehacer) {
            // Eliminar base de datos existente
            baseDatosRoot.execute("DROP DATABASE IF EXISTS " + baseDatosNombre);
        }
        // Creación base de datos por dios
        Boolean resultado = baseDatosRoot.execute("create database if not exists " + baseDatosNombre);
        if (!resultado) {
            System.out.println("Operaciones sobre base de datos no devuelven error");
        }


        // Creacion usuario y permisos por dios
        resultado = baseDatosRoot.execute("CREATE USER IF NOT EXISTS 'biblioUser'@'%' IDENTIFIED BY 'abc123.';");
        resultado = baseDatosRoot.execute("GRANT ALL PRIVILEGES ON " + baseDatosNombre + ".* TO 'biblioUser'@'%';");
        // Activar los cambios
        resultado = baseDatosRoot.execute("FLUSH PRIVILEGES;");

        // Conexión a la base de datos
        conexion = DriverManager.getConnection("jdbc:mysql://" + serverYPuerto + "/" + baseDatosNombre, "biblioUser", "abc123.");
        Statement baseDatos = this.conexion.createStatement();
        resultado = baseDatos.execute("create table if not exists libros (titulo varchar(100), autor varchar(100), año int, isbn varchar(20))");
        if (!resultado) {
            System.out.println("Operaciones sobre tabla libros no devuelven error");
        }
        baseDatosRoot.close();
        baseDatos.close();

    }
    public Connection getConexion() {
        return conexion;
    }
}