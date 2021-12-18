package dominio;

public class Bungalow {
	private int id,capacidad_maxima,m2;
	private double coste_dia;
	private String dimension, disponibilidad;
	
	public Bungalow(int id, String dimension, double coste_dia, int capacidad_maxima, int m2, String disponibilidad) {
		this.id = id;
		this.capacidad_maxima = capacidad_maxima;
		this.m2 = m2;
		this.coste_dia = coste_dia;
		this.dimension = dimension;
		this.disponibilidad = disponibilidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidad_maxima() {
		return capacidad_maxima;
	}

	public void setCapacidad_maxima(int capacidad_maxima) {
		this.capacidad_maxima = capacidad_maxima;
	}

	public int getM2() {
		return m2;
	}

	public void setM2(int m2) {
		this.m2 = m2;
	}

	public double getCoste_dia() {
		return coste_dia;
	}

	public void setCoste_dia(double coste_dia) {
		this.coste_dia = coste_dia;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
}
