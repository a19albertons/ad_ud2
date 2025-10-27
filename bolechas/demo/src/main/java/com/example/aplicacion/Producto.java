package com.example.aplicacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Producto {
	private int id;
	private String nombre;
	private double precio;
	private String descripcion;

	public void verTodo(Connection conexion) throws SQLException{
		Statement ejecuciones = conexion.createStatement();
        ResultSet contenido = ejecuciones.executeQuery("select * from producto");
		while (contenido.next()) {
			int id = contenido.getInt("id");
			String nombre = contenido.getString("nombre");
			double precio = contenido.getDouble("precio");
			String descripcion = contenido.getString("descripcion");
			System.out.println("ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio + ", Descripcion: " + descripcion);
		}
		ejecuciones.close();
	}
	public void anadirProducto(String nombre, double precio, String descripcion, Connection conexion) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("insert into producto (nombre, precio, descripcion) values ('"+nombre+"', '"+precio+"', '"+descripcion+"')");
        ejecuciones.close();
    }
	public void borrarProducto(int id, Connection conexion) throws SQLException{
		Statement ejecuciones = conexion.createStatement();
		ejecuciones.executeUpdate("delete from producto where id = '"+id+"'");
		ejecuciones.close();
	}
	public void modificarProducto(int id, String nombre, double precio, String descripcion, Connection conexion) throws SQLException {
		Statement ejecuciones = conexion.createStatement();
		ejecuciones.executeUpdate("update producto set nombre = '"+nombre+"', precio = '"+precio+"', descripcion = '"+descripcion+"' where id = '"+id+"'");
		ejecuciones.close();
	}

}
