package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorPromociones {
	public static JSONObject leerPromociones() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos\\promociones.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numPromociones");
			obj.getJSONObject("promociones");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo actividades.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo actividades.json no es valido.");
			return null;
		}
		return obj;
	}
	
	public static Object[][] devolverPromociones(JSONObject JSONPromociones){
		Object[][] promociones = new Object[JSONPromociones.getInt("numPromociones")][5];
		Iterator keys = JSONPromociones.getJSONObject("promociones").keys();
		int i = 0;
		while(keys.hasNext()) {
			String contador = (String) keys.next();
			if(GestorRutas.isNumeric(contador)) {
				int id = JSONPromociones.getJSONObject("promociones").getJSONObject(contador).getInt("id");
				String titulo = JSONPromociones.getJSONObject("promociones").getJSONObject(contador).getString("titulo");
				String descripcion = JSONPromociones.getJSONObject("promociones").getJSONObject(contador).getString("descripcion");
				String foto = JSONPromociones.getJSONObject("promociones").getJSONObject(contador).getString("foto");
				boolean alta = JSONPromociones.getJSONObject("promociones").getJSONObject(contador).getBoolean("alta");
				promociones[i++] = new Object[] {String.valueOf(id), titulo, descripcion,foto, String.valueOf(alta)};
			}
		}
		return promociones;
	}
	public static void modificarPromocion(String texto, String aspecto, String id) throws IOException {
		JSONObject json = leerPromociones();
		boolean auxBoolean;
		for(int i = 0; i<json.getInt("numPromociones");i++) {
			int idCompr = json.getJSONObject("promociones").getJSONObject("" + i).getInt("id");
			if((String.valueOf(idCompr)).equals(id)) {
				if (!aspecto.equals("alta")) {
					json.getJSONObject("promociones").getJSONObject(i + "").remove(aspecto);
					json.getJSONObject("promociones").getJSONObject(i + "").put(aspecto, texto);
				} else {
					auxBoolean = Boolean.parseBoolean(texto);
					json.getJSONObject("promociones").getJSONObject(i + "").remove(aspecto);
					json.getJSONObject("promociones").getJSONObject(i + "").put(aspecto, auxBoolean);
				}
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "promociones.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(json.toString());
		fw.close();
	}
	
	public static void addPromocion(JSONObject JSONPromociones, String titulo, String descripcion, String foto, boolean alta) throws IOException {
		JSONObject json = new JSONObject();
		json.put("id", JSONPromociones.getInt("numPromociones")+1);
		json.put("titulo", titulo);
		json.put("descripcion", descripcion);
		json.put("foto", foto);
		json.put("alta", alta);
		JSONPromociones.getJSONObject("promociones").put(String.valueOf(JSONPromociones.getInt("numPromociones")), json);
		JSONPromociones.put("numPromociones", JSONPromociones.getInt("numPromociones")+1);
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "promociones.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(JSONPromociones.toString());
		fw.close();
	}
	public static String[] devolverIDPromociones() {
		JSONObject JSONPromociones = leerPromociones();
		String[] idPromociones = new String[JSONPromociones.getInt("numPromociones")];
		for(int i = 0; i<JSONPromociones.getInt("numPromociones");i++) {
			idPromociones[i] = ""+JSONPromociones.getJSONObject("promociones").getJSONObject(""+i).getInt("id");
		}
		return idPromociones;
	}
	public static String devolverAspectoElegido(String id, String aspecto) {
		JSONObject json = leerPromociones();
		String texto = null;
		for (int i = 0; i < json.getInt("numPromociones"); i++) {
			if (String.valueOf(json.getJSONObject("promociones").getJSONObject("" + i).getInt("id")).equals(id)) {
				if(aspecto.equals("alta"))
					texto = String
							.valueOf(json.getJSONObject("promociones").getJSONObject("" + i).getBoolean(aspecto));
				else
					texto = json.getJSONObject("promociones").getJSONObject("" + i).getString(aspecto);
			}
		}
		return texto;
	}
}
