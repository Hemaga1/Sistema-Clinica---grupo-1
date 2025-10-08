package sistema;
import java.util.*;

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

    Map<IMedico, List<PacienteAtendido>> medicos = new HashMap<>();
    Map<Paciente, RegistroPaciente> pacientesAtendidos = new HashMap<>();
	
	public Clinica(String nombre, String direccion, String telefono, String ciudad) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.salaDeEspera = new SalaDeEspera();
	}

    public void registraMedico(IMedico medico) {
		medicos.put(medico, new ArrayList<>());
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
        if (pacientesAtendidos.get(paciente) == null){
            pacientesAtendidos.put(paciente, new RegistroPaciente());
        }
        pacientesAtendidos.get(paciente).agregarConsultaMedica(new ConsultaMedica(medico, medico.calcularHonorarios()));
        //String fecha = Date();
        String fecha = "99/99/9999";
        medicos.get(medico).add(new PacienteAtendido(paciente, fecha, medico.calcularHonorarios()));
		salaDeEspera.sacarPaciente(paciente);
	}
	
	public void internaPaciente(Paciente paciente, Habitacion habitacion) {
        pacientesAtendidos.get(paciente).setHabitacion(habitacion);
	}
	
    public Factura egresaPaciente(Paciente paciente)/* throws PacienteInvalidoException*/ {
        if (!pacientes.contains(paciente)) {
               //throw new PacienteInvalidoException("El paciente no está registrado en la clínica");
        }

        //Indice paciente

        Factura facturaNueva = new Factura(paciente,pacientesAtendidos.get(paciente));

        pacientesAtendidos.remove(paciente);
        System.out.println("Nº Factura: " + facturaNueva.getNumeroFactura());
        System.out.println(facturaNueva.ImprimeFactura());
        return facturaNueva;
    }

    public Factura egresaPaciente(Paciente paciente, int cantDiasInternado)/* throws PacienteInvalidoException*/ {
        if (!pacientes.contains(paciente)) {
            //throw new PacienteInvalidoException("El paciente no está registrado en la clínica");
        }

        //Indice paciente

        pacientesAtendidos.get(paciente).setCantDiasInternado(cantDiasInternado);

        Factura facturaNueva = new Factura(paciente,pacientesAtendidos.get(paciente));

        pacientesAtendidos.remove(paciente);
        System.out.println("Nº Factura: " + facturaNueva.getNumeroFactura());
        System.out.println(facturaNueva.ImprimeFactura());
        return facturaNueva;
    }

    @Override
    public String toString() {
		return "Clinica nombre: " + nombre + " direccion: " + direccion + " telefono: " + telefono + " ciudad: " + ciudad;
    }


}