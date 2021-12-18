package dominio;

public class Actividad {
	private int id,minimos,maximos,edadRec; //edad recomendada
	private String actividad, horario, materiales;
	private double preciohora;
	public Actividad(int id, int minimos, int maximos, int edadRec, String actividad, String horario, String materiales,
			double preciohora, Monitor monitor) {
		this.id = id;
		this.minimos = minimos;
		this.maximos = maximos;
		this.edadRec = edadRec;
		this.actividad = actividad;
		this.horario = horario;
		this.materiales = materiales;
		this.preciohora = preciohora;
		this.monitor = monitor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMinimos() {
		return minimos;
	}
	public void setMinimos(int minimos) {
		this.minimos = minimos;
	}
	public int getMaximos() {
		return maximos;
	}
	public void setMaximos(int maximos) {
		this.maximos = maximos;
	}
	public int getEdadRec() {
		return edadRec;
	}
	public void setEdadRec(int edadRec) {
		this.edadRec = edadRec;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getMateriales() {
		return materiales;
	}
	public void setMateriales(String materiales) {
		this.materiales = materiales;
	}
	public double getPreciohora() {
		return preciohora;
	}
	public void setPreciohora(double preciohora) {
		this.preciohora = preciohora;
	}
	public Monitor getMonitor() {
		return monitor;
	}
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	private Monitor monitor;
}
