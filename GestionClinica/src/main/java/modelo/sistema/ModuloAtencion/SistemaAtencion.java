package modelo.sistema.ModuloAtencion;

import modelo.excepciones.MedicoDuplicadoExcepcion;
import modelo.excepciones.MedicoNoRegistradoExcepcion;
import modelo.excepciones.PacienteDuplicadoExcepcion;
import modelo.excepciones.PacienteNoRegistradoExcepcion;
import modelo.excepciones.PacienteSinAtenderExcepcion;
import modelo.excepciones.InternacionCapacidadExcedidaExcepcion;

import modelo.facturacion.ConsultaMedica;
import modelo.facturacion.PacienteAtendido;
import modelo.facturacion.RegistroPaciente;

import modelo.interfaces.IMedico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.lugares.Habitacion;

import modelo.personas.Paciente;


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
     * Inicia el proceso de atención médica entre un médico y un paciente, registrando la consulta, actualizando los registros de ambos y el historial del modelo.sistema.<br>
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

    /**
     * Se establecen la cantidad de días que estuvo internado el paciente
     * @param paciente paciente!=null
     * @throws PacienteSinAtenderExcepcion
     */
    public void establecerDiasInternado(Paciente paciente) throws PacienteSinAtenderExcepcion {
        int dias;
        try {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                java.util.Date fechaIngresoDate = sdf.parse(servicioPacientes.getRegistroPaciente(paciente).getFechaIngreso());
                java.util.Date fechaActual = new java.util.Date();

                long diferenciaEnMillis = fechaActual.getTime() - fechaIngresoDate.getTime();
                dias = (int) (diferenciaEnMillis / (1000 * 60 * 60 * 24));
                if (dias < 1) {
                    dias = 1;
                }

        } catch (Exception e) {
                dias =  1;
        }
        servicioInternaciones.establecerDiasInternado(servicioPacientes.getRegistroPaciente(paciente), dias);
    }

    /**
     * Verifica si el paciente esta registrado
     * @param paciente Paciente a verificar, paciente!=null
     * @return true or false
     */
    public boolean estaRegistrado(Paciente paciente) {
        return  servicioPacientes.estaRegistrado(paciente);
    }

    /**
     * Retorna el registro del paciente indicado
     * @param paciente Paciente del cual se quiere obtener el registro, paciente!=null
     * @return registro del paciente que contiene la habitacion en caso de que sea internado con la cantidad de dias, fecha de ingreso y una lista con sus consultas medicas
     */
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
     * <b>Postcondición:</b> Se registra al médico en caso de no haber sido registrado anteriormente
     * @param medico El objeto medico a registrar, medico != null
     * @throws MedicoDuplicadoExcepcion
     */
    public void registrarMedico(IMedico medico) throws MedicoDuplicadoExcepcion {
        servicioMedicos.registrarMedico(medico);
    }

    /**
     * Registra un paciente<br>
     * <b>Precondición: </b> El paciente no debe haberse registrado anteriormente<br>
     * <b>Postcondición:</b> Se registra al paciente en caso de no haber sido registrado anteriormente
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
     * Muestra de atenciones del médico de cierta fecha a otra fecha, ambas indicadas por parámetro
     * @param medico Médico del cual se mostrarán las atenciones, medico != null
     * @param fechaInicio Fecha de inicio, fechaInicio!=null, fechaInicio!=""
     * @param fechaFin Fecha de fin, fechaFin!=null, fechaFin!=""
     * @return Se retorna el médico con su lista de atenciones
     */
    public List<PacienteAtendido> getAtencionesDelMedicoPorPeriodo(IMedico medico, String fechaInicio, String fechaFin) {
        return servicioMedicos.getAtencionesDelMedicoPorPeriodo(medico, fechaInicio, fechaFin);
    }

    /**
     * Agrega una habitación a la lista de habitaciones de la clínica
     * @param habitacion La habitación a agregar
     */ 
    public void agregarHabitacion(Habitacion habitacion) {
        servicioInternaciones.agregarHabitacion(habitacion);
    }

    public Set<Paciente> getPacientesRegistrados() {
        return servicioPacientes.getPacientesRegistrados();
    }

    public Map<IMedico, List<PacienteAtendido>> getMedicos() {
        return servicioMedicos.getMedicos();
    }
}


