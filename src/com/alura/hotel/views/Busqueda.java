package com.alura.hotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.controller.HuespedController;
import com.alura.hotel.controller.ReservaController;
import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	ReservaController reservaController;
	HuespedController huespedController;

	private List<Reserva> listaReservas;
	private List<Huesped> listaHuespedes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		reservaController = new ReservaController();
		huespedController = new HuespedController();

		// inicia las listas desde la base de datos
		listaReservas = reservaController.listar();
		listaHuespedes = huespedController.listar();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		resetListaReservas();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		resetListaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String texto = txtBuscar.getText();
				
				
				if (texto != "") {
					// cambia las listas y las tablas dependiendo del campo "Buscas"

					
					if (panel.getSelectedIndex() == 0) {
						buscarReservasLista(texto);
					}
					if (panel.getSelectedIndex() == 1) {
						BuscarHuespedLista(texto);
					}
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JButton btnEditar = new JButton();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		btnEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// si la perstaña seleccionada es "Reservas" (panel-getSelectedIndex() == 0)
				if (panel.getSelectedIndex() == 0) {
					int seleccionado = tbReservas.getSelectedRow();

					var fecha_entrada = formatearFecha(tbReservas.getValueAt(seleccionado, 1));
					var fecha_salida = formatearFecha(tbReservas.getValueAt(seleccionado, 2));

					Integer id = (Integer) tbReservas.getValueAt(seleccionado, 0);

					Reserva reserva = new Reserva(fecha_entrada, fecha_salida);
					reserva.setId(id);

					reservaController.modificar(reserva);

					resetListaReservas();
				}

				// si la perstaña seleccionada es "Huespedes" (panel-getSelectedIndex() == 1)
				if (panel.getSelectedIndex() == 1) {

					int seleccionado = tbHuespedes.getSelectedRow();

					String nombre = (String) tbHuespedes.getValueAt(seleccionado, 1);
					String apellido = (String) tbHuespedes.getValueAt(seleccionado, 2);

					var fecha = formatearFecha(tbHuespedes.getValueAt(seleccionado, 3));

					String nacionalidad = (String) tbHuespedes.getValueAt(seleccionado, 4);
					String telefono = (String) tbHuespedes.getValueAt(seleccionado, 5);
					Integer id = (Integer) tbHuespedes.getValueAt(seleccionado, 0);

					Huesped huesped = new Huesped(nombre, apellido, fecha, nacionalidad, telefono);
					huesped.setId(id);

					huespedController.modificar(huesped);

					resetListaHuespedes();
				}
			}
		});

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JButton btnEliminar = new JButton();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int huespedSeleccionado = tbHuespedes.getSelectedRow();

				if (huespedSeleccionado != -1) {
					int selectedColumn = (int) tbHuespedes.getValueAt(huespedSeleccionado, 0);
					int huespedEliminado = huespedController.eliminar(selectedColumn);
					resetListaHuespedes();

					System.out.println(String.format("Se ha eliminado %d huesped", huespedEliminado));

				}
				int reservaSeleccionada = tbReservas.getSelectedRow();

				if (reservaSeleccionada != -1) {
					int selectedColumn = (int) tbReservas.getValueAt(reservaSeleccionada, 0);
					int reservaEliminada = reservaController.eliminar(selectedColumn);

					resetListaReservas();
					System.out.println(String.format("Se ha eliminado %d reserva ", reservaEliminada));
				}
			}
		});

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	/**
	 * retorna un LocalDate sin importar si el parametro es LocalDate o String
	 * @param fecha
	 * @return
	 */
	private LocalDate formatearFecha(Object fecha) {
		if (fecha.getClass() == LocalDate.class) {
			return (LocalDate) fecha;
		} else {
			String fechaString = (String) fecha;
			return LocalDate.parse(fechaString);
		}
	}

	// resetea las listas y las tablas
	
	private void resetListaHuespedes() {
		modeloHuesped.setRowCount(0);
		listaHuespedes = huespedController.listar();

		for (Huesped huesped : listaHuespedes) {
			Object[] datosReserva = { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getReservaId()

			};
			modeloHuesped.addRow(datosReserva);

		}
	}

	private void resetListaReservas() {
		modelo.setRowCount(0);
		listaReservas = reservaController.listar();

		for (Reserva reserva : listaReservas) {
			Object[] datosReserva = { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
					reserva.getValor(), reserva.getFormaDePago() };
			modelo.addRow(datosReserva);

		}
	}
	
	// resetea las listas y las tablas dependiendo del contenido del campo "Buscar" (pasado como parametro)
	
	private void BuscarHuespedLista(String texto) {
		modeloHuesped.setRowCount(0);
		listaHuespedes = huespedController.buscar(texto);
		
		for (Huesped huesped : listaHuespedes) {
			Object[] datosReserva = { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getReservaId()
					
			};
			modeloHuesped.addRow(datosReserva);
			
		}
	}

	private void buscarReservasLista(String text) {
		modelo.setRowCount(0);
		listaReservas = reservaController.buscar(text);

		for (Reserva reserva : listaReservas) {
			Object[] datosReserva = { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
					reserva.getValor(), reserva.getFormaDePago() };
			modelo.addRow(datosReserva);

		}
	}

}
