package model;

import java.util.ArrayList;

import gui.EstudianteView;

public class AppEstudiante {
	private ManageEstudiante manager;
	private EstudianteView view;

	/**
	 * Constructor de la clase
	 */
	public AppEstudiante() {
		this.manager = new ManageEstudiante();
		this.view = new EstudianteView(this);
		this.view.setVisible(true);
	}

	/**
	 * Matricula a un estudiante en el sistema
	 * @param id el atributo unico del estudiante
	 * @param nombre del estudiante
	 * @param apellidos del estudiante
	 * @param edad del estudiante
	 * @param aula del estudiante
	 */
	public void enrollEstudiante(String id, String nombre, String apellidos, int edad, String aula) {
		Estudiante estudiante = new Estudiante(id, nombre, apellidos, edad, aula);
		boolean inserted = manager.addEstudiante(estudiante);
		if (inserted) {
			view.showMessage("ESTUDIANTE MATRICULADO CORRECTAMENTE.");
			view.clear();
			view.addEstudiante(id, nombre, apellidos, edad, aula);

		} else {
			view.showMessage("NO SE HA PODIDO MATRICULAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Desmatricula/Elimina a un estudiante del sistema
	 * @param id el atributo unico del estudiante
	 */
	public void dropEstudiante(String id) {
		boolean deleted = manager.deleteEstudiante(id);
		if (deleted) {
			view.showMessage("SE HA BORRADO CON ÉXITO AL ESTUDIANTE.");
			view.refresh();
		} else {
			view.showMessage("NO SE HA PODIDO DESMATRICULAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Actualiza los datos de un estudiante en el sistema
	 * @param id el atributo unico del estudiante
	 * @param nombre del estudiante
	 * @param apellidos del estudiante
	 * @param edad del estudiante
	 * @param aula del estudiante
	 */
	public void updateEstudiante(String id, String nombre, String apellidos, int edad, String aula) {
		Estudiante estudiante = new Estudiante(id, nombre, apellidos, edad, aula);
		boolean modified = manager.modifyEstudiante(estudiante);
		if (modified) {
			view.showMessage("SE HA ACTUALIZADO CON ÉXITO AL ESTUDIANTE.");
			view.refresh();
		} else {
			view.showMessage("NO SE HA PODIDO ACTUALIZAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Muestra todos los estudiantes en la tabla
	 */
	public void showAllEstudiante() {
		ArrayList<Estudiante> estudiantes = manager.getEstudianteList();
		for (Estudiante estudiante : estudiantes) {
			view.addEstudiante(estudiante.getId(), estudiante.getNombre(), estudiante.getApellidos(), estudiante.getEdad(), estudiante.getAula());
		}
	}
}
