package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorRutas {

	public static JSONObject leerRutas() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos\\rutas.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numRutas");
			obj.getJSONObject("rutas");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo rutas.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo rutas.json no es valido.");
			return null;
		}
		return obj;
	}
	
	public static Object[][] devolverRutas(JSONObject jsonRutas){
		Object[][] rutas = new Object[jsonRutas.getInt("numRutas")][7];
		for(int i = 0; i<jsonRutas.getInt("numRutas");i++) {
			int id = jsonRutas.getJSONObject("rutas").getJSONObject(""+i).getInt("id");
			String dia= jsonRutas.getJSONObject("rutas").getJSONObject(""+i).getString("dia");
			String horaInicio= jsonRutas.getJSONObject("rutas").getJSONObject(""+i).getString("hora_inicio");
			String horaFin= jsonRutas.getJSONObject("rutas").getJSONObject(""+i).getString("hora_fin");
			String monitor= jsonRutas.getJSONObject("rutas").getJSONObject(""+i).getString("monitor");
			int min = jsonRutas.getJSONObject("rutas").getJSONObject(""+i).getInt("minimo");
			int max = jsonRutas.getJSONObject("rutas").getJSONObject(""+i).getInt("maximo");
			rutas[i]= new Object[] {String.valueOf(id),dia,horaInicio,horaFin,monitor,String.valueOf(min),String.valueOf(max)};
		}
		return rutas;
	}
	
	public static Ruta buscarRuta(JSONObject jsonRutas, int idRuta){
		Ruta ruta = null;
		for(int i = 0; i<jsonRutas.getInt("numRutas");i++) {
			int id = jsonRutas.getJSONObject("rutas").getJSONObject(""+i).getInt("id");
			if (id == idRuta) {
				String dia = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getString("dia");
				String horaInicio = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getString("hora_inicio");
				String horaFin = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getString("hora_fin");
				String monitor = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getString("monitor");
				int min = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getInt("minimo");
				int max = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getInt("maximo");
				String dificultad = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getString("dificultad");
				String equipo = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getString("equipo_necesario");
				String descripcion = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getString("descripcion");
				String mapa = jsonRutas.getJSONObject("rutas").getJSONObject("" + i).getString("mapa");
				ruta = new Ruta(id, dia, horaInicio, horaFin, monitor, min, max, dificultad, equipo, descripcion, mapa);
				break;
			}
		}
		return ruta;
	}
	
	public static String[] devolverMonitores(JSONObject JSONMonitores) {
		String[] monitores = new String[JSONMonitores.getInt("numMonitores")];
		for(int i=0; i<JSONMonitores.getInt("numMonitores");i++) {
			monitores[i]= JSONMonitores.getJSONObject("monitores").getJSONObject(""+i).getString("nombre") +" "+JSONMonitores.getJSONObject("monitores").getJSONObject(""+i).getString("apellidos");
		}
		return monitores;
	}
	
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public static void addRuta(JSONObject JSONRutas, JSONObject JSONMonitores, String dia,String mes,String ano, String horaInicio, 
			String horaFin, int maximo, int minimo, String monitor,String dificultad, String equipo, String descripcion, String mapa) throws IOException {
		StringTokenizer t = new StringTokenizer(monitor," ");
		String nombre = t.nextToken();
		String fecha = dia+"/"+mes+"/"+ano;
		JSONObject json = new JSONObject();
		json.put("dia", fecha);
		json.put("hora_inicio", horaInicio);
		json.put("hora_fin", horaFin);
		json.put("minimo", minimo);
		json.put("maximo", maximo);
		json.put("descripcion", descripcion);
		json.put("equipo_necesario", equipo);
		json.put("dificultad", dificultad);
		json.put("monitor", nombre);
		json.put("id", JSONRutas.getInt("numRutas")+1);
		json.put("mapa", mapa);
		JSONRutas.getJSONObject("rutas").put(String.valueOf(JSONRutas.getInt("numRutas")), json);
		JSONRutas.put("numRutas",JSONRutas.getInt("numRutas")+1);
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "rutas.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(JSONRutas.toString());
		fw.close();
	}
	public static String[] devolverID() {
		JSONObject json = leerRutas();
		String[] ids = new String[json.getInt("numRutas")];
		for (int i = 0; i < json.getInt("numRutas"); i++) {
			ids[i] = String.valueOf(json.getJSONObject("rutas").getJSONObject("" + i).getInt("id"));
		}
		return ids;
	}
	public static String[] devolverAspectos() {
		JSONObject json = leerRutas();
		String aux;
		ArrayList<String> aspectos = new ArrayList<String>();
		Iterator<String> it = json.getJSONObject("rutas").getJSONObject("1").keys();
		while(it.hasNext()) {
			aux = it.next().toString();
			if(!aux.equals("mapa") && !aux.equals("id") && !aux.equals("monitor"))
				aspectos.add(aux);
		}
		String[] aspectos2 = new String[aspectos.size()];
		for (int i = 0; i < aspectos.size(); i++) {
			aspectos2[i] = aspectos.get(i);
		}
		return aspectos2;
	}
	public static String devolverAspectoElegido( String id, String aspecto) {
		JSONObject json = leerRutas();
		String texto = null;
		for (int i = 0; i < json.getInt("numRutas"); i++) {
			if (String.valueOf(json.getJSONObject("rutas").getJSONObject("" + i).getInt("id")).equals(id)) {
				if(aspecto.equals("minimo")||aspecto.equals("maximo")||aspecto.equals("id"))
					texto = String
							.valueOf(json.getJSONObject("rutas").getJSONObject("" + i).getInt(aspecto));
				else
					texto = json.getJSONObject("rutas").getJSONObject("" + i).getString(aspecto);
			}
		}
		return texto;
	}
	public static void modificarRuta(String texto, String aspecto, String id) throws IOException {
		JSONObject json = leerRutas();
		int auxInt;
		for(int i = 0; i<json.getInt("numRutas");i++) {
			int idCompr = json.getJSONObject("rutas").getJSONObject("" + i).getInt("id");
			if((String.valueOf(idCompr)).equals(id)) {
				if (!aspecto.equals("id")&&!aspecto.equals("minimo")&&!aspecto.equals("maximo")) {
					json.getJSONObject("rutas").getJSONObject(i + "").remove(aspecto);
					json.getJSONObject("rutas").getJSONObject(i + "").put(aspecto, texto);
				} else {
					auxInt = (int)Double.parseDouble(texto);
					json.getJSONObject("rutas").getJSONObject(i + "").remove(aspecto);
					json.getJSONObject("rutas").getJSONObject(i + "").put(aspecto, auxInt);
				}
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "rutas.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(json.toString());
		fw.close();
	}
	
}
