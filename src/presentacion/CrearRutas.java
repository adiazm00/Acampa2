package presentacion;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import org.json.JSONObject;

import dominio.GestorPersonal;
import dominio.GestorRutas;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearRutas extends JPanel {
	private JTextField textFieldEquipo;
	private JComboBox comboBoxDias;
	private JComboBox comboBoxMes;
	private JComboBox comboBoxHoraInicio;
	private JComboBox comboBoxMinimos;
	private JComboBox comboBoxMaximos;
	private JComboBox comboBoxHoraFin;
	private JComboBox comboBoxAno;
	private JComboBox comboBoxMonitor;
	private JComboBox comboBoxDificultad;
	private JTextArea textDescripcion;
	private JButton btnContinuar;
	private String[] dias_mes;
	private String[] v8dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
	private String[] t0dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
	private String[] t1dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private int x, y;
	
	int modo = -1;
	private final int Encuentro = 1;
	private final int Inicio = 2;
	private final int Final = 3;

	private JTextField txtTexto;
	private Toolkit toolkit;
	private Image imagEncuentro;
	private Image imagInicio;
	private Image imagFinal;
	private Image imagPincel;
	private Image imagCursorEncuentro;
	private Image imagCursorInicio; 
	private Image imagCursorFinal;
	private Image imagCursorPincel; 
	private Cursor cursorEncuentro;
	private Cursor cursorFinal;
	private Cursor cursorInicio;
	private Cursor cursorPincel;
	
	private MostrarRutas mostrarRutas;
	private JSONObject JSONMonitores;
	private JSONObject JSONRutas;
	private JPanel panelCrear;
	private JPanel panelFoto;
	private JToolBar tbBarraDibujo;
	private JButton btnEncuentro;
	private JButton btnInicio;
	private JButton btnFinal;
	private JButton btnCursor;
	private JToolBar tbAbajo;
	private MiAreaDibujo miAreaDibujo;
	private JButton btnVolverAtras;
	private JButton btnLimpiar;
	private JButton btnCrearRuta;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel;
	private JLabel lblDia;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_5;
	private JLabel lblMes;
	private JLabel lblAno;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_5_1;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_3;
	/**
	 * Create the panel.
	 */
	public CrearRutas(MostrarRutas mR) {
		mostrarRutas = mR;
		JSONRutas = GestorRutas.leerRutas();
		JSONMonitores = GestorPersonal.leerJSON("Monitores");
		
		setLayout(new CardLayout(0, 0));
		
		panelCrear = new JPanel();
		add(panelCrear, "name_267093445787800");
		panelCrear.setLayout(null);
		
		lblNewLabel = new JLabel("Indique la fecha de la nueva ruta:");
		lblNewLabel.setBounds(99, 21, 293, 27);
		panelCrear.add(lblNewLabel);
		
		
		lblDia = new JLabel("Dia:");
		lblDia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDia.setBounds(99, 52, 54, 22);
		panelCrear.add(lblDia);
		
		lblNewLabel_1 = new JLabel("Hora de inicio:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(87, 84, 119, 22);
		panelCrear.add(lblNewLabel_1);
		
		comboBoxHoraInicio = new JComboBox();
		comboBoxHoraInicio.setModel(new DefaultComboBoxModel(new String[] {"9:30", "10:30", "12:00", "16:00", "17:30", "18:00", "19:30"}));
		comboBoxHoraInicio.setBounds(216, 84, 63, 22);
		panelCrear.add(comboBoxHoraInicio);
		
		comboBoxMinimos = new JComboBox();
		comboBoxMinimos.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		comboBoxMinimos.setBounds(223, 116, 55, 22);
		panelCrear.add(comboBoxMinimos);
		
		lblNewLabel_5 = new JLabel("Participantes minimos:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(40, 116, 173, 22);
		panelCrear.add(lblNewLabel_5);
		
		lblMes = new JLabel("Mes:");
		lblMes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMes.setBounds(223, 52, 55, 22);
		panelCrear.add(lblMes);
		
		comboBoxMes = new JComboBox();
		comboBoxMes.addActionListener(new ComboBoxMesActionListener());
		String[] meses={"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		comboBoxMes.setModel(new DefaultComboBoxModel(meses));
		comboBoxMes.setBounds(285, 52, 107, 22);
		panelCrear.add(comboBoxMes);
		
		comboBoxDias = new JComboBox();
		if(comboBoxMes.getSelectedItem().equals(2))
			dias_mes=v8dias;
		else if(comboBoxMes.getSelectedItem().equals(1)||comboBoxMes.getSelectedItem().equals(3)||comboBoxMes.getSelectedItem().equals(5)||comboBoxMes.getSelectedItem().equals(7)||comboBoxMes.getSelectedItem().equals(8)
				||comboBoxMes.getSelectedItem().equals(10)||comboBoxMes.getSelectedItem().equals(12))
			dias_mes=t0dias;
		else
			dias_mes=t1dias;
		comboBoxDias.setModel(new DefaultComboBoxModel(dias_mes ));
		comboBoxDias.setBounds(163, 52, 55, 22);
		panelCrear.add(comboBoxDias);
		
		lblAno = new JLabel("Año");
		lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAno.setBounds(403, 52, 55, 22);
		panelCrear.add(lblAno);
		
		comboBoxAno = new JComboBox();
		comboBoxAno.setModel(new DefaultComboBoxModel(new String[] {"2021", "2022", "2023"}));
		comboBoxAno.setBounds(468, 52, 63, 22);
		panelCrear.add(comboBoxAno);
		
		comboBoxHoraFin = new JComboBox();
		comboBoxHoraFin.setModel(new DefaultComboBoxModel(new String[] {"10:30", "11:00", "13:00", "17:00", "18:30", "20:00", "21:00"}));
		comboBoxHoraFin.setBounds(468, 85, 63, 22);
		panelCrear.add(comboBoxHoraFin);
		
		lblNewLabel_2 = new JLabel("Hora de finalizacion:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(287, 84, 173, 22);
		panelCrear.add(lblNewLabel_2);
		
		lblNewLabel_5_1 = new JLabel("Participantes maximos:");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_1.setBounds(285, 118, 183, 22);
		panelCrear.add(lblNewLabel_5_1);
		
		comboBoxMaximos = new JComboBox();
		comboBoxMaximos.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		comboBoxMaximos.setBounds(476, 118, 55, 22);
		panelCrear.add(comboBoxMaximos);
		
		comboBoxMonitor = new JComboBox(GestorRutas.devolverMonitores(JSONMonitores));
		comboBoxMonitor.setBounds(358, 171, 173, 22);
		panelCrear.add(comboBoxMonitor);
		
		comboBoxDificultad = new JComboBox();
		comboBoxDificultad.setModel(new DefaultComboBoxModel(new String[] {"Baja", "Media", "Alta", "Experto"}));
		comboBoxDificultad.setBounds(359, 204, 173, 27);
		panelCrear.add(comboBoxDificultad);
		
		lblNewLabel_8 = new JLabel("Describa el equipamiento necesario:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(288, 255, 309, 27);
		panelCrear.add(lblNewLabel_8);
		
		textFieldEquipo = new JTextField();
		textFieldEquipo.setColumns(10);
		textFieldEquipo.setBounds(323, 281, 208, 35);
		panelCrear.add(textFieldEquipo);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new BtnContinuarActionListener());
		btnContinuar.setBounds(358, 327, 173, 38);
		panelCrear.add(btnContinuar);
		
		textDescripcion = new JTextArea();
		textDescripcion.setLineWrap(true);
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(74, 281, 179, 84);
		panelCrear.add(textDescripcion);
		
		lblNewLabel_7 = new JLabel("Describa la ruta:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(71, 255, 182, 27);
		panelCrear.add(lblNewLabel_7);
		
		lblNewLabel_6 = new JLabel("Indique la dificultad:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(192, 204, 157, 27);
		panelCrear.add(lblNewLabel_6);
		
		lblNewLabel_3 = new JLabel("Seleccione un monitor de la nueva ruta:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(25, 171, 323, 22);
		panelCrear.add(lblNewLabel_3);
		
		panelFoto = new JPanel();
		add(panelFoto, "name_267096463664500");
		panelFoto.setLayout(new BorderLayout(0, 0));
		
		tbBarraDibujo = new JToolBar();
		panelFoto.add(tbBarraDibujo, BorderLayout.NORTH);
		
		btnEncuentro = new JButton("");
		btnEncuentro.addActionListener(new BtnEncuentroActionListener());
		btnEncuentro.setBounds(0, 0, 35, 35);
		try {
			Image imagenOriginal = ImageIO.read(CrearRutas.class.getResource("/presentacion/images/Encuentro.png"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(btnEncuentro.getWidth(),
					btnEncuentro.getHeight(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			btnEncuentro.setIcon(iconoLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		lblNewLabel_9 = new JLabel("                                                                  ");
		tbBarraDibujo.add(lblNewLabel_9);
		tbBarraDibujo.add(btnEncuentro);
		
		btnInicio = new JButton("");
		btnInicio.addActionListener(new BtnInicioActionListener());
		btnInicio.setBounds(0, 0, 35, 35);
		try {
			Image imagenOriginal = ImageIO.read(CrearRutas.class.getResource("/presentacion/images/Inicio.png"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(btnInicio.getWidth(),
					btnInicio.getHeight(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			btnInicio.setIcon(iconoLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		tbBarraDibujo.add(btnInicio);
		
		btnFinal = new JButton("");
		btnFinal.addActionListener(new BtnFinalActionListener());
		btnFinal.setBounds(0, 0, 35, 35);
		try {
			Image imagenOriginal = ImageIO.read(CrearRutas.class.getResource("/presentacion/images/Final.png"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(btnFinal.getWidth(),
					btnFinal.getHeight(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			btnFinal.setIcon(iconoLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		tbBarraDibujo.add(btnFinal);
		
		btnCursor = new JButton("");
		btnCursor.addActionListener(new BtnCursorActionListener());
		btnCursor.setBounds(0, 0, 35, 35);
		try {
			Image imagenOriginal = ImageIO.read(CrearRutas.class.getResource("/presentacion/images/Cursor.png"));
			Image imagenEscalada = imagenOriginal.getScaledInstance(btnCursor.getWidth(),
					btnCursor.getHeight(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
			btnCursor.setIcon(iconoLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		tbBarraDibujo.add(btnCursor);
		
		tbAbajo = new JToolBar();
		panelFoto.add(tbAbajo, BorderLayout.SOUTH);
		
		btnVolverAtras = new JButton("Volver atras");
		btnVolverAtras.addActionListener(new BtnVolverAtrasActionListener());
		
		lblNewLabel_4 = new JLabel("                                                                  ");
		tbAbajo.add(lblNewLabel_4);
		tbAbajo.add(btnVolverAtras);
		
		btnLimpiar = new JButton("Limpiar todo");
		btnLimpiar.addActionListener(new BtnLimpiarActionListener());
		tbAbajo.add(btnLimpiar);
		
		btnCrearRuta = new JButton("Crear ruta");
		btnCrearRuta.addActionListener(new BtnCrearRutaActionListener());
		tbAbajo.add(btnCrearRuta);
		
		scrollPane = new JScrollPane();
		panelFoto.add(scrollPane, BorderLayout.CENTER);
		metodosMapa();

	}
	
	public void toNormal(){
		panelCrear.setBackground(null);
		panelFoto.setBackground(null);
		lblDia.setForeground(null);
		lblMes.setForeground(null);
		lblAno.setForeground(null);
		lblNewLabel.setForeground(null);
		lblNewLabel_1.setForeground(null);
		lblNewLabel_2.setForeground(null);
		lblNewLabel_3.setForeground(null);
		lblNewLabel_4.setForeground(null);
		lblNewLabel_5.setForeground(null);
		lblNewLabel_5_1.setForeground(null);
		lblNewLabel_6.setForeground(null);
		lblNewLabel_7.setForeground(null);
		lblNewLabel_8.setForeground(null);
	}
	
	public void toDark(){
		panelCrear.setBackground(Color.darkGray);
		panelFoto.setBackground(Color.darkGray);
		lblDia.setForeground(Color.white);
		lblMes.setForeground(Color.white);
		lblAno.setForeground(Color.white);
		lblNewLabel.setForeground(Color.white);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_2.setForeground(Color.white);
		lblNewLabel_3.setForeground(Color.white);
		lblNewLabel_4.setForeground(Color.white);
		lblNewLabel_5.setForeground(Color.white);
		lblNewLabel_5_1.setForeground(Color.white);
		lblNewLabel_6.setForeground(Color.white);
		lblNewLabel_7.setForeground(Color.white);
		lblNewLabel_8.setForeground(Color.white);
	}
	
	public void toEN(){
		lblDia.setText(Messages.getString("CrearRutas.lblDia.text"));
		lblMes.setText(Messages.getString("CrearRutas.lblMes.text"));
		lblAno.setText(Messages.getString("CrearRutas.lblAno.text"));
		lblNewLabel.setText(Messages.getString("CrearRutas.lblNewLabel.text"));
		lblNewLabel_1.setText(Messages.getString("CrearRutas.lblNewLabel_1.text"));
		lblNewLabel_2.setText(Messages.getString("CrearRutas.lblNewLabel_2.text"));
		lblNewLabel_3.setText(Messages.getString("CrearRutas.lblNewLabel_3.text"));
		lblNewLabel_4.setText(Messages.getString("CrearRutas.lblNewLabel_4.text"));
		lblNewLabel_5.setText(Messages.getString("CrearRutas.lblNewLabel_5.text"));
		lblNewLabel_5_1.setText(Messages.getString("CrearRutas.lblNewLabel_5_1.text"));
		lblNewLabel_6.setText(Messages.getString("CrearRutas.lblNewLabel_6.text"));
		lblNewLabel_7.setText(Messages.getString("CrearRutas.lblNewLabel_7.text"));
		lblNewLabel_8.setText(Messages.getString("CrearRutas.lblNewLabel_8.text"));
	}
	
	public void toES(){
		lblDia.setText(Messages.getString("CrearRutas.lblDia.text"));
		lblMes.setText(Messages.getString("CrearRutas.lblMes.text"));
		lblAno.setText(Messages.getString("CrearRutas.lblAno.text"));
		lblNewLabel.setText(Messages.getString("CrearRutas.lblNewLabel.text"));
		lblNewLabel_1.setText(Messages.getString("CrearRutas.lblNewLabel_1.text"));
		lblNewLabel_2.setText(Messages.getString("CrearRutas.lblNewLabel_2.text"));
		lblNewLabel_3.setText(Messages.getString("CrearRutas.lblNewLabel_3.text"));
		lblNewLabel_4.setText(Messages.getString("CrearRutas.lblNewLabel_4.text"));
		lblNewLabel_5.setText(Messages.getString("CrearRutas.lblNewLabel_5.text"));
		lblNewLabel_5_1.setText(Messages.getString("CrearRutas.lblNewLabel_5_1.text"));
		lblNewLabel_6.setText(Messages.getString("CrearRutas.lblNewLabel_6.text"));
		lblNewLabel_7.setText(Messages.getString("CrearRutas.lblNewLabel_7.text"));
		lblNewLabel_8.setText(Messages.getString("CrearRutas.lblNewLabel_8.text"));
	}
	
	public void toSmall(){
		lblDia.setFont(new Font(lblDia.getFont().getFontName(), lblDia.getFont().getStyle(), 10));
		lblMes.setFont(new Font(lblMes.getFont().getFontName(), lblMes.getFont().getStyle(), 10));
		lblAno.setFont(new Font(lblAno.getFont().getFontName(), lblAno.getFont().getStyle(), 10));
		lblNewLabel.setFont(new Font(lblNewLabel.getFont().getFontName(), lblNewLabel.getFont().getStyle(), 10));
		lblNewLabel_1.setFont(new Font(lblNewLabel_1.getFont().getFontName(), lblNewLabel_1.getFont().getStyle(), 10));
		lblNewLabel_2.setFont(new Font(lblNewLabel_2.getFont().getFontName(), lblNewLabel_2.getFont().getStyle(), 10));
		lblNewLabel_3.setFont(new Font(lblNewLabel_3.getFont().getFontName(), lblNewLabel_3.getFont().getStyle(), 10));
		lblNewLabel_4.setFont(new Font(lblNewLabel_4.getFont().getFontName(), lblNewLabel_4.getFont().getStyle(), 10));
		lblNewLabel_5.setFont(new Font(lblNewLabel_5.getFont().getFontName(), lblNewLabel_5.getFont().getStyle(), 10));
		lblNewLabel_5_1.setFont(new Font(lblNewLabel_5_1.getFont().getFontName(), lblNewLabel_5_1.getFont().getStyle(), 10));
		lblNewLabel_6.setFont(new Font(lblNewLabel_6.getFont().getFontName(), lblNewLabel_6.getFont().getStyle(), 10));
		lblNewLabel_7.setFont(new Font(lblNewLabel_7.getFont().getFontName(), lblNewLabel_7.getFont().getStyle(), 10));
		lblNewLabel_8.setFont(new Font(lblNewLabel_8.getFont().getFontName(), lblNewLabel_8.getFont().getStyle(), 10));
	}
	public void toMedium(){
		lblDia.setFont(new Font(lblDia.getFont().getFontName(), lblDia.getFont().getStyle(), 13));
		lblMes.setFont(new Font(lblMes.getFont().getFontName(), lblMes.getFont().getStyle(), 13));
		lblAno.setFont(new Font(lblAno.getFont().getFontName(), lblAno.getFont().getStyle(), 13));
		lblNewLabel.setFont(new Font(lblNewLabel.getFont().getFontName(), lblNewLabel.getFont().getStyle(), 13));
		lblNewLabel_1.setFont(new Font(lblNewLabel_1.getFont().getFontName(), lblNewLabel_1.getFont().getStyle(), 13));
		lblNewLabel_2.setFont(new Font(lblNewLabel_2.getFont().getFontName(), lblNewLabel_2.getFont().getStyle(), 13));
		lblNewLabel_3.setFont(new Font(lblNewLabel_3.getFont().getFontName(), lblNewLabel_3.getFont().getStyle(), 13));
		lblNewLabel_4.setFont(new Font(lblNewLabel_4.getFont().getFontName(), lblNewLabel_4.getFont().getStyle(), 13));
		lblNewLabel_5.setFont(new Font(lblNewLabel_5.getFont().getFontName(), lblNewLabel_5.getFont().getStyle(), 13));
		lblNewLabel_5_1.setFont(new Font(lblNewLabel_5_1.getFont().getFontName(), lblNewLabel_5_1.getFont().getStyle(), 13));
		lblNewLabel_6.setFont(new Font(lblNewLabel_6.getFont().getFontName(), lblNewLabel_6.getFont().getStyle(), 13));
		lblNewLabel_7.setFont(new Font(lblNewLabel_7.getFont().getFontName(), lblNewLabel_7.getFont().getStyle(), 13));
		lblNewLabel_8.setFont(new Font(lblNewLabel_8.getFont().getFontName(), lblNewLabel_8.getFont().getStyle(), 13));
	}
	public void toBig(){
		lblDia.setFont(new Font(lblDia.getFont().getFontName(), lblDia.getFont().getStyle(), 16));
		lblMes.setFont(new Font(lblMes.getFont().getFontName(), lblMes.getFont().getStyle(), 16));
		lblAno.setFont(new Font(lblAno.getFont().getFontName(), lblAno.getFont().getStyle(), 16));
		lblNewLabel.setFont(new Font(lblNewLabel.getFont().getFontName(), lblNewLabel.getFont().getStyle(), 16));
		lblNewLabel_1.setFont(new Font(lblNewLabel_1.getFont().getFontName(), lblNewLabel_1.getFont().getStyle(), 16));
		lblNewLabel_2.setFont(new Font(lblNewLabel_2.getFont().getFontName(), lblNewLabel_2.getFont().getStyle(), 16));
		lblNewLabel_3.setFont(new Font(lblNewLabel_3.getFont().getFontName(), lblNewLabel_3.getFont().getStyle(), 16));
		lblNewLabel_4.setFont(new Font(lblNewLabel_4.getFont().getFontName(), lblNewLabel_4.getFont().getStyle(), 16));
		lblNewLabel_5.setFont(new Font(lblNewLabel_5.getFont().getFontName(), lblNewLabel_5.getFont().getStyle(), 16));
		lblNewLabel_5_1.setFont(new Font(lblNewLabel_5_1.getFont().getFontName(), lblNewLabel_5_1.getFont().getStyle(), 16));
		lblNewLabel_6.setFont(new Font(lblNewLabel_6.getFont().getFontName(), lblNewLabel_6.getFont().getStyle(), 16));
		lblNewLabel_7.setFont(new Font(lblNewLabel_7.getFont().getFontName(), lblNewLabel_7.getFont().getStyle(), 16));
		lblNewLabel_8.setFont(new Font(lblNewLabel_8.getFont().getFontName(), lblNewLabel_8.getFont().getStyle(), 16));
	}
	
	private class ComboBoxMesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (comboBoxMes.getSelectedItem() != null) {
				if (comboBoxMes.getSelectedItem().toString().equals("2"))
					dias_mes = v8dias;
				else if (comboBoxMes.getSelectedItem().toString().equals("1")
						|| comboBoxMes.getSelectedItem().toString().equals("3")
						|| comboBoxMes.getSelectedItem().toString().equals("5")
						|| comboBoxMes.getSelectedItem().toString().equals("7")
						|| comboBoxMes.getSelectedItem().toString().equals("8")
						|| comboBoxMes.getSelectedItem().toString().equals("10")
						|| comboBoxMes.getSelectedItem().toString().equals("12"))
					dias_mes = t0dias;
				else
					dias_mes = t1dias;
				comboBoxDias.setModel(new DefaultComboBoxModel(dias_mes));
			}
		}
	}
	private class BtnContinuarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (comboBoxDias.getSelectedItem() != null && comboBoxMes.getSelectedItem() != null
					&& comboBoxAno.getSelectedItem() != null && comboBoxHoraFin.getSelectedItem() != null
					&& comboBoxMaximos.getSelectedItem() != null && comboBoxHoraInicio.getSelectedItem() != null
					&& comboBoxMonitor.getSelectedItem() != null && comboBoxMinimos.getSelectedItem() != null
					&& comboBoxDificultad.getSelectedItem() != null && textFieldEquipo.getText().length() > 0
					&& textDescripcion.getText().length() > 0) {
				if (Integer.valueOf(comboBoxMinimos.getSelectedItem().toString()) < Integer
						.valueOf(comboBoxMaximos.getSelectedItem().toString())) {
					SimpleDateFormat df = new SimpleDateFormat("hh:mm");
					Date inicioRuta = null, finalRuta = null;
					try {
						inicioRuta = df.parse(comboBoxHoraInicio.getSelectedItem().toString());
						finalRuta = df.parse(comboBoxHoraFin.getSelectedItem().toString());
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if (finalRuta.getTime() > inicioRuta.getTime()) {
						panelFoto.setVisible(true);
						panelCrear.setVisible(false);
					} else
						JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser posterior a la final.");
				} else
					JOptionPane.showMessageDialog(null,
							"Los participantes maximos no puede ser un numero menor que los minimos.");
			} else
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos.");
		}
	}
	private class BtnEncuentroActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = Encuentro;
			panelFoto.setCursor(cursorEncuentro);
		}
	}
	private class BtnInicioActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = Inicio;
			panelFoto.setCursor(cursorInicio);
		}
	}
	private class BtnFinalActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = Final;
			panelFoto.setCursor(cursorFinal);
		}
	}
	private class BtnCursorActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			panelFoto.setCursor(null);
			modo = -1;
		}
	}
	private class MiAreaDibujoMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
				switch (modo) {
				case Encuentro:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagEncuentro));
					miAreaDibujo.repaint();
					break;
				case Inicio:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagInicio));
					miAreaDibujo.repaint();
					break;
				case Final:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagFinal));
					miAreaDibujo.repaint();
					break;
				}

		}
	}
	private class BtnLimpiarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = -1;
			panelFoto.setCursor(null);
			miAreaDibujo.borrarObjetosGraficos();
			miAreaDibujo.repaint();
		}
	}

	private class BtnCrearRutaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String rutaMapa = crearImagen();
			try {
				GestorRutas.addRuta(JSONRutas, JSONMonitores, comboBoxDias.getSelectedItem().toString(),
						comboBoxMes.getSelectedItem().toString(), comboBoxAno.getSelectedItem().toString(),
						comboBoxHoraInicio.getSelectedItem().toString(), comboBoxHoraFin.getSelectedItem().toString(),
						Integer.valueOf(comboBoxMaximos.getSelectedItem().toString()),
						Integer.valueOf(comboBoxMinimos.getSelectedItem().toString()),
						comboBoxMonitor.getSelectedItem().toString(), comboBoxDificultad.getSelectedItem().toString(),
						textFieldEquipo.getText().toString(), textDescripcion.getText().toString(),
						rutaMapa);
				panelFoto.setVisible(false);
				panelCrear.setVisible(true);
				textFieldEquipo.setText(null);
				textDescripcion.setText(null);
				comboBoxMaximos.setSelectedItem(null);
				comboBoxMinimos.setSelectedItem(null);
				comboBoxDificultad.setSelectedItem(null);
				comboBoxMonitor.setSelectedItem(null);
				comboBoxHoraInicio.setSelectedItem(null);
				comboBoxHoraFin.setSelectedItem(null);
				comboBoxDias.setSelectedItem(null);
				comboBoxMes.setSelectedItem(null);
				comboBoxAno.setSelectedItem(null);
				
				modo = -1;
				miAreaDibujo.borrarObjetosGraficos();
				miAreaDibujo.repaint();
				JOptionPane.showMessageDialog(null, "Se ha creado la ruta correctamente.");
				mostrarRutas.actualizarTabla();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class BtnVolverAtrasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = -1;
			miAreaDibujo.borrarObjetosGraficos();
			miAreaDibujo.repaint();
			panelFoto.setVisible(false);
			panelCrear.setVisible(true);
		}
	}

	public void metodosMapa() {
		miAreaDibujo = new MiAreaDibujo();
		miAreaDibujo.addMouseListener(new MiAreaDibujoMouseListener());
		miAreaDibujo.setIcon(null);
		scrollPane.setViewportView(miAreaDibujo);
		miAreaDibujo.setIcon(new ImageIcon(getClass().getResource("/presentacion/images/Mapa.jpeg")));

		toolkit = Toolkit.getDefaultToolkit();
		imagEncuentro =
		toolkit.getImage(getClass().getClassLoader().getResource("presentacion/images/Encuentro2.png"));
		imagInicio =
		toolkit.getImage(getClass().getClassLoader().getResource("presentacion/images/Inicio.png"));
		imagFinal =
		toolkit.getImage(getClass().getClassLoader().getResource("presentacion/images/Final.png"));
		imagCursorEncuentro =
		toolkit.getImage(getClass().getClassLoader().getResource("presentacion/images/Encuentro.png"));
		imagCursorFinal =
		toolkit.getImage(getClass().getClassLoader().getResource("presentacion/images/Final.png"));
		imagCursorInicio =
		toolkit.getImage(getClass().getClassLoader().getResource("presentacion/images/Inicio.png"));
		//Creación de los cursores
		cursorInicio = toolkit.createCustomCursor(imagInicio,new Point(0,0),"CURSOR_INICIO");
		cursorEncuentro= toolkit.createCustomCursor(imagEncuentro,new Point(0,0),"CURSOR_ENCUENTRO");
		cursorFinal = toolkit.createCustomCursor(imagCursorFinal,new Point(0,0),"CURSOR_FINAL");
	}
	public String crearImagen(){
		/*Image mapaCreado = miAreaDibujo.createImage(miAreaDibujo.getWidth(), miAreaDibujo.getHeight());
		int idAux = JSONRutas.getInt("numRutas")+1;
		String ruta = "/presentacion/images/Mapa"+idAux+".png";
		try {
			ImageIO.write((RenderedImage) mapaCreado, "png", new File(System.getProperty("user.dir") + "\\IPO\\src\\presentacion\\images\\Mapa"+idAux+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruta;*/
		return "/presentacion/images/Mapa.jpeg";
	}
}