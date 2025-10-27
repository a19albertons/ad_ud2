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
        String user = "biblioUser";
        String password = "abc123.";
        String servidor = "mysql";

        // Conexión de dios
        Connection root = DriverManager.getConnection("jdbc:" + servidor + "://" + serverYPuerto + "/", "root", "root");
        Statement baseDatosRoot = root.createStatement();
        Boolean resultado;
        if (rehacer) {
            // Eliminar base de datos existente
            resultado = baseDatosRoot.execute("DROP DATABASE IF EXISTS " + baseDatosNombre);
            if (!resultado) {
                System.out.println("Base de datos reiniciado con exito");
            }
        }
        // Creación base de datos por dios
        resultado = baseDatosRoot.execute("create database if not exists " + baseDatosNombre);
        if (!resultado) {
            System.out.println("Operaciones sobre base de datos no devuelven error");
        }

        // Creacion usuario y permisos por dios
        resultado = baseDatosRoot
                .execute("CREATE USER IF NOT EXISTS '" + user + "'@'%' IDENTIFIED BY '" + password + "';");
        resultado = baseDatosRoot.execute("GRANT ALL PRIVILEGES ON " + baseDatosNombre + ".* TO '" + user + "'@'%';");
        // Activar los cambios
        resultado = baseDatosRoot.execute("FLUSH PRIVILEGES;");

        // Conexión a la base de datos
        conexion = DriverManager.getConnection("jdbc:" + servidor + "://" + serverYPuerto + "/" + baseDatosNombre, user, password);
        Statement baseDatos = this.conexion.createStatement();
        resultado = baseDatos.execute(
                "create table if not exists libros (titulo varchar(100), autor varchar(100), año int, isbn varchar(20))");
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
