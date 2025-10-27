package com.example.aplicacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBolechas {
    private Connection conexion;

    GestorBolechas() throws SQLException {
        String bd = "localhost:3306";
        String tipo = "mysql";
        String database = "Bolechas";
        String userDios = "root";
        String passwordDios = "root";
        this.conexion = DriverManager.getConnection("jdbc:" + tipo + "://" + bd, userDios, passwordDios);

        CrearDB(database);
        CrearTablas(database);
        CrearUsuario(database);
        conexion.close();
    }

    private void CrearDB(String db) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.execute("create database if not exists " + db);
        System.out.println("base de datos creada");
        ejecuciones.close();
    }

    private void CrearTablas(String db) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.execute("use " + db);
        // Crea tabla clientes
        ejecuciones.execute("""
                create table if not exists cliente (
                    dni char(9) primary key,
                    nombre varchar(100)
                )
                """);
        System.out.println("clientes creados");
        // Crea tabla productos
        ejecuciones.execute("""
                create table if not exists producto (
                    id int primary key auto_increment,
                    nombre varchar(100),
                    precio double,
                    descripcion varchar(255)
                )
                """);
        System.out.println("productos creados");
        // Crea tabla pedido
        ejecuciones.execute("""
                create table if not exists pedido (
                id int primary key auto_increment,
                fecha datetime,
                dni char(9),

                FOREIGN KEY (dni) REFERENCES cliente(dni)
                )
                """);
        System.out.println("pedidos creados");
        ejecuciones.execute("""
                create table if not exists pedido_producto (
                id_pedido int,
                id_producto int,
                cantidad int,
                primary key (id_pedido, id_producto),

                FOREIGN KEY (id_pedido) REFERENCES pedido(id),
                FOREIGN KEY (id_producto) REFERENCES producto(id)
                )
                """);

        ejecuciones.close();

    }

    private void CrearUsuario(String db) throws SQLException {
        // Crea usuario
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.execute("""
                    CREATE USER IF NOT EXISTS 'bolecha'@'%' IDENTIFIED BY 'abc123.';
                """);
        System.out.println("Usuario creado");
        ejecuciones.execute("GRANT ALL PRIVILEGES ON " + db + ".* TO 'bolecha'@'%';");
        System.out.println("Permisos usuarios");
        ejecuciones.execute("FLUSH PRIVILEGES;");
        System.out.println("Permisos actualizados");
        ejecuciones.close();
    }
}
