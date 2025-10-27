package com.example.aplicacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GestorLibros {
    private Connection conexion;

    GestorLibros(Connection conexion) {
        this.conexion = conexion;
    }

    public void addLibro(String titulo, String autor, int año, String isbn) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        baseDatos.execute("insert into libros (titulo, autor, año, isbn) VALUES ('" + titulo + "', '" + autor + "', "
                + año + ", '" + isbn + "')");
        System.out.println("Libro añadido correctamente");
        baseDatos.close();
    }

    public void addLibrosCSV(String ruta) throws SQLException, FileNotFoundException, IOException {
        Statement baseDatos = this.conexion.createStatement();
        File fichero = new File(ruta);
        FileInputStream fichero_legible = new FileInputStream(fichero);
        byte[] contenido = fichero_legible.readAllBytes();
        String[] convertido = new String(contenido, "UTF-8").split("\n");
        String titulo;
        String autor;
        int año;
        String isbn;
        for (int i = 0; i < convertido.length; i++) {
            String[] array = convertido[i].split(";");
            if (array.length != 4) {
                System.out.println("Revise los delimitadores el tamaño no es el esperado. Se indica la linea erronea");
                System.out.println(convertido[i]);
                continue;
            }
            titulo = array[0];
            autor = array[1];
            try {
                año = Integer.parseInt(array[2]);
            } catch (NumberFormatException e) {
                System.out.println("Has ingresado un valor para el año erroneo la siguiente linea esta mal formatada");
                System.out.println(convertido[i]);
                continue;
            }
            isbn = array[3];
            baseDatos
                    .execute("insert into libros (titulo, autor, año, isbn) VALUES ('" + titulo + "', '" + autor + "', "
                            + año + ", '" + isbn + "')");
            System.out.println("Libro añadido correctamente");
        }
        fichero_legible.close();
        baseDatos.close();
    }

    public void todosLibros() throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        ResultSet result = baseDatos.executeQuery("select * from libros;");
        System.out.println("Listado de libros:");
        while (result.next()) {
            System.out.println("Titulo: " + result.getString("titulo") + ", Autor: " + result.getString("autor")
                    + ", Año: " + result.getInt("año") + ", ISBN: " + result.getString("isbn"));
        }
        result.close();
        baseDatos.close();
    }

    public void LibrosAutor(String autor) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        ResultSet result = baseDatos.executeQuery("select * from libros where autor = '" + autor + "';");
        System.out.println("Listado de libros:");
        while (result.next()) {
            System.out.println("Titulo: " + result.getString("titulo") + ", Autor: " + result.getString("autor")
                    + ", Año: " + result.getInt("año") + ", ISBN: " + result.getString("isbn"));
        }
        result.close();
        baseDatos.close();
    }

    public void LibrosPosteriorAño(int año) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        ResultSet result = baseDatos.executeQuery("select * from libros where año > '" + año + "';");
        System.out.println("Listado de libros:");
        while (result.next()) {
            System.out.println("Titulo: " + result.getString("titulo") + ", Autor: " + result.getString("autor")
                    + ", Año: " + result.getInt("año") + ", ISBN: " + result.getString("isbn"));
        }
        result.close();
        baseDatos.close();
    }

    public void modificarTitulo(String tituloNuevo, String tituloViejo) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos
                .executeUpdate("update libros set titulo = '" + tituloNuevo + "' where titulo = '" + tituloViejo + "'");
        if (resultado == 1) {
            System.out.println("El libro existe por lo tanto se modifica");
        } else {
            System.out.println("El libro no se ha encontrado no se modifica nada");
        }
        baseDatos.close();
    }

    public void modificarAutor(String autor, String titulo) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos
                .executeUpdate("update libros set autor = '" + autor + "' where titulo = '" + titulo + "'");
        if (resultado == 1) {
            System.out.println("El libro existe por lo tanto se modifica");
        } else {
            System.out.println("El libro no se ha encontrado no se modifica nada");
        }
        baseDatos.close();
    }

    public void modificarAño(int año, String titulo) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos
                .executeUpdate("update libros set año = '" + año + "' where titulo = '" + titulo + "'");
        if (resultado == 1) {
            System.out.println("El libro existe por lo tanto se modifica");
        } else {
            System.out.println("El libro no se ha encontrado no se modifica nada");
        }
        baseDatos.close();
    }

    public void eliminarLibro(String titulo) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("delete from libros where titulo = '" + titulo + "'");
        if (resultado == 1) {
            System.out.println("El libro existe por lo tanto se borra");
        } else {
            System.out.println("El libro no se ha encontrado no se borra nada");
        }
        baseDatos.close();
    }

    public void eliminarLibrosAño(int año) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("delete from libros where año = '" + año + "'");
        if (resultado > 0) {
            System.out.println("Se han borrado " + resultado + " libros.");
        } else {
            System.out.println("El libro no se ha encontrado no se borra nada");
        }
        baseDatos.close();
    }

    public void eliminarLibrosAutor(String autor) throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("delete from libros where autor = '" + autor + "'");
        if (resultado > 0) {
            System.out.println("Se han borrado los libros del autor");
        } else {
            System.out.println("No se ha encontrado al autor así que no se ha borrado ningun libro");
        }
        baseDatos.close();
    }

    public void LimpiarLibros() throws SQLException {
        Statement baseDatos = this.conexion.createStatement();
        int resultado = baseDatos.executeUpdate("delete from libros");
        if (resultado > 0) {
            System.out.println("Se han borrado " + resultado + " libros.");
        } else {
            System.out.println("No se han encontrado libros para borrar.");
        }
        baseDatos.close();
    }
}
