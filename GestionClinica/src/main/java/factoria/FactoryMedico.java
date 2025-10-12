package factoria;

import honorarios.*;
import personas.Medico;

/**
 * Clase Factory encargada de la creación de objetos IMedico.<br>
 * Utiliza el patrón Factory para centralizar la instanciación de un objeto Medico base y aplica de forma secuencial y transparente los Decoradores de honorarios (Especialidad, Contratación y Posgrado).
 */

public class FactoryMedico {

	/**
	 * El método instancia un objeto Medico base y luego le aplica las tres capas de decoración (Especialidad, Contratacion y Posgrado) basándose en los tipos de String de entrada.<br>
	 * <b>Precondición: </b> Los parámetros DNI, nombre, apellido, matrícula y especialidad son válidos para crear un objeto Medico base.
	 * @param DNI Dni del médico, dni!=null, dni!=""
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
        IMedico medico = new Medico(DNI, nombre, apellido, calle, numero, ciudad, telefono, matricula, especialidad);
		medico = getEspecialidad(especialidad, medico);
		medico = getContratacion(contratacion, medico);
		medico = getPosgrado(posgrado, medico);
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
		if (tipo.equalsIgnoreCase("CLINICA"))
			return new EspecialidadClinica(medico);
		else
			if (tipo.equalsIgnoreCase("CIRUGIA"))
				return new EspecialidadCirugia(medico);
			else
				if (tipo.equalsIgnoreCase("PEDIATRIA"))
					return new EspecialidadPediatria(medico);
				else return medico;
	}

	/**
	 * Aplica el Decorador de Contratación al objeto IMedico.
	 *
	 * @param tipo El tipo de contratación a aplicar ("PERMANENTE", "RESIDENTE").
	 * @param medico El componente IMedico actual que será decorado. medico!=null
	 * @return El objeto IMedico decorado con el tipo de Contratación, o el objeto base si el tipo no es reconocido.
	 */
    public IMedico getContratacion(String tipo, IMedico medico) {
		if (tipo.equalsIgnoreCase("PERMANENTE"))
			return new ContratacionPermanente(medico);
		else
			if (tipo.equalsIgnoreCase("RESIDENTE"))
				return new ContratacionResidente(medico);
			else return medico;	
	}

	/**
	 * Aplica el Decorador de Posgrado al objeto IMedico.
	 *
	 * @param tipo El tipo de posgrado a aplicar ("MASTER", "DOCTORADO").
	 * @param medico El componente IMedico actual que será decorado. medico!=null
	 * @return El objeto IMedico decorado con el tipo de Posgrado, o el objeto base si el tipo no es reconocido.
	 */
    public IMedico getPosgrado(String tipo, IMedico medico) {
		if (tipo.equalsIgnoreCase("MASTER"))
			return new PosgradoMagister(medico);
		else
			if (tipo.equalsIgnoreCase("DOCTORADO"))
				return new PosgradoDoctorado(medico);
			else return medico;	
	}
}
