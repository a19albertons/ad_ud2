package com.example.aplicacion;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cliente {
	private String dni;
	private String nombre;

    /** 
     * Ver todos los clientes
     * @param conexion necesario para acceder
     * @throws SQLException si algo sale mal en la consulta
     */
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

    /** 
     * Metodo que añade usuario 
     * @param dni dni del usuario
     * @param nombre nombre del usuario
     * @param conexion conexion a la bd
     * @throws SQLException si algo sale mal en el insert
     */
    public void anadirUsuario(String dni, String nombre, Connection conexion) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("insert into cliente (dni, nombre) values ('"+dni+"', '"+nombre+"')");
        ejecuciones.close();
    }
    /** 
     * Metodo para borrar usuario
     * @param dni dni del usuario
     * @param conexion conexion a la bd
     * @throws SQLException si algo mal en el delete
     */
    public void borrarUsuario(String dni, Connection conexion) throws SQLException{
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("delete from cliente where dni = '"+dni+"'");
        ejecuciones.close();
    }
    /** 
     * Metodo para modificar el usuario
     * @param dni dni del usuario
     * @param nombre nombre usuario
     * @param conexion conexion a la bd
     * @throws SQLException si algo sale mal en el update
     */
    public void modificarUsuario(String dni, String nombre, Connection conexion) throws SQLException {
        Statement ejecuciones = conexion.createStatement();
        ejecuciones.executeUpdate("update cliente set nombre = '"+nombre+"' where dni = '"+dni+"'");
        ejecuciones.close();
    }
    /** 
     * Metodo para ver usuario especifico
     * @param dni dni del usuario
     * @param conexion conexion a la bd
     * @throws SQLException si algo sale mal en select
     */
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
