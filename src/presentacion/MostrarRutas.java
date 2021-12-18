package presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import dominio.GestorRutas;
import dominio.Ruta;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MostrarRutas extends JPanel {
	private JTextField textIDRuta;
	private JTable tableRutas;

	private JSONObject JSONRutas;
	private Object[][] rutas;
	private JPanel panelMostrarRutas;
	private JPanel panelDetallesRuta;
	private JTextField textFieldIDRuta2;
	private JTextPane textPaneDetalles;
	private JLabel lblRutaEspecifica;
	private JLabel lblRuta;
	private JButton btnVolverRutas;
	private JButton btnDetalles;
	
	private String dia;
	private String hini;
	private String hfin;
	private String minp;
	private String maxp;
	private JLabel lblImagenMapa;
	/**
	 * Create the panel.
	 */
	public MostrarRutas() {
		JSONRutas = GestorRutas.leerRutas();
		rutas = GestorRutas.devolverRutas(JSONRutas);
		setLayout(new CardLayout(0, 0));
		
		panelMostrarRutas = new JPanel();
		add(panelMostrarRutas, "name_2370522191201100");
		panelMostrarRutas.setLayout(null);
		
		JScrollPane scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(30, 39, 600, 192);
		panelMostrarRutas.add(scrollPaneTabla);
		
		dia = Messages.getString("Mostrar.Dia.text");
		hini = Messages.getString("Mostrar.Hini.text");
		hfin = Messages.getString("Mostrar.Hfin.text");
		minp = Messages.getString("Mostrar.Minp.text");
		maxp = Messages.getString("Mostrar.Maxp.text");
		tableRutas = new JTable();
		tableRutas.addMouseListener(new TableRutasMouseListener());
		tableRutas.setModel(new DefaultTableModel(
				rutas,
				new String[] {
					"ID", dia, hini, hfin, "Monitor", minp, maxp
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tableRutas.getColumnModel().getColumn(0).setResizable(false);
			tableRutas.getColumnModel().getColumn(0).setPreferredWidth(10);
			tableRutas.getColumnModel().getColumn(1).setResizable(false);
			tableRutas.getColumnModel().getColumn(1).setPreferredWidth(40);
			tableRutas.getColumnModel().getColumn(2).setResizable(false);
			tableRutas.getColumnModel().getColumn(1).setPreferredWidth(50);
			tableRutas.getColumnModel().getColumn(3).setResizable(false);
			tableRutas.getColumnModel().getColumn(3).setPreferredWidth(40);
			tableRutas.getColumnModel().getColumn(4).setResizable(false);
			tableRutas.getColumnModel().getColumn(4).setPreferredWidth(40);
			tableRutas.getColumnModel().getColumn(5).setResizable(false);
			tableRutas.getColumnModel().getColumn(5).setPreferredWidth(55);
			tableRutas.getColumnModel().getColumn(6).setResizable(false);
			tableRutas.getColumnModel().getColumn(6).setPreferredWidth(55);
		scrollPaneTabla.setViewportView(tableRutas);
		
		lblRutaEspecifica = new JLabel(Messages.getString("MostrarRutas.lblRutaEspecifica.text_1")); //$NON-NLS-1$
		lblRutaEspecifica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRutaEspecifica.setBounds(10, 258, 373, 20);
		panelMostrarRutas.add(lblRutaEspecifica);
		
		textIDRuta = new JTextField();
		textIDRuta.setColumns(10);
		textIDRuta.setBounds(393, 259, 72, 19);
		panelMostrarRutas.add(textIDRuta);
		
		btnDetalles = new JButton(Messages.getString("MostrarRutas.btnDetalles.text_1")); //$NON-NLS-1$
		btnDetalles.addActionListener(new BtnDetallesActionListener());
		btnDetalles.setBounds(473, 257, 111, 23);
		panelMostrarRutas.add(btnDetalles);
		
		panelDetallesRuta = new JPanel();
		add(panelDetallesRuta, "name_2370528437319600");
		panelDetallesRuta.setLayout(null);
		
		textFieldIDRuta2 = new JTextField();
		textFieldIDRuta2.setText(Messages.getString("CACA")); //$NON-NLS-1$
		textFieldIDRuta2.setEditable(false);
		textFieldIDRuta2.setBounds(292, 14, 62, 19);
		panelDetallesRuta.add(textFieldIDRuta2);
		
		lblRuta = new JLabel(Messages.getString("MostrarRutas.lblRuta.text")); //$NON-NLS-1$
		lblRuta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRuta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRuta.setBounds(210, 11, 71, 25);
		panelDetallesRuta.add(lblRuta);
		
		textPaneDetalles = new JTextPane();
		textPaneDetalles.setText(Messages.getString("CACA2")); //$NON-NLS-1$
		textPaneDetalles.setBounds(95, 38, 406, 164);
		panelDetallesRuta.add(textPaneDetalles);
		
		btnVolverRutas = new JButton(Messages.getString("MostrarRutas.btnVolverRutas.text")); //$NON-NLS-1$
		btnVolverRutas.addActionListener(new BtnVolverRutasActionListener());
		btnVolverRutas.setBounds(35, 399, 176, 45);
		panelDetallesRuta.add(btnVolverRutas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 203, 289, 240);
		panelDetallesRuta.add(scrollPane);
		
		lblImagenMapa = new JLabel("");
		scrollPane.setViewportView(lblImagenMapa);

	}
	private class BtnDetallesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (!textIDRuta.getText().isEmpty()) {
				if (GestorRutas.isNumeric(textIDRuta.getText())) {
					Ruta ruta = GestorRutas.buscarRuta(JSONRutas, Integer.valueOf(textIDRuta.getText()));
					if (ruta != null) {
						panelMostrarRutas.setVisible(false);
						panelDetallesRuta.setVisible(true);
						textFieldIDRuta2.setText(String.valueOf(ruta.getId()));
						textPaneDetalles.setText(ruta.toString());
						textIDRuta.setText("");
						tableRutas.clearSelection();
						//lblImagenMapa.setIcon(new ImageIcon(MostrarRutas.class.getResource(ruta.getMapa())));
						String[] splitAux = ruta.getMapa().split("/");
						BufferedImage img = null;
						try {
							img = ImageIO.read(new File(System.getProperty("user.dir")+"\\IPO\\src\\presentacion\\images\\"+splitAux[3]));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ImageIcon icon = new ImageIcon(img);
						lblImagenMapa.setIcon(icon);
					} else
						JOptionPane.showMessageDialog(null, "El ID introducido no se ha encontrado.");
				} else 
					JOptionPane.showMessageDialog(null, "Debe introducir valor numerico.");
			} else
				JOptionPane.showMessageDialog(null, "Debe introducir un ID.");
		}
	}
	private class BtnVolverRutasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			panelMostrarRutas.setVisible(true);
			panelDetallesRuta.setVisible(false);
		}
	}
	private class TableRutasMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(tableRutas.getSelectedRow()>-1)
				textIDRuta.setText(tableRutas.getValueAt(tableRutas.getSelectedRow(), 0).toString());
		}
	}

	public void toNormal() {
		panelMostrarRutas.setBackground(null);
		panelDetallesRuta.setBackground(null);
		lblRutaEspecifica.setForeground(null);
		lblRuta.setForeground(null);
	}
	
	public void toDark() {
		panelMostrarRutas.setBackground(Color.darkGray);
		panelDetallesRuta.setBackground(Color.darkGray);
		lblRutaEspecifica.setForeground(Color.white);
		lblRuta.setForeground(Color.white);
	}
	
	public void toES() {
		lblRutaEspecifica.setText(Messages.getString("MostrarRutas.lblRutaEspecifica.text"));
		btnDetalles.setText(Messages.getString("MostrarRutas.btnDetalles.text"));
		lblRuta.setText(Messages.getString("MostrarRutas.lblRuta.text"));
		btnVolverRutas.setText(Messages.getString("MostrarRutas.btnVolverRutas.text"));
		dia = Messages.getString("Mostrar.Dia.text");
		hini = Messages.getString("Mostrar.Hini.text");
		hfin = Messages.getString("Mostrar.Hfin.text");
		minp = Messages.getString("Mostrar.Minp.text");
		maxp = Messages.getString("Mostrar.Maxp.text");
		tableRutas.setModel(new DefaultTableModel(rutas,new String[] {"ID", dia, hini, hfin, "Monitor", minp, maxp}));
	}
	
	public void toEN() {
		lblRutaEspecifica.setText(Messages.getString("MostrarRutas.lblRutaEspecifica.text"));
		btnDetalles.setText(Messages.getString("MostrarRutas.btnDetalles.text"));
		lblRuta.setText(Messages.getString("MostrarRutas.lblRuta.text"));
		btnVolverRutas.setText(Messages.getString("MostrarRutas.btnVolverRutas.text"));
		dia = Messages.getString("Mostrar.Dia.text");
		hini = Messages.getString("Mostrar.Hini.text");
		hfin = Messages.getString("Mostrar.Hfin.text");
		minp = Messages.getString("Mostrar.Minp.text");
		maxp = Messages.getString("Mostrar.Maxp.text");
		tableRutas.setModel(new DefaultTableModel(rutas,new String[] {"ID", dia, hini, hfin, "Monitor", minp, maxp}));
	}
	
	public void toSmall() {
		lblRutaEspecifica.setFont(new Font(lblRutaEspecifica.getFont().getFontName(), lblRutaEspecifica.getFont().getStyle(), 10));
		btnDetalles.setFont(new Font(btnDetalles.getFont().getFontName(), btnDetalles.getFont().getStyle(), 10));
		lblRuta.setFont(new Font(lblRuta.getFont().getFontName(), lblRuta.getFont().getStyle(), 10));
		btnVolverRutas.setFont(new Font(btnVolverRutas.getFont().getFontName(), btnVolverRutas.getFont().getStyle(), 10));
	}
	public void toMedium() {
		lblRutaEspecifica.setFont(new Font(lblRutaEspecifica.getFont().getFontName(), lblRutaEspecifica.getFont().getStyle(), 13));
		btnDetalles.setFont(new Font(btnDetalles.getFont().getFontName(), btnDetalles.getFont().getStyle(), 13));
		lblRuta.setFont(new Font(lblRuta.getFont().getFontName(), lblRuta.getFont().getStyle(), 13));
		btnVolverRutas.setFont(new Font(btnVolverRutas.getFont().getFontName(), btnVolverRutas.getFont().getStyle(), 13));
	}
	public void toBig() {
		lblRutaEspecifica.setFont(new Font(lblRutaEspecifica.getFont().getFontName(), lblRutaEspecifica.getFont().getStyle(), 16));
		btnDetalles.setFont(new Font(btnDetalles.getFont().getFontName(), btnDetalles.getFont().getStyle(), 16));
		lblRuta.setFont(new Font(lblRuta.getFont().getFontName(), lblRuta.getFont().getStyle(), 16));
		btnVolverRutas.setFont(new Font(btnVolverRutas.getFont().getFontName(), btnVolverRutas.getFont().getStyle(), 16));
	}
	public void actualizarTabla() {
		tableRutas.removeAll();
		tableRutas.setModel(new DefaultTableModel(
				GestorRutas.devolverRutas(GestorRutas.leerRutas()),
				new String[] {
					"ID", dia, hini, hfin, "Monitor", minp, maxp
				}));
		JSONRutas = GestorRutas.leerRutas();
	}
}
