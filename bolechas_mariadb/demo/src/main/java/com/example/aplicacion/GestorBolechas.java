package com.example.aplicacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBolechas {
    private Connection conexion;
    private String database;
    private String userBD = "bolecha";
    private String passwordBD = "abc123.";
    private String tipo;
    private String bd;

    GestorBolechas(Boolean mysql) throws SQLException {
        
        
        
        if (mysql) {
            tipo = "mysql";
            bd = "localhost:3306";
        }
        else {
            tipo = "mysql";
            bd = "localhost:3307";
        }
        this.database = "Bolechas";
        String userDios = "root";
        String passwordDios = "root";
        
        this.conexion = DriverManager.getConnection("jdbc:" + tipo + "://"+this.bd, userDios, passwordDios);

    }

    /** 
     * @param db
     * @throws SQLException
     */
    public void CrearDB() throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.execute("create database if not exists " + this.database);
        System.out.println("base de datos creada");
        ejecuciones.close();
    }

    /** 
     * @param db
     * @throws SQLException
     */
    public void CrearTablas() throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.execute("use " + this.database);
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
    /**
     * 
     * @param db
     * @throws SQLException
     */
    public void CrearUsuario() throws SQLException {
        // Crea usuario
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.execute("CREATE USER IF NOT EXISTS '"+this.userBD+"'@'%' IDENTIFIED BY '"+this.passwordBD+"';");
        System.out.println("Usuario creado");
        ejecuciones.execute("GRANT ALL PRIVILEGES ON " + this.database + ".* TO '"+userBD+"'@'%';");
        System.out.println("Permisos usuarios");
        ejecuciones.execute("FLUSH PRIVILEGES;");
        System.out.println("Permisos actualizados");
        ejecuciones.close();
    }

    /** 
     * @return Connection
     * @throws SQLException
     */
    public Connection conexion() throws SQLException {
        Connection conexion = DriverManager.getConnection("jdbc:" + this.tipo + "://"+this.bd+"/"+this.database, this.userBD, this.passwordBD);
        return conexion;
    }

    
}
