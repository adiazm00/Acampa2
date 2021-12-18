package presentacion;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.JSONObject;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;

import dominio.GestorUsuario;
import dominio.Usuario;

public class Acampa2 {

	private JFrame frmAcampa;
	private JLabel lblUser;
	private JTextField txtUser;
	private JLabel lblPasswd;
	private JButton btnEnter;
	private JLabel lblAcampa;
	private JLabel lblBackground;
	private JPasswordField txtPasswd;
	
	private Border bordeRojo = BorderFactory.createLineBorder(Color.RED);
	private Border bordeVerde = BorderFactory.createLineBorder(Color.GREEN);
	private Color colorBlanco = new Color (255,255,255);
	private Color colorResaltado = new Color (255,255,220);
	private ImageIcon imagTick = new ImageIcon(Acampa2.class.getResource("/presentacion/images/tick.png"));
	private ImageIcon imagCross = new ImageIcon(Acampa2.class.getResource("/presentacion/images/cross.png"));
	private JLabel lblCheck;
	private JLabel lblCheckpasswd;
	
	private JMenuBar menuBar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JMenu mnIdioma;
	private JMenu mEdicion;
	private JMenu mAyuda;
	private JMenu mTamanoFuente;
	private JRadioButtonMenuItem miPequena;
	private JRadioButtonMenuItem miNormal;
	private JRadioButtonMenuItem miGrande;
	private JMenuItem mntmEspanol;
	private JMenuItem mntmIngles;
	private JMenu mnFondo;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButtonMenuItem rdbtnmntmNormal;
	private JRadioButtonMenuItem rdbtnmntmOscuro;
	
	private Usuario u;
	private JSONObject ficheroJSON;
	private String[] usuarioComprobado = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acampa2 window = new Acampa2();
					window.frmAcampa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Acampa2() {
		ficheroJSON = GestorUsuario.leerUsuarios();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAcampa = new JFrame();
		frmAcampa.setResizable(false);
		frmAcampa.setIconImage(Toolkit.getDefaultToolkit().getImage(Acampa2.class.getResource("/presentacion/images/logo_150.png")));
		frmAcampa.setTitle(Messages.getString("Acampa2.frmAcampa.title")); //$NON-NLS-1$
		frmAcampa.setBounds(100, 100, 601, 460);
		frmAcampa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAcampa.getContentPane().setLayout(null);
		
		lblUser = new JLabel(Messages.getString("Acampa2.lblUser.text")); //$NON-NLS-1$
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUser.setBounds(38, 90, 72, 23);
		frmAcampa.getContentPane().add(lblUser);
		
		txtUser = new JTextField();
		txtUser.addKeyListener(new TxtUserKeyListener());
		txtUser.setBounds(37, 115, 139, 23);
		frmAcampa.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		lblPasswd = new JLabel(Messages.getString("Acampa2.lblPasswd.text")); //$NON-NLS-1$
		lblPasswd.setEnabled(false);
		lblPasswd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPasswd.setBounds(38, 145, 93, 23);
		frmAcampa.getContentPane().add(lblPasswd);
		
		btnEnter = new JButton(Messages.getString("Acampa2.btnEnter.text")); //$NON-NLS-1$
		btnEnter.addKeyListener(new BtnEnterKeyListener());
		btnEnter.addActionListener(new BtnEnterActionListener());
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnter.setEnabled(false);
		btnEnter.setBounds(99, 212, 77, 33);
		frmAcampa.getContentPane().add(btnEnter);
		
		lblAcampa = new JLabel(Messages.getString("Acampa2.lblAcampa.text")); //$NON-NLS-1$
		lblAcampa.setBounds(26, 331, 177, 91);
		lblAcampa.setIcon(new ImageIcon(Acampa2.class.getResource("/presentacion/images/logo_150.png")));
		frmAcampa.getContentPane().add(lblAcampa);
		
		lblBackground = new JLabel(Messages.getString("Acampa2.lblBackground.text")); //$NON-NLS-1$
		lblBackground.setBounds(294, 181, 336, 267);
		lblBackground.setIcon(new ImageIcon(Acampa2.class.getResource("/presentacion/images/acampada_300.png")));
		frmAcampa.getContentPane().add(lblBackground);
		
		txtPasswd = new JPasswordField();
		txtPasswd.setEnabled(false);
		txtPasswd.setBounds(39, 170, 137, 23);
		frmAcampa.getContentPane().add(txtPasswd);
		
		lblCheck = new JLabel("");
		lblCheck.setBounds(187, 120, 45, 13);
		frmAcampa.getContentPane().add(lblCheck);
		
		lblCheckpasswd = new JLabel("");
		lblCheckpasswd.setBounds(187, 175, 45, 13);
		frmAcampa.getContentPane().add(lblCheckpasswd);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 597, 22);
		frmAcampa.getContentPane().add(menuBar);
		
