package com.example.aplicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.conexiones.PostgresConnection;

public class AlumnosDao {
    String db = "alumnos";
    /**
     * Gestiona el objeto añadir
     * 
     * @param alumno el objeto alumno a añadir
     */
    public void insertarAlumno(Alumnos alumno) {
        try (Connection conexion = new PostgresConnection().getConnection();
                PreparedStatement acciones = conexion.prepareStatement(
                        "insert into " + db + " (nombre, edad, email) values (?,?,?)")) {
            acciones.setString(1, alumno.nombre);
            acciones.setInt(2, alumno.edad);
            acciones.setString(3, alumno.email);

            int cantidad = acciones.executeUpdate();
            System.out.println("Se han añadido esta cantidad de filas: " + cantidad);
        } catch (Exception e) {
            System.out.println("No se ha podido intertar la fila");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    /**
     * Gestiona el borrado
     * 
     * @param id el id del alumno a borrar
     */
    public void borrarAlumno(int id) {
        try (Connection conexion = new PostgresConnection().getConnection();
                PreparedStatement acciones = conexion.prepareStatement(
                        "delete from " + db + " where id = ?")) {
            acciones.setInt(1, id);
            int cantidad = acciones.executeUpdate();
            System.out.println("Se ha borrado esta cantidad de filas: " + cantidad);
        } catch (Exception e) {
            System.out.println("No se ha podido borrar la fila");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    /**
     * Gestiona las modificaciones
     * 
     * @param alumno el objeto alumno a modificar
     */
    public void modificarAlumno(Alumnos alumno) {
        try (Connection conexion = new PostgresConnection().getConnection();
                PreparedStatement acciones = conexion.prepareStatement(
                        "update "+db+" set nombre = ? , edad = ? , email = ? where id = ?")) {
            acciones.setString(1, alumno.nombre);
            acciones.setInt(2, alumno.edad);
            acciones.setString(3, alumno.email);
            acciones.setInt(4, alumno.id);

            int cantidad = acciones.executeUpdate();
            System.out.println("Se ha modificado esta cantidad de filas: " + cantidad);
        } catch (Exception e) {
            System.out.println("No se ha podido modificar la fila");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    /**
     * Devuelve el alumno
     * @param id dato unico del alumno 
     * @return devuelve el alumno
     */
    public Alumnos selectAlumnos(int id) {
        Alumnos devolver = null;
        try (Connection conexion = new PostgresConnection().getConnection();
                PreparedStatement acciones = conexion.prepareStatement(
                        "select * from " + db + " where id = ?")) {
            acciones.setInt(1, id);

            try (ResultSet selecion = acciones.executeQuery();) {
                while (selecion.next()) {
                    devolver = new Alumnos(
                            selecion.getInt("id"),
                            selecion.getString("nombre"),
                            selecion.getInt("edad"),
                            selecion.getString("email"));
                }

            } catch (Exception e) {
                System.out.println("No se ha podido procesar el resultado");
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }

        } catch (Exception e) {
            System.out.println("Ha fallado la consulta del id");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        } finally {

        }
        return devolver;

    }
    /**
     * Devuelve todos los alumnos
     * @return Devuevle un array con todos
     */
    public ArrayList<Alumnos> ListaAlumnos() {
        ArrayList<Alumnos> devolver = null;
        try (Connection conexion = new PostgresConnection().getConnection();
                PreparedStatement acciones = conexion.prepareStatement(
                        "select * from " + db)) {

            try (ResultSet selecion = acciones.executeQuery();) {
                ArrayList<Alumnos> reemplazar = new ArrayList<>();
                while (selecion.next()) {
                    Alumnos anadir = new Alumnos(
                            selecion.getInt("id"),
                            selecion.getString("nombre"),
                            selecion.getInt("edad"),
                            selecion.getString("email"));
                    reemplazar.add(anadir);
                }
                devolver = reemplazar;

            } catch (Exception e) {
                System.out.println("No se ha podido procesar el resultado");
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }

        } catch (Exception e) {
            System.out.println("Ha fallado la consulta del id");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        } finally {

        }
        return devolver;

    }

}
