package presentacion;

import java.awt.Font;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.json.JSONObject;

import dominio.GestorUsuario;
import dominio.Usuario;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Menu {

	private JFrame frmAcampa;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenu mEdicion;
	private JMenu mAyuda;
	private JMenuItem mntmNombreUsuario;
	private JMenuItem mntmCorreo;
	private JMenuItem mntmTelefono;
	private JMenuItem mntmUltConex;
	private Usuario u;
	private JMenu mnDetalles;
	private JMenuItem mntmCerrarSesion;
	private JMenu mTamanoFuente;
	private JMenu mnFondo;
	private JRadioButtonMenuItem miPequena;
	private JRadioButtonMenuItem miNormal;
	private JRadioButtonMenuItem miGrande;
	private JRadioButtonMenuItem rdbtnmntmNormal;
	private JRadioButtonMenuItem rdbtnmntmOscuro;
	private JMenu mnIdioma;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JButton btnSalir;
	private JPanel panelCard;
	private JSONObject JSONUsuarios;

	private DefaultMutableTreeNode ReservarParcelas;
	private DefaultMutableTreeNode ReservarBungalows;
	private DefaultMutableTreeNode MostrarRutas;
	private DefaultMutableTreeNode CrearRutas;
	private DefaultMutableTreeNode MostrarPersonal;
	private DefaultMutableTreeNode MostrarActividades;
	private DefaultMutableTreeNode MostrarPersonasInscritas;
	private DefaultMutableTreeNode MostrarHistorial;
	private DefaultMutableTreeNode MostrarPromociones;
	private DefaultMutableTreeNode Modificaciones;
	private JScrollPane scrollPane;
	private JTree tree;

	ReservarParcelas reservarParcelas = new ReservarParcelas();
	ReservarBungalows reservarBungalows = new ReservarBungalows();
	MostrarRutas mostrarRutas = new MostrarRutas();
	CrearRutas crearRutas = new CrearRutas(mostrarRutas);
	MostrarPersonal mostrarPersonal = new MostrarPersonal();
	MostrarActividades mostrarActividades = new MostrarActividades();
	MostrarPersonasInscritas mostrarPersonasInscritas = new MostrarPersonasInscritas();
	MostrarPromociones mostrarPromociones = new MostrarPromociones();
	Modificaciones modificaciones = new Modificaciones(reservarParcelas, reservarBungalows, mostrarRutas, mostrarPersonal, mostrarActividades, mostrarPromociones);
	MostrarHistorial mostrarHistorial;
	private JPanel bienvenida;
	private JLabel lblLogo;
	private JLabel lblCamping;
	private JLabel lblTitulo;
	private JLabel lblInfo;
	private JPanel panelAbajo;

	/**
	 * Create the application.
	 */
	public Menu(Usuario usuario) {
		u = usuario;
		mostrarHistorial = new MostrarHistorial(u);
		JSONUsuarios = GestorUsuario.leerUsuarios();
		try {
			GestorUsuario.modificarUsuario(JSONUsuarios, u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAcampa = new JFrame();
		frmAcampa.setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/presentacion/images/logo_150.png")));
		frmAcampa.setResizable(false);
		frmAcampa.setTitle("Acampa-2. Menu");
		frmAcampa.setBounds(100, 100, 900, 600);
		frmAcampa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{199, 0, 0};
		gridBagLayout.rowHeights = new int[]{29, 484, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmAcampa.getContentPane().setLayout(gridBagLayout);
		
		JPanel panelArriba = new JPanel();
		panelArriba.setLayout(null);
		GridBagConstraints gbc_panelArriba = new GridBagConstraints();
		gbc_panelArriba.gridwidth = 2;
		gbc_panelArriba.insets = new Insets(0, 0, 5, 0);
		gbc_panelArriba.fill = GridBagConstraints.BOTH;
		gbc_panelArriba.gridx = 0;
		gbc_panelArriba.gridy = 0;
		frmAcampa.getContentPane().add(panelArriba, gbc_panelArriba);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 896, 22);
		panelArriba.add(menuBar);
		
		mnUsuario = new JMenu("Usuario");
		mnUsuario.setMnemonic('U');
		menuBar.add(mnUsuario);
		
		mnDetalles = new JMenu("Detalles");
		mnUsuario.add(mnDetalles);
		
		mntmNombreUsuario = new JMenuItem("Nombre: " + u.getNombre()); //$NON-NLS-1$
		mnDetalles.add(mntmNombreUsuario);
		
		mntmCorreo = new JMenuItem("Correo electronico: " + u.getCorreo()); //$NON-NLS-1$
		mnDetalles.add(mntmCorreo);
		
		mntmTelefono = new JMenuItem("Telefono: " + u.getTelefono()); //$NON-NLS-1$
		mnDetalles.add(mntmTelefono);
		
		mntmUltConex = new JMenuItem("Ultima conexion: " + u.getUltConexion()); //$NON-NLS-1$
		mnDetalles.add(mntmUltConex);
		
		mntmCerrarSesion = new JMenuItem("Cerrar sesi\u00F3n");
		mntmCerrarSesion.addActionListener(new MntmCerrarSesionActionListener());
		mnUsuario.add(mntmCerrarSesion);
		
		mEdicion = new JMenu("Accesibilidad");
		mEdicion.setMnemonic('E');
		menuBar.add(mEdicion);
		
		mTamanoFuente = new JMenu("Tamano fuente");
		mEdicion.add(mTamanoFuente);
		
		miPequena = new JRadioButtonMenuItem("Peque\u00F1a");
		miPequena.addActionListener(new MiPequenaActionListener());
		buttonGroup.add(miPequena);
		mTamanoFuente.add(miPequena);
		
		miNormal = new JRadioButtonMenuItem("Normal");
		miNormal.addActionListener(new MiNormalActionListener());
		buttonGroup.add(miNormal);
		mTamanoFuente.add(miNormal);
		
		miGrande = new JRadioButtonMenuItem("Grande");
		miGrande.addActionListener(new MiGrandeActionListener());
		buttonGroup.add(miGrande);
		mTamanoFuente.add(miGrande);
		
		mnFondo = new JMenu("Color del fondo");
		mEdicion.add(mnFondo);
		
		rdbtnmntmNormal = new JRadioButtonMenuItem("Normal");
		rdbtnmntmNormal.addActionListener(new RdbtnmntmNormalActionListener());
		buttonGroup_1.add(rdbtnmntmNormal);
		mnFondo.add(rdbtnmntmNormal);
		
		rdbtnmntmOscuro = new JRadioButtonMenuItem("Modo oscuro");
		rdbtnmntmOscuro.addActionListener(new RdbtnmntmOscuroActionListener());
		buttonGroup_1.add(rdbtnmntmOscuro);
		mnFondo.add(rdbtnmntmOscuro);
		
		mAyuda = new JMenu("Ayuda");
		mAyuda.setMnemonic('Y');
		menuBar.add(mAyuda);
		
		mnIdioma = new JMenu("Idioma");
		mAyuda.add(mnIdioma);
		
		JMenuItem mntmEspanol = new JMenuItem("Espa\u00F1ol");
		mntmEspanol.addActionListener(new MntmEspanolActionListener());
		mnIdioma.add(mntmEspanol);
		
		JMenuItem mntmIngles = new JMenuItem("English");
		mntmIngles.addActionListener(new MntmInglesActionListener());
		mnIdioma.add(mntmIngles);
		
		bienvenida = new JPanel();
		bienvenida.setLayout(null);
		
		panelCard = new JPanel();
		GridBagConstraints gbc_panelCard = new GridBagConstraints();
		gbc_panelCard.insets = new Insets(0, 0, 5, 0);
		gbc_panelCard.fill = GridBagConstraints.BOTH;
		gbc_panelCard.gridx = 1;
		gbc_panelCard.gridy = 1;
		frmAcampa.getContentPane().add(panelCard, gbc_panelCard);
		panelCard.setLayout(new CardLayout(0, 0));
	
		panelCard.add(bienvenida, "name_171072840372800");
		panelCard.add(reservarParcelas, "Reservar parcelas");
		panelCard.add(reservarBungalows, "Reservar bungalows");
		panelCard.add(mostrarRutas, "Mostrar rutas");
		panelCard.add(crearRutas, "Crear rutas");
		panelCard.add(mostrarPersonal, "Mostrar personal" );
		panelCard.add(mostrarActividades, "Mostrar actividades");
		panelCard.add(mostrarPersonasInscritas, "Mostrar inscritos");
		panelCard.add(mostrarHistorial, "Mostrar historial");
		panelCard.add(mostrarPromociones, "Mostrar promociones");
		panelCard.add(modificaciones, "Modificaciones");
		
		lblLogo = new JLabel("New label");
		lblLogo.setIcon(new ImageIcon(Menu.class.getResource("/presentacion/images/logo_150.png")));
		lblLogo.setBounds(10, 10, 151, 77);
		bienvenida.add(lblLogo);
		
		lblCamping = new JLabel("New label");
		lblCamping.setIcon(new ImageIcon(Menu.class.getResource("/presentacion/images/523-5236886_camping-png-clipart (1).png")));
		lblCamping.setBounds(331, 234, 472, 235);
		bienvenida.add(lblCamping);
		
		lblTitulo = new JLabel(Messages.getString("Menu.lblTitulo.text")); //$NON-NLS-1$
		lblTitulo.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		lblTitulo.setBounds(53, 134, 428, 41);
		bienvenida.add(lblTitulo);
		
		lblInfo = new JLabel(Messages.getString("Menu.lblInfo.text")); //$NON-NLS-1$
		lblInfo.setVerticalAlignment(SwingConstants.TOP);
		lblInfo.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblInfo.setBounds(53, 159, 318, 65);
		bienvenida.add(lblInfo);
		
		ReservarParcelas = new DefaultMutableTreeNode("Reservar parcelas");
		ReservarBungalows = new DefaultMutableTreeNode("Reservar bungalows");
		MostrarRutas = new DefaultMutableTreeNode("Mostrar rutas");
		CrearRutas = new DefaultMutableTreeNode("Crear rutas");
		MostrarPersonal = new DefaultMutableTreeNode("Mostrar personal");
		MostrarActividades = new DefaultMutableTreeNode("Mostrar actividades");
		MostrarPersonasInscritas = new DefaultMutableTreeNode("Mostrar inscritos");
		MostrarHistorial = new DefaultMutableTreeNode("Mostrar historial");
		MostrarPromociones = new DefaultMutableTreeNode("Mostrar promociones");
		Modificaciones = new DefaultMutableTreeNode("Modificaciones");
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		frmAcampa.getContentPane().add(scrollPane, gbc_scrollPane);
		
		tree = new JTree();
		tree.addTreeSelectionListener(new TreeTreeSelectionListener());
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Acampa-2") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("Reservar alojamientos");
							node_1.add(ReservarParcelas);
							node_1.add(ReservarBungalows);
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Rutas senderistas");
							node_1.add(MostrarRutas);
							node_1.add(CrearRutas);
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Mostrar personal ");
							node_1.add(MostrarPersonal);
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Actividades ");
							node_1.add(MostrarActividades);
							node_1.add(MostrarPersonasInscritas);
							node_1.add(MostrarHistorial);
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Promociones");
							node_1.add(MostrarPromociones);
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Modificaciones");
							node_1.add(Modificaciones);
						add(node_1);
					}
				}
			));
		scrollPane.setViewportView(tree);
		
		panelAbajo = new JPanel();
		panelAbajo.setLayout(null);
		GridBagConstraints gbc_panelAbajo = new GridBagConstraints();
		gbc_panelAbajo.fill = GridBagConstraints.BOTH;
		gbc_panelAbajo.gridx = 1;
		gbc_panelAbajo.gridy = 2;
		frmAcampa.getContentPane().add(panelAbajo, gbc_panelAbajo);
		
		btnSalir = new JButton(Messages.getString("Menu.btnSalir.text")); //$NON-NLS-1$
		btnSalir.addActionListener(new BtnSalirActionListener());
		btnSalir.setBounds(0, 3, 179, 39);
		panelAbajo.add(btnSalir);
	}
	private class MntmCerrarSesionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Acampa2 acampa2 = new Acampa2();
			acampa2.setVisible(true);
			frmAcampa.dispose();
		}
	}
	private class MntmEspanolActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Messages.setIdioma("español");
			lblTitulo.setText(Messages.getString("Menu.lblTitulo.text"));
			lblInfo.setText(Messages.getString("Menu.lblInfo.text"));
			btnSalir.setText(Messages.getString("Menu.btnSalir.text"));
			mnUsuario.setText(Messages.getString("Menu.mnUsuario.text"));
			mnDetalles.setText(Messages.getString("Menu.mnDetalles.text"));
			mntmCerrarSesion.setText(Messages.getString("Menu.mntmCerrarSesion.text"));
			mEdicion.setText(Messages.getString("Acampa2.mEdicion.text"));
			mTamanoFuente.setText(Messages.getString("Acampa2.mTamanoFuente.text"));
			miPequena.setText(Messages.getString("Acampa2.miPequena.text"));
			miNormal.setText(Messages.getString("Acampa2.miNormal.text"));
			miGrande.setText(Messages.getString("Acampa2.miGrande.text"));
			mnFondo.setText(Messages.getString("Acampa2.mnFondo.text"));
			rdbtnmntmNormal.setText(Messages.getString("Acampa2.rdbtnmntmNormal.text"));
			rdbtnmntmOscuro.setText(Messages.getString("Acampa2.rdbtnmntmOscuro.text"));
			mAyuda.setText(Messages.getString("Acampa2.mAyuda.text"));
			mnIdioma.setText(Messages.getString("Acampa2.mnIdioma.text"));
			
			crearRutas.toES();
			mostrarActividades.toES();
			mostrarHistorial.toES();
			mostrarPersonal.toES();
			mostrarPersonasInscritas.toES();
			mostrarRutas.toES();
			reservarParcelas.toES();
			reservarBungalows.toES();
			
		}
	}
	private class MntmInglesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Messages.setIdioma("inglés");
			lblTitulo.setText(Messages.getString("Menu.lblTitulo.text"));
			lblInfo.setText(Messages.getString("Menu.lblInfo.text"));
			btnSalir.setText(Messages.getString("Menu.btnSalir.text"));
			mnUsuario.setText(Messages.getString("Menu.mnUsuario.text"));
			mnDetalles.setText(Messages.getString("Menu.mnDetalles.text"));
			mntmCerrarSesion.setText(Messages.getString("Menu.mntmCerrarSesion.text"));
			mEdicion.setText(Messages.getString("Acampa2.mEdicion.text"));
			mTamanoFuente.setText(Messages.getString("Acampa2.mTamanoFuente.text"));
			miPequena.setText(Messages.getString("Acampa2.miPequena.text"));
			miNormal.setText(Messages.getString("Acampa2.miNormal.text"));
			miGrande.setText(Messages.getString("Acampa2.miGrande.text"));
			mnFondo.setText(Messages.getString("Acampa2.mnFondo.text"));
			rdbtnmntmNormal.setText(Messages.getString("Acampa2.rdbtnmntmNormal.text"));
			rdbtnmntmOscuro.setText(Messages.getString("Acampa2.rdbtnmntmOscuro.text"));
			mAyuda.setText(Messages.getString("Acampa2.mAyuda.text"));
			mnIdioma.setText(Messages.getString("Acampa2.mnIdioma.text"));
			
			crearRutas.toEN();
			mostrarActividades.toEN();
			mostrarHistorial.toEN();
			mostrarPersonal.toEN();
			mostrarPersonasInscritas.toEN();
			mostrarRutas.toEN();
			reservarParcelas.toEN();
			reservarBungalows.toEN();
		}
	}
	private class BtnSalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			frmAcampa.dispose();
		}
	}
	private class MiPequenaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnSalir.setFont(new Font(btnSalir.getFont().getFontName(), btnSalir.getFont().getStyle(), 10));
			lblTitulo.setFont(new Font(lblTitulo.getFont().getFontName(), lblTitulo.getFont().getStyle(), 12));
			lblInfo.setFont(new Font(lblInfo.getFont().getFontName(), lblInfo.getFont().getStyle(), 10));
			reservarParcelas.toSmall();
			reservarBungalows.toSmall();
			mostrarPersonasInscritas.toSmall();
			mostrarHistorial.toSmall();
			mostrarRutas.toSmall();
			crearRutas.toSmall();
			modificaciones.toSmall();
		}
	}
	private class MiNormalActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnSalir.setFont(new Font(btnSalir.getFont().getFontName(), btnSalir.getFont().getStyle(), 13));
			lblTitulo.setFont(new Font(lblTitulo.getFont().getFontName(), lblTitulo.getFont().getStyle(), 14));
			lblInfo.setFont(new Font(lblInfo.getFont().getFontName(), lblInfo.getFont().getStyle(), 12));
			reservarParcelas.toMedium();
			reservarBungalows.toMedium();
			mostrarPersonasInscritas.toMedium();
			mostrarHistorial.toMedium();
			mostrarRutas.toMedium();
			crearRutas.toMedium();
			modificaciones.toMedium();
		}
	}
	private class MiGrandeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnSalir.setFont(new Font(btnSalir.getFont().getFontName(), btnSalir.getFont().getStyle(), 16));
			lblTitulo.setFont(new Font(lblTitulo.getFont().getFontName(), lblTitulo.getFont().getStyle(), 18));
			lblInfo.setFont(new Font(lblInfo.getFont().getFontName(), lblInfo.getFont().getStyle(), 14));
			reservarParcelas.toBig();
			reservarBungalows.toBig();
			mostrarPersonasInscritas.toBig();
			mostrarHistorial.toBig();
			mostrarRutas.toBig();
			crearRutas.toBig();
			modificaciones.toBig();
		}
	}
	private class RdbtnmntmNormalActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			frmAcampa.getContentPane().setBackground(null);
			panelAbajo.setBackground(null);
			panelCard.setBackground(null);
			lblTitulo.setForeground(null);
			lblInfo.setForeground(null);
			crearRutas.setBackground(null);
			crearRutas.toNormal();
			reservarParcelas.toNormal();
			reservarBungalows.toNormal();
			mostrarRutas.toNormal();
			mostrarPersonasInscritas.toNormal();
			mostrarHistorial.toNormal();
			mostrarActividades.setBackground(null);
			mostrarPersonal.setBackground(null);
			mostrarPersonasInscritas.setBackground(null);
			mostrarPromociones.panelMostrar.setBackground(Color.darkGray);
			modificaciones.setBackground(null);
			modificaciones.toNormal();
		}
	}
	private class RdbtnmntmOscuroActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			frmAcampa.getContentPane().setBackground(Color.darkGray);
			panelAbajo.setBackground(Color.darkGray);
			bienvenida.setBackground(Color.darkGray);
			lblTitulo.setForeground(Color.white);
			lblInfo.setForeground(Color.white);
			reservarParcelas.toDark();
			reservarBungalows.toDark();
			crearRutas.setBackground(Color.darkGray);
			crearRutas.toDark();
			mostrarRutas.toDark();
			mostrarPersonasInscritas.toDark();
			mostrarHistorial.toDark();
			mostrarActividades.setBackground(Color.darkGray);
			mostrarPersonal.setBackground(Color.darkGray);
			mostrarPersonasInscritas.setBackground(Color.darkGray);
			mostrarPromociones.panelMostrar.setBackground(Color.darkGray);
			modificaciones.setBackground(Color.darkGray);
			modificaciones.toDark();
		}
	}
	private class TreeTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent arg0) {
			try {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				CardLayout cl = (CardLayout) (panelCard.getLayout());
				cl.show(panelCard, node.toString());
			} catch (NullPointerException ex) {}
		}
	}
	public void setVisible(boolean b) {
		if (b==true)
			frmAcampa.setVisible(true);
		else
			frmAcampa.setVisible(false);
	}
}
