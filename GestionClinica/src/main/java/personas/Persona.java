package personas;

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

	public String getDNI() {
		return DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

    @Override
    public String toString() {
        return " DNI: " + DNI + " nombre: " + nombre + " apellido: " + apellido + " domicilio: " + domicilio + " ciudad: " + ciudad + " telefono: " + telefono;
    }
	
	
	
}
