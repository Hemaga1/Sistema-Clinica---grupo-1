package factoria;

import honorarios.*;
import Personas.Medico;

public class FactoryMedico {

	public IHonorario crearMedico(String especialidad, String contratacion, String posgrado, String dni, String matricula, String nombre, String apellido, String domicilio, String ciudad, String telefono) {
		IHonorario medico = new Medico(dni, matricula, nombre, apellido, domicilio, ciudad, telefono);
		medico = getEspecialidad(especialidad, medico);
		medico = getContratacion(contratacion, medico);
		medico = getPosgrado(posgrado, medico);
		return medico;
	}
	
	public IHonorario getEspecialidad(String tipo, IHonorario medico) {
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
	
	public IHonorario getContratacion(String tipo, IHonorario medico) {
		if (tipo.equalsIgnoreCase("PERMANENTE"))
			return new ContratacionPermanente(medico);
		else
			if (tipo.equalsIgnoreCase("RESIDENTE"))
				return new ContratacionResidente(medico);
			else return medico;	
	}

	public IHonorario getPosgrado(String tipo, IHonorario medico) {
		if (tipo.equalsIgnoreCase("MASTER"))
			return new PosgradoMaster(medico);
		else
			if (tipo.equalsIgnoreCase("DOCTORADO"))
				return new PosgradoDoctorado(medico);
			else return medico;	
	}
}
