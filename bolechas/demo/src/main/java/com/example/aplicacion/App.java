package com.example.aplicacion;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class App {

    public static void main(String[] args) {
        try {
        // testConnection(dbConnection , "MySQL:biblioAlberN");
        Scanner scanner = new Scanner(System.in);
        
        int opcion = 999;
        while (opcion != -1) {
            Connection conexion;
            try {
                String bd = "jdbc:mysql://localhost:3306/Bolechas";
                String usuario = "bolecha";
                String password = "abc123.";
                conexion = DriverManager.getConnection(bd, usuario, password);
            } catch (SQLException e) {
                System.out.println("Aviso: La base de datos no esta inicializada. Si esta seguro de esto ultimo abra un ticket");
                conexion = null;
            }


            System.out.println("Menú de opciones:");
            System.out.println("");
            System.out.println("Mantenimiento de Bolechas");
            System.out.println("1 - Creacion bd y tablas");
            System.out.println("2 - Gestionar clientes");
            System.out.println("3 - Gestionar productos");
            System.out.println("999 - Custom borrar tablas");
            System.out.println("");
            System.out.println("Operaciones sobre la misma");
            System.out.println("4 - Informacion personal de un cliente");
            System.out.println("5 - Consultar pedidos de un cliente");
            System.out.println("6 - Realizar un pedido");
            System.out.println("7 - Exportar a Json");
            System.out.println("");
            System.out.println("-1 - Salir");

            opcion = scanner.nextInt();



            switch (opcion) {
                case 1:
                    // Gestion BD
                    new GestorBolechas();
                    break;
            
                case 2:
                    // Gestion cliente
                    System.out.println("Submenu");
                    System.out.println("1- Ver todos");
                    System.out.println("2- Añadir");
                    System.out.println("3- Eliminar");
                    System.out.println("4- Modificar");
                    int opcionCliente = scanner.nextInt();

                    Cliente cliente = new Cliente();
                    String dni;
                    String nombre;

                    switch (opcionCliente) {
                        
                        case 1:
                            cliente.verTodo(conexion);
                            break;
                        case 2:
                            System.out.println("Indique un dni (que no exista ya)");
                            dni = scanner.next();
                            System.out.println("Indica un nombre");
                            nombre = scanner.next();
                            cliente.anadirUsuario(dni, nombre, conexion);
                            break;
                        case 3: 
                            System.out.println("Indique un dni para borrar");
                            dni = scanner.next();
                            cliente.borrarUsuario(dni, conexion);
                            break;
                        case 4:
                            System.out.println("Indique un dni para modificar");
                            dni = scanner.next();
                            System.out.println("Indique un nuevo nombre");
                            nombre = scanner.next();
                            cliente.modificarUsuario(dni, nombre, conexion);
                            break;

                        default:
                            System.out.println("Opcion no valida");
                            break;
                    }

                    break;
                case 3:
                    // Gestion producto
                    System.out.println("Submenu");
                    System.out.println("1- Ver todos");
                    System.out.println("2- Añadir");
                    System.out.println("3- Eliminar");
                    System.out.println("4- Modificar");
                    int opcionProducto = scanner.nextInt();

                    Producto producto = new Producto();
                    String nombreProducto;
                    double precioProducto;
                    String descripcionProducto;
                    switch (opcionProducto) {

                        case 1:
                            producto.verTodo(conexion);
                            break;
                        case 2:
                            System.out.println("Indique un nombre");
                            nombreProducto = scanner.next();
                            System.out.println("Indique un precio");
                            precioProducto = scanner.nextDouble();
                            System.out.println("Indique una descripcion");
                            descripcionProducto = scanner.next();
                            producto.anadirProducto(nombreProducto, precioProducto, descripcionProducto, conexion);
                            break;
                        case 3:
                            System.out.println("Indique un id para borrar");
                            int idProducto = scanner.nextInt();
                            producto.borrarProducto(idProducto, conexion);
                            break;
                        case 4:
                            // Se modifican todos lo campos debes de conocer de antemano el id
                            System.out.println("Indique un id para modificar");
                            idProducto = scanner.nextInt();
                            System.out.println("Indique un nuevo nombre");
                            nombreProducto = scanner.next();
                            System.out.println("Indique un nuevo precio");
                            precioProducto = scanner.nextDouble();
                            System.out.println("Indique una nueva descripcion");
                            descripcionProducto = scanner.next();
                            producto.modificarProducto(idProducto, nombreProducto, precioProducto, descripcionProducto, conexion);
                            break;

                        default:
                            System.out.println("Opcion no valida");
                            break;
                    }

                    break;
                case 4:
                    System.out.println("Indique un dni");
                    dni = scanner.next();
                    cliente = new Cliente();
                    cliente.verUsuarioEspecifico(dni, conexion);
                    

                    break;
                case 5:
                    System.out.println("Indique un dni");
                    dni = scanner.next();
                    Pedido pedido = new Pedido();
                    pedido.verPedidoCliente(dni, conexion);

                    break;
                case 6:
                    // Realizar un pedido
                    System.out.println("Indique un dni");
                    dni = scanner.next();
                    String continuar = "s";
                    pedido = new Pedido();
                    PedidoProducto pedidoProducto = new PedidoProducto();
                    // obtener la fecha/hora actual del sistema y pasarla al pedido
                    String fechaPedido = java.time.LocalDateTime.now().toString();
                    pedido.AñadirPedido(fechaPedido, dni, conexion);
                    while (continuar.equals("s")) {
                        System.out.println("Indique un id_producto");
                        int id_producto = scanner.nextInt();
                        System.out.println("Indique una cantidad");
                        int cantidad = scanner.nextInt();
                        
                        // Añadir productos al pedido
                        System.out.println();
                        
                        int id_pedido = pedido.obtenerIdPedidoReciente(conexion);
                        pedidoProducto.añadirProductoAlPedido(id_pedido, id_producto, cantidad, conexion);
                        System.out.println("¿Desea añadir otro producto al pedido? (s/n)");
                        continuar = scanner.next();
                    }

                    break;
                case 7:
                    // Exportar a Json
                    
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.setPrettyPrinting().create();
                    System.out.println("Indica un nombre de archivo para exportar el pedido");
                    String nombreArchivo = scanner.next();
                    FileWriter writer = new FileWriter(nombreArchivo);
                    pedido = new Pedido();
                    System.out.println("Indica el id de un pedido a exportar");
                    int idPedido = scanner.nextInt();
                    // Obtiene todos los atributos distintos de Pedidos, productos y PedidoProductos
                    ArrayList<gsonPedido> datosPedido = pedido.obtenerDatosPedido(idPedido, conexion);
                    // Control de errores al interntar escribir el archivo
                    try {
                        gson.toJson(datosPedido, writer);
                    } catch (JsonIOException e) {
                        System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
                        System.out.println("Abra incidencia con este error " + e.getCause());
                    }
                    writer.close();

                    break;
                case 999:
                    System.out.println("Custom borrar tablas");
                    try (Statement stmt = conexion.createStatement()) {
                        stmt.execute("DROP TABLE pedido_producto");
                        stmt.execute("DROP TABLE pedido");
                        stmt.execute("DROP TABLE producto");
                        stmt.execute("DROP TABLE cliente");
                        System.out.println("Tablas borradas correctamente");
                    }
                    break;
                default:
                    break;
            }
            conexion.close();
        }

        scanner.close();
        
        }
        catch (SQLException e) {
            // Información para abrir la incidencia correspondiente
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e) {
            System.out.println("Que parte de que no esta inicializada la base de datos o hay otro error no ha entendido?");
        }
        catch (IOException e) {
            System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("ERROR inesperado: "+ e.getMessage());
            System.out.println("Abra incidencia con este error " + e.getCause());
        }
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
