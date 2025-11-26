package model;

public class Estudiante {
	private String id;
	private String nombre;
	private String apellidos;
	private int edad;
	private String aula;



	/**
	 * Constructor con parámetros
	 * @param id el atributo unico del estudiante
	 * @param nombre del estudiante
	 * @param apellidos del estudiante
	 * @param edad del estudiante
	 * @param aula del estudiante
	 */
	public Estudiante(String id, String nombre, String apellidos, int edad, String aula) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.aula = aula;
	}

	/**
	 * Constructor por defecto
	 */
	public Estudiante() {

	}

	/**
	 * Obtiene el id del estudiante
	 * @return el id del estudiante
	 */
	public String getId() {
		return id;
	}

	/**
	 * Establece el id del estudiante
	 * @param id el id del estudiante
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del estudiante
	 * @return el nombre del estudiante
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del estudiante
	 * @param nombre el nombre del estudiante
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el apellido del estudiante
	 * @return el apellido del estudiante
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece el apellido del estudiante
	 * @param apellidos el apellido del estudiante
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Obtiene la edad del estudiante
	 * @return la edad del estudiante
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Establece la edad del estudiante
	 * @param edad la edad del estudiante
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Obtiene el aula del estudiante
	 * @return el aula del estudiante
	 */
	public String getAula() {
		return aula;
	}

	/**
	 * Establece el aula del estudiante
	 * @param aula el aula del estudiante
	 */
	public void setAula(String aula) {
		this.aula = aula;
	}

	/**
	 * Representa la información del estudiante como una cadena de texto
	 */
	@Override
	public String toString() {
		return "Estudiante id: " + id + "\n" + "Estudiante nombre: " + nombre + "\n" + "Estudiante apellidos: " + apellidos + "\n"
				+ "Estudiante edad: " + edad + "\n" + "Estudiante aula: "+ aula +"\n";
	}
}
