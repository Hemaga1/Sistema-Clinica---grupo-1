package modelo.personas;

import java.util.Objects;

/**
 * Clase abstracta que será extendida desde medico y paciente, con los datos que contienen en común
 */
public abstract class Persona {
	private String DNI;
	private String nombre;
	private String apellido;
	private Domicilio domicilio;
	private String telefono;

	/**
	 * Constructor de la persona
	 * @param DNI dni!=null, dni!=""
	 * @param nombre nombre!=null, nombre!=""
	 * @param apellido apellido!=null, apellido!=""
	 * @param calle calle!=null, calle!=""
	 * @param numero numero>=o
	 * @param ciudad ciudad!=null, cidudad!=""
	 * @param telefono telefono!=null, telefono !=""
	 */
	public Persona(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono) {
		super();
		assert DNI!=null && DNI!="": "El dni de la persona, ya sea medico o paciente no debe ser null ni estar vacío";
		assert nombre!=null && nombre!="": "El nombre de la persona, ya sea medico o paciente no debe ser null ni estar vacío";
		assert apellido!=null && apellido!="": "El apellido de la persona, ya sea medico o paciente no debe ser null ni estar vacío";
		assert calle!=null && calle!="": "La calle del domicilio de la persona, ya sea medico o paciente no debe ser null ni estar vacía";
		assert numero>=0 : "El numero del domicilio no puede ser negativo";
		assert ciudad!=null && ciudad!="": "La ciudad del domicilio de la persona, ya sea medico o paciente no debe ser null ni estar vacía";
		assert telefono!=null && telefono!="": "El telefono de la persona, ya sea medico o paciente no debe ser null ni estar vacío";
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

	public String getTelefono() {
		return telefono;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

    @Override
    public String toString() {
        return nombre + " " + apellido + " (DNI: " + DNI + ") - Domicilio: " + domicilio + " - Tel: " + telefono;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(DNI, persona.DNI);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(DNI);
    }
}
