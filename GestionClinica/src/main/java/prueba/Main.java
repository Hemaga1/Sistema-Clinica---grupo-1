package prueba;

import facturacion.*;
import factoria.*;
import honorarios.*;
import personas.*;
import lugares.*;
import sistema.*;

public class Main {

	public static void main(String[] args) {
        FactoryMedico factoryMedico = new FactoryMedico();
        FactoryPaciente factoryPaciente = new FactoryPaciente();

		Clinica clinica = new Clinica("Clinica San Juan", "Av. Principal 123", "555-1234", "Buenos Aires");
		System.out.println(clinica);
		
		// Testing FactoryMedico
		IMedico medico = factoryMedico.crearMedico( "412421412", "Pablo", "Gonzalez", "Colon", "Mar del Plata", "32132321", "32132321", "CLINICA", "PERMANENTE", "MASTER");
		IMedico medico2 = factoryMedico.crearMedico( "523523523", "Ana", "Perez", "Rivadavia", "Buenos Aires", "111222333", "987654", "CIRUGIA", "RESIDENTE", "DOCTORADO");
		System.out.println("Sueldo del medico 1: $" + medico.calcularHonorarios());
		System.out.println("Sueldo del medico 2: $" + medico2.calcularHonorarios());

//		clinica.registraMedico((Medico)medico);
//		System.out.println("Medicos registrados: " + medico1.getNombre() + " y " + medico2.getNombre());
//
//
        Paciente paciente = factoryPaciente.crearPaciente("Ninio", "43543554", "Martina", "Lopez", "Independencia", "Mar del Plata", 1,"NINIO");

		// Registro en Clinica
		clinica.registraMedico(medico);
		clinica.registraMedico(medico2);
		clinica.registraPaciente(paciente);

		System.out.print(medico);
		System.out.print("\n");
		System.out.print(medico2);
		System.out.print("\n");
		System.out.print(paciente);

		clinica.ingresaPaciente(paciente);

		clinica.atiendePaciente(medico, paciente);

		HabitacionCompartida habitacion = new HabitacionCompartida(500.0);
		System.out.println("\n--- Internando paciente ---");
		clinica.internaPaciente(paciente, habitacion);
		double arancel = habitacion.calculaCosto(5);
		System.out.println("Arancel por 5 dias: $" + arancel);

		Factura factura = clinica.egresaPaciente(paciente);
		System.out.println("Factura generada N° " + factura.getNumeroFactura());
        
//		clinica.registraPaciente((Paciente)ninio1);
//		clinica.registraPaciente((Paciente)joven1);
//		clinica.registraPaciente((Paciente)mayor1);
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