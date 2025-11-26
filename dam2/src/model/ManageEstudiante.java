package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.MySQLConnection;

public class ManageEstudiante {

	/**
	 * Añade un estudiante a la base de datos
	 * 
	 * @param estudiante el estudiante a añadir
	 * @return true si el estudiante fue añadido correctamente, false en caso
	 *         contrario
	 */
	public boolean addEstudiante(Estudiante estudiante) {
		try (Connection connection = new MySQLConnection().getConnection()) {
			String sql = "INSERT INTO estudiante VALUES (?,?,?,?,?)";
			PreparedStatement sentence = connection.prepareStatement(sql);
			sentence.setString(1, estudiante.getId());
			sentence.setString(2, estudiante.getNombre());
			sentence.setString(3, estudiante.getApellidos());
			sentence.setInt(4, estudiante.getEdad());
			sentence.setString(5, estudiante.getAula());
			int rows = sentence.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			System.out.println("Error al modificar estudiante: ");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}
	}

	/**
	 * Obtiene un estudiante de la base de datos
	 * 
	 * @param id el id del estudiante a obtener
	 * @return el estudiante con el id especificado, o null si no se encuentra
	 */
	public Estudiante getEstudiante(String id) {
		try (Connection connection = new MySQLConnection().getConnection()) {
			String sql = "SELECT * FROM estudiante WHERE id LIKE ?";
			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, id);

			try (ResultSet result = query.executeQuery()) {
				Estudiante estudiante = new Estudiante();
				while (result.next()) {
					estudiante.setId(result.getString("id"));
					estudiante.setNombre(result.getString("nombre"));
					estudiante.setApellidos(result.getString("apellidos"));
					estudiante.setEdad(result.getInt("edad"));
					estudiante.setAula(result.getString("aula"));
				}
				return estudiante;
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar estudiante: ");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return null;
		}
	}

	/**
	 * Elimina un estudiante de la base de datos
	 * 
	 * @param id el id del estudiante a eliminar
	 * @return true si el estudiante fue eliminado correctamente, false en caso
	 *         contrario
	 */
	public boolean deleteEstudiante(String id) {
		try (Connection connection = new MySQLConnection().getConnection()) {
			String sql = "DELETE FROM estudiante WHERE id=?";
			PreparedStatement query = connection.prepareStatement(sql);
			query.setString(1, id);
			int deletedRow = query.executeUpdate();
			return deletedRow == 1;
		} catch (SQLException e) {
			System.out.println("Error al modificar estudiante: ");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}
	}

	/**
	 * Modifica un estudiante de la base de datos
	 * 
	 * @param estudiante el estudiante con los datos actualizados
	 * @return true si el estudiante fue modificado correctamente, false en caso
	 *         contrario
	 */
	public boolean modifyEstudiante(Estudiante estudiante) {
		try (Connection connection = new MySQLConnection().getConnection()) {
			String sql = "UPDATE estudiante SET nombre=?, apellidos=?, edad=?, aula=?" + " WHERE id=?";
			PreparedStatement sentence = connection.prepareStatement(sql);
			sentence.setString(1, estudiante.getNombre());
			sentence.setString(2, estudiante.getApellidos());
			sentence.setInt(3, estudiante.getEdad());
			sentence.setString(4, estudiante.getAula());
			sentence.setString(5, estudiante.getId());
			int rowsUpdated = sentence.executeUpdate();
			return rowsUpdated == 1;
		} catch (SQLException e) {
			System.out.println("Error al modificar estudiante: ");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}
	}

	/**
	 * Obtiene la lista de todos los estudiantes en la base de datos
	 * 
	 * @return una lista de estudiantes
	 */
	public ArrayList<Estudiante> getEstudianteList() {
		try (Connection connection = new MySQLConnection().getConnection()) {
			String sql = "SELECT * FROM estudiante";
			Statement query = connection.createStatement();

			try (ResultSet result = query.executeQuery(sql)) {
				ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
				while (result.next()) {
					Estudiante estudiante = new Estudiante();
					estudiante.setId(result.getString("id"));
					estudiante.setNombre(result.getString("nombre"));
					estudiante.setApellidos(result.getString("apellidos"));
					estudiante.setEdad(result.getInt("edad"));
					estudiante.setAula(result.getString("aula"));
					estudiantes.add(estudiante);
				}
				return estudiantes;
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar estudiante: ");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return null;
		}
	}
}
