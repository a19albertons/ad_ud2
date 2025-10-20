package com.example.aplicacion;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.example.conexiones.MySQLConnection;


public class App {

    public static void main(String[] args) {
        MySQLConnection dbConnection = new MySQLConnection();
        // testConnection(dbConnection , "MySQL:biblioAlberN");
        Scanner scanner = new Scanner(System.in);

        try {
            GestorBiblioteca prueba = new GestorBiblioteca(dbConnection);

            int opcion = 1;

            while (opcion != 0) {
                System.out.println("");
                System.out.println("Menu");
                System.out.println("1- Añadir libros");
                System.out.println("2- Listar todos los libros");
                System.out.println("3- Listar libros por autor");
                System.out.println("4- Listar libros por año");
                System.out.println("5- Modificar titulo de un libro");
                System.out.println("6- Modificar autor de un libro");
                System.out.println("7- Modificar año de un libro");
                System.out.println("8- Eliminar un libro por titulo");
                System.out.println("9- Eliminar libros por año");
                System.out.println("10- Eliminar todos los libros");
                System.out.println("0- Salir");
                System.out.println("");
                System.out.print("Indique una opción: ");
                opcion = scanner.nextInt();
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
                        prueba.addLibro(titulo, autor, año, isbn);
                    } catch (InputMismatchException e) {
                        System.out.println("Ha ingresado algo distinto a un int en año. Se cancela la adición del libro");
                        // Limpiamos el buffer por si las moscas
                        scanner.nextLine();
                    }
                    
                    break;
                case 2:
                    prueba.todosLibros();
                    break;
                case 3:
                    System.out.println("Indique el autor");
                    String autor = scanner.next();
                    prueba.LibrosAutor(autor);
                    break;
                case 4:
                    try {
                        System.out.println("Indique el año");
                        int año = scanner.nextInt();
                        prueba.LibrosAutor(año);
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
                    prueba.modificarTitulo(tituloNuevo, tituloViejo);
                    break;

                case 6:
                    System.out.println("Indique el autor (no se aceptan espacios)");
                    // Al parecer una variable declarada en un switch es para todo
                    autor = scanner.next();
                    System.out.println("Indique el titulo (no se aceptan espacios)");
                    tituloViejo = scanner.next();
                    prueba.modificarAutor(autor, tituloViejo);
                    break;
                
                case 7:
                    try {
                        System.out.println("Indique el año");
                        int año = scanner.nextInt();
                        System.out.println("Indique el titulo viejo (no se aceptan espacios)");
                        tituloViejo = scanner.next();
                        prueba.modificarAño(año, tituloViejo);
                    } catch (InputMismatchException e) {
                        System.out.println("Has insertado un int no valido no se hace nada");
                    }
                    break;
                case 8:
                    System.out.println("Indique un titulo");
                    String titulo = scanner.next();
                    prueba.eliminarLibro(titulo);
                    break;

                case 9:
                    try {
                        System.out.println("Indique el año");
                        int año = scanner.nextInt();
                        prueba.eliminarLibrosAño(año);
                    } catch (InputMismatchException e) {
                        System.out.println("Has insertado un int no valido no se hace nada");
                    }
                    break;
                case 10:
                    prueba.LimpiarLibros();
                    break;
                    

                default:
                    System.out.println("Opción no válida");
                    break;
            }
            }
            
        } catch (SQLException e) {
            System.out.println("Ha surgido algun error en ejecución le dejo el error sql");
            System.out.println(e.getErrorCode());
        }
        
        
        scanner.close();
    }
    

    // private static void testConnection(DBConnection dbConnection, String dbName) {
    //     System.out.println("Probando conexión con "+dbName+ "...");;
    //     try (Connection conn = dbConnection.getConnection()){
    //         if (conn != null) {
    //             System.out.println("Conexión establecida con " + dbName + "\n");
    //         }
    //         else {
    //             System.out.println("No se puede establecer la conexión con " + dbName + "\n");
    //         }
    //     } catch (Exception e) {
    //         System.out.println("ERROR inesperado: "+ e.getMessage());
    //     }
    // }
}
