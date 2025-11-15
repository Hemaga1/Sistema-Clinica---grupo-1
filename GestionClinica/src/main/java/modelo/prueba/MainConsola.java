package modelo.prueba;

import modelo.facturacion_y_registros.*;
import modelo.factoria.*;
import modelo.interfaces.IMedico;
import modelo.personas.*;
import modelo.lugares.*;
import modelo.sistema.*;

/**
 * Clase principal donde se simulan las distintas operaciones de la clínica a través de llamadas a los métodos del modelo.sistema
 */
public class MainConsola {

	public static void main(String[] args) {
		System.out.println("============================================================");
		System.out.println("           SISTEMA DE GESTION CLINICA");
		System.out.println("============================================================");
		
		Clinica clinica = Clinica.getInstancia("Clinica Central", "Avenida San Martin", 105, "+54 11 1234-5678", "Buenos Aires");
		SistemaFacade sistema = SistemaFacade.getInstancia(clinica);

		FactoryMedico factoryMedico = new FactoryMedico();
		FactoryPaciente factoryPaciente = new FactoryPaciente();

		System.out.println("\n========================================");
		System.out.println("1. CREACION DE MEDICOS Y PACIENTES");
		System.out.println("========================================");

		// Médicos
		IMedico medClinica = factoryMedico.crearMedico("10000000", "Ana", "García", "Calle 1", 1, "CABA", "111-1111", "M-123", "CLINICA", "PERMANENTE", "MASTER");
		IMedico medCirugia = factoryMedico.crearMedico("10000001", "Bruno", "Lopez", "Calle 2", 2, "CABA", "222-2222", "M-456", "CIRUGIA", "RESIDENTE", "DOCTORADO");
		IMedico medPediatra = factoryMedico.crearMedico("10000002", "Bianca", "Gonzalez", "Calle 2", 2, "CABA", "222-2222", "M-456", "PEDIATRIA", "RESIDENTE", "DOCTORADO");

		// Pacientes
		Paciente p1 = factoryPaciente.crearPaciente("20000000", "Juan", "Pérez", "Calle 10", 10, "CABA", "300-0000", 12345, "JOVEN");
		Paciente p2 = factoryPaciente.crearPaciente("20000001", "Lucía", "Suárez", "Calle 11", 11, "CABA", "300-0001", 22345, "NINIO");
		Paciente p3 = factoryPaciente.crearPaciente("20000002", "Mario", "Gómez", "Calle 12", 12, "CABA", "300-0002", 32345, "MAYOR");

		// Registro
        try {
            sistema.registraMedico(medClinica);
            sistema.registraMedico(medCirugia);
            sistema.registraMedico(medPediatra);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        try {
            sistema.registraPaciente(p1);
            sistema.registraPaciente(p2);
            sistema.registraPaciente(p3);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

		System.out.println("\n========================================");
		System.out.println("2. INGRESO DE PACIENTES AL SISTEMA");
		System.out.println("========================================");

		// Ingresos a sala
		sistema.ingresaPaciente(p1);
		sistema.ingresaPaciente(p2);
		sistema.ingresaPaciente(p3);

		System.out.println("\n========================================");
		System.out.println("3. ATENCION MEDICA");
		System.out.println("========================================");

		// Atenciones
        try {
            sistema.atiendePaciente(medClinica, p1);
            sistema.atiendePaciente(medClinica, p2);
            sistema.atiendePaciente(medPediatra, p2);
            sistema.atiendePaciente(medCirugia, p2);
            sistema.atiendePaciente(medCirugia, p3);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

		System.out.println("\n========================================");
		System.out.println("4. GESTION DE HABITACIONES");
		System.out.println("========================================");

		// Agregar habitación
		Habitacion habPrivada = new HabitacionPrivada(2000);
		sistema.agregarHabitacion(habPrivada);
		System.out.println("Habitacion privada agregada al modelo.sistema");

		Habitacion habCompartida = new HabitacionCompartida(1500, 2);
		sistema.agregarHabitacion(habCompartida);
		System.out.println("Habitacion compartida agregada al modelo.sistema");

		System.out.println("\n--- INTERNACION DE PACIENTES ---");

//		Internación de p2
        try {
            sistema.internaPaciente(p2, habPrivada);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

		// Caso de modelo.prueba: superar capacidad de internación (Habitación privada = 1)
		Habitacion habPrivadaTest = new HabitacionPrivada(1500);
        try {
            sistema.internaPaciente(p1, habPrivadaTest);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

		// Esta llamada debería disparar InternacionCapacidadExcedidaExcepcion
		System.out.println("Intentando internar paciente en habitación ya ocupada...");
        try {
            sistema.internaPaciente(p3, habPrivadaTest);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

		System.out.println("\n========================================");
		System.out.println("5. EGRESO Y FACTURACION");
		System.out.println("========================================");

		// Egresos
        try {
            Factura f1 = sistema.egresaPaciente(p1, 9);
            Factura f2 = sistema.egresaPaciente(p2);
            Factura f3 = sistema.egresaPaciente(p3);

            System.out.println("\n--- FACTURAS GENERADAS ---");

            System.out.println(f1.imprimeFactura());

            System.out.println(f2.imprimeFactura());

            System.out.println(f3.imprimeFactura());
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }



		System.out.println("\n========================================");
		System.out.println("6. SEGUNDA RONDA DE ATENCIONES");
		System.out.println("========================================");

		// Simular más consultas para el médico de clínica
		Paciente p6 = factoryPaciente.crearPaciente("20000003", "Carlos", "Rodriguez", "Calle 13", 13, "CABA", "300-0003", 42345, "JOVEN");
		Paciente p5 = factoryPaciente.crearPaciente("20000004", "Elena", "Martinez", "Calle 14", 14, "CABA", "300-0004", 52345, "MAYOR");

		System.out.println("\n--- NUEVOS PACIENTES ---");
        try {
            sistema.registraPaciente(p6);
            sistema.registraPaciente(p5);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

		sistema.ingresaPaciente(p6);
		sistema.ingresaPaciente(p5);

		System.out.println("\n--- NUEVAS ATENCIONES ---");
		// Atender pacientes con diferentes médicos
        try {
            sistema.atiendePaciente(medClinica, p6);
            sistema.atiendePaciente(medClinica, p5);
            sistema.atiendePaciente(medCirugia, p3);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

		System.out.println("\n--- NUEVOS EGRESOS ---");
		// Egresar pacientes
        try {
            Factura f6 = sistema.egresaPaciente(p6);
            Factura f4 = sistema.egresaPaciente(p5);
            Factura f5 = sistema.egresaPaciente(p3);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

		System.out.println("\n========================================");
		System.out.println("7. REPORTES DE ACTIVIDAD MÉDICA");
		System.out.println("========================================");

		ReporteActividadMedica reporteClinica = sistema.generarReporteActividadMedica(medClinica, "01/01/2024", "31/12/2027");
		System.out.println(reporteClinica.generarReporte());

		ReporteActividadMedica reporteCirugia = sistema.generarReporteActividadMedica(medCirugia, "01/01/2024", "31/12/2027");
		System.out.println(reporteCirugia.generarReporte());

	}

}