		mEdicion = new JMenu(Messages.getString("Acampa2.mEdicion.text")); //$NON-NLS-1$
		mEdicion.setMnemonic('E');
		menuBar.add(mEdicion);
		
		mTamanoFuente = new JMenu(Messages.getString("Acampa2.mTamanoFuente.text")); //$NON-NLS-1$
		mEdicion.add(mTamanoFuente);
		
		miPequena = new JRadioButtonMenuItem(Messages.getString("Acampa2.miPequena.text")); //$NON-NLS-1$
		miPequena.addActionListener(new MiPequenaActionListener());
		buttonGroup.add(miPequena);
		mTamanoFuente.add(miPequena);
		
		miNormal = new JRadioButtonMenuItem(Messages.getString("Acampa2.miNormal.text")); //$NON-NLS-1$
		miNormal.addActionListener(new MiNormalActionListener());
		buttonGroup.add(miNormal);
		mTamanoFuente.add(miNormal);
		
		miGrande = new JRadioButtonMenuItem(Messages.getString("Acampa2.miGrande.text")); //$NON-NLS-1$
		miGrande.addActionListener(new MiGrandeActionListener());
		buttonGroup.add(miGrande);
		mTamanoFuente.add(miGrande);
		
		mnFondo = new JMenu(Messages.getString("Acampa2.mnFondo.text")); //$NON-NLS-1$
		mEdicion.add(mnFondo);
		
		rdbtnmntmNormal = new JRadioButtonMenuItem(Messages.getString("Acampa2.rdbtnmntmNormal.text")); //$NON-NLS-1$
		rdbtnmntmNormal.addActionListener(new RdbtnmntmNormalActionListener());
		buttonGroup_1.add(rdbtnmntmNormal);
		mnFondo.add(rdbtnmntmNormal);
		
		rdbtnmntmOscuro = new JRadioButtonMenuItem(Messages.getString("Acampa2.rdbtnmntmOscuro.text")); //$NON-NLS-1$
		rdbtnmntmOscuro.addActionListener(new RdbtnmntmOscuroActionListener());
		buttonGroup_1.add(rdbtnmntmOscuro);
		mnFondo.add(rdbtnmntmOscuro);
		
		mAyuda = new JMenu(Messages.getString("Acampa2.mAyuda.text")); //$NON-NLS-1$
		mAyuda.setMnemonic('Y');
		menuBar.add(mAyuda);
		
		mnIdioma = new JMenu(Messages.getString("Acampa2.mnIdioma.text")); //$NON-NLS-1$
		mAyuda.add(mnIdioma);
		
		mntmEspanol = new JMenuItem(Messages.getString("Acampa2.mntmEspanol.text")); //$NON-NLS-1$
		mntmEspanol.addActionListener(new MntmEspanolActionListener());
		mnIdioma.add(mntmEspanol);
		
		mntmIngles = new JMenuItem(Messages.getString("Acampa2.mntmIngles.text")); //$NON-NLS-1$
		mntmIngles.addActionListener(new MntmInglesActionListener());
		mnIdioma.add(mntmIngles);
		
