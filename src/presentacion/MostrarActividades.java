package presentacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import dominio.GestorActividades;
import dominio.GestorPersonal;

import java.awt.Font;

public class MostrarActividades extends JPanel {
	private JTable tableActividades;

	private JSONObject JSONActividades;
	private Object[][] actividades;
	private String actividad;
	private String horario;
	private String edad;
	private String ehora;
	private String materiales;
	/**
	 * Create the panel.
	 */
	public MostrarActividades() {
		JSONActividades = GestorActividades.leerActividades();
		JSONObject monitores = GestorPersonal.leerJSON("Monitores");
		actividades = GestorActividades.devolverActividades(JSONActividades, monitores);
		setLayout(null);
		
		JScrollPane scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(30, 50, 615, 240);
		add(scrollPaneTabla);
		
		tableActividades = new JTable();
		tableActividades.setFont(new Font("Tahoma", Font.PLAIN, 10));
		actividad=Messages.getString("Mostrar.Actividad.text");
		horario=Messages.getString("Mostrar.Horario.text");
		edad=Messages.getString("Mostrar.Edad.text");
		ehora=Messages.getString("Mostrar.EHora.text");
		materiales=Messages.getString("Mostrar.Materiales.text");
		tableActividades.setModel(new DefaultTableModel(
			actividades,
			new String[] {
				"ID", actividad, "Monitor", horario, "Min.", "Max.", edad, ehora, materiales
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableActividades.getColumnModel().getColumn(0).setResizable(false);
		tableActividades.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableActividades.getColumnModel().getColumn(1).setResizable(false);
		tableActividades.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableActividades.getColumnModel().getColumn(2).setResizable(false);
		tableActividades.getColumnModel().getColumn(2).setPreferredWidth(60);
		tableActividades.getColumnModel().getColumn(3).setResizable(false);
		tableActividades.getColumnModel().getColumn(3).setPreferredWidth(115);
		tableActividades.getColumnModel().getColumn(4).setResizable(false);
		tableActividades.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableActividades.getColumnModel().getColumn(5).setResizable(false);
		tableActividades.getColumnModel().getColumn(5).setPreferredWidth(35);
		tableActividades.getColumnModel().getColumn(6).setResizable(false);
		tableActividades.getColumnModel().getColumn(6).setPreferredWidth(85);
		tableActividades.getColumnModel().getColumn(7).setResizable(false);
		tableActividades.getColumnModel().getColumn(7).setPreferredWidth(45);
		tableActividades.getColumnModel().getColumn(8).setResizable(false);
		tableActividades.getColumnModel().getColumn(8).setPreferredWidth(85);
		scrollPaneTabla.setViewportView(tableActividades);
	}
	public void toES() {
		actividad=Messages.getString("Mostrar.Actividad.text");
		horario=Messages.getString("Mostrar.Horario.text");
		edad=Messages.getString("Mostrar.Edad.text");
		ehora=Messages.getString("Mostrar.EHora.text");
		materiales=Messages.getString("Mostrar.Materiales.text");
		tableActividades.setModel(new DefaultTableModel(actividades,new String[] {"ID", actividad, "Monitor", horario, "Min.", "Max.", edad, ehora, materiales}));
	}
	
	public void toEN() {
		actividad=Messages.getString("Mostrar.Actividad.text");
		horario=Messages.getString("Mostrar.Horario.text");
		edad=Messages.getString("Mostrar.Edad.text");
		ehora=Messages.getString("Mostrar.EHora.text");
		materiales=Messages.getString("Mostrar.Materiales.text");
		tableActividades.setModel(new DefaultTableModel(actividades,new String[] {"ID", actividad, "Monitor", horario, "Min.", "Max.", edad, ehora, materiales}));
	}
	public void actualizarTabla() {
		tableActividades.removeAll();
		JSONActividades = GestorActividades.leerActividades();
		JSONObject monitores = GestorPersonal.leerJSON("Monitores");
		actividades = GestorActividades.devolverActividades(JSONActividades, monitores);
		tableActividades.setModel(new DefaultTableModel(
				actividades,
				new String[] {
					"ID", actividad, "Monitor", horario, "Min.", "Max.", edad, ehora, materiales}));
	}
}
