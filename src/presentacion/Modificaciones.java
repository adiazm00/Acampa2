package presentacion;

import javax.swing.JPanel;

import javax.swing.JTextField;

import org.json.JSONObject;

import dominio.GestorActividades;
import dominio.GestorPersonal;
import dominio.GestorPromociones;
import dominio.GestorReservas;
import dominio.GestorRutas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Modificaciones extends JPanel {
	private static JTextField textFieldTextoModificado;
	private JLabel lblIdentificador;
	private static JComboBox comboBoxIdentificador;
	private static JComboBox comboBoxAspectoAModificar;
	private JButton btnModificar;
	private JLabel lblAspecto;
	private JLabel lblTextoModificado;
	private static JComboBox comboBoxModificar;

	private ReservarParcelas reservarParcelas;
	private ReservarBungalows reservarBungalows;
	private MostrarRutas mostrarRutas;
	private MostrarPersonal mostrarPersonal;
	private MostrarActividades mostrarActividades;
	private MostrarPromociones mostrarPromociones;
	
	private JSONObject JSONParcelas;
	private JSONObject JSONBungalows;
	private JSONObject JSONMonitores;
	private JSONObject JSONGuias;
	private JSONObject JSONActividades;
	private JSONObject JSONPromociones;
	private JSONObject JSONRutas;
	
	
	String[] aspectosParcelas;
	String[] aspectosBungalows;
	String[] aspectosGuiasMonitores = {"Nombre", "Apellidos", "Formacion", "Restricciones", "Alta", "Correo", "Idioma", "Telefono"};
	String[] aspectosActividades = {"Minimos participantes","Maximos participantes","Edad recomendada","Precio/Hora","Monitor","Descripcion actividad","Horario","Materiales necesarios"};
	String[] aspectosPromociones = {"titulo", "descripcion", "alta"};
	String[] aspectosRutas;
	private JTextArea textArea;
	private JLabel lblModificar;
	/**
	 * Create the panel.
	 */
	public Modificaciones(ReservarParcelas rP, ReservarBungalows rB,
			MostrarRutas mR, MostrarPersonal mP, MostrarActividades mA,
			MostrarPromociones mPr) {
		JSONParcelas = GestorReservas.leerParcelas();
		JSONBungalows= GestorReservas.leerBungalows();
		JSONMonitores = GestorPersonal.leerJSON("Monitores");
		JSONGuias = GestorPersonal.leerJSON("Guias");
		JSONActividades = GestorActividades.leerActividades();
		JSONPromociones = GestorPromociones.leerPromociones();
		JSONRutas = GestorRutas.leerRutas();
		reservarParcelas = rP;reservarBungalows=rB;mostrarRutas = mR;
		mostrarPersonal=mP;mostrarActividades=mA;mostrarPromociones=mPr;
		setLayout(null);

		aspectosParcelas = GestorReservas.devolverAspectos("Parcelas");
		aspectosBungalows = GestorReservas.devolverAspectos("Bungalows");
		aspectosRutas = GestorRutas.devolverAspectos();
		
		lblModificar = new JLabel(Messages.getString("Modificaciones.lblModificar.text")); //$NON-NLS-1$
		lblModificar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModificar.setBounds(57, 50, 335, 14);
		add(lblModificar);
		
		comboBoxModificar = new JComboBox();
		comboBoxModificar.addActionListener(new ComboBoxModificarActionListener());
		comboBoxModificar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxModificar.setModel(new DefaultComboBoxModel(new String[] {"Parcelas", "Bungalows", "Rutas", "Guias", "Monitores", "Actividades", "Promociones"}));
		comboBoxModificar.setBounds(57, 67, 111, 22);
		add(comboBoxModificar);
		
		lblIdentificador = new JLabel(Messages.getString("Modificaciones.lblIdentificador.text")); //$NON-NLS-1$
		lblIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIdentificador.setBounds(57, 103, 329, 20);
		add(lblIdentificador);
		
		comboBoxIdentificador = new JComboBox(GestorReservas.devolverID("Parcelas"));
		comboBoxIdentificador.addActionListener(new ComboBoxIdentificadorActionListener());
		comboBoxIdentificador.setBounds(57, 125, 142, 26);
		add(comboBoxIdentificador);
		
		lblAspecto = new JLabel(Messages.getString("Modificaciones.lblAspecto.text")); //$NON-NLS-1$
		lblAspecto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAspecto.setBounds(57, 241, 473, 20);
		add(lblAspecto);
		
		comboBoxAspectoAModificar = new JComboBox(aspectosParcelas);
		comboBoxAspectoAModificar.addActionListener(new ComboBoxAspectoAModificarActionListener());
		comboBoxAspectoAModificar.setBounds(57, 272, 278, 27);
		add(comboBoxAspectoAModificar);
		
		lblTextoModificado = new JLabel(Messages.getString("Modificaciones.lblTextoModificado.text")); //$NON-NLS-1$
		lblTextoModificado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTextoModificado.setBounds(57, 310, 453, 20);
		add(lblTextoModificado);
		
		textFieldTextoModificado = new JTextField(GestorReservas.devolverAspectoElegido("Parcelas",
				comboBoxIdentificador.getSelectedItem().toString(),
				comboBoxAspectoAModificar.getSelectedItem().toString()));
		textFieldTextoModificado.setColumns(10);
		textFieldTextoModificado.setBounds(57, 341, 473, 26);
		add(textFieldTextoModificado);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new BtnModificarActionListener());
		btnModificar.setBounds(475, 378, 119, 46);
		add(btnModificar);
		
		textArea = new JTextArea(buscarCaracteristicaModificacion());
		textArea.setEditable(false);
		textArea.setBounds(233, 125, 336, 105);
		add(textArea);

	}
	
	private class BtnModificarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String elegido = comboBoxModificar.getSelectedItem().toString();
			if (comboBoxModificar.getSelectedItem() != null && comboBoxIdentificador.getSelectedItem() != null
					&& comboBoxAspectoAModificar.getSelectedItem() != null) {
				if (!textFieldTextoModificado.getText().isEmpty()) {
					if (elegido.equals("Parcelas") || elegido.equals("Bungalows")) {
						String aspecto = comboBoxAspectoAModificar.getSelectedItem().toString();
						String textoModificacion = textFieldTextoModificado.getText().toString();
						String idParcela = comboBoxIdentificador.getSelectedItem().toString();
						if ((aspecto.equals("m2") || aspecto.equals("capacidad_maxima")
								|| aspecto.equals("coste_dia"))) {
							if (GestorReservas.isNumeric(textoModificacion)) {
								try {
									GestorReservas.modificarPyB(elegido, textoModificacion, aspecto, idParcela);
									JOptionPane.showMessageDialog(null,
											"La modificacion se ha realizado correctamente.");
									reservarParcelas.actualizarTabla();
									reservarBungalows.actualizarTabla();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"El aspecto modificado debe ser un valor numerico.");
							}
						} else {
							if (aspecto.equals("disponibilidad")) {
								if (!textoModificacion.equals("libre") && !textoModificacion.equals("ocupado")
										&& !textoModificacion.equals("ocupada")&& !textoModificacion.equals("en reparacion")
										&& !textoModificacion.equals("en limpieza")) {
									JOptionPane.showMessageDialog(null,
											"La disponibilidad debe ser 'disponible', 'ocupada/o', 'en reparacion' o 'en limpieza'.");
								} else {
									try {
										GestorReservas.modificarPyB(elegido, textoModificacion, aspecto, idParcela);
										JOptionPane.showMessageDialog(null,
												"La modificacion se ha realizado correctamente.");
										reservarParcelas.actualizarTabla();
										reservarBungalows.actualizarTabla();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							} else {
								if (aspecto.equals("dimension")) {
									if (!textoModificacion.equals("Pequena") && !textoModificacion.equals("Mediana")
											&& !textoModificacion.equals("Autocaravana")
											&& !textoModificacion.equals("Grande")
											&& !textoModificacion.equals("Deluxe")) {
										JOptionPane.showMessageDialog(null,
												"La dimension debe ser 'Pequena', 'Mediana', 'Autocaravana', 'Grande' o 'Deluxe'.");
									} else {
										try {
											GestorReservas.modificarPyB(elegido, textoModificacion, aspecto, idParcela);
											JOptionPane.showMessageDialog(null,
													"La modificacion se ha realizado correctamente.");
											reservarParcelas.actualizarTabla();
											reservarBungalows.actualizarTabla();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								} else {
									if (aspecto.equals("temporada")) {
										if (!textoModificacion.equals("Baja") && !textoModificacion.equals("Media")
												&& !textoModificacion.equals("Alta")) {
											JOptionPane.showMessageDialog(null,
													"La temporada debe ser 'Baja', 'Media' o 'Alta'");
										} else {
											try {
												GestorReservas.modificarPyB(elegido, textoModificacion, aspecto,
														idParcela);
												JOptionPane.showMessageDialog(null,
														"La modificacion se ha realizado correctamente.");
												reservarParcelas.actualizarTabla();
												reservarBungalows.actualizarTabla();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
										}
									} else {
										if (!textoModificacion.equals("Bar") && !textoModificacion.equals("Entrada")
												&& !textoModificacion.equals("Restaurante")
												&& !textoModificacion.equals("Lavabos")) {
											JOptionPane.showMessageDialog(null,
													"'Cerca_de' debe ser 'Bar', 'Entrada', 'Restaurante' o 'Lavabos'");
										} else {
											try {
												GestorReservas.modificarPyB(elegido, textoModificacion, aspecto,
														idParcela);
												JOptionPane.showMessageDialog(null,
														"La modificacion se ha realizado correctamente.");
												reservarParcelas.actualizarTabla();
												reservarBungalows.actualizarTabla();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
										}
									}
								}
							}
						}
					} else if (elegido.equals("Guias") || elegido.equals("Monitores")) {
						if (comboBoxAspectoAModificar.getSelectedItem().toString().toLowerCase().equals("alta")
								&& (!textFieldTextoModificado.getText().toString().equals("false")
										&& !textFieldTextoModificado.getText().toString().equals("true")))
							JOptionPane.showMessageDialog(null, "El alta debe ser un valor booleano.");
						else {
							try {
								GestorPersonal.modificarPersonal(elegido, textFieldTextoModificado.getText().toString(),
										comboBoxAspectoAModificar.getSelectedItem().toString().toLowerCase(),
										comboBoxIdentificador.getSelectedItem().toString());
								JOptionPane.showMessageDialog(null, "La modificacion se ha realizado correctamente.");
								mostrarPersonal.actualizarTabla();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					} else if (elegido.equals("Actividades")) {
						if (GestorActividades.comprobarText(GestorPersonal.leerJSON("Monitores"),
								textFieldTextoModificado.getText().toString(),
								comboBoxAspectoAModificar.getSelectedItem().toString())) {
							try {
								GestorActividades.modificarActividades(GestorActividades.leerActividades(),
										comboBoxIdentificador.getSelectedItem().toString(),
										comboBoxAspectoAModificar.getSelectedItem().toString(),
										textFieldTextoModificado.getText().toString());
								JOptionPane.showMessageDialog(null, "Se ha modificado correctamente.");
								mostrarActividades.actualizarTabla();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						} else {
							if (comboBoxAspectoAModificar.getSelectedItem().toString().equals("Monitor")) {
								//Andres se lo dejo vacio, ns que deberia poner aqui
							}
						}

					} else if(elegido.equals("Rutas")) {
						if (comboBoxAspectoAModificar.getSelectedItem().toString().equals("minimo")
								|| comboBoxAspectoAModificar.getSelectedItem().toString().equals("maximo")) {
							if (GestorRutas.isNumeric(textFieldTextoModificado.getText().toString())) {
								try {
									GestorRutas.modificarRuta(textFieldTextoModificado.getText().toString(),
											comboBoxAspectoAModificar.getSelectedItem().toString(),
											comboBoxIdentificador.getSelectedItem().toString());
									JOptionPane.showMessageDialog(null, "Se ha modificado correctamente.");
									mostrarRutas.actualizarTabla();
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else
								JOptionPane.showMessageDialog(null,
										"El aspecto a modificar debe ser un numero entero.");
						} else {
							try {
								GestorRutas.modificarRuta(textFieldTextoModificado.getText().toString(),
										comboBoxAspectoAModificar.getSelectedItem().toString(),
										comboBoxIdentificador.getSelectedItem().toString());
								JOptionPane.showMessageDialog(null, "Se ha modificado correctamente.");
								mostrarRutas.actualizarTabla();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} else if (elegido.equals("Promociones")) {
						if(comboBoxAspectoAModificar.getSelectedItem().toString().equals("alta")) {
							if(!textFieldTextoModificado.getText().toString().equals("true")&&!textFieldTextoModificado.getText().toString().equals("false")) {
								JOptionPane.showMessageDialog(null, "El aspecto 'alta' debe ser booleano.");
							}else {
								try {
									GestorPromociones.modificarPromocion(textFieldTextoModificado.getText().toString(),
											comboBoxAspectoAModificar.getSelectedItem().toString(),
											comboBoxIdentificador.getSelectedItem().toString());
									JOptionPane.showMessageDialog(null, "Se ha modificado correctamente.");
									mostrarPromociones.actualizarTabla();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						} else {
							try {
								GestorPromociones.modificarPromocion(textFieldTextoModificado.getText().toString(),
										comboBoxAspectoAModificar.getSelectedItem().toString(),
										comboBoxIdentificador.getSelectedItem().toString());
								JOptionPane.showMessageDialog(null, "Se ha modificado correctamente.");
								mostrarPromociones.actualizarTabla();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				} else
					JOptionPane.showMessageDialog(null, "El aspecto modificado no puede estar vacio.");
			} else
				JOptionPane.showMessageDialog(null, "Debe escoger cada uno de los apartados anteriores.");
		}
	}
	
	private class ComboBoxModificarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String textoIdentificadorAux = "Seleccione el identificador de";
			String textoModificadoAux = "Modifique el dato de";
			comboBoxIdentificador.removeAllItems();
			comboBoxAspectoAModificar.removeAllItems();
			textFieldTextoModificado.setText(null);
			switch (comboBoxModificar.getSelectedItem().toString()){
			case "Parcelas":
				textoIdentificadorAux += " la parcela:";
				textoModificadoAux += " la parcela:";
				comboBoxIdentificador.setModel(new DefaultComboBoxModel(GestorReservas.devolverID("Parcelas")));
				comboBoxAspectoAModificar.setModel(new DefaultComboBoxModel(aspectosParcelas));
				break;
			case "Bungalows":
				textoIdentificadorAux += "l bungalow:";
				textoModificadoAux += "l bungalow:";
				comboBoxIdentificador.setModel(new DefaultComboBoxModel(GestorReservas.devolverID("Bungalows")));
				comboBoxAspectoAModificar.setModel(new DefaultComboBoxModel(aspectosBungalows));
				break;
			case "Rutas":
				textoIdentificadorAux += " la ruta:";
				textoModificadoAux += " la ruta:";
				comboBoxIdentificador.setModel(new DefaultComboBoxModel(GestorRutas.devolverID()));
				comboBoxAspectoAModificar.setModel(new DefaultComboBoxModel(aspectosRutas));
				break;
			case "Guias":
				textoIdentificadorAux += " la guia:";
				textoModificadoAux += " la guia:";
				comboBoxIdentificador.setModel(new DefaultComboBoxModel(GestorPersonal.devolverDNI("Guias")));
				comboBoxAspectoAModificar.setModel(new DefaultComboBoxModel(aspectosGuiasMonitores));
				break;
			case "Monitores":
				textoIdentificadorAux += "l monitor:";
				textoModificadoAux += "l monitor:";
				comboBoxIdentificador.setModel(new DefaultComboBoxModel(GestorPersonal.devolverDNI("Monitores")));
				comboBoxAspectoAModificar.setModel(new DefaultComboBoxModel(aspectosGuiasMonitores));
				break;
			case "Actividades":
				textoIdentificadorAux += " la actividad:";
				textoModificadoAux += " la actividad:";
				comboBoxIdentificador.setModel(new DefaultComboBoxModel(GestorActividades.devolverIDActividades()));
				comboBoxAspectoAModificar.setModel(new DefaultComboBoxModel(aspectosActividades));
				break;
			case "Promociones":
				textoIdentificadorAux += " la promocion:";
				textoModificadoAux += " la promocion:";
				comboBoxIdentificador.setModel(new DefaultComboBoxModel(GestorPromociones.devolverIDPromociones()));
				comboBoxAspectoAModificar.setModel(new DefaultComboBoxModel(aspectosPromociones));
				break;
			}
			lblIdentificador.setText(textoIdentificadorAux);
			lblTextoModificado.setText(textoModificadoAux);
			//comboBoxIdentificador.setSelectedIndex(0);
			metodoTextoModificado();
			textArea.setText(buscarCaracteristicaModificacion());
		}
	}
	private class ComboBoxIdentificadorActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			metodoTextoModificado();
			textArea.setText(buscarCaracteristicaModificacion());
		}
	}
	private class ComboBoxAspectoAModificarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			metodoTextoModificado();
		}
	}

	public String buscarCaracteristicaModificacion() {
		String caracteristica = "";
		if (comboBoxModificar.getSelectedItem() != null && comboBoxIdentificador.getSelectedItem() != null) {
			String modificacion = comboBoxModificar.getSelectedItem().toString();
			String idMod = "1";
			if (comboBoxIdentificador.getSelectedItem() != null) {
				idMod = comboBoxIdentificador.getSelectedItem().toString();
			}
			switch (modificacion) {
			case "Parcelas":
				caracteristica = "Cerca de: ";
				caracteristica += JSONParcelas.getJSONObject("parcelas")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getString("cerca_de");
				caracteristica += "\nCoste dia: ";
				caracteristica += JSONBungalows.getJSONObject("bungalows")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getDouble("coste_dia");
				caracteristica += "\nCapacidad maxima: ";
				caracteristica += JSONBungalows.getJSONObject("bungalows")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getInt("capacidad_maxima");
				break;
			case "Bungalows":
				caracteristica = "Coste dia: ";
				caracteristica += JSONBungalows.getJSONObject("bungalows")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getDouble("coste_dia");
				caracteristica += "\nCapacidad maxima: ";
				caracteristica += JSONBungalows.getJSONObject("bungalows")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getInt("capacidad_maxima");
				break;
			case "Rutas":
				caracteristica = "Descripcion:  ";
				caracteristica += JSONRutas.getJSONObject("rutas")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getString("descripcion");
				caracteristica += "\nDia:  ";
				caracteristica += JSONRutas.getJSONObject("rutas")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getString("dia");
				break;
			case "Guias":
				caracteristica = "Nombre y apellidos: ";
				caracteristica += GestorPersonal.devolverAspectoElegido("Guias",
						comboBoxIdentificador.getSelectedItem().toString(), "nombre");
				caracteristica += " " + GestorPersonal.devolverAspectoElegido("Guias",
						comboBoxIdentificador.getSelectedItem().toString(), "apellidos");
				caracteristica += "\nAlta: ";
				caracteristica += " " + GestorPersonal.devolverAspectoElegido("Guias",
						comboBoxIdentificador.getSelectedItem().toString(), "alta");
				break;
			case "Monitores":
				caracteristica = "Nombre y apellidos: ";
				caracteristica += GestorPersonal.devolverAspectoElegido("Monitores",
						comboBoxIdentificador.getSelectedItem().toString(), "nombre");
				caracteristica += " " + GestorPersonal.devolverAspectoElegido("Monitores",
						comboBoxIdentificador.getSelectedItem().toString(), "apellidos");
				caracteristica += "\nAlta: ";
				caracteristica += " " + GestorPersonal.devolverAspectoElegido("Monitores",
						comboBoxIdentificador.getSelectedItem().toString(), "alta");
				break;
			case "Actividades":
				caracteristica = "Actividad: ";
				caracteristica += JSONActividades.getJSONObject("actividades")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getString("actividad");
				caracteristica += "\nHorario: ";
				caracteristica += JSONActividades.getJSONObject("actividades")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getString("horario");
				break;
			case "Promociones":
				caracteristica = "Titulo: ";
				caracteristica += JSONPromociones.getJSONObject("promociones")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getString("titulo");
				caracteristica += "\nDescripcion: ";
				caracteristica += JSONPromociones.getJSONObject("promociones")
						.getJSONObject(String.valueOf(Integer.parseInt(idMod) - 1)).getString("descripcion");
				break;
			}
		}
		return caracteristica;
	}
	
	public void toNormal(){
		lblModificar.setForeground(null);
		lblIdentificador.setForeground(null);
		lblTextoModificado.setForeground(null);
		lblAspecto.setForeground(null);
	}
	
	public void toDark(){
		lblModificar.setForeground(Color.white);
		lblIdentificador.setForeground(Color.white);
		lblTextoModificado.setForeground(Color.white);
		lblAspecto.setForeground(Color.white);
	}
	
	public void toEN(){
		lblModificar.setText(Messages.getString(""));
		lblIdentificador.setText(Messages.getString("CrearRutas.lblMes.text"));
		lblTextoModificado.setText(Messages.getString("CrearRutas.lblAno.text"));
		lblAspecto.setText(Messages.getString("CrearRutas.lblNewLabel.text"));
		btnModificar.setText(Messages.getString("ModificarPersonal.btnModificar.text"));
	}
	
	public void toES(){
		lblModificar.setText(Messages.getString("CrearRutas.lblDia.text"));
		lblIdentificador.setText(Messages.getString("CrearRutas.lblMes.text"));
		lblTextoModificado.setText(Messages.getString("CrearRutas.lblAno.text"));
		lblAspecto.setText(Messages.getString("CrearRutas.lblNewLabel.text"));
		btnModificar.setText(Messages.getString("ModificarPersonal.btnModificar.text"));
	}
	
	public void toSmall(){
		lblModificar.setFont(new Font(lblModificar.getFont().getFontName(), lblModificar.getFont().getStyle(), 10));
		lblIdentificador.setFont(new Font(lblIdentificador.getFont().getFontName(), lblIdentificador.getFont().getStyle(), 10));
		lblTextoModificado.setFont(new Font(lblTextoModificado.getFont().getFontName(), lblTextoModificado.getFont().getStyle(), 10));
		lblAspecto.setFont(new Font(lblAspecto.getFont().getFontName(), lblAspecto.getFont().getStyle(), 10));
		btnModificar.setFont(new Font(btnModificar.getFont().getFontName(), btnModificar.getFont().getStyle(), 10));
	}
	public void toMedium(){
		lblModificar.setFont(new Font(lblModificar.getFont().getFontName(), lblModificar.getFont().getStyle(), 13));
		lblIdentificador.setFont(new Font(lblIdentificador.getFont().getFontName(), lblIdentificador.getFont().getStyle(), 13));
		lblTextoModificado.setFont(new Font(lblTextoModificado.getFont().getFontName(), lblTextoModificado.getFont().getStyle(), 13));
		lblAspecto.setFont(new Font(lblAspecto.getFont().getFontName(), lblAspecto.getFont().getStyle(), 13));
		btnModificar.setFont(new Font(btnModificar.getFont().getFontName(), btnModificar.getFont().getStyle(), 13));
	}
	public void toBig(){
		lblModificar.setFont(new Font(lblModificar.getFont().getFontName(), lblModificar.getFont().getStyle(), 16));
		lblIdentificador.setFont(new Font(lblIdentificador.getFont().getFontName(), lblIdentificador.getFont().getStyle(), 16));
		lblTextoModificado.setFont(new Font(lblTextoModificado.getFont().getFontName(), lblTextoModificado.getFont().getStyle(), 16));
		lblAspecto.setFont(new Font(lblAspecto.getFont().getFontName(), lblAspecto.getFont().getStyle(), 16));
		btnModificar.setFont(new Font(btnModificar.getFont().getFontName(), btnModificar.getFont().getStyle(), 16));
	}
	
	public static void metodoTextoModificado() {
		String elegido = comboBoxModificar.getSelectedItem().toString();
		textFieldTextoModificado.setText(null);
		if (comboBoxModificar.getSelectedItem() != null && comboBoxIdentificador.getSelectedItem() != null
				&& comboBoxAspectoAModificar.getSelectedItem()!=null) {
			if(elegido.equals("Parcelas")) {
				textFieldTextoModificado.setText(GestorReservas.devolverAspectoElegido("Parcelas",
						comboBoxIdentificador.getSelectedItem().toString(),
						comboBoxAspectoAModificar.getSelectedItem().toString()));
			}else if(elegido.equals("Bungalows")) {
				textFieldTextoModificado.setText(GestorReservas.devolverAspectoElegido("Bungalows",
						comboBoxIdentificador.getSelectedItem().toString(),
						comboBoxAspectoAModificar.getSelectedItem().toString()));
			}else if(elegido.equals("Rutas")) {
				textFieldTextoModificado
						.setText(GestorRutas.devolverAspectoElegido(comboBoxIdentificador.getSelectedItem().toString(),
								comboBoxAspectoAModificar.getSelectedItem().toString()));
			}else if(elegido.equals("Guias")) {
				textFieldTextoModificado.setText(GestorPersonal.devolverAspectoElegido("Guias",
						comboBoxIdentificador.getSelectedItem().toString(),
						comboBoxAspectoAModificar.getSelectedItem().toString().toLowerCase()));
			}else if(elegido.equals("Monitores")) {
				textFieldTextoModificado.setText(GestorPersonal.devolverAspectoElegido("Monitores",
						comboBoxIdentificador.getSelectedItem().toString(),
						comboBoxAspectoAModificar.getSelectedItem().toString().toLowerCase()));
			}else if(elegido.equals("Actividades")) {
				textFieldTextoModificado.setText(GestorActividades.devolverTextTipo(GestorActividades.leerActividades(),
						comboBoxIdentificador.getSelectedItem().toString(),
						comboBoxAspectoAModificar.getSelectedItem().toString()));
			} else if (elegido.equals("Promociones")) {
				textFieldTextoModificado.setText(
						GestorPromociones.devolverAspectoElegido(comboBoxIdentificador.getSelectedItem().toString(),
								comboBoxAspectoAModificar.getSelectedItem().toString()));
			}
		}
	}
}
