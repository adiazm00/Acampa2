package presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import dominio.GestorActividades;
import dominio.Usuario;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class MostrarHistorial extends JPanel {
	private JTable table;
	private JPanel panelHistorial;
	private JPanel panelComentariosPuntuacion;
	private JTextField textFieldID;
	private JTable table_1;
	private JTextField textField_1;
	private JLabel lblActividad;
	private JScrollPane scrollPane;
	private JLabel lblComentario;
	private JLabel lblPunt;
	private JComboBox comboBoxPuntuacion;
	private JButton btnPuntuarComentar;
	private JComboBox comboBoxCliente;
	
	private Object[][] actividadesAnteriores;
	private Object[][] comentariosActividad;
	private JSONObject jactividadesAnteriores;
	private JSONObject JSONActividades;
	private JSONObject JSONClientes;
	private JLabel lblNewDescripcion;
	private JTextField textFieldDescripcion;
	private JLabel lblNewComentario;
	private JLabel lblPuntuacion;
	private JButton btnAcceso;
	private JButton btnHistorial;
	
	private String id;
	private String actividad;
	private String horario;
	private String cliente;
	private String comentario;
	private String punt;


	/**
	 * Create the panel.
	 */
	public MostrarHistorial(Usuario u) {
		JSONActividades=GestorActividades.leerActividades();
		JSONClientes = GestorActividades.leerClientes();
		setLayout(new CardLayout(0, 0));
		
		panelHistorial = new JPanel();
		add(panelHistorial, "name_8276568222700");
		panelHistorial.setLayout(null);
		
		JScrollPane scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(44, 50, 539, 169);
		panelHistorial.add(scrollPaneTabla);
		
		JSONActividades = GestorActividades.leerActividades();
		try {
			jactividadesAnteriores = GestorActividades.comprobarActividadesAnteriorFecha(u.getUltConexion(), JSONActividades);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actividadesAnteriores = GestorActividades.devolverActividadesAnteriores(jactividadesAnteriores);
		table = new JTable();
		id="ID";
		actividad=Messages.getString("Menu.btnActividades.text");
		horario=Messages.getString("Mostrar.Horario.text");
		table.setModel(new DefaultTableModel(
				actividadesAnteriores,
				new String[] {
					id, actividad, "Monitor", horario
				}
			));
		scrollPaneTabla.setViewportView(table);
		
		lblPuntuacion = new JLabel(Messages.getString("MostrarHistorial.lblPuntuacion.text")); //$NON-NLS-1$
		lblPuntuacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuntuacion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPuntuacion.setBounds(10, 229, 418, 30);
		panelHistorial.add(lblPuntuacion);
		
		btnAcceso = new JButton(Messages.getString("MostrarHistorial.btnAcceso.text")); //$NON-NLS-1$
		btnAcceso.addActionListener(new BtnAccesoActionListener());
		btnAcceso.setBounds(438, 229, 97, 30);
		panelHistorial.add(btnAcceso);
		
		panelComentariosPuntuacion = new JPanel();
		add(panelComentariosPuntuacion, "name_8282579805300");
		panelComentariosPuntuacion.setLayout(null);
		
		lblActividad = new JLabel(Messages.getString("MostrarHistorial.lblActividad.text")); //$NON-NLS-1$
		lblActividad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActividad.setBounds(83, 43, 110, 33);
		panelComentariosPuntuacion.add(lblActividad);
		
		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setBounds(197, 43, 46, 33);
		panelComentariosPuntuacion.add(textFieldID);
		textFieldID.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 84, 460, 116);
		panelComentariosPuntuacion.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setBounds(0, 0, 1, 1);
		cliente=Messages.getString("Mostrar.cliente.text");
		comentario=Messages.getString("Mostrar.comentario.text");
		punt=Messages.getString("Mostrar.punt.text");
		table_1.setModel(new DefaultTableModel(
				actividadesAnteriores,
				new String[] {
					cliente,comentario,punt
				}
			));
		scrollPane.setViewportView(table_1);
		
		lblComentario = new JLabel(Messages.getString("MostrarHistorial.lblComentario.text")); //$NON-NLS-1$
		lblComentario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComentario.setBounds(83, 207, 90, 33);
		panelComentariosPuntuacion.add(lblComentario);
		
		textField_1 = new JTextField();
		textField_1.setBounds(178, 247, 191, 27);
		panelComentariosPuntuacion.add(textField_1);
		textField_1.setColumns(10);
		
		lblPunt = new JLabel(Messages.getString("MostrarHistorial.lblPunt.text")); //$NON-NLS-1$
		lblPunt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPunt.setBounds(364, 207, 105, 33);
		panelComentariosPuntuacion.add(lblPunt);
		
		comboBoxPuntuacion = new JComboBox();
		comboBoxPuntuacion.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBoxPuntuacion.setBounds(479, 207, 46, 33);
		panelComentariosPuntuacion.add(comboBoxPuntuacion);
		
		btnPuntuarComentar = new JButton(Messages.getString("MostrarHistorial.btnPuntuarComentar.text")); //$NON-NLS-1$
		btnPuntuarComentar.addActionListener(new BtnPuntuarComentarActionListener());
		btnPuntuarComentar.setBounds(318, 284, 195, 33);
		panelComentariosPuntuacion.add(btnPuntuarComentar);
		
		comboBoxCliente = new JComboBox(GestorActividades.devolverNombreClientes(JSONClientes));
		comboBoxCliente.setBounds(178, 207, 176, 33);
		panelComentariosPuntuacion.add(comboBoxCliente);
		
		btnHistorial = new JButton(Messages.getString("MostrarHistorial.btnHistorial.text")); //$NON-NLS-1$
		btnHistorial.addActionListener(new BtnHistorialActionListener());
		btnHistorial.setBounds(143, 284, 165, 33);
		panelComentariosPuntuacion.add(btnHistorial);
		
		lblNewDescripcion = new JLabel(Messages.getString("MostrarHistorial.lblNewDescripcion.text")); //$NON-NLS-1$
		lblNewDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewDescripcion.setBounds(249, 43, 105, 33);
		panelComentariosPuntuacion.add(lblNewDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setEditable(false);
		textFieldDescripcion.setBounds(358, 43, 165, 33);
		panelComentariosPuntuacion.add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		lblNewComentario = new JLabel(Messages.getString("MostrarHistorial.lblNewComentario.text")); //$NON-NLS-1$
		lblNewComentario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewComentario.setBounds(73, 240, 100, 33);
		panelComentariosPuntuacion.add(lblNewComentario);

	}

	private class BtnAccesoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() != -1) {
				if (table.getSelectedRowCount() == 1) {
					comentariosActividad = GestorActividades.devolverComentariosActividad(JSONActividades, (String) table.getValueAt(table.getSelectedRow(), 0),JSONClientes);

					textFieldID.setText((String) table.getValueAt(table.getSelectedRow(), 0));
					textFieldDescripcion.setText((String) table.getValueAt(table.getSelectedRow(), 1));
					
					cliente=Messages.getString("Mostrar.cliente.text");
					comentario=Messages.getString("Mostrar.comentario.text");
					punt=Messages.getString("Mostrar.punt.text");
					table_1.setModel(new DefaultTableModel(
							comentariosActividad,
							new String[] {
								cliente,comentario,punt
							}
						));
					panelHistorial.setVisible(false);
					panelComentariosPuntuacion.setVisible(true);
						
				} else
					JOptionPane.showMessageDialog(null, "Debe seleccionar solo una fila.");
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una fila primero.");
			}

		}
	}
	private class BtnHistorialActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panelComentariosPuntuacion.setVisible(false);
			panelHistorial.setVisible(true);
		}
	}
	private class BtnPuntuarComentarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean encontrado;
			if(comboBoxPuntuacion.getSelectedItem() !=null   ) {
				if(comboBoxCliente.getSelectedItem() !=null) {
					if(textField_1.getText().length()!=0) {
								try {
									encontrado = GestorActividades.addComentario(JSONActividades,JSONClientes,(String) table.getValueAt(table.getSelectedRow(), 0),comboBoxCliente.getSelectedItem().toString(),Integer.parseInt(comboBoxPuntuacion.getSelectedItem().toString()),textField_1.getText(),comentariosActividad.length);
									if(!encontrado) {
										JSONActividades=GestorActividades.leerActividades();
										comentariosActividad = GestorActividades.devolverComentariosActividad(JSONActividades, (String) table.getValueAt(table.getSelectedRow(), 0),JSONClientes);
										JOptionPane.showMessageDialog(null, "El comentario se ha realizado.");
										table_1.setModel(new DefaultTableModel(comentariosActividad,new String[] {"Cliente","Comentario","Puntuacion"}));
									}else {
										JOptionPane.showMessageDialog(null, "Ese cliente ya ha realizado un comentario.");
									}
								} catch (NumberFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

					}else {
						JOptionPane.showMessageDialog(null, "Debe poner un comentario.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe elegir un cliente.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Debe establecer una puntuacion.");
			}
		}
	}
	
	public void toNormal() {
		panelHistorial.setBackground(null);
		panelComentariosPuntuacion.setBackground(null);
		lblPuntuacion.setForeground(null);
		lblActividad.setForeground(null);
		lblComentario.setForeground(null);
		lblPunt.setForeground(null);
		lblNewDescripcion.setForeground(null);
		lblNewComentario.setForeground(null);
	}
	
	public void toDark() {
		panelHistorial.setBackground(Color.darkGray);
		panelComentariosPuntuacion.setBackground(Color.darkGray);
		lblPuntuacion.setForeground(Color.white);
		lblActividad.setForeground(Color.white);
		lblComentario.setForeground(Color.white);
		lblPunt.setForeground(Color.white);
		lblNewDescripcion.setForeground(Color.white);
		lblNewComentario.setForeground(Color.white);
	}
	
	public void toES() {
		lblPuntuacion.setText(Messages.getString("MostrarHistorial.lblPuntuacion.text"));
		lblActividad.setText(Messages.getString("MostrarHistorial.lblActividad.text"));
		lblComentario.setText(Messages.getString("MostrarHistorial.lblComentario.text"));
		lblPunt.setText(Messages.getString("MostrarHistorial.lblPunt.text"));
		lblNewDescripcion.setText(Messages.getString("MostrarHistorial.lblNewDescripcion.text"));
		lblNewComentario.setText(Messages.getString("MostrarHistorial.lblNewComentario.text"));
		btnAcceso.setText(Messages.getString("MostrarHistorial.btnAcceso.text"));
		btnHistorial.setText(Messages.getString("MostrarHistorial.btnHistorial.text"));
		btnPuntuarComentar.setText(Messages.getString("MostrarHistorial.btnPuntuarComentar.text"));
		actividad=Messages.getString("Menu.btnActividades.text");
		horario=Messages.getString("Mostrar.Horario.text");
		table.setModel(new DefaultTableModel(actividadesAnteriores,new String[] {id, actividad, "Monitor", horario}));
		cliente=Messages.getString("Mostrar.cliente.text");
		comentario=Messages.getString("Mostrar.comentario.text");
		punt=Messages.getString("Mostrar.punt.text");
		table_1.setModel(new DefaultTableModel(actividadesAnteriores,new String[] {cliente,comentario,punt}));
	}
	
	public void toEN() {
		lblPuntuacion.setText(Messages.getString("MostrarHistorial.lblPuntuacion.text"));
		lblActividad.setText(Messages.getString("MostrarHistorial.lblActividad.text"));
		lblComentario.setText(Messages.getString("MostrarHistorial.lblComentario.text"));
		lblPunt.setText(Messages.getString("MostrarHistorial.lblPunt.text"));
		lblNewDescripcion.setText(Messages.getString("MostrarHistorial.lblNewDescripcion.text"));
		lblNewComentario.setText(Messages.getString("MostrarHistorial.lblNewComentario.text"));
		btnAcceso.setText(Messages.getString("MostrarHistorial.btnAcceso.text"));
		btnHistorial.setText(Messages.getString("MostrarHistorial.btnHistorial.text"));
		btnPuntuarComentar.setText(Messages.getString("MostrarHistorial.btnPuntuarComentar.text"));
		actividad=Messages.getString("Menu.btnActividades.text");
		horario=Messages.getString("Mostrar.Horario.text");
		table.setModel(new DefaultTableModel(actividadesAnteriores,new String[] {id, actividad, "Monitor", horario}));
		cliente=Messages.getString("Mostrar.cliente.text");
		comentario=Messages.getString("Mostrar.comentario.text");
		punt=Messages.getString("Mostrar.punt.text");
		table_1.setModel(new DefaultTableModel(actividadesAnteriores,new String[] {cliente,comentario,punt}));
	}
	
	public void toSmall() {
		lblPuntuacion.setFont(new Font(lblPuntuacion.getFont().getFontName(), lblPuntuacion.getFont().getStyle(), 10));
		lblActividad.setFont(new Font(lblActividad.getFont().getFontName(), lblActividad.getFont().getStyle(), 10));
		lblComentario.setFont(new Font(lblComentario.getFont().getFontName(), lblComentario.getFont().getStyle(), 10));
		lblPunt.setFont(new Font(lblPunt.getFont().getFontName(), lblPunt.getFont().getStyle(), 10));
		lblNewDescripcion.setFont(new Font(lblNewDescripcion.getFont().getFontName(), lblNewDescripcion.getFont().getStyle(), 10));
		lblNewComentario.setFont(new Font(lblNewComentario.getFont().getFontName(), lblNewComentario.getFont().getStyle(), 10));
		btnAcceso.setFont(new Font(btnAcceso.getFont().getFontName(), btnAcceso.getFont().getStyle(), 10));
		btnHistorial.setFont(new Font(btnHistorial.getFont().getFontName(), btnHistorial.getFont().getStyle(), 10));
		btnPuntuarComentar.setFont(new Font(btnPuntuarComentar.getFont().getFontName(), btnPuntuarComentar.getFont().getStyle(), 10));
	}
	public void toMedium() {
		lblPuntuacion.setFont(new Font(lblPuntuacion.getFont().getFontName(), lblPuntuacion.getFont().getStyle(), 13));
		lblActividad.setFont(new Font(lblActividad.getFont().getFontName(), lblActividad.getFont().getStyle(), 13));
		lblComentario.setFont(new Font(lblComentario.getFont().getFontName(), lblComentario.getFont().getStyle(), 13));
		lblPunt.setFont(new Font(lblPunt.getFont().getFontName(), lblPunt.getFont().getStyle(), 13));
		lblNewDescripcion.setFont(new Font(lblNewDescripcion.getFont().getFontName(), lblNewDescripcion.getFont().getStyle(), 13));
		lblNewComentario.setFont(new Font(lblNewComentario.getFont().getFontName(), lblNewComentario.getFont().getStyle(), 13));
		btnAcceso.setFont(new Font(btnAcceso.getFont().getFontName(), btnAcceso.getFont().getStyle(), 13));
		btnHistorial.setFont(new Font(btnHistorial.getFont().getFontName(), btnHistorial.getFont().getStyle(), 13));
		btnPuntuarComentar.setFont(new Font(btnPuntuarComentar.getFont().getFontName(), btnPuntuarComentar.getFont().getStyle(), 13));
	}
	public void toBig() {
		lblPuntuacion.setFont(new Font(lblPuntuacion.getFont().getFontName(), lblPuntuacion.getFont().getStyle(), 16));
		lblActividad.setFont(new Font(lblActividad.getFont().getFontName(), lblActividad.getFont().getStyle(), 16));
		lblComentario.setFont(new Font(lblComentario.getFont().getFontName(), lblComentario.getFont().getStyle(), 16));
		lblPunt.setFont(new Font(lblPunt.getFont().getFontName(), lblPunt.getFont().getStyle(), 16));
		lblNewDescripcion.setFont(new Font(lblNewDescripcion.getFont().getFontName(), lblNewDescripcion.getFont().getStyle(), 16));
		lblNewComentario.setFont(new Font(lblNewComentario.getFont().getFontName(), lblNewComentario.getFont().getStyle(), 16));
		btnAcceso.setFont(new Font(btnAcceso.getFont().getFontName(), btnAcceso.getFont().getStyle(), 16));
		btnHistorial.setFont(new Font(btnHistorial.getFont().getFontName(), btnHistorial.getFont().getStyle(), 16));
		btnPuntuarComentar.setFont(new Font(btnPuntuarComentar.getFont().getFontName(), btnPuntuarComentar.getFont().getStyle(), 16));
	}
}