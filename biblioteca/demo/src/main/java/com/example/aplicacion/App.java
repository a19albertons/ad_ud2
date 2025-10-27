package com.example.aplicacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // testConnection(dbConnection , "MySQL:biblioAlberN");
        Scanner scanner = new Scanner(System.in);
        Boolean borrarBaseDeDatos;
        System.out.println(
                "¿Desea borrar la base de datos existente y crear una nueva? ('s') cualquier otro caracter para no");
        if (scanner.next().equals("s")) {
            borrarBaseDeDatos = true;
        } else {
            borrarBaseDeDatos = false;
        }

        try {
            // Gestiona las bases de la biblioteca y libros
            GestorBiblioteca gestorBiblioteca = new GestorBiblioteca(borrarBaseDeDatos);
            GestorLibros gestorLibros = new GestorLibros(gestorBiblioteca.getConexion());

            int opcion = 1;

            while (opcion != 0) {
                // Gestiona la impresion del menu
                System.out.println("");
                System.out.println("Menu");
                System.out.println("0- Salir");
                System.out.println("1- Añadir libros");
                System.out.println("2- Listar todos los libros");
                System.out.println("3- Listar libros por autor");
                System.out.println("4- Listar libros posteriores a un año");
                System.out.println("5- Modificar titulo de un libro");
                System.out.println("6- Modificar autor de un libro");
                System.out.println("7- Modificar año de un libro");
                System.out.println("8- Eliminar un libro por titulo");
                System.out.println("9- Eliminar libros por año");
                System.out.println("10- Eliminar todos los libros");
                System.out.println("");
                System.out.println("Extras:");
                System.out.println("11- Eliminar libros de un autor");
                System.out.println("12- Añadir libros desde CSV");
                System.out.println("");
                System.out.print("Indique una opción: ");
                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Has ingresado un numero invalido");
                    // Limpiamos el buffer
                    scanner.nextLine();
                    opcion = 999;
                }

                switch (opcion) {
                    case 1:
                        try {
                            System.out.println("Indique el titulo (no se aceptan espacios)");
                            String titulo = scanner.next();
                            System.out.println("Indique el autor (no se aceptan espacios)");
                            String autor = scanner.next();
                            System.out.println("Indique el año");
                            int año = scanner.nextInt();
                            System.out.println("Indique el ISBN");
                            String isbn = scanner.next();
                            gestorLibros.addLibro(titulo, autor, año, isbn);
                        } catch (InputMismatchException e) {
                            System.out.println(
                                    "Ha ingresado algo distinto a un int en año. Se cancela la adición del libro");
                            // Limpiamos el buffer por si las moscas
                            scanner.nextLine();
                        }

                        break;
                    case 2:
                        gestorLibros.todosLibros();
                        break;
                    case 3:
                        System.out.println("Indique el autor");
                        String autor = scanner.next();
                        gestorLibros.LibrosAutor(autor);
                        break;
                    case 4:
                        try {
                            System.out.println("Indique el año");
                            int año = scanner.nextInt();
                            gestorLibros.LibrosPosteriorAño(año);
                        } catch (InputMismatchException e) {
                            System.out.println("Ha ingresado algo distinto a un int en año. Se cancela la búsqueda");
                            // Limpiamos el buffer por si las moscas
                            scanner.nextLine();
                        }
                        break;
                    case 5:
                        System.out.println("Indique el titulo nuevo (no se aceptan espacios)");
                        String tituloNuevo = scanner.next();
                        System.out.println("Indique el titulo viejo (no se aceptan espacios)");
                        String tituloViejo = scanner.next();
                        gestorLibros.modificarTitulo(tituloNuevo, tituloViejo);
                        break;

                    case 6:
                        System.out.println("Indique el autor (no se aceptan espacios)");
                        // Al parecer una variable declarada en un switch es para todo
                        autor = scanner.next();
                        System.out.println("Indique el titulo (no se aceptan espacios)");
                        tituloViejo = scanner.next();
                        gestorLibros.modificarAutor(autor, tituloViejo);
                        break;

                    case 7:
                        try {
                            System.out.println("Indique el año");
                            int año = scanner.nextInt();
                            System.out.println("Indique el titulo viejo (no se aceptan espacios)");
                            tituloViejo = scanner.next();
                            gestorLibros.modificarAño(año, tituloViejo);
                        } catch (InputMismatchException e) {
                            System.out.println("Has insertado un int no valido no se hace nada");
                        }
                        break;
                    case 8:
                        System.out.println("Indique un titulo");
                        String titulo = scanner.next();
                        gestorLibros.eliminarLibro(titulo);
                        break;

                    case 9:
                        try {
                            System.out.println("Indique el año");
                            int año = scanner.nextInt();
                            gestorLibros.eliminarLibrosAño(año);
                        } catch (InputMismatchException e) {
                            System.out.println("Has insertado un int no valido no se hace nada");
                        }
                        break;
                    case 10:
                        gestorLibros.LimpiarLibros();
                        break;

                    case 11:
                        System.out.println("Indique el autor (no se aceptan espacios)");
                        // En algun momento previo se declara al autor en switch
                        autor = scanner.next();
                        gestorLibros.eliminarLibrosAutor(autor);
                        break;
                    case 12:
                        System.out.println("Indique ruta al fichero (que no tenga cabecera) y la ruta sin espacios.");
                        String ruta = scanner.next();
                        gestorLibros.addLibrosCSV(ruta);
                        break;

                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println("Ha surgido algun error en ejecución del SQL le dejo el código de error:");
            System.out.println(e.getErrorCode());
        } catch (FileNotFoundException e) {
            System.out.println("El fichero indicado no ha sido encontrado revise la ruta");
        } catch (IOException e) {
            System.out.println("Revise los permisos del fichero ha habido problemas para leer su contenido");
        } catch (Exception e) {
            System.out.println(
                    "Ha surgido algun error no previsto en pruebas internas en la ejecución habra una incidencia con el error:");
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    // private static void testConnection(DBConnection dbConnection, String dbName)
    // {
    // System.out.println("Probando conexión con "+dbName+ "...");;
    // try (Connection conn = dbConnection.getConnection()){
    // if (conn != null) {
    // System.out.println("Conexión establecida con " + dbName + "\n");
    // }
    // else {
    // System.out.println("No se puede establecer la conexión con " + dbName +
    // "\n");
    // }
    // } catch (Exception e) {
    // System.out.println("ERROR inesperado: "+ e.getMessage());
    // }
    // }
}
