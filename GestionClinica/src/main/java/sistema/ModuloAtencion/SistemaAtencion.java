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
import java.util.List;
import lugares.Habitacion;
import facturacion.RegistroPaciente;
import personas.Paciente;
import excepciones.PacienteSinAtenderExcepcion;
import excepciones.InternacionCapacidadExcedidaExcepcion;

/**
 * En esta clase se encuentra todo lo que corresponde con la atención al paciente
 */
public class SistemaAtencion {

    private final ServicioInternaciones servicioInternaciones;
    private final ServicioMedicos servicioMedicos;
    private final ServicioPacientes servicioPacientes;

    public SistemaAtencion() {
        this.servicioMedicos = new ServicioMedicos();
        this.servicioPacientes = new ServicioPacientes();
        this.servicioInternaciones = new ServicioInternaciones();
    }

    /**
     * Inicia el proceso de atención médica entre un médico y un paciente, registrando la consulta, actualizando los registros de ambos y el historial del sistema.<br>
     * <b>Precondición: </b> El médico y el paciente deben estar previamente registrados<br>
     * <b>Postcondiciones:</b> Se inicia el registro del paciente y se agrega la consulta al médico
     * @param medico Médico que atiende, medico!=null
     * @param paciente Paciente a ser atendido, paciente!=null
     * @throws PacienteNoRegistradoExcepcion
     * @throws MedicoNoRegistradoExcepcion
     */
    public void atender(IMedico medico, Paciente paciente) throws PacienteNoRegistradoExcepcion, MedicoNoRegistradoExcepcion {
        if (!servicioMedicos.estaRegistrado(medico)) {
            throw new MedicoNoRegistradoExcepcion();
        }
        if (!servicioPacientes.estaRegistrado(paciente)) {
            throw new PacienteNoRegistradoExcepcion();
        }
        if(!servicioPacientes.estaEnAtencion(paciente)) {
            servicioPacientes.iniciarRegistroAtencion(paciente);
        }

        ConsultaMedica consulta = new ConsultaMedica(medico, medico.calcularHonorarios());
        servicioPacientes.getRegistroPaciente(paciente).agregarConsultaMedica(consulta);
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        PacienteAtendido atencion = new PacienteAtendido(paciente, fecha, medico.calcularHonorarios());
        servicioMedicos.agregarAtencion(medico, atencion);
    }

    /**
     * Internación del paciente<br>
     * <b>Precondiciones: </b>
     * <ul>
     *     <li>El paciente debe estar atendido</li>
     *     <li>La habitacion debe tener capacidad para poder internar al paciente</li>
     * </ul><br>
     * <b>Postcondición: </b> Se procede a internar al paciente en la habitacion que se indique
     * @param paciente Paciente a ser internado, paciente!=null
     * @param habitacion Habitación en la que se internará al paciente, habitación1=null
     * @throws PacienteSinAtenderExcepcion
     * @throws InternacionCapacidadExcedidaExcepcion
     */
    public void internaPaciente(Paciente paciente, Habitacion habitacion) throws PacienteSinAtenderExcepcion, InternacionCapacidadExcedidaExcepcion {
        servicioInternaciones.internar(paciente, servicioPacientes.getRegistroPaciente(paciente), habitacion);
    }

    /**
     * Se establece en el registro del paciente la cantidad de días internado<br>
     * <b>Precondición: </b> El paciente debe estar atendido<br>
     * <b>Postcondición: </b> Se establecen los dias que se interna el paciente
     * @param paciente Paciente a ser internado, paciente != null
     * @param cantDiasInternado canDiasInternado>=0
     * @throws PacienteSinAtenderExcepcion
     */
    public void establecerDiasInternado(Paciente paciente, int cantDiasInternado) throws PacienteSinAtenderExcepcion {
        servicioInternaciones.establecerDiasInternado(servicioPacientes.getRegistroPaciente(paciente), cantDiasInternado);
    }

    public boolean estaRegistrado(Paciente paciente) {
        return  servicioPacientes.estaRegistrado(paciente);
    }
    
    public RegistroPaciente getRegistroPaciente(Paciente paciente) {
        return servicioPacientes.getRegistroPaciente(paciente);
    }

    /**
     * El paciente egresa y deja de ser uno de los pacientes en atención.
     * @param paciente Paciente que egresa, paciente != null
     */
    public void removerRegistroPaciente(Paciente paciente) {
        servicioPacientes.removerRegistroPaciente(paciente);
    }

    /**
     * Registra un médico<br>
     * <b>Precondición: </b> El médico no debe haberse registrado anteriormente<br>
     * <b>Postcondición:</b> Se registra al médico
     * @param medico El objeto medico a registrar, medico != null
     * @throws MedicoDuplicadoExcepcion
     */
    public void registrarMedico(IMedico medico) throws MedicoDuplicadoExcepcion {
        servicioMedicos.registrarMedico(medico);
    }

    /**
     * Registra un paciente<br>
     * <b>Precondición: </b> El paciente no debe haberse registrado anteriormente<br>
     * <b>Postcondición:</b> Se registra al paciente
     * @param paciente El objeto paciente a registrar, paciente != null
     * @throws PacienteDuplicadoExcepcion
     */
    public void registrarPaciente(Paciente paciente) throws PacienteDuplicadoExcepcion {
        servicioPacientes.registrarPaciente(paciente);
    }

    /**
     * Muestra de atenciones del médico
     * @param medico Médico del cual se mostrarán las atenciones, medico != null
     * @return Se retorna el médico con su lista de atenciones
     */
    public List<PacienteAtendido> getAtencionesDelMedico(IMedico medico) {
        return servicioMedicos.getAtencionesDelMedico(medico);
    }

    /**
     * Muestra de atenciones del médico de cierta fecha a otra fecha
     * @param medico Médico del cual se mostrarán las atenciones, medico != null
     * @param fechaInicio Fecha de inicio, fechaInicio!=null, fechaInicio!=""
     * @param fechaFin Fecha de fin, fechaFin!=null, fechaFin!=""
     * @return Se retorna el médico con su lista de atenciones
     */
    public List<PacienteAtendido> getAtencionesDelMedicoPorPeriodo(IMedico medico, String fechaInicio, String fechaFin) {
        return servicioMedicos.getAtencionesDelMedicoPorPeriodo(medico, fechaInicio, fechaFin);
    }
}


