package modelo.factoria;

import modelo.personas.*;

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
	 * @param rangoEtario Rango Eterario del paciente, rangoEtario!=null, rangoEtario!=""
	 * @return Una subclase de Paciente (Ninio, Joven, Mayor) o null si el rango etario no es válido.
	 */
	public Paciente crearPaciente(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono, int historiaClinica, String rangoEtario) {
		assert DNI!=null && !DNI.isEmpty() : "El DNI no puede ser null ni estar vacío";
		assert nombre!=null && !nombre.isEmpty() : "El nombre no puede ser null ni estar vacío";
		assert apellido!=null && !apellido.isEmpty() : "El apellido no puede ser null ni estar vacío";
		assert calle!=null && !calle.isEmpty() : "La calle no puede ser null ni estar vacía";
		assert numero>=0 : "El numero del domicilio no puede ser negativo";
		assert ciudad!=null && !ciudad.isEmpty() : "La ciudad no puede ser null ni estar vacía";
		assert telefono!=null && !telefono.isEmpty() : "El teléfono no puede ser null ni estar vacío";
		assert historiaClinica>=0 : "La historia clínica no puede ser negativa";
		assert rangoEtario!=null && !rangoEtario.isEmpty() : "El rango etario del paciente no puede ser null ni estar vacío";

		Paciente paciente;

		if (rangoEtario.equalsIgnoreCase("NINIO"))
			paciente = new Ninio(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
		else
			if (rangoEtario.equalsIgnoreCase("JOVEN"))
				paciente = new Joven(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
			else
				if (rangoEtario.equalsIgnoreCase("MAYOR"))
					paciente = new Mayor(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
				else paciente = null;

		assert paciente != null : "El objeto Paciente retornado no puede ser null";
		return paciente;
	}
	
}
