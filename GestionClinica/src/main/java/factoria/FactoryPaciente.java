package factoria;

import personas.*;

public class FactoryPaciente {
	
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
