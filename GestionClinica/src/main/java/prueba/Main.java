package prueba;

import facturacion.*;
import factoria.*;
import honorarios.*;
import personas.*;
import lugares.*;
import sistema.*;

/**
 * Clase principal donde se simulan las distintas operaciones de la clínica a través de llamadas a los métodos del sistema
 */
public class Main {

	public static void main(String[] args) {
		Clinica clinica = Clinica.getInstancia("Clinica Central", "Avenida Salud", 105, "+54 11 1234-5678", "Buenos Aires");
		SistemaFacade sistema = new SistemaFacade(clinica);

		FactoryMedico factoryMedico = new FactoryMedico();
		FactoryPaciente factoryPaciente = new FactoryPaciente();

		// Médicos
		IMedico medClinica = factoryMedico.crearMedico("10000000", "Ana", "García", "Calle 1", 1, "CABA", "111-1111", "M-123", "CLINICA", "PERMANENTE", "MASTER");
		IMedico medCirugia = factoryMedico.crearMedico("10000001", "Bruno", "Lopez", "Calle 2", 2, "CABA", "222-2222", "M-456", "CIRUGIA", "RESIDENTE", "DOCTORADO");
		IMedico medPediatra = factoryMedico.crearMedico("10000001", "Bianca", "Gonzalez", "Calle 2", 2, "CABA", "222-2222", "M-456", "PEDIATRIA", "RESIDENTE", "DOCTORADO");

		// Pacientes
		Paciente p1 = factoryPaciente.crearPaciente("20000000", "Juan", "Pérez", "Calle 10", 10, "CABA", "300-0000", 12345, "JOVEN");
		Paciente p2 = factoryPaciente.crearPaciente("20000001", "Lucía", "Suárez", "Calle 11", 11, "CABA", "300-0001", 22345, "NINIO");
		Paciente p3 = factoryPaciente.crearPaciente("20000002", "Mario", "Gómez", "Calle 12", 12, "CABA", "300-0002", 32345, "MAYOR");
		Paciente p4 = factoryPaciente.crearPaciente("20000002", "Mario", "Gómez", "Calle 9", 9, "CABA", "300-0003", 32346, "MAYOR");

		// Registro
		sistema.registraMedico(medClinica);
		sistema.registraMedico(medCirugia);
		sistema.registraMedico(medPediatra);
		sistema.registraPaciente(p1);
		sistema.registraPaciente(p2);
		sistema.registraPaciente(p3);
        sistema.registraPaciente(p4);

		// Ingresos a sala
		sistema.ingresaPaciente(p1);
		sistema.ingresaPaciente(p2);
		sistema.ingresaPaciente(p3);

		// Atenciones
		sistema.atiendePaciente(medClinica, p1);
		sistema.atiendePaciente(medClinica, p2);
		sistema.atiendePaciente(medPediatra, p2);
		sistema.atiendePaciente(medCirugia, p2);
        sistema.atiendePaciente(medCirugia, p3);
        sistema.atiendePaciente(medCirugia, p4);

		// Internación de p2
		Habitacion habPrivada = new HabitacionPrivada(2000);
		sistema.internaPaciente(p2, habPrivada);

		// Caso de prueba: superar capacidad de internación (Habitación privada = 1)
		Habitacion habPrivadaTest = new HabitacionPrivada(1500);
		sistema.internaPaciente(p1, habPrivadaTest);
		// Esta llamada debería disparar InternacionCapacidadExcedidaExcepcion
		sistema.internaPaciente(p2, habPrivadaTest);

		// Egresos
		Factura f1 = sistema.egresaPaciente(p1);
		Factura f2 = sistema.egresaPaciente(p2, 2);
        Factura f3 = sistema.egresaPaciente(p3);

		System.out.println("==== FACTURA P1 ====");
		System.out.println(f1.ImprimeFactura());

		System.out.println("==== FACTURA P2 (con internación) ====");
		System.out.println(f2.ImprimeFactura());

        System.out.println("==== FACTURA P3 ====");
        System.out.println(f3.ImprimeFactura());
		// Simular más consultas para el médico de clínica
		Paciente p6 = factoryPaciente.crearPaciente("20000003", "Carlos", "Rodriguez", "Calle 13", 13, "CABA", "300-0003", 42345, "JOVEN");
		Paciente p5 = factoryPaciente.crearPaciente("20000004", "Elena", "Martinez", "Calle 14", 14, "CABA", "300-0004", 52345, "MAYOR");

		sistema.registraPaciente(p6);
		sistema.registraPaciente(p5);
		sistema.ingresaPaciente(p6);
		sistema.ingresaPaciente(p5);

		// Atender pacientes con diferentes médicos
		sistema.atiendePaciente(medClinica, p6);
		sistema.atiendePaciente(medClinica, p5);
		sistema.atiendePaciente(medCirugia, p3);

		// Egresar pacientes
		Factura f6 = sistema.egresaPaciente(p6);
		Factura f4 = sistema.egresaPaciente(p5);
		Factura f5 = sistema.egresaPaciente(p3);

		ReporteActividadMedica reporteClinica = sistema.generarReporteActividadMedica(medClinica, "01/01/2024", "31/12/2027");
		System.out.println(reporteClinica.generarReporte());

		ReporteActividadMedica reporteCirugia = sistema.generarReporteActividadMedica(medCirugia, "01/01/2024", "31/12/2027");
		System.out.println(reporteCirugia.generarReporte());

		ReporteActividadMedica reportePeriodo = sistema.generarReporteActividadMedica(medClinica, "01/12/2024", "07/12/2027");
		System.out.println(reportePeriodo.generarReporte());
	}

}