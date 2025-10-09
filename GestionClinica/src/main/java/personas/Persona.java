package personas;

import java.util.Objects;

public abstract class Persona {
	private String DNI;
	private String nombre;
	private String apellido;
	private Domicilio domicilio;
	private String telefono;
	
	public Persona(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono) {
		super();
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.domicilio = new Domicilio(calle, numero, ciudad);
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

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

    @Override
    public String toString() {
        return nombre + " " + apellido + " (DNI: " + DNI + ") - Domicilio: " + domicilio + " - Tel: " + telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(DNI, persona.DNI) && Objects.equals(nombre, persona.nombre) && Objects.equals(apellido, persona.apellido);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
