package com.example.aplicacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorLibros {
    private Connection conexion;
    GestorLibros(Connection conexion) {
        this.conexion = conexion;
    }
    public void addLibro(String titulo, String autor, int año, String isbn) throws SQLException{
        Statement baseDatos = this.conexion.createStatement();
        baseDatos.execute("insert into libros (titulo, autor, año, isbn) VALUES ('"+titulo+"', '"+autor+"', "+año+", '"+isbn+"')");
        System.out.println("Libro añadido correctamente");
        baseDatos.close();
        
    }
    public void todosLibros() throws SQLException{
        Statement baseDatos = this.conexion.createStatement();
        ResultSet result = baseDatos.executeQuery("select * from libros;");
        System.out.println("Listado de libros:");
        while (result.next()) {
            System.out.println("Titulo: " + result.getString("titulo") + ", Autor: " + result.getString("autor") + ", Año: " + result.getInt("año") + ", ISBN: " + result.getString("isbn"));
        }
        result.close();
        baseDatos.close();
    }
    public void LibrosAutor(String autor) throws SQLException{
        Statement baseDatos = this.conexion.createStatement();
        ResultSet result = baseDatos.executeQuery("select * from libros where autor = '"+autor+"';");
        System.out.println("Listado de libros:");
        while (result.next()) {
            System.out.println("Titulo: " + result.getString("titulo") + ", Autor: " + result.getString("autor") + ", Año: " + result.getInt("año") + ", ISBN: " + result.getString("isbn"));
        }
        result.close();
        baseDatos.close();
    }
    public void LibrosPosteriorAño(int año) throws SQLException{
        Statement baseDatos = this.conexion.createStatement();
        ResultSet result = baseDatos.executeQuery("select * from libros where año > '"+año+"';");
        System.out.println("Listado de libros:");
        while (result.next()) {
            System.out.println("Titulo: " + result.getString("titulo") + ", Autor: " + result.getString("autor") + ", Año: " + result.getInt("año") + ", ISBN: " + result.getString("isbn"));
        }
        result.close();
        baseDatos.close();
    }
    public void modificarTitulo(String tituloNuevo, String tituloViejo) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("update libros set titulo = '"+tituloNuevo+"' where titulo = '"+tituloViejo+"'");
        if (resultado == 1) {
            System.out.println("El libro existe por lo tanto se modifica");
        }
        else {
            System.out.println("El libro no se ha encontrado no se modifica nada");
        }
        baseDatos.close();
    }
    public void modificarAutor(String autor, String titulo) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("update libros set autor = '"+autor+"' where titulo = '"+titulo+"'");
        if (resultado == 1) {
            System.out.println("El libro existe por lo tanto se modifica");
        }
        else {
            System.out.println("El libro no se ha encontrado no se modifica nada");
        }
        baseDatos.close();
    }
    public void modificarAño(int año, String titulo) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("update libros set año = '"+año+"' where titulo = '"+titulo+"'");
        if (resultado == 1) {
            System.out.println("El libro existe por lo tanto se modifica");
        }
        else {
            System.out.println("El libro no se ha encontrado no se modifica nada");
        }
        baseDatos.close();
    }
    public void eliminarLibro(String titulo) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("delete from libros where titulo = '"+titulo+"'");
        if (resultado == 1) {
            System.out.println("El libro existe por lo tanto se borra");
        }
        else {
            System.out.println("El libro no se ha encontrado no se borra nada");
        }
        baseDatos.close();
    }
    public void eliminarLibrosAño(int año) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("delete from libros where año = '"+año+"'");
        if (resultado > 0) {
            System.out.println("Se han borrado " + resultado + " libros.");
        }
        else {
            System.out.println("El libro no se ha encontrado no se borra nada");
        }
        baseDatos.close();
    }
    public void LimpiarLibros() throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("delete from libros");
        if (resultado > 0) {
            System.out.println("Se han borrado " + resultado + " libros.");
        }
        else {
            System.out.println("No se han encontrado libros para borrar.");
        }
        baseDatos.close();
    }
}
