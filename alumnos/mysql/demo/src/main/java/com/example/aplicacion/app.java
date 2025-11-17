package com.example.aplicacion;

import java.sql.Connection;
import java.util.ArrayList;

import com.example.conexiones.PostgresConnection;

public class app {
    public static void main(String[] args) {
        testConnection(new PostgresConnection().getConnectionServer());

        GestorDam2 gestorAlumnos = new GestorDam2();
        gestorAlumnos.crearBD();
        System.out.println("Ahora la base de datos");
        Alumnos alumnos = new Alumnos();
        alumnos.CrearTabla();

        // Añadir fila
        AlumnosDao alumnosDao = new AlumnosDao();
        Alumnos anadiralumno = new Alumnos("juan", 30, "fdafsa");
        alumnosDao.insertarAlumno(anadiralumno);

        // Obtener fila
        int id = 1;
        Alumnos verAlumno = alumnosDao.selectAlumnos(id);
        System.out.println(
                "Alumno con ID " + id + ": " + verAlumno.nombre + ", " + verAlumno.edad + ", " + verAlumno.email);

        // modficar fila
        verAlumno.nombre = "Pedro";
        verAlumno.edad = 25;
        verAlumno.email = "pedro@example.com";
        alumnosDao.modificarAlumno(verAlumno);

        // Añadir fila
        alumnosDao.insertarAlumno(anadiralumno);

        // Listar todos
        ArrayList<Alumnos> listaAlumnos = alumnosDao.ListaAlumnos();
        System.out.println("Lista de alumnos:");
        for (Alumnos a : listaAlumnos) {
            System.out.println(a.id + ": " + a.nombre + ", " + a.edad + ", " + a.email);
        }

        // Borrar alumno
        alumnosDao.borrarAlumno(id);
    }

    public static void testConnection(Connection conn) {
        if (conn != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }

}
