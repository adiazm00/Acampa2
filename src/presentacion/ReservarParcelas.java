package presentacion;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.json.JSONObject;
import java.util.Date;

import dominio.GestorReservas;

import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReservarParcelas extends JPanel {
	public JPanel panelBuscar;
	public JPanel panelReservar;
	public JPanel panelConfirmarReserva;
	private JLabel lblTamano;
	private JToggleButton tpPglbtnPequena;
	private JToggleButton tpPglbtnMediana;
	private JToggleButton tglbtnAutocaravana;
	private JToggleButton tpPglbtnDeluxe;
	private JToggleButton tpPglbtnGrande;
	private JToggleButton tglbtnBar;
	private JToggleButton tglbtnEntrada;
	private JToggleButton tglbtnLavabos;
	private JToggleButton tglbtnRestaurante;
	private JToggleButton tglbtnAlta;
	private JButton btnBuscar;
	private JToggleButton tglbtnMedia;
	private JToggleButton tglbtnBaja;
	private JLabel lblTemporada;
	private JLabel lblCerca;
	private JScrollPane scrollPaneTablaParcelas;
	private JButton btnReservar;
	private JTable tableParcelas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JSONObject JSONParcelas;
	private Object[][] parcelas;
	private JButton btnAtras;
	private JLabel lblIDParcela;
	private JTextField textFieldIDParcela;
	private JTextField textFieldNombre;
	private JSpinner spinnerOcupantes;
	private JTextField textFieldEmail;
	private JTextField textFieldSolicitudesEspeciales;
	private JSpinner spinnerAnoEntrada;
	private JSpinner spinnerDiaEntrada;
	private JSpinner spinnerDiaSalida;
	private JSpinner spinnerAnoSalida;
	private JButton btnConfimar;
	private JButton btnVolver;
	private JSpinner spinnerMesEntrada;
	private JSpinner spinnerMesSalida;
	private JTextField textFieldMostrarMesEntrada;
	private JTextField textFieldMostrarMesSalida;
	private String[] meses;
	private int mesActualEntrada;
	private int mesActualSalida;
	private JLabel lblNombre;
	private JLabel lblTelefono;
	private JLabel lblTelefonoopc;
	private JLabel lblOcupantes;
	private JLabel lblEmail;
	private JLabel lblSolicitudEspecial;
	private JLabel lblHoraEntrada;
	private JLabel lblHoraSalida;
	private JLabel lblyearEntrada;
	private JLabel lblMesEntrada;
	private JLabel lblNewLabel;
	private JLabel lblDia;
	private JLabel lblNewLabel_1;
	private JLabel lblyearEntrada_1;
	private JLabel lblMesEntrada_1;
	private JLabel lblDia_1;
	private String buttonGroupAux;
	private String buttonGroupAux2;
	private String buttonGroupAux3;
	
	private String tamano;
	private String precio;
	private String capacidad;
	private String temporada;
	private String cercade;
	private String disponibilidad;
	private JComboBox comboBoxHoraEntrada;
	private JComboBox comboBoxHoraSalida;
	private JTextField textFieldTlf1;
	private JTextField textFieldTlf2;
	private JLabel lblFotos;
	private JLabel lblFoto;
	/**
	 * Create the panel.
	 */
	public ReservarParcelas(){
		setLayout(new CardLayout(0, 0));
		{
			panelBuscar = new JPanel();
			add(panelBuscar, "name_2263451971518900");
			panelBuscar.setLayout(null);
			{
				lblTamano = new JLabel(Messages.getString("ReservarParcelas.lblTamano.text")); //$NON-NLS-1$
				lblTamano.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTamano.setBounds(27, 91, 112, 25);
				panelBuscar.add(lblTamano);
			}
			{
				tpPglbtnPequena = new JToggleButton(Messages.getString("ReservarParcelas.tpPglbtnPequena.text")); //$NON-NLS-1$
				buttonGroup.add(tpPglbtnPequena);
				tpPglbtnPequena.setActionCommand(Messages.getString("ReservarParcelas.tpPglbtnPequena.actionCommand")); //$NON-NLS-1$
				tpPglbtnPequena.setBounds(149, 80, 132, 23);
				panelBuscar.add(tpPglbtnPequena);
			}
			{
				tpPglbtnMediana = new JToggleButton(Messages.getString("ReservarParcelas.tpPglbtnMediana.text")); //$NON-NLS-1$
				buttonGroup.add(tpPglbtnMediana);
				tpPglbtnMediana.setActionCommand(Messages.getString("ReservarParcelas.tpPglbtnMediana.actionCommand")); //$NON-NLS-1$
				tpPglbtnMediana.setBounds(282, 80, 132, 23);
				panelBuscar.add(tpPglbtnMediana);
			}
			{
				tglbtnAutocaravana = new JToggleButton(Messages.getString("ReservarParcelas.tglbtnAutocaravana.text")); //$NON-NLS-1$
				buttonGroup.add(tglbtnAutocaravana);
				tglbtnAutocaravana.setActionCommand(Messages.getString("ReservarParcelas.tglbtnAutocaravana.actionCommand")); //$NON-NLS-1$
				tglbtnAutocaravana.setBounds(415, 80, 150, 23);
				panelBuscar.add(tglbtnAutocaravana);
			}
			{
				tpPglbtnDeluxe = new JToggleButton(Messages.getString("ReservarParcelas.tpPglbtnDeluxe.text")); //$NON-NLS-1$
				buttonGroup.add(tpPglbtnDeluxe);
				tpPglbtnDeluxe.setActionCommand(Messages.getString("ReservarParcelas.tpPglbtnDeluxe.actionCommand")); //$NON-NLS-1$
				tpPglbtnDeluxe.setBounds(282, 104, 132, 23);
				panelBuscar.add(tpPglbtnDeluxe);
			}
			{
				tpPglbtnGrande = new JToggleButton(Messages.getString("ReservarParcelas.tpPglbtnGrande.text")); //$NON-NLS-1$
				buttonGroup.add(tpPglbtnGrande);
				tpPglbtnGrande.setActionCommand(Messages.getString("ReservarParcelas.tpPglbtnGrande.actionCommand")); //$NON-NLS-1$
				tpPglbtnGrande.setBounds(149, 104, 132, 23);
				panelBuscar.add(tpPglbtnGrande);
			}
			{
				tglbtnBar = new JToggleButton(Messages.getString("ReservarParcelas.tglbtnBar.text")); //$NON-NLS-1$
				buttonGroup_1.add(tglbtnBar);
				tglbtnBar.setActionCommand(Messages.getString("ReservarParcelas.tglbtnBar.actionCommand")); //$NON-NLS-1$
				tglbtnBar.setBounds(149, 162, 132, 23);
				panelBuscar.add(tglbtnBar);
			}
			{
				tglbtnEntrada = new JToggleButton(Messages.getString("ReservarParcelas.tglbtnEntrada.text")); //$NON-NLS-1$
				buttonGroup_1.add(tglbtnEntrada);
				tglbtnEntrada.setActionCommand(Messages.getString("ReservarParcelas.tglbtnEntrada.actionCommand")); //$NON-NLS-1$
				tglbtnEntrada.setBounds(149, 186, 132, 23);
				panelBuscar.add(tglbtnEntrada);
			}
			{
				tglbtnLavabos = new JToggleButton(Messages.getString("ReservarParcelas.tglbtnLavabos.text")); //$NON-NLS-1$
				buttonGroup_1.add(tglbtnLavabos);
				tglbtnLavabos.setActionCommand(Messages.getString("ReservarParcelas.tglbtnLavabos.actionCommand")); //$NON-NLS-1$
				tglbtnLavabos.setBounds(282, 186, 132, 23);
				panelBuscar.add(tglbtnLavabos);
			}
			{
				tglbtnRestaurante = new JToggleButton(Messages.getString("ReservarParcelas.tglbtnRestaurante.text")); //$NON-NLS-1$
				buttonGroup_1.add(tglbtnRestaurante);
				tglbtnRestaurante.setActionCommand(Messages.getString("ReservarParcelas.tglbtnRestaurante.actionCommand")); //$NON-NLS-1$
				tglbtnRestaurante.setBounds(282, 162, 132, 23);
				panelBuscar.add(tglbtnRestaurante);
			}
			{
				tglbtnAlta = new JToggleButton(Messages.getString("ReservarParcelas.tglbtnAlta.text")); //$NON-NLS-1$
				buttonGroup_2.add(tglbtnAlta);
				tglbtnAlta.setActionCommand(Messages.getString("ReservarParcelas.tglbtnAlta.actionCommand")); //$NON-NLS-1$
				tglbtnAlta.setBounds(415, 237, 132, 23);
				panelBuscar.add(tglbtnAlta);
			}
			{
				btnBuscar = new JButton(Messages.getString("ReservarParcelas.btnBuscar.text")); //$NON-NLS-1$
				btnBuscar.addActionListener(new BtnBuscarActionListener());
				buttonGroup_2.add(btnBuscar);
				btnBuscar.setBounds(458, 293, 89, 39);
				panelBuscar.add(btnBuscar);
			}
			{
				tglbtnMedia = new JToggleButton(Messages.getString("ReservarParcelas.tglbtnMedia.text")); //$NON-NLS-1$
				buttonGroup_2.add(tglbtnMedia);
				tglbtnMedia.setActionCommand(Messages.getString("ReservarParcelas.tglbtnMedia.actionCommand")); //$NON-NLS-1$
				tglbtnMedia.setBounds(282, 237, 132, 23);
				panelBuscar.add(tglbtnMedia);
			}
			{
				tglbtnBaja = new JToggleButton(Messages.getString("ReservarParcelas.tglbtnBaja.text")); //$NON-NLS-1$
				buttonGroup_2.add(tglbtnBaja);
				tglbtnBaja.setActionCommand(Messages.getString("ReservarParcelas.tglbtnBaja.actionCommand")); //$NON-NLS-1$
				tglbtnBaja.setBounds(149, 237, 132, 23);
				panelBuscar.add(tglbtnBaja);
			}
			{
				lblTemporada = new JLabel(Messages.getString("ReservarParcelas.lblTemporada.text")); //$NON-NLS-1$
				lblTemporada.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTemporada.setBounds(27, 236, 112, 25);
				panelBuscar.add(lblTemporada);
			}
			{
				lblCerca = new JLabel(Messages.getString("ReservarParcelas.lblCerca.text")); //$NON-NLS-1$
				lblCerca.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCerca.setBounds(27, 174, 112, 25);
				panelBuscar.add(lblCerca);
			}
		}
		{
			panelReservar = new JPanel();
			add(panelReservar, "name_2263466329942400");
			panelReservar.setLayout(null);
			{
				scrollPaneTablaParcelas = new JScrollPane();
				scrollPaneTablaParcelas.setBounds(22, 37, 615, 154);
				panelReservar.add(scrollPaneTablaParcelas);
				{
					tableParcelas = new JTable();
					tableParcelas.addMouseListener(new TableParcelasMouseListener());
					tableParcelas.setFont(new Font("Tahoma", Font.PLAIN, 10));
					
					scrollPaneTablaParcelas.setViewportView(tableParcelas);
				}
			}
			{
				btnReservar = new JButton(Messages.getString("ReservarParcelas.btnReservar.text")); //$NON-NLS-1$
				btnReservar.addActionListener(new BtnReservarActionListener());
				btnReservar.setBounds(428, 287, 148, 53);
				panelReservar.add(btnReservar);
			}
			{
				btnAtras = new JButton(Messages.getString("ReservarParcelas.btnAtras.text")); //$NON-NLS-1$
				btnAtras.addActionListener(new BtnAtrasActionListener());
				btnAtras.setBounds(40, 287, 155, 53);
				panelReservar.add(btnAtras);
			}
			
			lblFotos = new JLabel();
			lblFotos.setBounds(216, 223, 188, 188);
			panelReservar.add(lblFotos);
			
			lblFoto = new JLabel(Messages.getString("ReservarParcelas.lblFoto.text")); //$NON-NLS-1$ //$NON-NLS-1$
			lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
			lblFoto.setBounds(189, 195, 227, 25);
			lblFoto.setVisible(false);
			panelReservar.add(lblFoto);
		}
		{
			panelConfirmarReserva = new JPanel();
			add(panelConfirmarReserva, "name_29471369690800");
			panelConfirmarReserva.setLayout(null);
			{
				lblIDParcela = new JLabel(Messages.getString("ReservarParcelas.lblIDParcela.text")); //$NON-NLS-1$
				lblIDParcela.setHorizontalAlignment(SwingConstants.RIGHT);
				lblIDParcela.setBounds(165, 32, 132, 20);
				panelConfirmarReserva.add(lblIDParcela);
			}
			{
				textFieldIDParcela = new JTextField();
				textFieldIDParcela.setBounds(307, 32, 63, 20);
				textFieldIDParcela.setEditable(false);
				panelConfirmarReserva.add(textFieldIDParcela);
				textFieldIDParcela.setColumns(10);
			}
			
			lblNombre = new JLabel(Messages.getString("ReservarParcelas.lblNombre.text")); //$NON-NLS-1$
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(21, 219, 88, 17);
			panelConfirmarReserva.add(lblNombre);
			
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(120, 219, 197, 20);
			panelConfirmarReserva.add(textFieldNombre);
			textFieldNombre.setColumns(10);
			
			lblTelefono = new JLabel(Messages.getString("ReservarParcelas.lblTelefono.text")); //$NON-NLS-1$
			lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTelefono.setBounds(21, 241, 88, 20);
			panelConfirmarReserva.add(lblTelefono);
			
			lblTelefonoopc = new JLabel(Messages.getString("ReservarParcelas.lblTelefonoopc.text")); //$NON-NLS-1$
			lblTelefonoopc.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTelefonoopc.setBounds(290, 244, 142, 20);
			panelConfirmarReserva.add(lblTelefonoopc);
			
			lblOcupantes = new JLabel(Messages.getString("ReservarParcelas.lblOcupantes.text")); //$NON-NLS-1$
			lblOcupantes.setHorizontalAlignment(SwingConstants.RIGHT);
			lblOcupantes.setBounds(363, 216, 89, 20);
			panelConfirmarReserva.add(lblOcupantes);
			
			spinnerOcupantes = new JSpinner();
			spinnerOcupantes.setModel(new SpinnerNumberModel(1, 1, 50, 1));
			spinnerOcupantes.setBounds(455, 218, 39, 20);
			panelConfirmarReserva.add(spinnerOcupantes);
			
			lblEmail = new JLabel(Messages.getString("ReservarParcelas.lblEmail.text")); //$NON-NLS-1$
			lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEmail.setBounds(10, 266, 99, 20);
			panelConfirmarReserva.add(lblEmail);
			
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(120, 269, 205, 20);
			panelConfirmarReserva.add(textFieldEmail);
			textFieldEmail.setColumns(10);
			
			lblSolicitudEspecial = new JLabel(Messages.getString("ReservarParcelas.lblSolicitudEspecial.text")); //$NON-NLS-1$
			lblSolicitudEspecial.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSolicitudEspecial.setBounds(30, 296, 218, 20);
			panelConfirmarReserva.add(lblSolicitudEspecial);
			
			textFieldSolicitudesEspeciales = new JTextField();
			textFieldSolicitudesEspeciales.setBounds(257, 296, 368, 20);
			panelConfirmarReserva.add(textFieldSolicitudesEspeciales);
			textFieldSolicitudesEspeciales.setColumns(10);
			
			lblHoraEntrada = new JLabel(Messages.getString("ReservarParcelas.lblHoraEntrada.text")); //$NON-NLS-1$
			lblHoraEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHoraEntrada.setBounds(35, 175, 132, 20);
			panelConfirmarReserva.add(lblHoraEntrada);
			
			MaskFormatter formatoHora = null;
			try {
				formatoHora = new MaskFormatter("##:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			lblHoraSalida = new JLabel(Messages.getString("ReservarParcelas.lblHoraSalida.text")); //$NON-NLS-1$
			lblHoraSalida.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHoraSalida.setBounds(363, 175, 89, 20);
			panelConfirmarReserva.add(lblHoraSalida);
			
			btnConfimar = new JButton(Messages.getString("ReservarParcelas.btnConfimar.text")); //$NON-NLS-1$
			btnConfimar.addActionListener(new BtnConfimarActionListener());
			btnConfimar.setBounds(317, 334, 177, 33);
			panelConfirmarReserva.add(btnConfimar);
			
			btnVolver = new JButton(Messages.getString("ReservarParcelas.btnVolver.text")); //$NON-NLS-1$
			btnVolver.addActionListener(new BtnVolverActionListener());
			btnVolver.setBounds(173, 334, 109, 33);
			panelConfirmarReserva.add(btnVolver);
			
			lblyearEntrada = new JLabel(Messages.getString("ReservarParcelas.lblyearEntrada.text")); //$NON-NLS-1$
			lblyearEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
			lblyearEntrada.setBounds(116, 87, 52, 14);
			panelConfirmarReserva.add(lblyearEntrada);
			
			spinnerAnoEntrada = new JSpinner();
			spinnerAnoEntrada.setModel(new SpinnerNumberModel(2021, 2021, 2025, 1));
			spinnerAnoEntrada.setBounds(173, 84, 63, 20);
			panelConfirmarReserva.add(spinnerAnoEntrada);
			
			lblMesEntrada = new JLabel(Messages.getString("ReservarParcelas.lblMesEntrada.text")); //$NON-NLS-1$
			lblMesEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMesEntrada.setBounds(116, 119, 52, 14);
			panelConfirmarReserva.add(lblMesEntrada);
			
			lblNewLabel = new JLabel(Messages.getString("ReservarParcelas.lblNewLabel.text")); //$NON-NLS-1$
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(147, 56, 100, 20);
			panelConfirmarReserva.add(lblNewLabel);
			
			lblDia = new JLabel(Messages.getString("ReservarParcelas.lblDia.text")); //$NON-NLS-1$
			lblDia.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDia.setBounds(116, 148, 52, 14);
			panelConfirmarReserva.add(lblDia);
			
			lblNewLabel_1 = new JLabel(Messages.getString("ReservarParcelas.lblNewLabel_1.text")); //$NON-NLS-1$
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_1.setBounds(431, 51, 89, 20);
			panelConfirmarReserva.add(lblNewLabel_1);
			
			lblyearEntrada_1 = new JLabel(Messages.getString("ReservarParcelas.lblAñoEntrada_1.text")); //$NON-NLS-1$
			lblyearEntrada_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblyearEntrada_1.setBounds(400, 82, 52, 14);
			panelConfirmarReserva.add(lblyearEntrada_1);
			
			spinnerAnoSalida = new JSpinner();
			spinnerAnoSalida.setModel(new SpinnerNumberModel(2021, 2021, 2025, 1));
			spinnerAnoSalida.setBounds(457, 79, 63, 20);
			panelConfirmarReserva.add(spinnerAnoSalida);
			
			lblMesEntrada_1 = new JLabel(Messages.getString("ReservarParcelas.lblMesEntrada_1.text")); //$NON-NLS-1$
			lblMesEntrada_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMesEntrada_1.setBounds(400, 114, 52, 14);
			panelConfirmarReserva.add(lblMesEntrada_1);
			
			lblDia_1 = new JLabel(Messages.getString("ReservarParcelas.lblDia_1.text")); //$NON-NLS-1$
			lblDia_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDia_1.setBounds(400, 143, 52, 14);
			panelConfirmarReserva.add(lblDia_1);
			
			spinnerMesEntrada = new JSpinner();
			spinnerMesEntrada.addChangeListener(new SpinnerMesEntradaChangeListener());
			spinnerMesEntrada.setModel(new SpinnerNumberModel(1, 1, 12, 1));
			spinnerMesEntrada.setBounds(173, 115, 37, 20);
			panelConfirmarReserva.add(spinnerMesEntrada);
			
			spinnerMesSalida = new JSpinner();
			spinnerMesSalida.addChangeListener(new SpinnerMesSalidaChangeListener());
			spinnerMesSalida.setModel(new SpinnerNumberModel(1, 1, 12, 1));
			spinnerMesSalida.setBounds(457, 111, 37, 20);
			panelConfirmarReserva.add(spinnerMesSalida);
			
			textFieldMostrarMesEntrada = new JTextField();
			textFieldMostrarMesEntrada.setEditable(false);
			textFieldMostrarMesEntrada.setBounds(211, 115, 74, 20);
			panelConfirmarReserva.add(textFieldMostrarMesEntrada);
			textFieldMostrarMesEntrada.setColumns(10);
			
			textFieldMostrarMesSalida = new JTextField();
			textFieldMostrarMesSalida.setEditable(false);
			textFieldMostrarMesSalida.setColumns(10);
			textFieldMostrarMesSalida.setBounds(495, 111, 74, 20);
			panelConfirmarReserva.add(textFieldMostrarMesSalida);
			
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
			spinnerDiaSalida.setBounds(457, 140, 46, 20);
			panelConfirmarReserva.add(spinnerDiaSalida);
			
			String[] horas = {"10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};
			comboBoxHoraEntrada = new JComboBox(horas);
			comboBoxHoraEntrada.setBounds(173, 174, 99, 22);
			panelConfirmarReserva.add(comboBoxHoraEntrada);
			
			comboBoxHoraSalida = new JComboBox(horas);
			comboBoxHoraSalida.setBounds(455, 174, 99, 22);
			panelConfirmarReserva.add(comboBoxHoraSalida);
			
			textFieldTlf1 = new JTextField();
			textFieldTlf1.setText(""); //$NON-NLS-1$
			textFieldTlf1.setBounds(120, 243, 132, 20);
			panelConfirmarReserva.add(textFieldTlf1);
			textFieldTlf1.setColumns(10);
			
			textFieldTlf2 = new JTextField();
			textFieldTlf2.setText("");
			textFieldTlf2.setColumns(10);
			textFieldTlf2.setBounds(438, 243, 132, 20);
			panelConfirmarReserva.add(textFieldTlf2);
			
			
		}

	}
	private class BtnBuscarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (buttonGroup.getSelection() != null && buttonGroup_1.getSelection() != null
					&& buttonGroup_2.getSelection() != null) {
				JSONParcelas = GestorReservas.leerParcelas();
				parcelas = GestorReservas.devolverParcelas(JSONParcelas,
						buttonGroup.getSelection().getActionCommand().toString(),
						buttonGroup_1.getSelection().getActionCommand().toString(),
						buttonGroup_2.getSelection().getActionCommand().toString());
				buttonGroupAux = buttonGroup.getSelection().getActionCommand().toString();
				buttonGroupAux2 = buttonGroup_1.getSelection().getActionCommand().toString();
				buttonGroupAux3 = buttonGroup_2.getSelection().getActionCommand().toString();
				tableParcelas.removeAll();
				tamano=Messages.getString("Reservas.tamano.text");
				precio=Messages.getString("Reservas.precio.text");
				capacidad=Messages.getString("Reservas.capacidad.text");
				temporada=Messages.getString("ReservarParcelas.temporada.text");
				cercade=Messages.getString("ReservarParcelas.cercade.text");
				disponibilidad=Messages.getString("Reservas.disponibilidad.text");
				
				tableParcelas.setModel(new DefaultTableModel(parcelas,
						new String[] {
							"ID", tamano, precio, capacidad, "m^2", temporada, cercade, disponibilidad}
					) {
						boolean[] columnEditables = new boolean[] {
							false, false, false, false, false, false, false, false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					tableParcelas.getColumnModel().getColumn(0).setResizable(false);
					tableParcelas.getColumnModel().getColumn(0).setPreferredWidth(25);
					tableParcelas.getColumnModel().getColumn(0).setResizable(false);
					tableParcelas.getColumnModel().getColumn(4).setPreferredWidth(35);
				panelReservar.setVisible(true);
				panelBuscar.setVisible(false);
			} else
				JOptionPane.showMessageDialog(null,"Debe seleccionar su preferencia en cada uno de los apartados anteriores.");
		}
	}

	private class BtnReservarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (tableParcelas.getSelectedRow() != -1) {
				if (tableParcelas.getSelectedRowCount() == 1) {
					if (((String) tableParcelas.getValueAt(tableParcelas.getSelectedRow(), 7)).equals("libre")) {
						panelConfirmarReserva.setVisible(true);
						panelReservar.setVisible(false);
						textFieldIDParcela.setText(parcelas[tableParcelas.getSelectedRow()][0].toString());
						int maxOcupantes = Integer.valueOf(parcelas[tableParcelas.getSelectedRow()][3].toString());
						spinnerOcupantes.setModel(new SpinnerNumberModel(1, 1, maxOcupantes, 1));
					} else {
						JOptionPane.showMessageDialog(null, "La parcela seleccionada no se encuentra disponible.");
					}
				} else
					JOptionPane.showMessageDialog(null, "Debe seleccionar solo una fila.");
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una fila primero.");
			}
		}
	}
	private class BtnAtrasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panelReservar.setVisible(false);
			panelBuscar.setVisible(true);
			buttonGroup.clearSelection();buttonGroup_1.clearSelection();buttonGroup_2.clearSelection();
			lblFotos.setIcon(null);
			lblFoto.setVisible(false);
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
							GestorReservas.modificarPyB("Parcelas", "ocupada", "disponibilidad",
									textFieldIDParcela.getText().toString());
							parcelas[tableParcelas.getSelectedRow()][7] = "ocupada";
							tableParcelas.setValueAt("ocupada", tableParcelas.getSelectedRow(), 7);
							JOptionPane.showMessageDialog(null, "La parcela ha sido reservada.");
							panelConfirmarReserva.setVisible(false);
							panelReservar.setVisible(false);
							panelBuscar.setVisible(true);
							buttonGroup.clearSelection();
							buttonGroup_1.clearSelection();
							buttonGroup_2.clearSelection();
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
							lblFotos.setIcon(null);
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
	private class BtnVolverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			panelReservar.setVisible(true);
			panelConfirmarReserva.setVisible(false);
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

	private class TableParcelasMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(tableParcelas.getSelectedRow()>-1) {
				int i = Integer.valueOf(tableParcelas.getValueAt(tableParcelas.getSelectedRow(), 0).toString());
				i = i-1;
				try {
					Image imagenOriginal = ImageIO.read(ReservarParcelas.class.getResource(JSONParcelas.getJSONObject("parcelas").getJSONObject(""+i).getString("foto")));
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
		panelBuscar.setBackground(Color.darkGray);
		panelReservar.setBackground(Color.darkGray);
		panelConfirmarReserva.setBackground(Color.darkGray);
		lblTamano.setForeground(Color.white);
		lblCerca.setForeground(Color.white);
		lblTemporada.setForeground(Color.white);
		lblIDParcela.setForeground(Color.white);
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
		panelBuscar.setBackground(null);
		panelReservar.setBackground(null);
		panelConfirmarReserva.setBackground(null);
		lblTamano.setForeground(null);
		lblCerca.setForeground(null);
		lblTemporada.setForeground(null);
		lblIDParcela.setForeground(null);
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
		tpPglbtnPequena.setText(Messages.getString("ReservarParcelas.tpPglbtnPequena.text"));
		tpPglbtnMediana.setText(Messages.getString("ReservarParcelas.tpPglbtnMediana.text"));
		tpPglbtnDeluxe.setText(Messages.getString("ReservarParcelas.tpPglbtnDeluxe.text"));
		tglbtnAutocaravana.setText(Messages.getString("ReservarParcelas.tglbtnAutocaravana.text"));
		tglbtnBar.setText(Messages.getString("ReservarParcelas.tglbtnBar.text"));
		tglbtnEntrada.setText(Messages.getString("ReservarParcelas.tglbtnEntrada.text"));
		tglbtnRestaurante.setText(Messages.getString("ReservarParcelas.tglbtnRestaurante.text"));
		tglbtnLavabos.setText(Messages.getString("ReservarParcelas.tglbtnLavabos.text"));
		tglbtnBaja.setText(Messages.getString("ReservarParcelas.tglbtnBaja.text"));
		tglbtnMedia.setText(Messages.getString("ReservarParcelas.tglbtnMedia.text"));
		tglbtnAlta.setText(Messages.getString("ReservarParcelas.tglbtnAlta.text"));
		btnBuscar.setText(Messages.getString("ReservarParcelas.btnBuscar.text"));
		btnAtras.setText(Messages.getString("ReservarParcelas.btnAtras.text"));
		btnReservar.setText(Messages.getString("ReservarParcelas.btnReservar.text"));
		btnConfimar.setText(Messages.getString("ReservarParcelas.btnConfimar.text"));
		btnVolver.setText(Messages.getString("ReservarParcelas.btnVolver.text"));
		
		lblTamano.setText(Messages.getString("ReservarParcelas.lblTamano.text"));
		lblCerca.setText(Messages.getString("ReservarParcelas.lblCerca.text"));
		lblTemporada.setText(Messages.getString("ReservarParcelas.lblTemporada.text"));
		lblIDParcela.setText(Messages.getString("ReservarParcelas.lblIDParcela.text"));
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
		
		tamano=Messages.getString("Reservas.tamano.text");
		precio=Messages.getString("Reservas.precio.text");
		capacidad=Messages.getString("Reservas.capacidad.text");
		temporada=Messages.getString("ReservarParcelas.temporada.text");
		cercade=Messages.getString("ReservarParcelas.cercade.text");
		disponibilidad=Messages.getString("Reservas.disponibilidad.text");
		tableParcelas.getColumnModel().getColumn(0).setWidth(25);
		tableParcelas.setModel(new DefaultTableModel(parcelas,new String[] {"ID", tamano, precio, capacidad, "m^2", temporada, cercade, disponibilidad}));
		lblFoto.setText(Messages.getString("ReservarParcelas.lblFoto.text"));
	}
	
	public void toEN() {
		tpPglbtnPequena.setText(Messages.getString("ReservarParcelas.tpPglbtnPequena.text"));
		tpPglbtnMediana.setText(Messages.getString("ReservarParcelas.tpPglbtnMediana.text"));
		tpPglbtnDeluxe.setText(Messages.getString("ReservarParcelas.tpPglbtnDeluxe.text"));
		tglbtnAutocaravana.setText(Messages.getString("ReservarParcelas.tglbtnAutocaravana.text"));
		tglbtnBar.setText(Messages.getString("ReservarParcelas.tglbtnBar.text"));
		tglbtnEntrada.setText(Messages.getString("ReservarParcelas.tglbtnEntrada.text"));
		tglbtnRestaurante.setText(Messages.getString("ReservarParcelas.tglbtnRestaurante.text"));
		tglbtnLavabos.setText(Messages.getString("ReservarParcelas.tglbtnLavabos.text"));
		tglbtnBaja.setText(Messages.getString("ReservarParcelas.tglbtnBaja.text"));
		tglbtnMedia.setText(Messages.getString("ReservarParcelas.tglbtnMedia.text"));
		tglbtnAlta.setText(Messages.getString("ReservarParcelas.tglbtnAlta.text"));
		btnBuscar.setText(Messages.getString("ReservarParcelas.btnBuscar.text"));
		btnAtras.setText(Messages.getString("ReservarParcelas.btnAtras.text"));
		btnReservar.setText(Messages.getString("ReservarParcelas.btnReservar.text"));
		btnConfimar.setText(Messages.getString("ReservarParcelas.btnConfimar.text"));
		btnVolver.setText(Messages.getString("ReservarParcelas.btnVolver.text"));
		
		lblTamano.setText(Messages.getString("ReservarParcelas.lblTamano.text"));
		lblCerca.setText(Messages.getString("ReservarParcelas.lblCerca.text"));
		lblTemporada.setText(Messages.getString("ReservarParcelas.lblTemporada.text"));
		lblIDParcela.setText(Messages.getString("ReservarParcelas.lblIDParcela.text"));
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
		
		tamano=Messages.getString("Reservas.tamano.text");
		precio=Messages.getString("Reservas.precio.text");
		capacidad=Messages.getString("Reservas.capacidad.text");
		temporada=Messages.getString("ReservarParcelas.temporada.text");
		cercade=Messages.getString("ReservarParcelas.cercade.text");
		disponibilidad=Messages.getString("Reservas.disponibilidad.text");
		tableParcelas.setModel(new DefaultTableModel(parcelas,new String[] {"ID", tamano, precio, capacidad, "m^2", temporada, cercade, disponibilidad}));
		lblFoto.setText(Messages.getString("ReservarParcelas.lblFoto.text"));
	}
	
	public void toSmall(){
			btnReservar.setFont(new Font(btnReservar.getFont().getFontName(), btnReservar.getFont().getStyle(), 10));
			btnAtras.setFont(new Font(btnAtras.getFont().getFontName(), btnAtras.getFont().getStyle(), 10));
			btnBuscar.setFont(new Font(btnBuscar.getFont().getFontName(), btnBuscar.getFont().getStyle(), 10));
			lblTamano.setFont(new Font(lblTamano.getFont().getFontName(), lblTamano.getFont().getStyle(), 10));
			tpPglbtnPequena.setFont(new Font(tpPglbtnPequena.getFont().getFontName(), tpPglbtnPequena.getFont().getStyle(), 10));
			tpPglbtnMediana.setFont(new Font(tpPglbtnMediana.getFont().getFontName(), tpPglbtnMediana.getFont().getStyle(), 10));
			tpPglbtnGrande.setFont(new Font(tpPglbtnGrande.getFont().getFontName(), tpPglbtnGrande.getFont().getStyle(), 10));
			tpPglbtnDeluxe.setFont(new Font(tpPglbtnDeluxe.getFont().getFontName(), tpPglbtnDeluxe.getFont().getStyle(), 10));
			tglbtnAutocaravana.setFont(new Font(tglbtnAutocaravana.getFont().getFontName(), tglbtnAutocaravana.getFont().getStyle(), 10));
			lblCerca.setFont(new Font(lblCerca.getFont().getFontName(), lblCerca.getFont().getStyle(), 10));
			tglbtnBar.setFont(new Font(tglbtnBar.getFont().getFontName(), tglbtnBar.getFont().getStyle(), 10));
			tglbtnRestaurante.setFont(new Font(tglbtnRestaurante.getFont().getFontName(), tglbtnRestaurante.getFont().getStyle(), 10));
			tglbtnEntrada.setFont(new Font(tglbtnEntrada.getFont().getFontName(), tglbtnEntrada.getFont().getStyle(), 10));
			tglbtnLavabos.setFont(new Font(tglbtnLavabos.getFont().getFontName(), tglbtnLavabos.getFont().getStyle(), 10));
			lblTemporada.setFont(new Font(lblTemporada.getFont().getFontName(), lblTemporada.getFont().getStyle(), 10));
			tglbtnBaja.setFont(new Font(tglbtnBaja.getFont().getFontName(), tglbtnBaja.getFont().getStyle(), 10));
			tglbtnMedia.setFont(new Font(tglbtnMedia.getFont().getFontName(), tglbtnMedia.getFont().getStyle(), 10));
			tglbtnAlta.setFont(new Font(tglbtnAlta.getFont().getFontName(), tglbtnAlta.getFont().getStyle(), 10));
			
			btnBuscar.setFont(new Font(btnBuscar.getFont().getFontName(), btnBuscar.getFont().getStyle(), 10));
			btnAtras.setFont(new Font(btnAtras.getFont().getFontName(), btnAtras.getFont().getStyle(), 10));
			btnReservar.setFont(new Font(btnReservar.getFont().getFontName(), btnReservar.getFont().getStyle(), 10));
			btnConfimar.setFont(new Font(btnConfimar.getFont().getFontName(), btnConfimar.getFont().getStyle(), 10));
			btnVolver.setFont(new Font(btnVolver.getFont().getFontName(), btnVolver.getFont().getStyle(), 10));
			
			lblIDParcela.setFont(new Font(lblIDParcela.getFont().getFontName(), lblIDParcela.getFont().getStyle(), 10));
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
			btnAtras.setFont(new Font(btnAtras.getFont().getFontName(), btnAtras.getFont().getStyle(), 13));
			btnBuscar.setFont(new Font(btnBuscar.getFont().getFontName(), btnBuscar.getFont().getStyle(), 13));
			lblTamano.setFont(new Font(lblTamano.getFont().getFontName(), lblTamano.getFont().getStyle(), 13));
			tpPglbtnPequena.setFont(new Font(tpPglbtnPequena.getFont().getFontName(), tpPglbtnPequena.getFont().getStyle(), 13));
			tpPglbtnMediana.setFont(new Font(tpPglbtnMediana.getFont().getFontName(), tpPglbtnMediana.getFont().getStyle(), 13));
			tpPglbtnGrande.setFont(new Font(tpPglbtnGrande.getFont().getFontName(), tpPglbtnGrande.getFont().getStyle(), 13));
			tpPglbtnDeluxe.setFont(new Font(tpPglbtnDeluxe.getFont().getFontName(), tpPglbtnDeluxe.getFont().getStyle(), 13));
			tglbtnAutocaravana.setFont(new Font(tglbtnAutocaravana.getFont().getFontName(), tglbtnAutocaravana.getFont().getStyle(), 13));
			lblCerca.setFont(new Font(lblCerca.getFont().getFontName(), lblCerca.getFont().getStyle(), 13));
			tglbtnBar.setFont(new Font(tglbtnBar.getFont().getFontName(), tglbtnBar.getFont().getStyle(), 13));
			tglbtnRestaurante.setFont(new Font(tglbtnRestaurante.getFont().getFontName(), tglbtnRestaurante.getFont().getStyle(), 13));
			tglbtnEntrada.setFont(new Font(tglbtnEntrada.getFont().getFontName(), tglbtnEntrada.getFont().getStyle(), 13));
			tglbtnLavabos.setFont(new Font(tglbtnLavabos.getFont().getFontName(), tglbtnLavabos.getFont().getStyle(), 13));
			lblTemporada.setFont(new Font(lblTemporada.getFont().getFontName(), lblTemporada.getFont().getStyle(), 13));
			tglbtnBaja.setFont(new Font(tglbtnBaja.getFont().getFontName(), tglbtnBaja.getFont().getStyle(), 13));
			tglbtnMedia.setFont(new Font(tglbtnMedia.getFont().getFontName(), tglbtnMedia.getFont().getStyle(), 13));
			tglbtnAlta.setFont(new Font(tglbtnAlta.getFont().getFontName(), tglbtnAlta.getFont().getStyle(), 13));
			
			btnBuscar.setFont(new Font(btnBuscar.getFont().getFontName(), btnBuscar.getFont().getStyle(), 13));
			btnAtras.setFont(new Font(btnAtras.getFont().getFontName(), btnAtras.getFont().getStyle(), 13));
			btnReservar.setFont(new Font(btnReservar.getFont().getFontName(), btnReservar.getFont().getStyle(), 13));
			btnConfimar.setFont(new Font(btnConfimar.getFont().getFontName(), btnConfimar.getFont().getStyle(), 13));
			btnVolver.setFont(new Font(btnVolver.getFont().getFontName(), btnVolver.getFont().getStyle(), 13));
			
			lblIDParcela.setFont(new Font(lblIDParcela.getFont().getFontName(), lblIDParcela.getFont().getStyle(), 13));
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
			btnAtras.setFont(new Font(btnAtras.getFont().getFontName(), btnAtras.getFont().getStyle(), 16));
			btnBuscar.setFont(new Font(btnBuscar.getFont().getFontName(), btnBuscar.getFont().getStyle(), 16));
			lblTamano.setFont(new Font(lblTamano.getFont().getFontName(), lblTamano.getFont().getStyle(), 16));
			tpPglbtnPequena.setFont(new Font(tpPglbtnPequena.getFont().getFontName(), tpPglbtnPequena.getFont().getStyle(), 16));
			tpPglbtnMediana.setFont(new Font(tpPglbtnMediana.getFont().getFontName(), tpPglbtnMediana.getFont().getStyle(), 16));
			tpPglbtnGrande.setFont(new Font(tpPglbtnGrande.getFont().getFontName(), tpPglbtnGrande.getFont().getStyle(), 16));
			tpPglbtnDeluxe.setFont(new Font(tpPglbtnDeluxe.getFont().getFontName(), tpPglbtnDeluxe.getFont().getStyle(), 16));
			tglbtnAutocaravana.setFont(new Font(tglbtnAutocaravana.getFont().getFontName(), tglbtnAutocaravana.getFont().getStyle(), 16));
			lblCerca.setFont(new Font(lblCerca.getFont().getFontName(), lblCerca.getFont().getStyle(), 16));
			tglbtnBar.setFont(new Font(tglbtnBar.getFont().getFontName(), tglbtnBar.getFont().getStyle(), 16));
			tglbtnRestaurante.setFont(new Font(tglbtnRestaurante.getFont().getFontName(), tglbtnRestaurante.getFont().getStyle(), 16));
			tglbtnEntrada.setFont(new Font(tglbtnEntrada.getFont().getFontName(), tglbtnEntrada.getFont().getStyle(), 16));
			tglbtnLavabos.setFont(new Font(tglbtnLavabos.getFont().getFontName(), tglbtnLavabos.getFont().getStyle(), 16));
			lblTemporada.setFont(new Font(lblTemporada.getFont().getFontName(), lblTemporada.getFont().getStyle(), 16));
			tglbtnBaja.setFont(new Font(tglbtnBaja.getFont().getFontName(), tglbtnBaja.getFont().getStyle(), 16));
			tglbtnMedia.setFont(new Font(tglbtnMedia.getFont().getFontName(), tglbtnMedia.getFont().getStyle(), 16));
			tglbtnAlta.setFont(new Font(tglbtnAlta.getFont().getFontName(), tglbtnAlta.getFont().getStyle(), 16));
			
			btnBuscar.setFont(new Font(btnBuscar.getFont().getFontName(), btnBuscar.getFont().getStyle(), 16));
			btnAtras.setFont(new Font(btnAtras.getFont().getFontName(), btnAtras.getFont().getStyle(), 16));
			btnReservar.setFont(new Font(btnReservar.getFont().getFontName(), btnReservar.getFont().getStyle(), 16));
			btnConfimar.setFont(new Font(btnConfimar.getFont().getFontName(), btnConfimar.getFont().getStyle(), 16));
			btnVolver.setFont(new Font(btnVolver.getFont().getFontName(), btnVolver.getFont().getStyle(), 16));
			
			lblIDParcela.setFont(new Font(lblIDParcela.getFont().getFontName(), lblIDParcela.getFont().getStyle(), 16));
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
		tableParcelas.removeAll();
		JSONParcelas = GestorReservas.leerParcelas();
		parcelas = GestorReservas.devolverParcelas(JSONParcelas, buttonGroupAux, buttonGroupAux2, buttonGroupAux3);
		tableParcelas.setModel(new DefaultTableModel(parcelas,
				new String[] {
					"ID", tamano, precio, capacidad, "m^2", temporada, cercade, disponibilidad}));
	}
}
