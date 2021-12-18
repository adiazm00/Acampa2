package presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import dominio.GestorActividades;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class MostrarPersonasInscritas extends JPanel {
	private JTextField textActividad;
	private JTable table;

	private JSONObject JSONClientes;
	private JSONObject JSONActividades;
	private Object[][] inscritos;
	private JComboBox comboBoxID;
	private JComboBox comboBoxNombres;
	private JComboBox comboBoxTipoPago;
	private JLabel lblDescripcion;
	private JCheckBox chckbxPago;
	private JLabel lblIdActividad;
	private JLabel lblNombre;
	private JLabel lblTipoPago;
	private JButton btnCancelarInscripcion;
	private JButton btnIncribir;
	
	private String nc;
	private String tlf;
	private String tp;
	private String pagado;
	/**
	 * Create the panel.
	 */
	public MostrarPersonasInscritas() {
		JSONActividades=GestorActividades.leerActividades();
		JSONClientes = GestorActividades.leerClientes();
		setLayout(null);
		
		lblIdActividad = new JLabel(Messages.getString("MostrarPersonasInscritas.lblIdActividad.text")); //$NON-NLS-1$
		lblIdActividad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdActividad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIdActividad.setBounds(0, 35, 122, 33);
		add(lblIdActividad);
		
		comboBoxID = new JComboBox(GestorActividades.devolverIDActividades());
		comboBoxID.addActionListener(new ComboBoxIDActionListener());
		comboBoxID.setBounds(132, 37, 68, 30);
		add(comboBoxID);
		
		textActividad = new JTextField();
		textActividad.setEditable(false);
		textActividad.setText(Messages.getString("MostrarPersonasInscritas.textActividad.text")); //$NON-NLS-1$
		textActividad.setColumns(10);
		textActividad.setBounds(374, 39, 258, 28);
		add(textActividad);
		
		JScrollPane scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(20, 78, 612, 156);
		add(scrollPaneTabla);
		
		textActividad.setText(
				GestorActividades.devolverActividad(JSONActividades, String.valueOf(1)));
		inscritos = GestorActividades.devolverInscritos(GestorActividades
				.devolverInscritosActividad(JSONActividades, String.valueOf(1)));
		table = new JTable();
		nc=Messages.getString("Mostrar.nc.text");
		tlf=Messages.getString("Mostrar.tlf.text");
		tp=Messages.getString("Mostrar.tp.text");
		pagado=Messages.getString("Mostrar.pagado.text");
		table.setModel(new DefaultTableModel(inscritos,
				new String[] { nc, tlf, tp, pagado}));
		scrollPaneTabla.setViewportView(table);
		
		btnCancelarInscripcion = new JButton(Messages.getString("MostrarPersonasInscritas.btnCancelarInscripcion.text")); //$NON-NLS-1$
		btnCancelarInscripcion.addActionListener(new BtnCancelarInscripcionActionListener());
		btnCancelarInscripcion.setBounds(20, 257, 199, 45);
		add(btnCancelarInscripcion);
		
		comboBoxNombres = new JComboBox(GestorActividades.devolverNombreClientes(JSONClientes));
		comboBoxNombres.setBounds(387, 263, 245, 33);
		add(comboBoxNombres);
		
		String[] tipos = {"Mensual","Por horas"};
		comboBoxTipoPago = new JComboBox(tipos);
		comboBoxTipoPago.setBounds(387, 318, 136, 33);
		add(comboBoxTipoPago);
		
		btnIncribir = new JButton(Messages.getString("MostrarPersonasInscritas.btnIncribir.text")); //$NON-NLS-1$
		btnIncribir.addActionListener(new BtnIncribirActionListener());
		btnIncribir.setBounds(20, 312, 199, 45);
		add(btnIncribir);
		
		lblDescripcion = new JLabel(Messages.getString("MostrarPersonasInscritas.lblDescripcion.text")); //$NON-NLS-1$
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(220, 38, 149, 27);
		add(lblDescripcion);
		
		chckbxPago = new JCheckBox(Messages.getString("MostrarPersonasInscritas.chckbxPago.text")); //$NON-NLS-1$
		chckbxPago.setBounds(540, 315, 136, 38);
		add(chckbxPago);
		
		lblNombre = new JLabel(Messages.getString("MostrarPersonasInscritas.lblNombre.text")); //$NON-NLS-1$
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(210, 257, 174, 45);
		add(lblNombre);
		
		lblTipoPago = new JLabel(Messages.getString("MostrarPersonasInscritas.lblTipoPago.text")); //$NON-NLS-1$
		lblTipoPago.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoPago.setBounds(210, 312, 167, 45);
		add(lblTipoPago);

	}
	private class ComboBoxIDActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			textActividad.setText(
					GestorActividades.devolverActividad(JSONActividades, comboBoxID.getSelectedItem().toString()));
			inscritos = GestorActividades.devolverInscritos(GestorActividades
					.devolverInscritosActividad(JSONActividades, comboBoxID.getSelectedItem().toString()));
			table.setModel(new DefaultTableModel(inscritos,new String[] { nc, tlf, tp, pagado}));
		}
	}
	private class BtnCancelarInscripcionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (table.getSelectedRow() != -1) {
				if (table.getSelectedRowCount() == 1) {
						try {
							GestorActividades.cancelarInscripcion(JSONActividades,JSONClientes, comboBoxID.getSelectedItem().toString(),inscritos[table.getSelectedRow()][0].toString());
							JSONActividades=GestorActividades.leerActividades();
							JOptionPane.showMessageDialog(null, "La inscripcion se ha cancelado.");
							inscritos= GestorActividades.devolverInscritos(GestorActividades.devolverInscritosActividad(JSONActividades, comboBoxID.getSelectedItem().toString()));
							table.setModel(new DefaultTableModel(inscritos,new String[] { nc, tlf, tp, pagado}));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				} else
					JOptionPane.showMessageDialog(null, "Debe seleccionar solo una fila.");
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una inscripcion en la tabla.");
			}
		}
	}
	private class BtnIncribirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			boolean encontrado;
			if(comboBoxID.getSelectedItem() !=null   ) {
				if(comboBoxNombres.getSelectedItem() !=null) {
					if(comboBoxTipoPago.getSelectedItem() !=null) {
						if(chckbxPago.isSelected()) {
							try {
								encontrado=GestorActividades.inscribirCliente(JSONActividades, JSONClientes,comboBoxID.getSelectedItem().toString(), comboBoxNombres.getSelectedItem().toString(), comboBoxTipoPago.getSelectedItem().toString(), true);
								if(!encontrado) {
									JSONActividades=GestorActividades.leerActividades();
									JOptionPane.showMessageDialog(null, "La inscripcion se ha realizado.");
									inscritos= GestorActividades.devolverInscritos(GestorActividades.devolverInscritosActividad(JSONActividades, comboBoxID.getSelectedItem().toString()));
									table.setModel(new DefaultTableModel(inscritos,new String[] { nc, tlf, tp, pagado}));
								}else {
									JOptionPane.showMessageDialog(null, "Ese cliente ya esta inscrito.");

								}

							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}else {
							try {
								encontrado=GestorActividades.inscribirCliente(JSONActividades, JSONClientes,comboBoxID.getSelectedItem().toString(), comboBoxNombres.getSelectedItem().toString(), comboBoxTipoPago.getSelectedItem().toString(), false);
								if(!encontrado) {
									JSONActividades=GestorActividades.leerActividades();
									JOptionPane.showMessageDialog(null, "La inscripcion se ha realizado.");
									inscritos= GestorActividades.devolverInscritos(GestorActividades.devolverInscritosActividad(JSONActividades, comboBoxID.getSelectedItem().toString()));
									table.setModel(new DefaultTableModel(inscritos,new String[] { nc, tlf, tp, pagado}));
								}else {
								JOptionPane.showMessageDialog(null, "Ese cliente ya esta inscrito.");

								}

							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe elegir un tipo de pago.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe elegir un cliente.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una Actividad.");
			}
		}
	}
	
	public void toNormal() {
		lblIdActividad.setForeground(null);
		lblDescripcion.setForeground(null);
		lblNombre.setForeground(null);
		lblTipoPago.setForeground(null);
	}
	
	public void toDark() {
		lblIdActividad.setForeground(Color.white);
		lblDescripcion.setForeground(Color.white);
		lblNombre.setForeground(Color.white);
		lblTipoPago.setForeground(Color.white);
	}
	
	public void toES() {
		lblIdActividad.setText(Messages.getString("MostrarPersonasInscritas.lblIdActividad.text"));
		lblDescripcion.setText(Messages.getString("MostrarPersonasInscritas.lblDescripcion.text"));
		lblNombre.setText(Messages.getString("MostrarPersonasInscritas.lblNombre.text"));
		lblTipoPago.setText(Messages.getString("MostrarPersonasInscritas.lblTipoPago.text"));
		btnCancelarInscripcion.setText(Messages.getString("MostrarPersonasInscritas.btnCancelarInscripcion.text"));
		btnIncribir.setText(Messages.getString("MostrarPersonasInscritas.btnIncribir.text"));
		chckbxPago.setText(Messages.getString("MostrarPersonasInscritas.chckbxPago.text"));
		nc=Messages.getString("Mostrar.nc.text");
		tlf=Messages.getString("Mostrar.tlf.text");
		tp=Messages.getString("Mostrar.tp.text");
		pagado=Messages.getString("Mostrar.pagado.text");
		table.setModel(new DefaultTableModel(inscritos,new String[] { nc, tlf, tp, pagado}));
	}
	
	public void toEN() {
		lblIdActividad.setText(Messages.getString("MostrarPersonasInscritas.lblIdActividad.text"));
		lblDescripcion.setText(Messages.getString("MostrarPersonasInscritas.lblDescripcion.text"));
		lblNombre.setText(Messages.getString("MostrarPersonasInscritas.lblNombre.text"));
		lblTipoPago.setText(Messages.getString("MostrarPersonasInscritas.lblTipoPago.text"));
		btnCancelarInscripcion.setText(Messages.getString("MostrarPersonasInscritas.btnCancelarInscripcion.text"));
		btnIncribir.setText(Messages.getString("MostrarPersonasInscritas.btnIncribir.text"));
		chckbxPago.setText(Messages.getString("MostrarPersonasInscritas.chckbxPago.text"));
		nc=Messages.getString("Mostrar.nc.text");
		tlf=Messages.getString("Mostrar.tlf.text");
		tp=Messages.getString("Mostrar.tp.text");
		pagado=Messages.getString("Mostrar.pagado.text");
		table.setModel(new DefaultTableModel(inscritos,new String[] { nc, tlf, tp, pagado}));
	}
	
	public void toSmall() {
		lblIdActividad.setFont(new Font(lblIdActividad.getFont().getFontName(), lblIdActividad.getFont().getStyle(), 10));
		lblDescripcion.setFont(new Font(lblDescripcion.getFont().getFontName(), lblDescripcion.getFont().getStyle(), 10));
		lblNombre.setFont(new Font(lblNombre.getFont().getFontName(), lblNombre.getFont().getStyle(), 10));
		lblTipoPago.setFont(new Font(lblTipoPago.getFont().getFontName(), lblTipoPago.getFont().getStyle(), 10));
		btnCancelarInscripcion.setFont(new Font(btnCancelarInscripcion.getFont().getFontName(), btnCancelarInscripcion.getFont().getStyle(), 10));
		btnIncribir.setFont(new Font(btnIncribir.getFont().getFontName(), btnIncribir.getFont().getStyle(), 10));
		chckbxPago.setFont(new Font(chckbxPago.getFont().getFontName(), chckbxPago.getFont().getStyle(), 10));
	}
	public void toMedium() {
		lblIdActividad.setFont(new Font(lblIdActividad.getFont().getFontName(), lblIdActividad.getFont().getStyle(), 13));
		lblDescripcion.setFont(new Font(lblDescripcion.getFont().getFontName(), lblDescripcion.getFont().getStyle(), 13));
		lblNombre.setFont(new Font(lblNombre.getFont().getFontName(), lblNombre.getFont().getStyle(), 13));
		lblTipoPago.setFont(new Font(lblTipoPago.getFont().getFontName(), lblTipoPago.getFont().getStyle(), 13));
		btnCancelarInscripcion.setFont(new Font(btnCancelarInscripcion.getFont().getFontName(), btnCancelarInscripcion.getFont().getStyle(), 13));
		btnIncribir.setFont(new Font(btnIncribir.getFont().getFontName(), btnIncribir.getFont().getStyle(), 13));
		chckbxPago.setFont(new Font(chckbxPago.getFont().getFontName(), chckbxPago.getFont().getStyle(), 13));
	}
	public void toBig() {
		lblIdActividad.setFont(new Font(lblIdActividad.getFont().getFontName(), lblIdActividad.getFont().getStyle(), 16));
		lblDescripcion.setFont(new Font(lblDescripcion.getFont().getFontName(), lblDescripcion.getFont().getStyle(), 16));
		lblNombre.setFont(new Font(lblNombre.getFont().getFontName(), lblNombre.getFont().getStyle(), 16));
		lblTipoPago.setFont(new Font(lblTipoPago.getFont().getFontName(), lblTipoPago.getFont().getStyle(), 16));
		btnCancelarInscripcion.setFont(new Font(btnCancelarInscripcion.getFont().getFontName(), btnCancelarInscripcion.getFont().getStyle(), 16));
		btnIncribir.setFont(new Font(btnIncribir.getFont().getFontName(), btnIncribir.getFont().getStyle(), 16));
		chckbxPago.setFont(new Font(chckbxPago.getFont().getFontName(), chckbxPago.getFont().getStyle(), 16));
	}
}
