package Sistema;
import java.util.ArrayList;

import Facturacion.Factura;
import Lugares.Habitacion;
import Lugares.Patio;
import Lugares.SalaDeEspera;
import Personas.Medico;
import Personas.Paciente;

public class Clinica {
	
	private String nombre;
	private String direccion;
	private String telefono;
	private String ciudad;
	
	private SalaDeEspera salaDeEspera;
	
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	
	public Clinica(String nombre, String direccion, String telefono, String ciudad) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.salaDeEspera = new SalaDeEspera();
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
	
	   public Factura egresaPaciente(Paciente paciente)/* throws PacienteInvalidoException*/ {
	        if (!pacientes.contains(paciente)) {
	            //throw new PacienteInvalidoException("El paciente no está registrado en la clínica");
	        }

	        Factura facturaNueva = new Factura(paciente);
	        pacientes.remove(paciente);
	        System.out.println("Nº Factura: " + facturaNueva.getNumeroFactura());
	        System.out.println(facturaNueva.ImprimeFactura());
	        return facturaNueva;
	    }

	   @Override
	   public String toString() {
		return "Clinica [nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", ciudad="
				+ ciudad + "]";
	   }

	   
	

}