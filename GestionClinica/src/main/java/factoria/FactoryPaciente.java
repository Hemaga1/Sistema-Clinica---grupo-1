package factoria;

import personas.*;

/**
 * Clase Factory encargada de la creación de objetos de tipo Paciente. <br>
 * Implementa el patrón Factory para instanciar la subclase de Paciente adecuada (Niño, Joven o Mayor) basándose en el rango etario proporcionado en los parámetros de entrada.
 */

public class FactoryPaciente {

	/**
	 * Crea una instancia de la subclase de Paciente que corresponde al rango etario especificado.<br>
	 * La elección de la clase concreta (Ninio, Joven o Mayor) se determina mediante la comparación del parámetro rangoEtario<br>
	 *
	 * <b>Precondiciones:</b>
	 * <ul>
	 * 	<li>Todos los parámetros de tipo String deben ser válidos y no nulos.</li>
	 * 	<li>El parámetro historiaClinica debe ser >=0.</li>
	 * </ul><br>
	 * <b>Postcondiciones:</b>
	 * <ul>
	 * 	<li>Si rangoEtario es reconocido ("NINIO", "JOVEN" o "MAYOR"): Se devuelve una instancia de la clase de Paciente concreta (Ninio, Joven o Mayor).</li>
	 * 	<li>Si rangoEtario no es reconocido: El método devuelve null.</li>
	 * </ul>
	 * @param DNI Dni del médico, dni!=null, dni!=""
	 * @param nombre Nombre del paciente, nombre!=null, nombre!=""
	 * @param apellido Apellido del paciente, apellido!=null, apellido!=""
	 * @param calle Calle del domicilio del paciente, calle!=null, calle!=""
	 * @param numero Numero del domicilio del paciente, numero>=0
	 * @param ciudad Ciudad del paciente, ciudad!=null, ciudad!=""
	 * @param telefono Teléfono del paciente, telefono!=null, telefono!=""
	 * @param historiaClinica Historia Clínica del paciente, historiaClinica>=0
	 * @param rangoEtario Rango Eterario del paciente
	 * @return Una subclase de Paciente (Ninio, Joven, Mayor) o null si el rango etario no es válido.
	 */
	public Paciente crearPaciente(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono, int historiaClinica, String rangoEtario) {
		if (rangoEtario.equalsIgnoreCase("NINIO"))
			return new Ninio(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
		else
			if (rangoEtario.equalsIgnoreCase("JOVEN"))
				return new Joven(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
			else
				if (rangoEtario.equalsIgnoreCase("MAYOR"))
					return new Mayor(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
				else return null;
	}
	
}
