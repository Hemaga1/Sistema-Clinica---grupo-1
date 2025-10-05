package paquete;
import java.util.ArrayList;

public class Clinica {
	public static int sigFactura = 0;
	
	private String nombre;
	private String direccion;
	private String telefono;
	private String ciudad;
	
	private SalaDeEspera salaDeEspera;
	private Patio patio;
	
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	
	public Clinica(String nombre, String direccion, String telefono, String ciudad) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
	}

	public void registraMedico(Medico medico) {
		medicos.add(medico);
	}
	
	public void registraPaciente(Paciente paciente) {
		pacientes.add(paciente);
	}
	
	public void removeMedico(Medico medico) {
		medicos.remove(medico);
	}
	
	public void ingresaPaciente(Paciente paciente) {
		salaDeEspera.ingresar(paciente);
	}
	
	public void atiendePaciente(Medico medico, Paciente paciente) {
		salaDeEspera.sacarPaciente(paciente);
	}
	
	public void internaPaciente(Paciente paciente, Habitacion habitacion) {
		
	}
	
	public Factura egresaPaciente(Paciente paciente) {
		sigFactura++;
		System.out.print("Nº Factura: "+sigFactura);
		// . . .
		pacientes.remove(paciente);
		return  null; //puse null solo para que por ahora no tire error
	}
	

}
