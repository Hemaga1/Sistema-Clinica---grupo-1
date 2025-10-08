package sistema.ModuloAtencion;

import excepciones.MedicoDuplicadoExcepcion;
import excepciones.MedicoNoRegistradoExcepcion;
import excepciones.PacienteDuplicadoExcepcion;
import excepciones.PacienteNoRegistradoExcepcion;
import facturacion.ConsultaMedica;
import facturacion.PacienteAtendido;
import honorarios.IMedico;
import java.text.SimpleDateFormat;
import java.util.Date;
import lugares.Habitacion;
import facturacion.RegistroPaciente;
import personas.Paciente;
import excepciones.PacienteSinAtenderExcepcion;

public class SistemaAtencion {

    private final ServicioInternaciones servicioInternaciones;
    private final ServicioMedicos servicioMedicos;
    private final ServicioPacientes servicioPacientes;

    public SistemaAtencion() {
        this.servicioMedicos = new ServicioMedicos();
        this.servicioPacientes = new ServicioPacientes();
        this.servicioInternaciones = new ServicioInternaciones();
    }

    public void atender(IMedico medico, Paciente paciente) throws PacienteNoRegistradoExcepcion, MedicoNoRegistradoExcepcion {
        if (!servicioMedicos.estaRegistrado(medico)) {
            throw new MedicoNoRegistradoExcepcion();
        }
        if (!servicioPacientes.estaRegistrado(paciente)) {
            throw new PacienteNoRegistradoExcepcion();
        }
        servicioPacientes.iniciarRegistroAtencion(paciente);
        ConsultaMedica consulta = new ConsultaMedica(medico, medico.calcularHonorarios());
        servicioPacientes.getRegistroPaciente(paciente).agregarConsultaMedica(consulta);
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        PacienteAtendido atencion = new PacienteAtendido(paciente, fecha, medico.calcularHonorarios());
        servicioMedicos.agregarAtencion(medico, atencion);
    }

    public void internaPaciente(Paciente paciente, Habitacion habitacion) throws PacienteSinAtenderExcepcion {
        servicioInternaciones.internar(servicioPacientes.getRegistroPaciente(paciente), habitacion);
    }

    public void establecerDiasInternado(Paciente paciente, int cantDiasInternado) throws PacienteSinAtenderExcepcion {
        servicioInternaciones.establecerDiasInternado(servicioPacientes.getRegistroPaciente(paciente), cantDiasInternado);
    }

    public boolean estaRegistrado(Paciente paciente) {
        return  servicioPacientes.estaRegistrado(paciente);
    }
    
    public RegistroPaciente getRegistroPaciente(Paciente paciente) {
        return servicioPacientes.getRegistroPaciente(paciente);
    }

    public void removerRegistroPaciente(Paciente paciente) {
        servicioPacientes.removerRegistroPaciente(paciente);
    }

    public void registrarMedico(IMedico medico) throws MedicoDuplicadoExcepcion {
        servicioMedicos.registrarMedico(medico);
    }

    public void registrarPaciente(Paciente paciente) throws PacienteDuplicadoExcepcion {
        servicioPacientes.registrarPaciente(paciente);
    }
}


