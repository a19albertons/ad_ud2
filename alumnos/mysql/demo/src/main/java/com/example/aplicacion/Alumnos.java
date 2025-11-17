package com.example.aplicacion;

import java.sql.Connection;
import java.sql.Statement;

import com.example.conexiones.MySQLConnection;

public class Alumnos {
    int id; // PK y AUTO INCREMENTAL
    String nombre;
    int edad;
    String email;

    public Alumnos() {
        
    }

    public Alumnos(int id, String nombre, int edad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public Alumnos(String nombre, int edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public void CrearTabla() {
        try (Connection conexion = new MySQLConnection().getConnection();
        Statement acciones = conexion.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS alumnos (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "nombre VARCHAR(100) NOT NULL," +
                         "edad INT NOT NULL," +
                         "email VARCHAR(100) NOT NULL" +
                         ");";
            acciones.executeUpdate(sql);
            System.out.println("Tabla 'alumnos' creada correctamente.");
            
        } catch (Exception e) {
            System.out.println("Error al crear la tabla alumnos esto tiene 2 causas posibles de error conocidas");
            System.out.println("1- La conexion");
            System.out.println("2- La sintasix no es correcta");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

}
