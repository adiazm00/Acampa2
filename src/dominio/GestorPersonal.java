package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorPersonal {
	
	public static JSONObject leerJSON(String tipo) {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos\\"+tipo.toLowerCase()+".json"));
			obj = new JSONObject(tokener);
			obj.getInt("num"+tipo);
			obj.getJSONObject(tipo.toLowerCase());
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo "+tipo.toLowerCase()+".json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo "+tipo.toLowerCase()+".json no es valido.");
			return null;
		}
		return obj;
	}
	
	public static Object[][] devolverObject(String tipo, JSONObject json){
		Object[][] object = new Object[json.getInt("num"+tipo)][6];
		for(int i = 0; i<json.getInt("num"+tipo);i++) {
			String dni = json.getJSONObject(tipo.toLowerCase()).getJSONObject(""+i).getString("dni");
			String nombre= json.getJSONObject(tipo.toLowerCase()).getJSONObject(""+i).getString("nombre");
			String apellidos= json.getJSONObject(tipo.toLowerCase()).getJSONObject(""+i).getString("apellidos");
			String telefono= json.getJSONObject(tipo.toLowerCase()).getJSONObject(""+i).getString("telefono");
			String correo= json.getJSONObject(tipo.toLowerCase()).getJSONObject(""+i).getString("correo");
			String idioma= json.getJSONObject(tipo.toLowerCase()).getJSONObject(""+i).getString("idioma");
			String formacion= json.getJSONObject(tipo.toLowerCase()).getJSONObject(""+i).getString("formacion");
			boolean alta= json.getJSONObject(tipo.toLowerCase()).getJSONObject(""+i).getBoolean("alta");
			object[i]= new Object[] {dni,nombre,apellidos,telefono,correo,idioma,formacion,alta};
		}
		return object;
	}
	
	public static String[] devolverDNI(String tipo) {
		JSONObject json;
		if (tipo.equals("Guias"))
			json = leerJSON("Guias");
		else
			json = leerJSON("Monitores");
		String[] dnis = new String[json.getInt("num" + tipo)];
		for (int i = 0; i < json.getInt("num" + tipo); i++) {
			dnis[i] = json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getString("dni");
		}
		return dnis;
	}

	public static String devolverAspectoElegido(String tipo, String dni, String aspecto) {
		JSONObject json;
		if (tipo.equals("Guias"))
			json = leerJSON("Guias");
		else
			json = leerJSON("Monitores");
		String texto = null;
		for (int i = 0; i < json.getInt("num" + tipo); i++) {
			if (json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getString("dni").equals(dni)) {
				if (!aspecto.equals("alta"))
					texto = json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getString(aspecto);
				else
					texto = String
							.valueOf(json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getBoolean(aspecto));
			}
		}
		return texto;
	}
	
	public static void modificarPersonal(String tipo, String texto, String aspecto, String dni) throws IOException {
		JSONObject json;
		boolean aux;
		if (tipo.equals("Guias"))
			json = leerJSON("Guias");
		else
			json = leerJSON("Monitores");
		for(int i = 0; i<json.getInt("num"+tipo);i++) {
			String dniCompr = json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getString("dni");
			if(dniCompr.equals(dni)) {
				if (!aspecto.equals("alta")) {
					json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").remove(aspecto);
					json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").put(aspecto, texto);
				} else {
					aux = Boolean.valueOf(texto);
					json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").remove(aspecto);
					json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").put(aspecto, aux);
				}
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = tipo.toLowerCase()+".json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(json.toString());
		fw.close();
	}
}