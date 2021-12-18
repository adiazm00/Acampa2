package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorReservas {
	
	public static JSONObject leerBungalows() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos\\bungalows.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numBungalows");
			obj.getJSONObject("bungalows");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo bungalows.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo bungalows.json no es valido.");
			return null;
		}
		return obj;
	}
	
	public static Object[][] devolverBungalows(JSONObject jsonBung){
		Object[][] bungalows = new Object[jsonBung.getInt("numBungalows")][6];
		for(int i = 0; i<jsonBung.getInt("numBungalows");i++) {
			double cte = jsonBung.getJSONObject("bungalows").getJSONObject(""+i).getDouble("coste_dia");
			int m2 = jsonBung.getJSONObject("bungalows").getJSONObject(""+i).getInt("m2");
			int capMax = jsonBung.getJSONObject("bungalows").getJSONObject(""+i).getInt("capacidad_maxima");
			int id = jsonBung.getJSONObject("bungalows").getJSONObject(""+i).getInt("id");
			String dimension= jsonBung.getJSONObject("bungalows").getJSONObject(""+i).getString("dimension");
			String disponibilidad = jsonBung.getJSONObject("bungalows").getJSONObject(""+i).getString("disponibilidad");
			bungalows[i]= new Object[] {String.valueOf(id),dimension,String.valueOf(cte),String.valueOf(capMax),String.valueOf(m2),disponibilidad};
		}
		return bungalows;
	}
	
	public static JSONObject leerParcelas() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos\\parcelas.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numParcelas");
			obj.getJSONObject("parcelas");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo parcelas.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo parcelas.json no es valido.");
			return null;
		}
		return obj;
	}
	
	public static Object[][] devolverParcelas(JSONObject jsonParc, String dim, String cer, String temp) {
		ArrayList<Parcela> parcelasAux = new ArrayList<Parcela>();
		int id, m2, capMax;
		double precio_dia;
		String dimension, cerca, temporada;
		for (int i = 0; i < jsonParc.getInt("numParcelas"); i++) {
			dimension = jsonParc.getJSONObject("parcelas").getJSONObject("" + i).getString("dimension");
			cerca = jsonParc.getJSONObject("parcelas").getJSONObject("" + i).getString("cerca_de");
			temporada = jsonParc.getJSONObject("parcelas").getJSONObject("" + i).getString("temporada");
			if (dimension.equals(dim) && cerca.equals(cer) && temporada.equals(temp)) {
				id = jsonParc.getJSONObject("parcelas").getJSONObject("" + i).getInt("id");
				m2 = jsonParc.getJSONObject("parcelas").getJSONObject("" + i).getInt("m2");
				capMax = jsonParc.getJSONObject("parcelas").getJSONObject("" + i).getInt("capacidad_maxima");
				precio_dia = jsonParc.getJSONObject("parcelas").getJSONObject("" + i).getDouble("coste_dia");
				String disponibilidad = jsonParc.getJSONObject("parcelas").getJSONObject("" + i)
						.getString("disponibilidad");
				Parcela parcela = new Parcela(id, dimension, precio_dia, capMax, m2, temporada, cerca, disponibilidad);
				parcelasAux.add(parcela);
			}
		}
		Object[][] parcelas = new Object[parcelasAux.size()][8];
		Parcela pAux;
		for (int j = 0; j < parcelasAux.size(); j++) {
			pAux = parcelasAux.get(j);
			parcelas[j] = new Object[] { String.valueOf(pAux.getId()), pAux.getDimension(),
					String.valueOf(pAux.getCoste_dia()), String.valueOf(pAux.getCapacidad_maxima()), String.valueOf(pAux.getM2()),
					pAux.getTemporada(), pAux.getCerca(), pAux.getDisponibilidad() };
		}
		return parcelas;
	}
	
	public static void reservarBungalows(JSONObject jbungalows,String id) throws IOException {
		int i;
		for(i = 0; i<jbungalows.getInt("numBungalows");i++) {
			if(Integer.parseInt(id) == jbungalows.getJSONObject("bungalows").getJSONObject(i+"").getInt("id")) {
				jbungalows.getJSONObject("bungalows").getJSONObject(i+"").remove("disponibilidad");
				jbungalows.getJSONObject("bungalows").getJSONObject(i+"").put("disponibilidad", "ocupado");
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "bungalows.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(jbungalows.toString());
		fw.close();
	}
	
	public static void reservarParcelas(JSONObject jparcelas,String id) throws IOException {
		int i;
		for(i = 0; i<jparcelas.getInt("numParcelas");i++) {
			if(Integer.parseInt(id) == jparcelas.getJSONObject("parcelas").getJSONObject(i+"").getInt("id")) {
				jparcelas.getJSONObject("parcelas").getJSONObject(i+"").remove("disponibilidad");
				jparcelas.getJSONObject("parcelas").getJSONObject(i+"").put("disponibilidad", "ocupada");
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = "parcelas.json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(jparcelas.toString());
		fw.close();
	}
	
	public static String[] devolverID(String tipo) {
		JSONObject json;
		if (tipo.equals("Parcelas"))
			json = leerParcelas();
		else
			json = leerBungalows();
		String[] ids = new String[json.getInt("num" + tipo)];
		for (int i = 0; i < json.getInt("num" + tipo); i++) {
			ids[i] = String.valueOf(json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getInt("id"));
		}
		return ids;
	}
	
	public static String[] devolverAspectos(String tipo) {
		JSONObject json;
		String aux;
		if (tipo.equals("Parcelas"))
			json = leerParcelas();
		else
			json = leerBungalows();
		ArrayList<String> aspectos = new ArrayList<String>();
		Iterator<String> it = json.getJSONObject(tipo.toLowerCase()).getJSONObject("1").keys();
		while(it.hasNext()) {
			aux = it.next().toString();
			if(!aux.equals("id")&&!aux.equals("foto"))
				aspectos.add(aux);
		}
		String[] aspectos2 = new String[aspectos.size()];
		for (int i = 0; i < aspectos.size(); i++) {
			aspectos2[i] = aspectos.get(i);
		}
		return aspectos2;
	}
	
	public static String devolverAspectoElegido(String tipo, String id, String aspecto) {
		JSONObject json;
		if (tipo.equals("Parcelas"))
			json = leerParcelas();
		else
			json = leerBungalows();
		String texto = null;
		for (int i = 0; i < json.getInt("num" + tipo); i++) {
			if (String.valueOf(json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getInt("id")).equals(id)) {
				if (aspecto.equals("coste_dia"))
					texto = String.valueOf(json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getDouble(aspecto));
				else if(aspecto.equals("id")||aspecto.equals("m2")||aspecto.equals("capacidad_maxima"))
					texto = String
							.valueOf(json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getInt(aspecto));
				else
					texto = json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getString(aspecto);
			}
		}
		return texto;
	}
	
	public static void modificarPyB(String tipo, String texto, String aspecto, String id) throws IOException {
		JSONObject json;
		int auxInt; double auxDouble;
		if (tipo.equals("Parcelas"))
			json = leerParcelas();
		else
			json = leerBungalows();
		for(int i = 0; i<json.getInt("num"+tipo);i++) {
			int idCompr = json.getJSONObject(tipo.toLowerCase()).getJSONObject("" + i).getInt("id");
			if((String.valueOf(idCompr)).equals(id)) {
				if (!aspecto.equals("m2")&&!aspecto.equals("capacidad_maxima")&&!aspecto.equals("coste_dia")) {
					json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").remove(aspecto);
					json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").put(aspecto, texto);
				} else {
					if(!aspecto.equals("coste_dia")) {
						auxInt = (int)Double.parseDouble(texto);
						json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").remove(aspecto);
						json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").put(aspecto, auxInt);
					} else {
						auxDouble = Double.parseDouble(texto);
						json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").remove(aspecto);
						json.getJSONObject(tipo.toLowerCase()).getJSONObject(i + "").put(aspecto, auxDouble);
					}
				}
			}
		}
		String rutaescritura = System.getProperty("user.dir") + "\\IPO\\src\\dominio\\Datos";
		String file = tipo.toLowerCase()+".json";
		FileWriter fw = new FileWriter(new File(rutaescritura, file));
		fw.write(json.toString());
		fw.close();
	}
	
	public static boolean isNumeric(String cadena){
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
