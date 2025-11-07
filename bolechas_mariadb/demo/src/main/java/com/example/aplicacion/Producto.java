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

	/**
	 * Ver todos los productos 
	 * @param conexion conexin a la bd
	 * @throws SQLException si algo sale mal en los select
	 */
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
	/** 
	 * añadir productos
	 * @param nombre nombre del producto
	 * @param precio precio del producto
	 * @param descripcion descripcion del producto
	 * @param conexion conexion a la bd
	 * @throws SQLException error en los inserts
	 */
	public void anadirProducto(String nombre, double precio, String descripcion, Connection conexion) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("insert into producto (nombre, precio, descripcion) values ('"+nombre+"', '"+precio+"', '"+descripcion+"')");
        ejecuciones.close();
    }
	/** 
	 * Borra un producto
	 * @param id id del producto a borrar
	 * @param conexion conexion a la bd
	 * @throws SQLException si algo sale mal en borrado del producto
	 */
	public void borrarProducto(int id, Connection conexion) throws SQLException{
		Statement ejecuciones = conexion.createStatement();
		ejecuciones.executeUpdate("delete from producto where id = '"+id+"'");
		ejecuciones.close();
	}
	/** 
	 * Modifica un producto
	 * @param id Indica el ID del producto a modificar
	 * @param nombre nuevo nombre o el antiguo sino se quiere cambiar
	 * @param precio nuevo precio o el antiguo sino se quiere cambiar
	 * @param descripcion nueva descripcion o la antigua sino se quiere cambiar
	 * @param conexion conexion a la bd
	 * @throws SQLException si algo sale mal en el update
	 */
	public void modificarProducto(int id, String nombre, double precio, String descripcion, Connection conexion) throws SQLException {
		Statement ejecuciones = conexion.createStatement();
		ejecuciones.executeUpdate("update producto set nombre = '"+nombre+"', precio = '"+precio+"', descripcion = '"+descripcion+"' where id = '"+id+"'");
		ejecuciones.close();
	}

}
