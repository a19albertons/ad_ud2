package com.example.aplicacion;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cliente {
	private String dni;
	private String nombre;

    public void verTodo(Connection conexion) throws SQLException{
        Statement ejecuciones = conexion.createStatement();
        ResultSet contenido = ejecuciones.executeQuery("select * from cliente");
        while (contenido.next()) {
            String dni = contenido.getString("dni");
            String nombre = contenido.getString("nombre");
            System.out.println("DNI: " + dni + ", Nombre: " + nombre);
        }
        ejecuciones.close();
    }

    public void anadirUsuario(String dni, String nombre, Connection conexion) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("insert into cliente (dni, nombre) values ('"+dni+"', '"+nombre+"')");
        ejecuciones.close();
    }
    public void borrarUsuario(String dni, Connection conexion) throws SQLException{
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("delete from cliente where dni = '"+dni+"'");
        ejecuciones.close();
    }
    public void modificarUsuario(String dni, String nombre, Connection conexion) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("update cliente set nombre = '"+nombre+"' where dni = '"+dni+"'");
        ejecuciones.close();
    }
    public void verUsuarioEspecifico(String dni, Connection conexion) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ResultSet contenido = ejecuciones.executeQuery("select * from cliente where dni = '"+dni+"'");
        while (contenido.next()) {
            String dniInterno = contenido.getString("dni");
            String nombre = contenido.getString("nombre");
            System.out.println("DNI: " + dniInterno + ", Nombre: " + nombre);
        }
        ejecuciones.close();
    }
}
