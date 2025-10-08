package sistema;

import excepciones.*;
import facturacion.*;
import honorarios.*;
import personas.*;
import lugares.*;
import sistema.ModuloIngreso.*;
import sistema.ModuloAtencion.*;
import sistema.ModuloEgreso.*;

public class SistemaFacade {
    
    private final Clinica clinica;
    
    private final SistemaAtencion sistemaAtencion;
    private final SistemaIngreso sistemaIngreso;
    private final SistemaEgreso sistemaEgreso;
    
    public SistemaFacade(Clinica clinica) {
        this.clinica = clinica;
        this.sistemaIngreso = new SistemaIngreso();
        this.sistemaAtencion = new SistemaAtencion();
        this.sistemaEgreso = new SistemaEgreso();
    }
    
    public void registraMedico(IMedico medico) {
        try {
            sistemaAtencion.registrarMedico(medico);
        }
        catch (MedicoDuplicadoExcepcion e) {
            System.out.print(e.getMessage() + "\n");
        }
    }
    
    public void registraPaciente(Paciente paciente) {
        try {
            sistemaAtencion.registrarPaciente(paciente);
        }
        catch (PacienteDuplicadoExcepcion e) {
            System.out.print(e.getMessage() + "\n");
        }
    }

    public void ingresaPaciente(Paciente paciente) {
        if (sistemaAtencion.estaRegistrado(paciente)) {
            sistemaIngreso.ingresarPaciente(paciente);
            System.out.print(paciente.getNombre() + " " + paciente.getApellido() + " ingresado\n");
        }
        else System.out.print("Paciente no registrado\n");
    }
    
    public void atiendePaciente(IMedico medico, Paciente paciente) {
        if (sistemaAtencion.getRegistroPaciente(paciente) == null)
            try {
                sistemaIngreso.SacarPaciente(paciente);
                System.out.print(paciente.getNombre() + " " + paciente.getApellido() + " sacado correctamente\n");
            }
            catch (Exception e) {
                System.out.print(e.getMessage() + "\n");
            }
        try {
            sistemaAtencion.atender(medico, paciente);
        }
        catch (Exception e) {
            System.out.print(e.getMessage() + "\n");
        }
    }
    
    public void internaPaciente(Paciente paciente, Habitacion habitacion) {
        try {
            sistemaAtencion.internaPaciente(paciente, habitacion);
        }
        catch (PacienteSinAtenderExcepcion e) {
            System.out.print(e.getMessage() + "\n");
        }
    }

    public Factura egresaPaciente(Paciente paciente) {
        try {
            Factura factura = sistemaEgreso.egresar(paciente, sistemaAtencion.getRegistroPaciente(paciente));
            sistemaAtencion.removerRegistroPaciente(paciente);
            return factura;
        }
        catch (PacienteSinAtenderExcepcion e) {
            System.out.print(e.getMessage() + "\n");
            return null;
        }
    }

    public Factura egresaPaciente(Paciente paciente, int cantDiasInternado) {
        try {
            sistemaAtencion.establecerDiasInternado(paciente, cantDiasInternado);
            Factura factura = sistemaEgreso.egresar(paciente, sistemaAtencion.getRegistroPaciente(paciente));
            sistemaAtencion.removerRegistroPaciente(paciente);
            return factura;
        }
        catch (PacienteSinAtenderExcepcion e) {
            System.out.print(e.getMessage() + "\n");
            return null;
        }
    }
    
}
