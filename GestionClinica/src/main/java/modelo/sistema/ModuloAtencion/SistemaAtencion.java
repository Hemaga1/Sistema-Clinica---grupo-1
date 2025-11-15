package modelo.sistema.ModuloAtencion;

import modelo.excepciones.*;

import modelo.facturacion_y_registros.ConsultaMedica;
import modelo.facturacion_y_registros.PacienteAtendido;
import modelo.facturacion_y_registros.RegistroPaciente;

import modelo.interfaces.IMedico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.lugares.Habitacion;

import modelo.personas.Asociado;
import modelo.personas.Paciente;


/**
 * En esta clase se encuentra todo lo que corresponde con la atención al paciente
 */
public class SistemaAtencion {

    private final ServicioInternaciones servicioInternaciones;
    private final ServicioMedicos servicioMedicos;
    private final ServicioPacientes servicioPacientes;
    private final ServicioAsociados servicioAsociados;

    public SistemaAtencion() {
        this.servicioMedicos = new ServicioMedicos();
        this.servicioPacientes = new ServicioPacientes();
        this.servicioInternaciones = new ServicioInternaciones();
        this.servicioAsociados = new ServicioAsociados();
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
        assert medico!=null : "El medico que atenderá no puede ser null";
        assert paciente!=null : "El paciente a atender no puede ser null";
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
        assert paciente!=null : "El paciente a internar no puede ser null";
        assert habitacion!= null : "La habitacion donde se internará el paciente no puede ser null";
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
        assert paciente!=null : "El paciente al cual se le quieren establecer los días internados no puede ser null";
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
        assert paciente!=null : "El paciente que se quiere ver si esta registrado no puede ser null";
        return  servicioPacientes.estaRegistrado(paciente);
    }

    /**
     * Retorna el registro del paciente indicado
     * @param paciente Paciente del cual se quiere obtener el registro, paciente!=null
     * @return registro del paciente que contiene la habitacion en caso de que sea internado con la cantidad de dias, fecha de ingreso y una lista con sus consultas medicas
     */
    public RegistroPaciente getRegistroPaciente(Paciente paciente) {
        assert paciente!=null : "El paciente que se quiere ver el registro no puede ser null";
        return servicioPacientes.getRegistroPaciente(paciente);
    }

    /**
     * El paciente egresa y deja de ser uno de los pacientes en atención.
     * @param paciente Paciente que egresa, paciente != null
     */
    public void removerRegistroPaciente(Paciente paciente) {
        assert paciente!=null : "El paciente que se quiere remover el registro no puede ser null";
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
        //No hago assert porque lo hice en SistemaFacade que implementa este metodo asi que el medico ya tiene que venir sin ser null
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
        //No hago assert porque lo hice en SistemaFacade que implementa este metodo asi que el paciente ya tiene que venir sin ser null
        servicioPacientes.registrarPaciente(paciente);
    }

    public void registrarAsociado(Asociado asociado) throws AsociadoDuplicadoExcepcion {
        servicioAsociados.registrarAsociado(asociado);
    }

    public void eliminarAsociado(Asociado asociado) throws AsociadoNoRegistradoExcepcion {
        servicioAsociados.eliminarAsociado(asociado);
    }

    /**
     * Muestra de atenciones del médico
     * @param medico Médico del cual se mostrarán las atenciones, medico != null
     * @return Se retorna el médico con su lista de atenciones
     */
    public List<PacienteAtendido> getAtencionesDelMedico(IMedico medico) {
        assert medico!=null : "El medico del cual se quieren ver las atenciones no puede ser null";
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
        assert medico!=null : "El medico del cual se quieren ver las atenciones no puede ser null";
        assert fechaInicio!=null && fechaInicio!="" : "La fecha inicial desde la cual se quieren ver las atenciones del medico no debe ser null";
        assert fechaFin!=null && fechaFin!="" : "La fecha final limite hasta la cual se quieren ver las atenciones del medico no debe ser null";
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

    public Map<Paciente, RegistroPaciente> getPacientesAtendidos() {
        return servicioPacientes.getPacientesAtendidos();
    }

    public Map<IMedico, List<PacienteAtendido>> getMedicos() {
        return servicioMedicos.getMedicos();
    }

    public List<Habitacion> getHabitaciones() {
        return servicioInternaciones.getHabitaciones();
    }

    public Set<Asociado> getAsociados() {
        return servicioAsociados.getAsociadosRegistrados();
    }

}