		txtPasswd.addActionListener(new PasswdActionListener());
		txtUser.addFocusListener(new TxtUserFocusListener());
		txtPasswd.addFocusListener(new PasswdFocusListener());
		txtPasswd.addKeyListener(new PasswdKeyListener());
		
	}
		
	private class TxtUserFocusListener extends FocusAdapter{
		@Override
		public void focusGained(FocusEvent e) {
			txtUser.setBackground(colorResaltado);
		}
		@Override
		public void focusLost(FocusEvent e) {
			txtUser.setBackground(colorBlanco);
		}
	}
	
	private class TxtUserKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			usuarioComprobado = GestorUsuario.comprobarUsuario(txtUser.getText().toLowerCase(), ficheroJSON);
			if(usuarioComprobado == null) {
				txtUser.setBorder(bordeRojo);
				lblCheck.setIcon(imagCross);
				txtPasswd.setText(null);
				lblPasswd.setEnabled(false);
				txtPasswd.setEnabled(false);
				lblCheckpasswd.setIcon(null);
				txtPasswd.setBorder(null);
				btnEnter.setEnabled(false);
			} else {
				txtUser.setBorder(bordeVerde);
				lblCheck.setIcon(imagTick);
				lblPasswd.setEnabled(true);
				txtPasswd.setEnabled(true);
				txtPasswd.requestFocus();
			}
		}
	}

	private class PasswdActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		//Activamos los de la contrasena
		btnEnter.setEnabled(true);
		}
	}
	
	private class PasswdFocusListener extends FocusAdapter{
		@Override
		public void focusGained(FocusEvent e) {
			txtPasswd.setBackground(colorResaltado);
		}
		@Override
		public void focusLost(FocusEvent e) {
			txtPasswd.setBackground(colorBlanco);
		}
	}
	
	private class PasswdKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			String contrasenaRegistrada = usuarioComprobado[1];
			String password=String.valueOf(txtPasswd.getPassword());
				if (contrasenaRegistrada.equals(password)){
					txtPasswd.setBorder(bordeVerde);
					lblCheckpasswd.setIcon(imagTick);
					btnEnter.setEnabled(true);
					btnEnter.requestFocus();
				} 
				else {
					txtPasswd.setBorder(bordeRojo);
					lblCheckpasswd.setIcon(imagCross);
				}
		}
	}
		
	private class BtnEnterActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String nombre, correo, telefono, tipo, contrasena, ultConexion;
			nombre = usuarioComprobado[0];
			contrasena = usuarioComprobado[1];
			correo = usuarioComprobado[2];
			telefono = usuarioComprobado[3];
			tipo = usuarioComprobado[4];
			ultConexion = usuarioComprobado[5];
			u = new Usuario(nombre, correo, telefono, tipo, contrasena, ultConexion); 
			Menu menu= new Menu(u);
			menu.setVisible(true);
			frmAcampa.dispose();
		}
	}
	
	private class BtnEnterKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == '\n') {
				String nombre, correo, telefono, tipo, contrasena, ultConexion;
				nombre = usuarioComprobado[0];
				contrasena = usuarioComprobado[1];
				correo = usuarioComprobado[2];
				telefono = usuarioComprobado[3];
				tipo = usuarioComprobado[4];
				ultConexion = usuarioComprobado[5];
				u = new Usuario(nombre, correo, telefono, tipo, contrasena, ultConexion);
				Menu menu = new Menu(u);
				menu.setVisible(true);
				frmAcampa.dispose();
			}
		}
	}
	
	private class MiPequenaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			lblUser.setFont(new Font(lblUser.getFont().getFontName(), lblUser.getFont().getStyle(), 10));
			lblPasswd.setFont(new Font(lblPasswd.getFont().getFontName(), lblPasswd.getFont().getStyle(), 10));
			btnEnter.setFont(new Font(btnEnter.getFont().getFontName(), btnEnter.getFont().getStyle(), 10));
		}
	}
	private class MiNormalActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			lblUser.setFont(new Font(lblUser.getFont().getFontName(), lblUser.getFont().getStyle(), 13));
			lblPasswd.setFont(new Font(lblPasswd.getFont().getFontName(), lblPasswd.getFont().getStyle(), 13));
			btnEnter.setFont(new Font(btnEnter.getFont().getFontName(), btnEnter.getFont().getStyle(), 13));
		}
	}
	private class MiGrandeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			lblUser.setFont(new Font(lblUser.getFont().getFontName(), lblUser.getFont().getStyle(), 16));
			lblPasswd.setFont(new Font(lblPasswd.getFont().getFontName(), lblPasswd.getFont().getStyle(), 16));
			btnEnter.setFont(new Font(btnEnter.getFont().getFontName(), btnEnter.getFont().getStyle(), 16));
		}
	}
	private class MntmEspanolActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Messages.setIdioma("español");
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

			lblUser.setText(Messages.getString("Acampa2.lblUser.text"));
			lblPasswd.setText(Messages.getString("Acampa2.lblPasswd.text"));
			btnEnter.setText(Messages.getString("Acampa2.btnEnter.text"));	
		}
	}

	private class RdbtnmntmNormalActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			frmAcampa.getContentPane().setBackground(null);
			lblUser.setForeground(null);
			lblPasswd.setForeground(null);
		}
	}
	private class RdbtnmntmOscuroActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			frmAcampa.getContentPane().setBackground(Color.darkGray);
			lblUser.setForeground(Color.white);
			lblPasswd.setForeground(Color.white);
		}
	}
	private class MntmInglesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				Messages.setIdioma("inglés");
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

				lblUser.setText(Messages.getString("Acampa2.lblUser.text"));
				lblPasswd.setText(Messages.getString("Acampa2.lblPasswd.text"));
				btnEnter.setText(Messages.getString("Acampa2.btnEnter.text"));	
		}
	}
	
	public void setVisible(boolean b) {
		if (b==true)
			frmAcampa.setVisible(true);
		else
			frmAcampa.setVisible(false);
	}
}