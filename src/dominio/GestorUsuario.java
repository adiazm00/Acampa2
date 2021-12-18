package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorUsuario {
	
	public static JSONObject leerUsuarios() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos\\usuarios.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numUsuarios");
			obj.getJSONObject("usuarios");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo usuarios.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo usuarios.json no es valido.");
			return null;
		}
		return obj;
	}
	
	public static String[] comprobarUsuario(String u, JSONObject obj) {
		boolean encontrado = false;
		String[] usuario = new String[6];
		JSONObject usuarios = obj.getJSONObject("usuarios");
		for(int i=0; i<obj.getInt("numUsuarios"); i++) {
			if(usuarios.getJSONObject(String.valueOf(i)).getString("nombre").equals(u)) {
				encontrado = true;
				usuario[0] = usuarios.getJSONObject(String.valueOf(i)).getString("nombre");
				usuario[1] = usuarios.getJSONObject(String.valueOf(i)).getString("contrasena");
				usuario[2] = usuarios.getJSONObject(String.valueOf(i)).getString("correo");
				usuario[3] = usuarios.getJSONObject(String.valueOf(i)).getString("telefono");
				usuario[4] = usuarios.getJSONObject(String.valueOf(i)).getString("tipo");
				usuario[5] = usuarios.getJSONObject(String.valueOf(i)).getString("ultima_conexion");
				break;
			}
		}
		if(encontrado == false)
			return null;
		else
			return usuario;
	}

	public static void modificarUsuario(JSONObject jusuarios,Usuario u) throws IOException {
		int i;
		Calendar c1 = Calendar.getInstance();
		for(i = 0; i<jusuarios.getInt("numUsuarios");i++) {
			if (u.getNombre().equals(jusuarios.getJSONObject("usuarios").getJSONObject(i + "").getString("nombre"))
					&& u.getCorreo().equals(jusuarios.getJSONObject("usuarios").getJSONObject(i + "").getString("correo"))) {
				jusuarios.getJSONObject("usuarios").getJSONObject(i + "").remove("ultima_conexion");
				jusuarios.getJSONObject("usuarios").getJSONObject(i + "").put("ultima_conexion",
						c1.get(Calendar.HOUR) + ":" + c1.get(Calendar.MINUTE) + " " + c1.get(Calendar.DATE) + "/"
								+ (c1.get(Calendar.MONTH)+1) + "/" + c1.get(Calendar.YEAR));
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "usuarios.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(jusuarios.toString());
		fw.close();
	}
}
