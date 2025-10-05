package Prueba;

import Personas.*;
import Sistema.Clinica;
import Lugares.*;
import Facturacion.Factura;
import factoria.FactoryMedico;
import honorarios.*;

public class main {

	public static void main(String[] args) {		

		Clinica clinica = new Clinica("Clinica San Juan", "Av. Principal 123", "555-1234", "Buenos Aires");
		System.out.println("Clinica creada: " + clinica.toString());
		
		// Testing FactoryMedico
		FactoryMedico factoryMedico = new FactoryMedico();
		IHonorario medico = factoryMedico.crearMedico("CLINICA", "PERMANENTE", "MASTER", "412421412", "312331", "Pablo", "Gonzalez", "Colon", "Mar del Plata", "32132321");
		System.out.println("Sueldo del medico creado con factory: $" + medico.calcularHonorarios());

//		clinica.registraMedico((Medico)medico);
//		System.out.println("Medicos registrados: " + medico1.getNombre() + " y " + medico2.getNombre());
//
//
//		Ninio ninio1 = new Ninio("11111111", "Carlos", "Lopez", "Calle 3", "Buenos Aires", "555-0003", 1001);
//		Joven joven1 = new Joven("22222222", "Ana", "Martinez", "Calle 4", "Buenos Aires", "555-0004", 1002);
//		Mayor mayor1 = new Mayor("33333333", "Roberto", "Silva", "Calle 5", "Buenos Aires", "555-0005", 1003);
//
//
//		clinica.registraPaciente(ninio1);
//		clinica.registraPaciente(joven1);
//		clinica.registraPaciente(mayor1);
//		System.out.println("Pacientes registrados: " + ninio1.toString() + ", " +
//						   joven1.toString() + ", " + mayor1.toString());
//
//
//		clinica.ingresaPaciente(ninio1);
//
//		clinica.ingresaPaciente(joven1);
//
//		clinica.ingresaPaciente(mayor1);
//
//
//		System.out.println("\n--- Atendiendo pacientes ---");
//		clinica.atiendePaciente(medico1, ninio1);
//		System.out.println("Dr. " + medico1.getApellido() + " atendio al nino");
//
//		clinica.atiendePaciente(medico2, joven1);
//		System.out.println("Dra. " + medico2.getApellido() + " atendio al joven");
//
//		HabitacionCompartida habitacion = new HabitacionCompartida(500.0);
//		System.out.println("\n--- Internando paciente ---");
//		clinica.internaPaciente(mayor1, habitacion);
//		System.out.println("Mayor internado en habitacion compartida");
//
//		double arancel = habitacion.calculaCosto(5);
//		System.out.println("Arancel por 5 dias: $" + arancel);
//
//
//		Factura factura1 = clinica.egresaPaciente(ninio1);
//
//
//		Factura factura2 = clinica.egresaPaciente(joven1);
//
//		Factura factura3 = clinica.egresaPaciente(mayor1);

	}

}