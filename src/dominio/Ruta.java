package dominio;

public class Ruta {
	private int id, minPersonas, maxPersonas;
	private String dia, horaInicio, horaFin, monitor, dificultad, equipoNecesario, descripcion, mapa;

	public Ruta(int id, String dia, String horaInicio, String horaFin, String monitor, int minPersonas,
			int maxPersonas, String dificultad, String equipoNecesario, String descripcion, String mapa) {
		this.id = id;
		this.minPersonas = minPersonas;
		this.maxPersonas = maxPersonas;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.monitor = monitor;
		this.dificultad = dificultad;
		this.equipoNecesario = equipoNecesario;
		this.descripcion = descripcion;
		this.mapa = mapa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMinPersonas() {
		return minPersonas;
	}

	public void setMinPersonas(int minPersonas) {
		this.minPersonas = minPersonas;
	}

	public int getMaxPersonas() {
		return maxPersonas;
	}

	public void setMaxPersonas(int maxPersonas) {
		this.maxPersonas = maxPersonas;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getEquipoNecesario() {
		return equipoNecesario;
	}

	public void setEquipoNecesario(String equipoNecesario) {
		this.equipoNecesario = equipoNecesario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}
	@Override
	public String toString() {
		return "Minimo de personas= " + minPersonas
				+ "\nMaximo de personas= " + maxPersonas
				+ "\nDia= " + dia
				+ "\nHora de inicio= " + horaInicio
				+ "\nHora fin= " + horaFin
				+ "\nMonitor= " + monitor
				+ "\nDificultad= " + dificultad
				+ "\nEquipo necesario= " + equipoNecesario
				+ "\nDescripcion= " + descripcion;
	}

}
