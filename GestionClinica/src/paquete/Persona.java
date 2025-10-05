package paquete;

public abstract class Persona {
	private String DNI;
	private String nombre;
	private String apellido;
	private String domicilio;
	private String ciudad;
	private String telefono;
	
	public Persona(String DNI, String nombre, String apellido, String domicilio, String ciudad, String telefono) {
		super();
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return " DNI: " + DNI + " nombre: " + nombre + " apellido: " + apellido + " domicilio: " + domicilio + " ciudad: " + ciudad + " telefono: " + telefono;
	}
	
	
	
}
