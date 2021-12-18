package presentacion;

import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import dominio.GestorPromociones;

public class MostrarPromociones extends JPanel {
	public JPanel panelMostrar;
	private JTable tablePromociones;
	
	
	private JSONObject JSONPromociones;
	private Object[][] promociones;

	/**
	 * Create the panel.
	 */
	public MostrarPromociones() {
		setLayout(new CardLayout(0, 0));
		JSONPromociones=GestorPromociones.leerPromociones();
		promociones= GestorPromociones.devolverPromociones(JSONPromociones);
		panelMostrar = new JPanel();
		add(panelMostrar, "name_12333568465900");
		panelMostrar.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 67, 560, 144);
		panelMostrar.add(scrollPane);
		
		tablePromociones = new JTable();
		tablePromociones.setModel(new DefaultTableModel(
				promociones,
				new String[] {
					"ID","Titulo", "Descripcion"
				}
			));
		scrollPane.setViewportView(tablePromociones);

	}
	public void actualizarTabla() {
		tablePromociones.removeAll();
		JSONPromociones=GestorPromociones.leerPromociones();
		promociones= GestorPromociones.devolverPromociones(JSONPromociones);
		tablePromociones.setModel(new DefaultTableModel(
				promociones,
				new String[] {
					"ID","Titulo", "Descripcion"
				}
			));
	}
}
