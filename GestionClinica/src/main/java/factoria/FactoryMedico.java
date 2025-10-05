package factoria;

import honorarios.*;
import personas.Medico;

public class FactoryMedico {

    public IMedico crearMedico(String DNI, String nombre, String apellido, String domicilio, String ciudad, String telefono, String matricula, String especialidad, String contratacion, String posgrado) {
        IMedico medico = new Medico(DNI, nombre, apellido, domicilio, ciudad, telefono, matricula);
		medico = getEspecialidad(especialidad, medico);
		medico = getContratacion(contratacion, medico);
		medico = getPosgrado(posgrado, medico);
		return medico;
	}
	
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
	
    public IMedico getContratacion(String tipo, IMedico medico) {
		if (tipo.equalsIgnoreCase("PERMANENTE"))
			return new ContratacionPermanente(medico);
		else
			if (tipo.equalsIgnoreCase("RESIDENTE"))
				return new ContratacionResidente(medico);
			else return medico;	
	}

    public IMedico getPosgrado(String tipo, IMedico medico) {
		if (tipo.equalsIgnoreCase("MASTER"))
			return new PosgradoMaster(medico);
		else
			if (tipo.equalsIgnoreCase("DOCTORADO"))
				return new PosgradoDoctorado(medico);
			else return medico;	
	}
}
