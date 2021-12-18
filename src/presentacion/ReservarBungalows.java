package presentacion;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.json.JSONObject;

import dominio.GestorReservas;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReservarBungalows extends JPanel {
	private JTable tableBungalows;

	private JSONObject JSONBungalows;
	private Object[][] bungalows;
	public JPanel panelReservarBungalows;
	public JPanel panelConfirmarReserva;
	
	private String tamano;
	private String precio;
	private String disponibilidad;
	private String m2;
	private String capacidad;
	private JButton btnReservar;
	private JTextField textFieldIDBungalow;
	private JTextField textFieldMostrarMesEntrada;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private JTextField textFieldSolicitudesEspeciales;
	private JTextField textFieldMostrarMesSalida;
	private JSpinner spinnerAnoEntrada;
	private JSpinner spinnerMesEntrada;
	private JSpinner spinnerAnoSalida;
	private JSpinner spinnerMesSalida;
	private JSpinner spinnerDiaSalida;
	private JSpinner spinnerDiaEntrada;
	private JSpinner spinnerOcupantes;
	private String[] meses;
	private int mesActualEntrada;
	private int mesActualSalida;
	private JLabel lblIDBungalow;
	private JLabel lblNewLabel;
	private JLabel lblyearEntrada;
	private JLabel lblMesEntrada;
	private JLabel lblDia;
	private JLabel lblHoraEntrada;
	private JLabel lblNombre;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JLabel lblSolicitudEspecial;
	private JLabel lblTelefonoopc;
	private JLabel lblOcupantes;
	private JLabel lblHoraSalida;
	private JLabel lblDia_1;
	private JLabel lblMesEntrada_1;
	private JLabel lblyearEntrada_1;
	private JLabel lblNewLabel_1;
	private JComboBox comboBoxHoraEntrada;
	private JComboBox comboBoxHoraSalida;
	private JTextField textFieldTlf1;
	private JTextField textFieldTlf2;
	private JLabel lblFotos;
	private JLabel lblFoto;
	/**
	 * Create the panel.
	 */
	public ReservarBungalows() {
		JSONBungalows = GestorReservas.leerBungalows();
		bungalows = GestorReservas.devolverBungalows(JSONBungalows);
		setLayout(new CardLayout(0, 0));
		
		panelReservarBungalows = new JPanel();
		add(panelReservarBungalows, "name_42484428198000");
		panelReservarBungalows.setLayout(null);
		
		JScrollPane scrollPaneTablaBungalows = new JScrollPane();
		scrollPaneTablaBungalows.setBounds(new Rectangle(10, 11, 539, 153));
		scrollPaneTablaBungalows.setBounds(64, 48, 496, 193);
		panelReservarBungalows.add(scrollPaneTablaBungalows);
		
		tableBungalows = new JTable();
		tableBungalows.addMouseListener(new TableBungalowsMouseListener());
		tableBungalows.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tamano = Messages.getString("Reservas.tamano.text");
		precio = Messages.getString("Reservas.precio.text");
		capacidad = Messages.getString("Reservas.capacidad.text");
		m2 = Messages.getString("Reservas.m2.text");
		disponibilidad = Messages.getString("Reservas.disponibilidad.text");
		tableBungalows.setModel(new DefaultTableModel(
			bungalows,
			new String[] {
				"ID", tamano, precio, capacidad, m2, disponibilidad}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableBungalows.getColumnModel().getColumn(0).setResizable(false);
		tableBungalows.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableBungalows.getColumnModel().getColumn(1).setResizable(false);
		tableBungalows.getColumnModel().getColumn(2).setResizable(false);
		tableBungalows.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableBungalows.getColumnModel().getColumn(3).setResizable(false);
		tableBungalows.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableBungalows.getColumnModel().getColumn(4).setResizable(false);
		tableBungalows.getColumnModel().getColumn(4).setPreferredWidth(35);
		scrollPaneTablaBungalows.setViewportView(tableBungalows);
		
		btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new BtnReservarActionListener());
		btnReservar.setBounds(443, 251, 103, 39);
		panelReservarBungalows.add(btnReservar);
		
		lblFotos = new JLabel();
		lblFotos.setBounds(64, 265, 188, 188);
		panelReservarBungalows.add(lblFotos);
		
		lblFoto = new JLabel(Messages.getString("ReservarBungalows.lblFoto.text")); //$NON-NLS-1$
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(46, 240, 206, 29);
		lblFoto.setVisible(false);
		panelReservarBungalows.add(lblFoto);
		
		panelConfirmarReserva = new JPanel();
		add(panelConfirmarReserva, "name_42486846503400");
		panelConfirmarReserva.setLayout(null);
		
		textFieldIDBungalow = new JTextField();
		textFieldIDBungalow.setEditable(false);
		textFieldIDBungalow.setColumns(10);
		textFieldIDBungalow.setBounds(315, 30, 63, 20);
		panelConfirmarReserva.add(textFieldIDBungalow);
		
		lblIDBungalow = new JLabel("ID bungalow:");
		lblIDBungalow.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIDBungalow.setBounds(173, 27, 132, 23);
		panelConfirmarReserva.add(lblIDBungalow);
		
		lblNewLabel = new JLabel("Entrada:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(147, 51, 89, 23);
		panelConfirmarReserva.add(lblNewLabel);
		
		lblyearEntrada = new JLabel("A\u00F1o:");
		lblyearEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblyearEntrada.setBounds(116, 84, 52, 18);
		panelConfirmarReserva.add(lblyearEntrada);
		
		spinnerAnoEntrada = new JSpinner();
		spinnerAnoEntrada.setModel(new SpinnerNumberModel(2021, 2021, 2022, 1));
		spinnerAnoEntrada.setBounds(173, 82, 63, 20);
		panelConfirmarReserva.add(spinnerAnoEntrada);
		
		textFieldMostrarMesEntrada = new JTextField();
		textFieldMostrarMesEntrada.setText((String) null);
		textFieldMostrarMesEntrada.setEditable(false);
		textFieldMostrarMesEntrada.setColumns(10);
		textFieldMostrarMesEntrada.setBounds(211, 113, 74, 20);
		panelConfirmarReserva.add(textFieldMostrarMesEntrada);
		
		spinnerMesEntrada = new JSpinner();
		spinnerMesEntrada.addChangeListener(new SpinnerMesEntradaChangeListener());
		spinnerMesEntrada.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinnerMesEntrada.setBounds(173, 113, 37, 20);
		panelConfirmarReserva.add(spinnerMesEntrada);
		
		lblMesEntrada = new JLabel("Mes:");
		lblMesEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesEntrada.setBounds(116, 113, 52, 20);
		panelConfirmarReserva.add(lblMesEntrada);
		
		lblDia = new JLabel("Dia:");
		lblDia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDia.setBounds(116, 143, 52, 20);
		panelConfirmarReserva.add(lblDia);
		
		MaskFormatter formatoHora = null;
		try {
			formatoHora = new MaskFormatter("##:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lblHoraEntrada = new JLabel("Hora entrada:");
		lblHoraEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoraEntrada.setBounds(55, 174, 112, 20);
		panelConfirmarReserva.add(lblHoraEntrada);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 210, 89, 20);
		panelConfirmarReserva.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(100, 213, 197, 20);
		panelConfirmarReserva.add(textFieldNombre);
		
		MaskFormatter formatoTelefono = null;
		try {
			formatoTelefono = new MaskFormatter("###-###-###");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(20, 240, 79, 18);
		panelConfirmarReserva.add(lblTelefono);
		
		lblEmail = new JLabel("E-mail(opc):");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(0, 263, 99, 20);
		panelConfirmarReserva.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(100, 263, 205, 20);
		panelConfirmarReserva.add(textFieldEmail);
		
		lblSolicitudEspecial = new JLabel("Solicitudes especiales(opc):");
		lblSolicitudEspecial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSolicitudEspecial.setBounds(10, 286, 228, 20);
		panelConfirmarReserva.add(lblSolicitudEspecial);
		
		textFieldSolicitudesEspeciales = new JTextField();
		textFieldSolicitudesEspeciales.setColumns(10);
		textFieldSolicitudesEspeciales.setBounds(236, 287, 368, 20);
		panelConfirmarReserva.add(textFieldSolicitudesEspeciales);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new BtnVolverActionListener());
		btnVolver.setBounds(147, 316, 112, 34);
		panelConfirmarReserva.add(btnVolver);
		
		JButton btnConfimar = new JButton("Confirmar reserva");
		btnConfimar.addActionListener(new BtnConfimarActionListener());
		btnConfimar.setBounds(291, 316, 177, 34);
		panelConfirmarReserva.add(btnConfimar);
		
		lblTelefonoopc = new JLabel("2\u00BA Telefono(opc):");
		lblTelefonoopc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefonoopc.setBounds(251, 239, 167, 20);
		panelConfirmarReserva.add(lblTelefonoopc);
		
		lblOcupantes = new JLabel("Ocupantes:");
		lblOcupantes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOcupantes.setBounds(325, 213, 112, 20);
		panelConfirmarReserva.add(lblOcupantes);
		
		lblHoraSalida = new JLabel("Hora salida:");
		lblHoraSalida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoraSalida.setBounds(315, 174, 128, 20);
		panelConfirmarReserva.add(lblHoraSalida);
		
		spinnerOcupantes = new JSpinner();
		spinnerOcupantes.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		spinnerOcupantes.setBounds(440, 214, 39, 20);
		panelConfirmarReserva.add(spinnerOcupantes);
		
		lblDia_1 = new JLabel("Dia:");
		lblDia_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDia_1.setBounds(374, 143, 69, 20);
		panelConfirmarReserva.add(lblDia_1);
		
		lblMesEntrada_1 = new JLabel("Mes:");
		lblMesEntrada_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesEntrada_1.setBounds(374, 113, 69, 20);
		panelConfirmarReserva.add(lblMesEntrada_1);
		
		spinnerMesSalida = new JSpinner();
		spinnerMesSalida.addChangeListener(new SpinnerMesSalidaChangeListener());
		spinnerMesSalida.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinnerMesSalida.setBounds(448, 111, 37, 20);
		panelConfirmarReserva.add(spinnerMesSalida);
		
		textFieldMostrarMesSalida = new JTextField();
		textFieldMostrarMesSalida.setText((String) null);
		textFieldMostrarMesSalida.setEditable(false);
		textFieldMostrarMesSalida.setColumns(10);
		textFieldMostrarMesSalida.setBounds(486, 111, 74, 20);
		panelConfirmarReserva.add(textFieldMostrarMesSalida);
		
		spinnerAnoSalida = new JSpinner();
		spinnerAnoSalida.setModel(new SpinnerNumberModel(2021, 2021, 2022, 1));
		spinnerAnoSalida.setBounds(448, 79, 63, 20);
		panelConfirmarReserva.add(spinnerAnoSalida);
		
		lblyearEntrada_1 = new JLabel("A\u00F1o:");
		lblyearEntrada_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblyearEntrada_1.setBounds(374, 82, 69, 20);
		panelConfirmarReserva.add(lblyearEntrada_1);
		
		lblNewLabel_1 = new JLabel("Salida:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(422, 51, 89, 23);
		panelConfirmarReserva.add(lblNewLabel_1);
		
		meses = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		textFieldMostrarMesEntrada.setText(meses[mesActualEntrada]);
		textFieldMostrarMesSalida.setText(meses[mesActualSalida]);
		
		spinnerDiaEntrada = new JSpinner();
		int mes;
		if(meses[mesActualEntrada].equals("Febrero"))
			mes=28;
		else if(meses[mesActualEntrada].equals("Enero")||meses[mesActualEntrada].equals("Marzo")||meses[mesActualEntrada].equals("Mayo")||meses[mesActualEntrada].equals("Julio")
				||meses[mesActualEntrada].equals("Agosto")||meses[mesActualEntrada].equals("Octubre")||meses[mesActualEntrada].equals("Diciembre"))
			mes=31;
		else
			mes=30;
		spinnerDiaEntrada.setModel(new SpinnerNumberModel(1, 1, mes, 1));
		spinnerDiaEntrada.setBounds(173, 145, 46, 20);
		panelConfirmarReserva.add(spinnerDiaEntrada);
		
		spinnerDiaSalida = new JSpinner();
		int mes_s;
		if(meses[mesActualSalida].equals("Febrero"))
			mes_s=28;
		else if(meses[mesActualSalida].equals("Enero")||meses[mesActualSalida].equals("Marzo")||meses[mesActualSalida].equals("Mayo")||meses[mesActualSalida].equals("Julio")
				||meses[mesActualSalida].equals("Agosto")||meses[mesActualSalida].equals("Octubre")||meses[mesActualSalida].equals("Diciembre"))
			mes_s=31;
		else
			mes_s=30;
		spinnerDiaSalida.setModel(new SpinnerNumberModel(1, 1, mes_s, 1));
		spinnerDiaSalida.setBounds(448, 141, 46, 20);
		panelConfirmarReserva.add(spinnerDiaSalida);
		
		String[] horas = {"10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};
		comboBoxHoraEntrada = new JComboBox(horas);
		comboBoxHoraEntrada.setBounds(173, 174, 99, 22);
		panelConfirmarReserva.add(comboBoxHoraEntrada);
		
		comboBoxHoraSalida = new JComboBox(horas);
		comboBoxHoraSalida.setBounds(455, 174, 99, 22);
		panelConfirmarReserva.add(comboBoxHoraSalida);
		
		textFieldTlf1 = new JTextField();
		textFieldTlf1.setText("");
		textFieldTlf1.setColumns(10);
		textFieldTlf1.setBounds(100, 238, 132, 20);
		panelConfirmarReserva.add(textFieldTlf1);
		
		textFieldTlf2 = new JTextField();
		textFieldTlf2.setText("");
		textFieldTlf2.setColumns(10);
		textFieldTlf2.setBounds(418, 238, 132, 20);
		panelConfirmarReserva.add(textFieldTlf2);
	}

	private class BtnReservarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (tableBungalows.getSelectedRow() != -1) {
				if (tableBungalows.getSelectedRowCount() == 1) {
					if (((String) tableBungalows.getValueAt(tableBungalows.getSelectedRow(), 5)).equals("libre")) {
						panelReservarBungalows.setVisible(false);
						panelConfirmarReserva.setVisible(true);
						textFieldIDBungalow.setText(bungalows[tableBungalows.getSelectedRow()][0].toString());
						int maxOcupantes = Integer.valueOf(bungalows[tableBungalows.getSelectedRow()][3].toString());
						spinnerOcupantes.setModel(new SpinnerNumberModel(1, 1, maxOcupantes, 1));
					} else
						JOptionPane.showMessageDialog(null, "El bungalow seleccionado no se encuentra disponible.");
				} else
					JOptionPane.showMessageDialog(null, "Debe seleccionar solo una fila.");
			} else
				JOptionPane.showMessageDialog(null, "Debe seleccionar un bungalow.");
		}
	}
	private class BtnVolverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			panelReservarBungalows.setVisible(true);
			panelConfirmarReserva.setVisible(false);
		}
	}

	private class BtnConfimarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm");
			Date date1 = null, date2 = null, hora1 = null, hora2 = null;
			String ano, mes, dia, ano2, mes2, dia2;
			if (comboBoxHoraEntrada.getSelectedItem() != null && comboBoxHoraSalida.getSelectedItem() != null
					&& !textFieldNombre.getText().toString().equals("")
					&& !textFieldTlf1.getText().toString().equals("")) {
				if (textFieldTlf1.getText().toString().length() == 9
						&& (textFieldTlf1.getText().toString().length() == 9
								|| textFieldTlf1.getText().toString().length() == 0)) {
					ano = spinnerAnoEntrada.getValue().toString();
					ano2 = spinnerAnoSalida.getValue().toString();
					if (spinnerMesEntrada.getValue().toString().length() == 1)
						mes = "0" + spinnerMesEntrada.getValue().toString();
					else
						mes = spinnerMesEntrada.getValue().toString();
					if (spinnerMesSalida.getValue().toString().length() == 1)
						mes2 = "0" + spinnerMesSalida.getValue().toString();
					else
						mes2 = spinnerMesSalida.getValue().toString();
					if (spinnerDiaEntrada.getValue().toString().length() == 1)
						dia = "0" + spinnerDiaEntrada.getValue().toString();
					else
						dia = spinnerDiaEntrada.getValue().toString();
					if (spinnerDiaSalida.getValue().toString().length() == 1)
						dia2 = "0" + spinnerDiaSalida.getValue().toString();
					else
						dia2 = spinnerDiaSalida.getValue().toString();
					try {
						date1 = dateFormat.parse(ano + "-" + mes + "-" + dia);
						date2 = dateFormat.parse(ano2 + "-" + mes2 + "-" + dia2);
						hora1 = dateFormat2.parse(comboBoxHoraEntrada.getSelectedItem().toString());
						hora2 = dateFormat2.parse(comboBoxHoraSalida.getSelectedItem().toString());
						if (date1.before(date2) || (date1.equals(date2) && hora1.getTime() < hora2.getTime())) {
							GestorReservas.reservarBungalows(JSONBungalows,
									bungalows[tableBungalows.getSelectedRow()][0].toString());
							JOptionPane.showMessageDialog(null, "El bungalow se ha reservado.");
							bungalows[tableBungalows.getSelectedRow()][5] = "ocupado";
							tableBungalows.setValueAt("ocupado", tableBungalows.getSelectedRow(), 5);
							panelConfirmarReserva.setVisible(false);
							panelReservarBungalows.setVisible(true);
							spinnerDiaEntrada.setValue(1);
							spinnerDiaSalida.setValue(1);
							spinnerMesEntrada.setValue(1);
							spinnerMesSalida.setValue(1);
							spinnerAnoEntrada.setValue(2021);
							spinnerAnoSalida.setValue(2021);
							comboBoxHoraEntrada.setSelectedItem(null);
							comboBoxHoraSalida.setSelectedItem(null);
							textFieldTlf1.setText(null);
							textFieldTlf2.setText(null);
							textFieldNombre.setText(null);
							spinnerOcupantes.setValue(1);
							textFieldEmail.setText(null);
							textFieldSolicitudesEspeciales.setText(null);
							lblFotos.setVisible(false);
							lblFoto.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null,
									"La fecha de entrada no puede ser posterior a la de salida.");
						}
					} catch (ParseException ex) {
						ex.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Los telefonos no son validos.");
			} else {
				JOptionPane.showMessageDialog(null, "Rellene todos los apartados obligatorios.");
			}
		}
	}
	private class SpinnerMesEntradaChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			int mesActualEntrada = (Integer)spinnerMesEntrada.getValue();
			textFieldMostrarMesEntrada.setText(meses[--mesActualEntrada]);
			int mes;
			if(meses[mesActualEntrada].equals("Febrero"))
				mes=28;
			else if(meses[mesActualEntrada].equals("Enero")||meses[mesActualEntrada].equals("Marzo")||meses[mesActualEntrada].equals("Mayo")||meses[mesActualEntrada].equals("Julio")
					||meses[mesActualEntrada].equals("Agosto")||meses[mesActualEntrada].equals("Octubre")||meses[mesActualEntrada].equals("Diciembre"))
				mes=31;
			else
				mes=30;
			spinnerDiaEntrada.setModel(new SpinnerNumberModel(1, 1, mes, 1));
		}
	}
	private class SpinnerMesSalidaChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			int mesActualSalida = (Integer)spinnerMesSalida.getValue();
			textFieldMostrarMesSalida.setText(meses[--mesActualSalida]);
			int mes_s;
			if(meses[mesActualSalida].equals("Febrero"))
				mes_s=28;
			else if(meses[mesActualSalida].equals("Enero")||meses[mesActualSalida].equals("Marzo")||meses[mesActualSalida].equals("Mayo")||meses[mesActualSalida].equals("Julio")
					||meses[mesActualSalida].equals("Agosto")||meses[mesActualSalida].equals("Octubre")||meses[mesActualSalida].equals("Diciembre"))
				mes_s=31;
			else
				mes_s=30;
			spinnerDiaSalida.setModel(new SpinnerNumberModel(1, 1, mes_s, 1));
		}
	}
	private class TableBungalowsMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(tableBungalows.getSelectedRow()>-1) {
				int i = Integer.valueOf(tableBungalows.getValueAt(tableBungalows.getSelectedRow(), 0).toString());
				i = i-1;
				try {
					Image imagenOriginal = ImageIO.read(ReservarBungalows.class.getResource(JSONBungalows.getJSONObject("bungalows").getJSONObject(""+i).getString("foto")));
					Image imagenEscalada = imagenOriginal.getScaledInstance(lblFotos.getWidth(),
							lblFotos.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
					lblFotos.setIcon(iconoLabel);
					lblFoto.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void toDark() {
		panelReservarBungalows.setBackground(Color.darkGray);
		panelConfirmarReserva.setBackground(Color.darkGray);
		lblIDBungalow.setForeground(Color.white);
		lblNewLabel.setForeground(Color.white);
		lblyearEntrada.setForeground(Color.white);
		lblMesEntrada.setForeground(Color.white);
		lblDia.setForeground(Color.white);
		lblHoraEntrada.setForeground(Color.white);
		lblNombre.setForeground(Color.white);
		lblTelefono.setForeground(Color.white);
		lblEmail.setForeground(Color.white);
		lblSolicitudEspecial.setForeground(Color.white);
		lblTelefonoopc.setForeground(Color.white);
		lblOcupantes.setForeground(Color.white);
		lblHoraSalida.setForeground(Color.white);
		lblDia_1.setForeground(Color.white);
		lblMesEntrada_1.setForeground(Color.white);
		lblyearEntrada_1.setForeground(Color.white);
		lblNewLabel_1.setForeground(Color.white);
		lblFoto.setForeground(Color.white);
	}
	
	public void toNormal() {
		panelReservarBungalows.setBackground(null);
		panelConfirmarReserva.setBackground(null);
		lblIDBungalow.setForeground(null);
		lblNewLabel.setForeground(null);
		lblyearEntrada.setForeground(null);
		lblMesEntrada.setForeground(null);
		lblDia.setForeground(null);
		lblHoraEntrada.setForeground(null);
		lblNombre.setForeground(null);
		lblTelefono.setForeground(null);
		lblEmail.setForeground(null);
		lblSolicitudEspecial.setForeground(null);
		lblTelefonoopc.setForeground(null);
		lblOcupantes.setForeground(null);
		lblHoraSalida.setForeground(null);
		lblDia_1.setForeground(null);
		lblMesEntrada_1.setForeground(null);
		lblyearEntrada_1.setForeground(null);
		lblNewLabel_1.setForeground(null);
		lblFoto.setForeground(null);
	}
	
	public void toES() {
		btnReservar.setText(Messages.getString("ReservarParcelas.btnReservar.text"));
		
		lblNewLabel.setText(Messages.getString("ReservarParcelas.lblNewLabel.text"));
		lblyearEntrada.setText(Messages.getString("ReservarParcelas.lblyearEntrada.text"));
		lblMesEntrada.setText(Messages.getString("ReservarParcelas.lblMesEntrada.text"));
		lblDia.setText(Messages.getString("ReservarParcelas.lblDia.text"));
		lblHoraEntrada.setText(Messages.getString("ReservarParcelas.lblHoraEntrada.text"));
		lblNombre.setText(Messages.getString("ReservarParcelas.lblNombre.text"));
		lblTelefono.setText(Messages.getString("ReservarParcelas.lblTelefono.text"));
		lblEmail.setText(Messages.getString("ReservarParcelas.lblEmail.text"));
		lblSolicitudEspecial.setText(Messages.getString("ReservarParcelas.lblSolicitudEspecial.text"));
		lblTelefonoopc.setText(Messages.getString("ReservarParcelas.lblTelefonoopc.text"));
		lblOcupantes.setText(Messages.getString("ReservarParcelas.lblOcupantes.text"));
		lblHoraSalida.setText(Messages.getString("ReservarParcelas.lblHoraSalida.text"));
		lblDia_1.setText(Messages.getString("ReservarParcelas.lblDia.text"));
		lblMesEntrada_1.setText(Messages.getString("ReservarParcelas.lblMesEntrada.text"));
		lblyearEntrada_1.setText(Messages.getString("ReservarParcelas.lblyearEntrada.text"));
		lblNewLabel_1.setText(Messages.getString("ReservarParcelas.lblNewLabel.text"));
		tamano = Messages.getString("Reservas.tamano.text");
		precio = Messages.getString("Reservas.precio.text");
		capacidad = Messages.getString("Reservas.capacidad.text");
		m2 = Messages.getString("Reservas.m2.text");
		disponibilidad = Messages.getString("Reservas.disponibilidad.text");
		tableBungalows.setModel(new DefaultTableModel(bungalows,new String[] {"ID", tamano, precio, capacidad, m2, disponibilidad}));
		lblFoto.setText(Messages.getString("ReservarBungalows.lblFoto.text"));
	}
	
	public void toEN() {
		btnReservar.setText(Messages.getString("ReservarParcelas.btnReservar.text"));
		lblNewLabel.setText(Messages.getString("ReservarParcelas.lblNewLabel.text"));
		lblyearEntrada.setText(Messages.getString("ReservarParcelas.lblyearEntrada.text"));
		lblMesEntrada.setText(Messages.getString("ReservarParcelas.lblMesEntrada.text"));
		lblDia.setText(Messages.getString("ReservarParcelas.lblDia.text"));
		lblHoraEntrada.setText(Messages.getString("ReservarParcelas.lblHoraEntrada.text"));
		lblNombre.setText(Messages.getString("ReservarParcelas.lblNombre.text"));
		lblTelefono.setText(Messages.getString("ReservarParcelas.lblTelefono.text"));
		lblEmail.setText(Messages.getString("ReservarParcelas.lblEmail.text"));
		lblSolicitudEspecial.setText(Messages.getString("ReservarParcelas.lblSolicitudEspecial.text"));
		lblTelefonoopc.setText(Messages.getString("ReservarParcelas.lblTelefonoopc.text"));
		lblOcupantes.setText(Messages.getString("ReservarParcelas.lblOcupantes.text"));
		lblHoraSalida.setText(Messages.getString("ReservarParcelas.lblHoraSalida.text"));
		lblDia_1.setText(Messages.getString("ReservarParcelas.lblDia.text"));
		lblMesEntrada_1.setText(Messages.getString("ReservarParcelas.lblMesEntrada.text"));
		lblyearEntrada_1.setText(Messages.getString("ReservarParcelas.lblyearEntrada.text"));
		lblNewLabel_1.setText(Messages.getString("ReservarParcelas.lblNewLabel.text"));
		tamano = Messages.getString("Reservas.tamano.text");
		precio = Messages.getString("Reservas.precio.text");
		capacidad = Messages.getString("Reservas.capacidad.text");
		m2 = Messages.getString("Reservas.m2.text");
		disponibilidad = Messages.getString("Reservas.disponibilidad.text");
		tableBungalows.setModel(new DefaultTableModel(bungalows,new String[] {"ID", tamano, precio, capacidad, m2, disponibilidad}));
		lblFoto.setText(Messages.getString("ReservarBungalows.lblFoto.text"));
	}
	
	public void toSmall(){
		btnReservar.setFont(new Font(btnReservar.getFont().getFontName(), btnReservar.getFont().getStyle(), 10));
		lblIDBungalow.setFont(new Font(lblIDBungalow.getFont().getFontName(), lblIDBungalow.getFont().getStyle(), 10));
		lblNewLabel.setFont(new Font(lblNewLabel.getFont().getFontName(), lblNewLabel.getFont().getStyle(), 10));
		lblyearEntrada.setFont(new Font(lblyearEntrada.getFont().getFontName(), lblyearEntrada.getFont().getStyle(), 10));
		lblMesEntrada.setFont(new Font(lblMesEntrada.getFont().getFontName(), lblMesEntrada.getFont().getStyle(), 10));
		lblDia.setFont(new Font(lblDia.getFont().getFontName(), lblDia.getFont().getStyle(), 10));
		lblHoraEntrada.setFont(new Font(lblHoraEntrada.getFont().getFontName(), lblHoraEntrada.getFont().getStyle(), 10));
		lblNombre.setFont(new Font(lblNombre.getFont().getFontName(), lblNombre.getFont().getStyle(), 10));
		lblTelefono.setFont(new Font(lblTelefono.getFont().getFontName(), lblTelefono.getFont().getStyle(), 10));
		lblEmail.setFont(new Font(lblEmail.getFont().getFontName(), lblEmail.getFont().getStyle(), 10));
		lblSolicitudEspecial.setFont(new Font(lblSolicitudEspecial.getFont().getFontName(), lblSolicitudEspecial.getFont().getStyle(), 10));
		lblTelefonoopc.setFont(new Font(lblTelefonoopc.getFont().getFontName(), lblTelefonoopc.getFont().getStyle(), 10));
		lblOcupantes.setFont(new Font(lblOcupantes.getFont().getFontName(), lblOcupantes.getFont().getStyle(), 10));
		lblHoraSalida.setFont(new Font(lblHoraSalida.getFont().getFontName(), lblHoraSalida.getFont().getStyle(), 10));
		lblDia_1.setFont(new Font(lblDia_1.getFont().getFontName(), lblDia_1.getFont().getStyle(), 10));
		lblMesEntrada_1.setFont(new Font(lblMesEntrada_1.getFont().getFontName(), lblMesEntrada_1.getFont().getStyle(), 10));
		lblyearEntrada_1.setFont(new Font(lblyearEntrada_1.getFont().getFontName(), lblyearEntrada_1.getFont().getStyle(), 10));
		lblNewLabel_1.setFont(new Font(lblNewLabel_1.getFont().getFontName(), lblNewLabel_1.getFont().getStyle(), 10));
		lblFoto.setFont(new Font(lblFoto.getFont().getFontName(), lblFoto.getFont().getStyle(), 10));
	}
	public void toMedium(){
		btnReservar.setFont(new Font(btnReservar.getFont().getFontName(), btnReservar.getFont().getStyle(), 13));
		lblIDBungalow.setFont(new Font(lblIDBungalow.getFont().getFontName(), lblIDBungalow.getFont().getStyle(), 13));
		lblNewLabel.setFont(new Font(lblNewLabel.getFont().getFontName(), lblNewLabel.getFont().getStyle(), 13));
		lblyearEntrada.setFont(new Font(lblyearEntrada.getFont().getFontName(), lblyearEntrada.getFont().getStyle(), 13));
		lblMesEntrada.setFont(new Font(lblMesEntrada.getFont().getFontName(), lblMesEntrada.getFont().getStyle(), 13));
		lblDia.setFont(new Font(lblDia.getFont().getFontName(), lblDia.getFont().getStyle(), 13));
		lblHoraEntrada.setFont(new Font(lblHoraEntrada.getFont().getFontName(), lblHoraEntrada.getFont().getStyle(), 13));
		lblNombre.setFont(new Font(lblNombre.getFont().getFontName(), lblNombre.getFont().getStyle(), 13));
		lblTelefono.setFont(new Font(lblTelefono.getFont().getFontName(), lblTelefono.getFont().getStyle(), 13));
		lblEmail.setFont(new Font(lblEmail.getFont().getFontName(), lblEmail.getFont().getStyle(), 13));
		lblSolicitudEspecial.setFont(new Font(lblSolicitudEspecial.getFont().getFontName(), lblSolicitudEspecial.getFont().getStyle(), 13));
		lblTelefonoopc.setFont(new Font(lblTelefonoopc.getFont().getFontName(), lblTelefonoopc.getFont().getStyle(), 13));
		lblOcupantes.setFont(new Font(lblOcupantes.getFont().getFontName(), lblOcupantes.getFont().getStyle(), 13));
		lblHoraSalida.setFont(new Font(lblHoraSalida.getFont().getFontName(), lblHoraSalida.getFont().getStyle(), 13));
		lblDia_1.setFont(new Font(lblDia_1.getFont().getFontName(), lblDia_1.getFont().getStyle(), 13));
		lblMesEntrada_1.setFont(new Font(lblMesEntrada_1.getFont().getFontName(), lblMesEntrada_1.getFont().getStyle(), 13));
		lblyearEntrada_1.setFont(new Font(lblyearEntrada_1.getFont().getFontName(), lblyearEntrada_1.getFont().getStyle(), 13));
		lblNewLabel_1.setFont(new Font(lblNewLabel_1.getFont().getFontName(), lblNewLabel_1.getFont().getStyle(), 13));
		lblFoto.setFont(new Font(lblFoto.getFont().getFontName(), lblFoto.getFont().getStyle(), 13));
	}
	public void toBig () {
		btnReservar.setFont(new Font(btnReservar.getFont().getFontName(), btnReservar.getFont().getStyle(), 16));
		lblIDBungalow.setFont(new Font(lblIDBungalow.getFont().getFontName(), lblIDBungalow.getFont().getStyle(), 16));
		lblNewLabel.setFont(new Font(lblNewLabel.getFont().getFontName(), lblNewLabel.getFont().getStyle(), 16));
		lblyearEntrada.setFont(new Font(lblyearEntrada.getFont().getFontName(), lblyearEntrada.getFont().getStyle(), 16));
		lblMesEntrada.setFont(new Font(lblMesEntrada.getFont().getFontName(), lblMesEntrada.getFont().getStyle(), 16));
		lblDia.setFont(new Font(lblDia.getFont().getFontName(), lblDia.getFont().getStyle(), 16));
		lblHoraEntrada.setFont(new Font(lblHoraEntrada.getFont().getFontName(), lblHoraEntrada.getFont().getStyle(), 16));
		lblNombre.setFont(new Font(lblNombre.getFont().getFontName(), lblNombre.getFont().getStyle(), 16));
		lblTelefono.setFont(new Font(lblTelefono.getFont().getFontName(), lblTelefono.getFont().getStyle(), 16));
		lblEmail.setFont(new Font(lblEmail.getFont().getFontName(), lblEmail.getFont().getStyle(), 16));
		lblSolicitudEspecial.setFont(new Font(lblSolicitudEspecial.getFont().getFontName(), lblSolicitudEspecial.getFont().getStyle(), 16));
		lblTelefonoopc.setFont(new Font(lblTelefonoopc.getFont().getFontName(), lblTelefonoopc.getFont().getStyle(), 16));
		lblOcupantes.setFont(new Font(lblOcupantes.getFont().getFontName(), lblOcupantes.getFont().getStyle(), 16));
		lblHoraSalida.setFont(new Font(lblHoraSalida.getFont().getFontName(), lblHoraSalida.getFont().getStyle(), 16));
		lblDia_1.setFont(new Font(lblDia_1.getFont().getFontName(), lblDia_1.getFont().getStyle(), 16));
		lblMesEntrada_1.setFont(new Font(lblMesEntrada_1.getFont().getFontName(), lblMesEntrada_1.getFont().getStyle(), 16));
		lblyearEntrada_1.setFont(new Font(lblyearEntrada_1.getFont().getFontName(), lblyearEntrada_1.getFont().getStyle(), 16));
		lblNewLabel_1.setFont(new Font(lblNewLabel_1.getFont().getFontName(), lblNewLabel_1.getFont().getStyle(), 16));
		lblFoto.setFont(new Font(lblFoto.getFont().getFontName(), lblFoto.getFont().getStyle(), 16));
	}
	public void actualizarTabla() {
		JSONBungalows = GestorReservas.leerBungalows();
		tableBungalows.removeAll();
		tableBungalows.setModel(new DefaultTableModel(
				GestorReservas.devolverBungalows(GestorReservas.leerBungalows()),
				new String[] {
					"ID", tamano, precio, capacidad, m2, disponibilidad}));
	}
}
	