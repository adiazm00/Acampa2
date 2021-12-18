package dominio;

public class Cliente {
	private String nombre,telefono, id;
	
	public Cliente(String id,String nombre, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.telefono=telefono;
	}
	public Cliente() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
