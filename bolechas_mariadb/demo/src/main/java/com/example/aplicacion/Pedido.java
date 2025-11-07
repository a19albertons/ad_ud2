package com.example.aplicacion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
	private int id;
	private String fecha;
	private int dni_cliente;

	/** 
	 * Ver pedidos clientes 
	 * @param dni dni del cliente que se quiere ver
	 * @param conexion conexion a la bd
	 * @throws SQLException Si algo sale mal en la consulta
	 */
	public void verPedidoCliente(String dni, Connection conexion) throws SQLException {
		Statement ejecuciones = conexion.createStatement();
		String sql = """
				select pedido.fecha, producto.nombre, (precio * cantidad) as total
				from pedido
				join pedido_producto on pedido.id = pedido_producto.id_pedido
				join producto on pedido_producto.id_producto = producto.id
				where pedido.dni = '?'
				""";
		sql = sql.replace("?", dni);
		ResultSet contenido = ejecuciones.executeQuery(sql);
		while (contenido.next()) {
			LocalDate fecha = contenido.getDate("fecha").toLocalDate();
			String nombreProducto = contenido.getString("nombre");
			double total = contenido.getDouble("total");
			System.out.println("Fecha: " + fecha + ", Producto: " + nombreProducto + ", Total: " + total);
		}
		ejecuciones.close();

	}

	/** 
	 * Añadir un pedido
	 * @param fecha una fecha valida (se puede coger una del sistema)
	 * @param dni dni del cliente
	 * @param conexion conexion a la bd
	 * @throws SQLException Si sale algo mal en el insert
	 */
	public void AñadirPedido(String fecha, String dni, Connection conexion) throws SQLException {
		Statement ejecuciones = conexion.createStatement();
		ejecuciones.executeUpdate("insert into pedido (fecha, dni) values ('"+fecha+"', '"+dni+"')");
		ejecuciones.close();
	}
	/** 
	 * Se obtiene el id del utlimo pedido 
	 * @param conexion conexion a labd
	 * @return int devuel el id más alto
	 * @throws SQLException si algo sale mal en la consulta
	 */
	public int obtenerIdPedidoReciente(Connection conexion) throws SQLException {
		Statement ejecuciones = conexion.createStatement();
		ResultSet contenido = ejecuciones.executeQuery("select max(id) as id from pedido");
		int devolver = -1;
		while (contenido.next()) {
			 devolver= contenido.getInt("id");
		}
		ejecuciones.close();
		if (devolver == -1) {
			throw new SQLException("No se ha podido obtener el id del pedido reciente");
		}
		return devolver;
	}
	/** 
	 * Te devuelve todos los productos asociados a un pedido
	 * @param idPedido indicas el id de pedido a exportar
	 * @param conexion conexion a la bd
	 * @return ArrayList<gsonPedido> Devuelve un array para ser pasado a un fichero json
	 * @throws SQLException si algo sale mal en las operaciones sobre la bd
	 */
	public ArrayList<gsonPedido> obtenerDatosPedido(int idPedido, Connection conexion) throws SQLException {
		ArrayList<gsonPedido> listaPedidos = new ArrayList<>();
		Statement ejecuciones = conexion.createStatement();
		String sql = """
				select pedido.fecha, pedido.dni as dni_cliente, producto.id as idProducto, producto.nombre, producto.precio, producto.descripcion, pedido_producto.cantidad
				from pedido
				join pedido_producto on pedido.id = pedido_producto.id_pedido
				join producto on pedido_producto.id_producto = producto.id
				where pedido.id = ?
				""";
		sql = sql.replace("?", Integer.toString(idPedido));
		ResultSet contenido = ejecuciones.executeQuery(sql);
		while (contenido.next()) {
			gsonPedido gp = new gsonPedido();
			gp.fecha = contenido.getDate("fecha").toString();
			gp.dni_cliente = contenido.getInt("dni_cliente");
			gp.idProducto = contenido.getInt("idProducto");
			gp.nombre = contenido.getString("nombre");
			gp.precio = contenido.getDouble("precio");
			gp.descripcion = contenido.getString("descripcion");
			gp.cantidad = contenido.getInt("cantidad");
			listaPedidos.add(gp);
		}
		ejecuciones.close();
		return listaPedidos;
	}
}
class gsonPedido {
	String fecha;
	int dni_cliente;
	int idPedido;
    int idProducto;
    int cantidad;
	String nombre;
	double precio;
	String descripcion;
}
