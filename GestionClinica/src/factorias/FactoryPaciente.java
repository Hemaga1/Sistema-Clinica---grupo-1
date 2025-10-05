package factorias;

import paquete.*;

public class FactoryPaciente {
	
	public Paciente crearPaciente(String rangoEtario, String DNI, String nombre, String apellido, String domicilio, String ciudad, String telefono, int historiaClinica) {
		if (rangoEtario.equalsIgnoreCase("NINIO"))
			return new Ninio(DNI, nombre, apellido, domicilio, ciudad, telefono, historiaClinica);
		else
			if (rangoEtario.equalsIgnoreCase("JOVEN"))
				return new Joven(DNI, nombre, apellido, domicilio, ciudad, telefono, historiaClinica);
			else
				if (rangoEtario.equalsIgnoreCase("MAYOR"))
					return new Mayor(DNI, nombre, apellido, domicilio, ciudad, telefono, historiaClinica);
				else return null;
	}
	
}
