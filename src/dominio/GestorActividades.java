package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorActividades {
	public static JSONObject leerActividades() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos\\actividades.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numActividades");
			obj.getJSONObject("actividades");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo actividades.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo actividades.json no es valido.");
			return null;
		}
		return obj;
	}
	
	public static JSONObject leerClientes() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos\\clientes.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numClientes");
			obj.getJSONObject("clientes");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo actividades.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo actividades.json no es valido.");
			return null;
		}
		return obj;
	}
	
	public static Object[][] devolverActividades(JSONObject jsonAct,JSONObject monitores){
		Object[][] actividades = new Object[jsonAct.getInt("numActividades")][6];
		boolean encontrado = false;
		for(int i = 0; i<jsonAct.getInt("numActividades");i++) {
			int j;
			double preciohora = jsonAct.getJSONObject("actividades").getJSONObject(""+i).getDouble("preciohora");
			int minimo = jsonAct.getJSONObject("actividades").getJSONObject(""+i).getInt("minimo");
			int edadRec = jsonAct.getJSONObject("actividades").getJSONObject(""+i).getInt("edadRec");
			int maximo = jsonAct.getJSONObject("actividades").getJSONObject(""+i).getInt("maximo");
			int id = jsonAct.getJSONObject("actividades").getJSONObject(""+i).getInt("id");
			String monitor= jsonAct.getJSONObject("actividades").getJSONObject(""+i).getString("monitor");
			for(j=0;j<monitores.getInt("numMonitores");j++) {
				if(monitor.equals(monitores.getJSONObject("monitores").getJSONObject(""+j).getString("dni"))) {
					encontrado=true;
					break;
				}
			}
			if(!encontrado) {
				System.out.println("Error, monitor no encontrado");
			}
			String actividad= jsonAct.getJSONObject("actividades").getJSONObject(""+i).getString("actividad");
			String materiales= jsonAct.getJSONObject("actividades").getJSONObject(""+i).getString("materiales");
			String horario = jsonAct.getJSONObject("actividades").getJSONObject(""+i).getString("horario");
			actividades[i]= new Object[] {String.valueOf(id),actividad,monitores.getJSONObject("monitores").getJSONObject(""+j).getString("nombre"),horario,String.valueOf(minimo),String.valueOf(maximo),String.valueOf(edadRec),String.valueOf(preciohora),materiales};
		}
		return actividades;
	}
	
	public static String[] devolverIDActividades() {
		JSONObject JSONActividades = leerActividades();
		String[] idActividades = new String[JSONActividades.getInt("numActividades")];
		for(int i = 0; i<JSONActividades.getInt("numActividades");i++) {
			idActividades[i] = ""+JSONActividades.getJSONObject("actividades").getJSONObject(""+i).getInt("id");
		}
		return idActividades;
	}
	
	public static String devolverTextTipo(JSONObject JSONActividades,String id, String tipo) {
		String text = "";
		int i = Integer.parseInt(id)-1;
		switch(tipo) {
		case "Materiales necesarios":
			text = JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getString("materiales");
			break;
		case "Horario":
			text = JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getString("horario");
			break;
		case "Descripcion actividad":
			text = JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getString("actividad");
			break;
		case "Monitor":
			text = JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getString("monitor");
			break;
		case "Precio/Hora":
			text = ""+JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getInt("preciohora");
			break;
		case "Edad recomendada":
			text = ""+JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getInt("edadRec");
			break;
		case "Maximos participantes":
			text = ""+JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getInt("maximo");
			break;
		case "Minimos participantes":
			text = ""+JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getInt("minimo");
			break;
		}
		return text;
		
	}
	
	public static String devolverActividad(JSONObject JSONActividades, String id) {
		String actividad="";
		for(int i = 0; i<JSONActividades.getInt("numActividades");i++) {
			if((""+JSONActividades.getJSONObject("actividades").getJSONObject(""+i).getInt("id")).equals(id)) {
				actividad=JSONActividades.getJSONObject("actividades").getJSONObject(""+i).getString("actividad");
			}
		}
		return actividad;
	}
	
	public static JSONObject devolverInscritosActividad(JSONObject JSONActividades, String id) {
		JSONObject inscritos = new JSONObject();
		for(int i = 0; i<JSONActividades.getInt("numActividades");i++) {
			if((""+JSONActividades.getJSONObject("actividades").getJSONObject(""+i).getInt("id")).equals(id)) {
				inscritos=JSONActividades.getJSONObject("actividades").getJSONObject(""+i).getJSONObject("inscritos");
			}
		}
		return inscritos;

	}
	public static Object[][] devolverInscritos(JSONObject inscritos){
		Object[][] obj = new Object[inscritos.getInt("numInscritos")][4];
		JSONObject clientes = leerClientes();
		int j=0;
		Cliente cliente;
		Iterator keys = inscritos.keys();
		
		while(keys.hasNext()) {
			String id = (String) keys.next();
			if(GestorRutas.isNumeric(id)) {
				cliente = devolverCliente(clientes,id);
				

				obj[j++]= new Object[] {cliente.getNombre(),cliente.getTelefono(),inscritos.getJSONObject(id).getString("tipoPago"),
						inscritos.getJSONObject(id).getBoolean("pagado")};
			}
		}
		

	return obj;
	}
	public static Cliente devolverCliente(JSONObject clientes, String id) {
		Cliente cliente = new Cliente();
		for(int i = 0;i<clientes.getInt("numClientes");i++){
			if(i == Integer.parseInt(id)) {
				cliente.setId(id);
				cliente.setNombre(clientes.getJSONObject("clientes").getJSONObject(""+i).getString("nombre"));
				cliente.setTelefono(clientes.getJSONObject("clientes").getJSONObject(""+i).getString("telefono"));
			}
		}
		return cliente;
	}
	
	public static Cliente devolverClienteNombre(JSONObject clientes, String nombre) {
		Cliente cliente = new Cliente();
		for(int i = 0;i<clientes.getInt("numClientes");i++){
			if(clientes.getJSONObject("clientes").getJSONObject(""+i).getString("nombre").equals(nombre)) {
				cliente.setId(""+i);
				cliente.setNombre(clientes.getJSONObject("clientes").getJSONObject(""+i).getString("nombre"));
				cliente.setTelefono(clientes.getJSONObject("clientes").getJSONObject(""+i).getString("telefono"));
			}
		}
		return cliente;
	}
	
	public static String[] devolverNombreClientes(JSONObject clientes) {
		String[] nombres = new String[clientes.getInt("numClientes")];
		for(int i = 0;i<clientes.getInt("numClientes");i++){
			nombres[i] = clientes.getJSONObject("clientes").getJSONObject(""+i).getString("nombre");
		}
		return nombres;
	}
	
	public static boolean comprobarText(JSONObject monitores, String texto,String tipo) {
		boolean t = true;
		switch (tipo) {
		case "Monitor":
			t=false;
			for (int i = 0; i < monitores.getInt("numMonitores"); i++) {
				t = texto.equals(monitores.getJSONObject("monitores").getJSONObject(i + "").getString("dni"));
				if (t)
					break;
			}
			break;
		case "Precio/Hora":
			t = GestorRutas.isNumeric(texto);
			break;
		case "Edad recomendada":
			t = GestorRutas.isNumeric(texto);
			break;
		case "Maximos participantes":
			t = GestorRutas.isNumeric(texto);
			break;
		case "Minimos participantes":
			t = GestorRutas.isNumeric(texto);
			break;
		}
		return t;
	}
	public static void modificarActividades(JSONObject JSONActividades,String id,String tipo,String text) throws IOException {
		int i;
		String key="";
		switch(tipo) {
		case "Materiales necesarios":
			key = "materiales";
			break;
		case "Horario":
			key = "horario";
			break;
		case "Descripcion actividad":
			key ="actividad";
			break;
		case "Monitor":
			key = "monitor";
			break;
		case "Precio/Hora":
			key = "preciohora";
			break;
		case "Edad recomendada":
			key = "edadRec";
			break;
		case "Maximos participantes":
			key = "maximo";
			break;
		case "Minimos participantes":
			key = "minimo";
			break;
		}
		for(i = 0; i<JSONActividades.getInt("numActividades");i++) {
			if(Integer.parseInt(id) == JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getInt("id")) {
				JSONActividades.getJSONObject("actividades").getJSONObject(i+"").remove(key);
				JSONActividades.getJSONObject("actividades").getJSONObject(i+"").put(key, text);
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "actividades.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(JSONActividades.toString());
		fw.close();
	}
	
	public static boolean inscribirCliente(JSONObject JSONActividades,JSONObject clientes, String idActividad, String nombre, String tipoPago, boolean pagado) throws IOException {
		Cliente cliente = devolverClienteNombre(clientes,nombre);
		JSONObject json = new JSONObject();
		JSONObject inscritos = devolverInscritosActividad(JSONActividades,idActividad);
		Iterator keys = inscritos.keys();
		boolean encontrado = false;
		for(int i = 0; i<JSONActividades.getInt("numActividades");i++) {
			if((""+JSONActividades.getJSONObject("actividades").getJSONObject(""+i).getInt("id")).equals(idActividad)) {
				json.put("tipoPago", tipoPago);
				json.put("pagado", pagado);
				json.put("id", cliente.getId());
				while(keys.hasNext()) {
					String id = (String) keys.next();
					if(GestorRutas.isNumeric(id)) {
						if(String.valueOf(inscritos.getJSONObject(id).getInt("id")).equals(cliente.getId())){
							encontrado= true;
						}
					}
				}
				if(!encontrado) {
					JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getJSONObject("inscritos").put("numInscritos", 
							JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getJSONObject("inscritos").getInt("numInscritos")+1);
					JSONActividades.getJSONObject("actividades").getJSONObject(i+"").getJSONObject("inscritos").put(cliente.getId(), json);
					String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
					String file = "actividades.json";
					FileWriter fw = new FileWriter(new File(rutaescritura, file));
					fw.write(JSONActividades.toString());
					fw.close();
				}


			}
		}

		return encontrado;
	}
	
	public static void cancelarInscripcion(JSONObject JSONActividades,JSONObject clientes,String idActividad, String nombre) throws IOException {
		Cliente cliente = devolverClienteNombre(clientes,nombre);
		JSONObject inscritos = devolverInscritosActividad(JSONActividades,idActividad);
		int numInscritos=inscritos.getInt("numInscritos");
		for(int i = 0; i<inscritos.getInt("numInscritos");i++) {
			if(i == Integer.parseInt(cliente.getId())) {
				inscritos.remove(""+i);
			}
		}
		inscritos.put("numInscritos",numInscritos-1);
		for(int i= 0; i<JSONActividades.getInt("numActividades");i++) {
			if(JSONActividades.getJSONObject("actividades").getJSONObject(""+i).getInt("id") == Integer.parseInt(idActividad)) {
				JSONActividades.getJSONObject("actividades").getJSONObject(""+i).remove("inscritos");
				JSONActividades.getJSONObject("actividades").getJSONObject(""+i).put("inscritos", inscritos);
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "actividades.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(JSONActividades.toString());
		fw.close();
	}
	public static JSONObject comprobarActividadesAnteriorFecha(String time, JSONObject JSONActividades) throws ParseException {
		String horario;
		SimpleDateFormat df= new SimpleDateFormat("hh:mm dd/MM/yyyy");
		JSONObject actividadesAnteriores = new JSONObject();
		JSONObject jactividades = new JSONObject();
		Date fechaActual = df.parse(time);
		Iterator keys = JSONActividades.getJSONObject("actividades").keys();
		int contador = 0;
		while(keys.hasNext()) {
			String i = (String) keys.next();
			if(GestorRutas.isNumeric(i)) {
				horario = JSONActividades.getJSONObject("actividades").getJSONObject(i).getString("horario");
				StringTokenizer t = new StringTokenizer(horario," ");
				String horas = t.nextToken();
				String fecha = t.nextToken();
				StringTokenizer h = new StringTokenizer(horas,"-");
				h.nextToken();
				String horafinal=h.nextToken();
				Date fechaActividad = df.parse(horafinal+" "+ fecha);
				if(fechaActividad.getTime()<fechaActual.getTime()) {
					actividadesAnteriores.put(String.valueOf(contador), JSONActividades.getJSONObject("actividades").getJSONObject(i));
					contador++;
				}


			}
		}
		jactividades.put("actividades", actividadesAnteriores);
		jactividades.put("numActividades", contador);

		return jactividades;
	}
	public static Object[][] devolverActividadesAnteriores(JSONObject jactividades){
		Object[][] actividades = new Object[jactividades.getInt("numActividades")][4];
		Iterator keys = jactividades.getJSONObject("actividades").keys();
		int j= 0;
		while(keys.hasNext()) {
			String i = (String) keys.next();
			if(GestorRutas.isNumeric(i)) {
				int id = jactividades.getJSONObject("actividades").getJSONObject(i).getInt("id");
				String monitor= jactividades.getJSONObject("actividades").getJSONObject(i).getString("monitor");
				String actividad= jactividades.getJSONObject("actividades").getJSONObject(i).getString("actividad");
				String horario = jactividades.getJSONObject("actividades").getJSONObject(i).getString("horario");

				actividades[j++] = new Object[] {String.valueOf(id),actividad,monitor,horario};

			}
		}
		return actividades;
	}
	public static Object[][] devolverComentariosActividad(JSONObject JSONActividad, String idActividad,JSONObject JSONClientes){
		Object[][] comentarios = new Object[JSONActividad.getJSONObject("actividades").getJSONObject(idActividad).getJSONObject("comentarios").getInt("numComentarios")][3];
		Iterator keys = JSONActividad.getJSONObject("actividades").getJSONObject(idActividad).getJSONObject("comentarios").keys();
		
		int contador = 0;
		while(keys.hasNext()) {
			String id = (String) keys.next();
			if(GestorRutas.isNumeric(id)) {
				String comentario=JSONActividad.getJSONObject("actividades").getJSONObject(idActividad).getJSONObject("comentarios").getJSONObject(id).getString("comentario");
				int idComentario = JSONActividad.getJSONObject("actividades").getJSONObject(idActividad).getJSONObject("comentarios").getJSONObject(id).getInt("id");
				int puntuacion= JSONActividad.getJSONObject("actividades").getJSONObject(idActividad).getJSONObject("comentarios").getJSONObject(id).getInt("puntuacion");
				Cliente cliente = devolverCliente(JSONClientes,String.valueOf(idComentario));
				comentarios[contador++] = new Object[] {cliente.getNombre(), comentario, String.valueOf(puntuacion)};

			}
		}
		
		return comentarios;
	}
	
	
	public static boolean addComentario(JSONObject JSONActividades,JSONObject clientes, String idActividad, String nombre, int puntuacion, String comentario, int idcomentario) throws IOException {
		Cliente cliente = devolverClienteNombre(clientes,nombre);
		JSONObject json = new JSONObject();
		JSONObject comentarios = JSONActividades.getJSONObject("actividades").getJSONObject(idActividad).getJSONObject("comentarios");
		Iterator keys = comentarios.keys();
		boolean encontrado = false;
		int numComentario;
		json.put("comentario", comentario);
		json.put("puntuacion", puntuacion);
		json.put("id", cliente.getId());
		while (keys.hasNext()) {
			String id = (String) keys.next();
			if (GestorRutas.isNumeric(id)) {
				if (String.valueOf(comentarios.getJSONObject(id).getInt("id")).equals(cliente.getId())) {
					encontrado = true;
				}
			}
		}
		if (!encontrado) {
			numComentario = JSONActividades.getJSONObject("actividades").getJSONObject(idActividad)
					.getJSONObject("comentarios").getInt("numComentarios");
			JSONActividades.getJSONObject("actividades").getJSONObject(idActividad).getJSONObject("comentarios")
					.put("numComentarios", JSONActividades.getJSONObject("actividades").getJSONObject(idActividad)
							.getJSONObject("comentarios").getInt("numComentarios") + 1);
			JSONActividades.getJSONObject("actividades").getJSONObject(idActividad).getJSONObject("comentarios")
					.put(String.valueOf(numComentario), json);
			String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
			String file = "actividades.json";
			FileWriter fw = new FileWriter(new File(rutaescritura, file));
			fw.write(JSONActividades.toString());
			fw.close();
		}
		return encontrado;
	}
}
