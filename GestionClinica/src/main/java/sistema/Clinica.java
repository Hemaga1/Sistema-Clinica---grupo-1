package sistema;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import facturacion.*;
import honorarios.*;
import personas.*;
import lugares.*;

public class Clinica {
	
	private String nombre;
	private String direccion;
	private String telefono;
	private String ciudad;
	
	private SalaDeEspera salaDeEspera;
	
    private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    private ArrayList<IMedico> medicos = new ArrayList<IMedico>();
	
	public Clinica(String nombre, String direccion, String telefono, String ciudad) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.salaDeEspera = new SalaDeEspera();
	}

    public void registraMedico(IMedico medico) {
		medicos.add(medico);
	}
	
	public void registraPaciente(Paciente paciente) {
		pacientes.add(paciente);
	}
	
    public void removeMedico(IMedico medico) {
		medicos.remove(medico);
	}
	
	public void ingresaPaciente(Paciente paciente) {
		salaDeEspera.ingresar(paciente);
	}
	
    public void atiendePaciente(IMedico medico, Paciente paciente) {
        paciente.agregarMedico(medico);
		salaDeEspera.sacarPaciente(paciente);
	}
	
	public void internaPaciente(Paciente paciente, Habitacion habitacion) {
		
	}
	
	   public Factura egresaPaciente(Paciente paciente)/* throws PacienteInvalidoException*/ {
           if (!pacientes.contains(paciente)) {
               //throw new PacienteInvalidoException("El paciente no está registrado en la clínica");
           }

           Factura facturaNueva = new Factura(paciente);

           Set<IMedico> medicosPaciente = new HashSet<>();
           medicosPaciente.addAll(paciente.getConsultasMedicos());


           //simple iteration
           String fecha = "99/99/9999"; //habría que poner la fecha de la factura

           for (IMedico medico : medicosPaciente) {
               medico.agregarAtendido(new Reporte(paciente, fecha, medico.calcularHonorarios()));
           }

           paciente.sacarMedicos(); //vaciar la lista de medicos para la proxima vez que venga el paciente
           pacientes.remove(paciente);
           System.out.println("Nº Factura: " + facturaNueva.getNumeroFactura());
           System.out.println(facturaNueva.ImprimeFactura());
           return facturaNueva;
       }

	   @Override
	   public String toString() {
		return "Clinica nombre: " + nombre + " direccion: " + direccion + " telefono: " + telefono + " ciudad: " + ciudad;
	   }

	   
	

}