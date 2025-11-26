package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import model.AppEstudiante;

public class EstudianteView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEdad;
	private JTextField txtAula;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JTable table;
	private AppEstudiante app;

	public EstudianteView(AppEstudiante app) {
		this.app = app;

		// Establece el titulo y las propiedades de la ventana
		setTitle("Students App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// Añade un segundo titulo a la ventana
		JLabel lblTitle = new JLabel("Student Management System");
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(20, 11, 387, 60);
		contentPane.add(lblTitle);

		// Paneles de la izquierda
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(20, 71, 387, 284);
		contentPane.add(panel);
		panel.setLayout(null);

		// Cada etiqueta con posicion especifica (a mano)
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(31, 46, 36, 24);
		panel.add(lblId);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(14, 81, 60, 24);
		panel.add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(10, 116, 74, 24);
		panel.add(lblApellidos);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdad.setBounds(21, 154, 46, 24);
		panel.add(lblEdad);

		JLabel lblAula = new JLabel("Aula");
		lblAula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAula.setBounds(21, 192, 46, 24);
		panel.add(lblAula);

		// Cada campo a la derecha de su label va a mano
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtID.setBounds(102, 46, 263, 24);
		panel.add(txtID);
		txtID.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombre.setColumns(10);
		txtNombre.setBounds(102, 81, 263, 24);
		panel.add(txtNombre);

		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(102, 120, 263, 24);
		panel.add(txtApellidos);

		txtEdad = new JTextField();
		txtEdad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEdad.setColumns(10);
		txtEdad.setBounds(102, 155, 263, 24);
		panel.add(txtEdad);		
		
		txtAula = new JTextField();
		txtAula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAula.setColumns(10);
		txtAula.setBounds(102, 190, 263, 24);
		panel.add(txtAula);

		// Los botones debajo de los campos el posicionamiento va a mano
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(78, 230, 89, 23);
		btnSave.addActionListener(this);
		panel.add(btnSave);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(177, 230, 89, 23);
		btnUpdate.addActionListener(this);
		panel.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(276, 230, 89, 23);
		btnDelete.addActionListener(this);
		panel.add(btnDelete);

		// Panel derecho con la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(417, 71, 467, 284);
		contentPane.add(scrollPane);

		// Tabla con los estudiantes
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Apellidos", "Edad", "Aula" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Integer.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
	}

	// Control de errores en los formularios
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) {
			String id = txtID.getText();
			String nombre = txtNombre.getText();
			String apellidos = txtApellidos.getText();
			String edad = txtEdad.getText();
			String aula = txtAula.getText();
			if (id.isEmpty()|| nombre.isEmpty() || apellidos.isEmpty() || edad.isEmpty() || aula.isEmpty()) {
				showMessage("POR FAVOR COMPLETA TODOS LOS CAMPOS");
				return;
			}
			app.enrollEstudiante(id, nombre, apellidos, Integer.parseInt(edad), aula);
		} else if (e.getSource() == btnDelete) {
			String id = txtID.getText();
			app.dropEstudiante(id);
		} else if (e.getSource() == btnUpdate) {
			String id = txtID.getText();
			String nombre = txtNombre.getText();
			String apellidos = txtApellidos.getText();
			String edad = txtEdad.getText();
			if (id.isEmpty()|| nombre.isEmpty() || apellidos.isEmpty() || edad.isEmpty()) {
				showMessage("POR FAVOR COMPLETA TODOS LOS CAMPOS");
				return;
			}
			String aula = txtAula.getText();
			app.updateEstudiante(id, nombre, apellidos, Integer.parseInt(edad), aula);
		}
	}

	// Muestra los mensajes de erorres
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	// Limpia los campos del formulario
	public void clear() {
		txtID.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtEdad.setText("");
		txtAula.setText("");
	}

	// Carga los estudiantes en la tabla
	public void load() {
		app.showAllEstudiante();
	}

	// Refresca la tabla de estudiantes
	public void refresh() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		clear();
		load();
	}

	// Añade un estudiante a la tabla
	public void addEstudiante(String id, String nombre, String apellidos, int edad, String aula) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addRow(new Object[] { id, nombre, apellidos, edad, aula });
	}
}
