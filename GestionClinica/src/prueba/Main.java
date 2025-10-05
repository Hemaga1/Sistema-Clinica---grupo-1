package prueba;

import paquete.*;
import honorarios.*;
import factorias.*;

public class Main {

	public static void main(String[] args) {
		FactoryMedico factoryMedico = new FactoryMedico();
		FactoryPaciente factoryPaciente = new FactoryPaciente();
		
		IHonorario medico = factoryMedico.crearMedico("CLINICA", "PERMANENTE", "MASTER", "412421412", "Pablito", "Gonzalez", "Colon", "Mar del Plata", "32132321", "312331");
		
		System.out.print(medico + "\n");
		System.out.print("Sueldo: $"+medico.calcularHonorarios()+"\n");
		
		Paciente paciente = factoryPaciente.crearPaciente("Ninio", "43543554", "Martina", "Lopez", "Independencia", "Mar del Plata", "37128937",1);
		
		System.out.print(paciente);
		
		

	}

}
