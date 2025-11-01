package modelo.factoria;

import modelo.honorarios.*;
import modelo.interfaces.IMedico;
import modelo.personas.Medico;

/**
 * Clase Factory encargada de la creación de objetos IMedico.<br>
 * Utiliza el patrón Factory para centralizar la instanciación de un objeto Medico base y aplica de forma secuencial y transparente los Decoradores de modelo.honorarios (Especialidad, Contratación y Posgrado).
 */

public class FactoryMedico {

	/**
	 * El método instancia un objeto Medico base y luego le aplica las tres capas de decoración (Especialidad, Contratacion y Posgrado) basándose en los tipos de String de entrada.<br>
	 * <b>Precondición: </b> Los parámetros DNI, nombre, apellido, matrícula y especialidad son válidos para crear un objeto Medico base.
	 * @param DNI Dni del médico, DNI!=null, DNI!=""
	 * @param nombre Nombre del médico, nombre!=null, nombre!=""
	 * @param apellido Apellido del médico, apellido!=null, apellido!=""
	 * @param calle Calle del domicilio del médico, calle!=null, calle!=""
	 * @param numero Numero del domicilio del médico, numero>=o
	 * @param ciudad Ciudad del medico, ciudad!=null, ciudad!=""
	 * @param telefono Teléfono del medico, telefono!=null, telefono!=""
	 * @param matricula Matrícula del medico, matricula!=null, matricula!=""
	 * @param especialidad Especialidad del medico, especialidad!=null, especialidad!=""
	 * @param contratacion Tipo de Contratación del medico, contratacion!=null, contratacion!=""
	 * @param posgrado Posgrado que posee el médico, posgrado!=null, posgrado!=""
	 * @return el medico creado
	 */

    public IMedico crearMedico(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono, String matricula, String especialidad, String contratacion, String posgrado) {
		assert DNI!=null && !DNI.isEmpty(): "DNI no puede ser null ni vacío";
		assert nombre!=null && !nombre.isEmpty() : "Nombre no puede ser null ni vacío";
		assert apellido!=null && !apellido.isEmpty() : "Apellido no puede ser null ni vacío";
		assert calle!=null && !calle.isEmpty() : "Calle no puede ser null ni vacía";
		assert numero >= 0 : "Número de domicilio no puede ser negativo";
		assert ciudad!=null && !ciudad.isEmpty() : "Ciudad no puede ser null ni vacía";
		assert telefono!=null && !telefono.isEmpty() : "Teléfono no puede ser null ni vacío";
		assert matricula!=null && !matricula.isEmpty() : "Matrícula no puede ser null ni vacía";
		assert especialidad != null && !especialidad.isEmpty() :"Especialidad no puede ser null ni vacía";
		assert contratacion != null && !contratacion.isEmpty() : "Contratación no puede ser null ni vacía";
		assert posgrado != null && !posgrado.isEmpty(): "Posgrado no puede ser null ni vacío";

		IMedico medico = new Medico(DNI, nombre, apellido, calle, numero, ciudad, telefono, matricula, especialidad);
		medico = getEspecialidad(especialidad, medico);
		medico = getContratacion(contratacion, medico);
		medico = getPosgrado(posgrado, medico);

		assert medico != null : "El médico creado no debe ser null";
		return medico;
	}

	/**
	 * Aplica el Decorador de Especialidad al objeto IMedico base.
	 *
	 * @param tipo El tipo de especialidad a aplicar ("CLINICA", "CIRUGIA", "PEDIATRIA").
	 * @param medico El componente IMedico actual que será decorado. medico!=null
	 * @return El objeto IMedico decorado con la Especialidad correspondiente, o el objeto base si el tipo no es reconocido.
	 */
    public IMedico getEspecialidad(String tipo, IMedico medico) {
		assert medico!=null : "El médico base no puede ser null";
		assert tipo!=null && !tipo.isEmpty() : "El tipo de especialidad no puede ser null ni estar vacío";

		IMedico decorado;
		if (tipo.equalsIgnoreCase("CLINICA"))
			decorado = new EspecialidadClinica(medico);
		else
			if (tipo.equalsIgnoreCase("CIRUGIA"))
				decorado = new EspecialidadCirugia(medico);
			else
				if (tipo.equalsIgnoreCase("PEDIATRIA"))
					decorado =  new EspecialidadPediatria(medico);
				else decorado = medico;

		assert decorado != null : "El objeto IMedico retornado no puede ser null";

		return decorado;
	}

	/**
	 * Aplica el Decorador de Contratación al objeto IMedico.
	 *
	 * @param tipo El tipo de contratación a aplicar ("PERMANENTE", "RESIDENTE").
	 * @param medico El componente IMedico actual que será decorado. medico!=null
	 * @return El objeto IMedico decorado con el tipo de Contratación, o el objeto base si el tipo no es reconocido.
	 */
    public IMedico getContratacion(String tipo, IMedico medico) {
		assert tipo!=null && !tipo.isEmpty() : "El tipo de contratación no puede ser null ni estar vacía";
		assert medico!=null : "El médico base no puede ser null";

		IMedico decorado;

		if (tipo.equalsIgnoreCase("PERMANENTE"))
			decorado = new ContratacionPermanente(medico);
		else
			if (tipo.equalsIgnoreCase("RESIDENTE"))
				decorado =  new ContratacionResidente(medico);
			else decorado = medico;

		assert decorado != null : "El objeto IMedico retornado no puede ser null";

		return decorado;
	}

	/**
	 * Aplica el Decorador de Posgrado al objeto IMedico.
	 *
	 * @param tipo El tipo de posgrado a aplicar ("MASTER", "DOCTORADO").
	 * @param medico El componente IMedico actual que será decorado. medico!=null
	 * @return El objeto IMedico decorado con el tipo de Posgrado, o el objeto base si el tipo no es reconocido.
	 */
    public IMedico getPosgrado(String tipo, IMedico medico) {
		assert tipo!=null && !tipo.isEmpty() : "El tipo de contratación no puede ser null ni estar vacía";
		assert medico!=null : "El médico base no puede ser null";

		IMedico decorado;

		if (tipo.equalsIgnoreCase("MASTER"))
			decorado = new PosgradoMagister(medico);
		else
			if (tipo.equalsIgnoreCase("DOCTORADO"))
				decorado = new PosgradoDoctorado(medico);
			else decorado = medico;

		assert decorado != null : "El objeto IMedico retornado no puede ser null";

		return decorado;
	}
}
