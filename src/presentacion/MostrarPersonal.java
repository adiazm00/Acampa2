package presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import dominio.GestorPersonal;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.io.IOException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MostrarPersonal extends JPanel {
	private JTable tableGuias;
	private JSONObject JSONGuias;
	private Object[][] guias;
	
	private String nombre;
	private String apellidos;
	private String tlf;
	private String correo;
	private String idioma;
	private String form;
	private String alta;
	private JSONObject JSONMonitores;
	private Object[][] monitores;
	
	private JComboBox comboBox;
	private JLabel lblFotos;
	private JLabel lblFoto;
	
	/**
	 * Create the panel.
	 */
	public MostrarPersonal() {
		JSONGuias = GestorPersonal.leerJSON("Guias");
		guias = GestorPersonal.devolverObject("Guias",JSONGuias);
		setLayout(null);
		
		JScrollPane scrollPaneTablaGui = new JScrollPane();
		scrollPaneTablaGui.setBounds(21, 90, 620, 225);
		add(scrollPaneTablaGui);
		
		tableGuias = new JTable();
		tableGuias.addMouseListener(new TableGuiasMouseListener());
		tableGuias.setFont(new Font("Tahoma", Font.PLAIN, 10));
		nombre=Messages.getString("Mostrar.nombre.text");
		apellidos=Messages.getString("Mostrar.apellidos.text");
		tlf=Messages.getString("Mostrar.tlf.text");
		correo=Messages.getString("Mostrar.correo.text");
		idioma=Messages.getString("Mostrar.idioma.text");
		form=Messages.getString("Mostrar.form.text");
		alta=Messages.getString("Mostrar.alta.text");
		tableGuias.setModel(new DefaultTableModel(
			guias,
			new String[] {
				"DNI", nombre, apellidos, tlf, correo, idioma, form, alta
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneTablaGui.setViewportView(tableGuias);
		
		JSONMonitores = GestorPersonal.leerJSON("Monitores");
		monitores = GestorPersonal.devolverObject("Monitores",JSONMonitores);
		setLayout(null);
		nombre=Messages.getString("Mostrar.nombre.text");
		apellidos=Messages.getString("Mostrar.apellidos.text");
		tlf=Messages.getString("Mostrar.tlf.text");
		correo=Messages.getString("Mostrar.correo.text");
		idioma=Messages.getString("Mostrar.idioma.text");
		form=Messages.getString("Mostrar.form.text");
		alta=Messages.getString("Mostrar.alta.text");

		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ComboBoxItemListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {Messages.getString("ModificarPersonal.Guias.text"), Messages.getString("ModificarPersonal.Monitores.text")}));
		comboBox.setBounds(282, 53, 105, 26);
		add(comboBox);
		
		lblFotos = new JLabel();
		lblFotos.setBounds(287, 337, 100, 100);
		add(lblFotos);
		
		lblFoto = new JLabel("Foto:");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(282, 320, 105, 14);
		lblFoto.setVisible(false);
		add(lblFoto);
	}
	
	public void toES() {
		nombre=Messages.getString("Mostrar.nombre.text");
		apellidos=Messages.getString("Mostrar.apellidos.text");
		tlf=Messages.getString("Mostrar.tlf.text");
		correo=Messages.getString("Mostrar.correo.text");
		idioma=Messages.getString("Mostrar.idioma.text");
		form=Messages.getString("Mostrar.form.text");
		alta=Messages.getString("Mostrar.alta.text");
		tableGuias.setModel(new DefaultTableModel(guias,new String[] {"DNI", nombre, apellidos, tlf, correo, idioma, form, alta}));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {Messages.getString("ModificarPersonal.Guias.text"), Messages.getString("ModificarPersonal.Monitores.text")}));
	}
	public void toEN() {
		nombre=Messages.getString("Mostrar.nombre.text");
		apellidos=Messages.getString("Mostrar.apellidos.text");
		tlf=Messages.getString("Mostrar.tlf.text");
		correo=Messages.getString("Mostrar.correo.text");
		idioma=Messages.getString("Mostrar.idioma.text");
		form=Messages.getString("Mostrar.form.text");
		alta=Messages.getString("Mostrar.alta.text");
		tableGuias.setModel(new DefaultTableModel(guias,new String[] {"DNI", nombre, apellidos, tlf, correo, idioma, form, alta}));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {Messages.getString("ModificarPersonal.Guias.text"), Messages.getString("ModificarPersonal.Monitores.text")}));
	}
	
	
	private class ComboBoxItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(comboBox.getSelectedItem().toString().equals(Messages.getString("ModificarPersonal.Guias.text"))) {
				tableGuias.removeAll();
				JSONGuias = GestorPersonal.leerJSON("Guias");
				guias = GestorPersonal.devolverObject("Guias",JSONGuias);
				tableGuias.setModel(new DefaultTableModel(guias,new String[] {"DNI", nombre, apellidos, tlf, correo, idioma, form, alta}));
				lblFotos.setVisible(false);
				lblFoto.setVisible(false);
			}
			else if (comboBox.getSelectedItem().toString().equals(Messages.getString("ModificarPersonal.Monitores.text"))){
				tableGuias.removeAll();
				JSONMonitores = GestorPersonal.leerJSON("Monitores");
				monitores = GestorPersonal.devolverObject("Monitores",JSONMonitores);
				tableGuias.setModel(new DefaultTableModel(monitores,new String[] {"DNI", nombre, apellidos, tlf, correo, idioma, form, alta}));
				lblFotos.setVisible(false);
				lblFoto.setVisible(false);
			}
		}
	}

	private class TableGuiasMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (tableGuias.getSelectedRow() > -1) {
				if (comboBox.getSelectedItem() != null) {
					String aux = "";
					if (comboBox.getSelectedItem().toString().equals("Monitores"))
						aux = "Monitores";
					else
						aux = "Guias";
					try {
						Image imagenOriginal = ImageIO.read(
								ReservarBungalows.class.getResource(GestorPersonal.devolverAspectoElegido(aux,
										tableGuias.getValueAt(tableGuias.getSelectedRow(), 0).toString(), "foto")));
						Image imagenEscalada = imagenOriginal.getScaledInstance(lblFotos.getWidth(),
								lblFotos.getHeight(), java.awt.Image.SCALE_SMOOTH);
						ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
						lblFotos.setIcon(iconoLabel);
						lblFotos.setVisible(true);
						lblFoto.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	public void actualizarTabla() {
		if(comboBox.getSelectedItem().toString().equals(Messages.getString("ModificarPersonal.Guias.text"))) {
			tableGuias.removeAll();
			JSONGuias = GestorPersonal.leerJSON("Guias");
			guias = GestorPersonal.devolverObject("Guias",JSONGuias);
			tableGuias.setModel(new DefaultTableModel(guias,new String[] {"DNI", nombre, apellidos, tlf, correo, idioma, form, alta}));
		}
		else if (comboBox.getSelectedItem().toString().equals(Messages.getString("ModificarPersonal.Monitores.text"))){
			tableGuias.removeAll();
			JSONMonitores = GestorPersonal.leerJSON("Monitores");
			monitores = GestorPersonal.devolverObject("Monitores",JSONMonitores);
			tableGuias.setModel(new DefaultTableModel(monitores,new String[] {"DNI", nombre, apellidos, tlf, correo, idioma, form, alta}));
		}
	}
}
