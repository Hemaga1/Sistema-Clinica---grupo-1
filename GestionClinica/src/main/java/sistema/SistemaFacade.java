package sistema;

import excepciones.*;
import facturacion.*;
import honorarios.*;
import personas.*;
import lugares.*;
import sistema.ModuloIngreso.*;
import sistema.ModuloAtencion.*;
import sistema.ModuloEgreso.*;

import java.util.List;

/**
 * Cumple el rol de fachada utilizando Patrón Facade, centralizando la comunicación entre: SistemaIngreso, SistemaAtencion y SistemaEgreso.
 */
public class SistemaFacade {
    
    private final Clinica clinica;
    
    private final SistemaAtencion sistemaAtencion;
    private final SistemaIngreso sistemaIngreso;
    private final SistemaEgreso sistemaEgreso;

    private static SistemaFacade instancia;

    /**
     * Constructor del sistema
     * @param clinica Es el objeto clinica. clinica!=null
     */
    private SistemaFacade(Clinica clinica) {
        this.clinica = clinica;
        this.sistemaIngreso = new SistemaIngreso();
        this.sistemaAtencion = new SistemaAtencion();
        this.sistemaEgreso = new SistemaEgreso();
    }

    public static SistemaFacade getInstancia(Clinica clinica) {
        if (instancia == null) {
            instancia = new SistemaFacade(clinica);
        }
        return instancia;
    }

    /**
     * Registra un médico<br>
     * <b>Precondición: </b> El médico no debe haberse registrado anteriormente<br>
     * <b>Postcondición:</b>
     * <ul>
     *  <li>Si el médico estaba registrado no se registra nuevamente y se captura una excepción</li>
     *  <li>Si el médico no estaba registrado se procede a registrarlo.</li>
     * </ul>
     *
     * @param medico El objeto medico a registrar, medico != null
    */
    public void registraMedico(IMedico medico) {
        try {
            sistemaAtencion.registrarMedico(medico);
        }
        catch (MedicoDuplicadoExcepcion e) {
            System.out.print(e.getMessage() + "\n");
        }
    }

    /**
     * Registra un paciente.<br>
     * <b>Precondición: </b> <p>El paciente no debe haberse registrado anteriormente<br></p>
     * <b>Postcondiciones:</b>
     * <ul>
     * <li>Si el paciente estaba registrado no se registra nuevamente y se captura una excepción</li>
     * <li>Si el paciente no estaba registrado se lo registra.</li>
     * </ul>
     *
     * @param paciente El objeto Paciente a registrar, paciente != null
     */
    public void registraPaciente(Paciente paciente) {
        try {
            sistemaAtencion.registrarPaciente(paciente);
        }
        catch (PacienteDuplicadoExcepcion e) {
            System.out.print(e.getMessage() + "\n");
        }
    }

    /**
     * Intenta ingresar un paciente al sistema de atención, moviéndolo a la sala de espera si ya estaba registrado.<br>
     * <b>Precondición: </b> El paciente debe haberse registrado previamente.<br>
     * <b>Postcondiciones:</b>
     * <ul>
     * <li>Si el paciente estaba registrado se ingresa</li>
     * <li>Si el paciente no está registrado en el sistema la operación de ingreso no se realiza.</li>
     * </ul><br>
     *
     * @param paciente El objeto Paciente que intenta ingresar al sistema de atención, paciente != null
     */
    public void ingresaPaciente(Paciente paciente) {
        if (sistemaAtencion.estaRegistrado(paciente)) {
            sistemaIngreso.ingresarPaciente(paciente);
            System.out.print(paciente.getNombre() + " " + paciente.getApellido() + " ingresado correctamente\n");
        }
        else System.out.print("Paciente no registrado\n");
    }

    /**
     * Atencion del paciente, se quita de espera y se atiende por un medico<br>
     * <b>Precondiciones:</b>
     * <ul>
     * <li>El paciente no debe estar siendo atendido</li>
     * <li>El paciente y medico deben estar registrados</li>
     * </ul><br>
     * <b>Postcondición: </b> Se saca al paciente de espera y comienza la atención<br>

     * @param medico Es el objeto medico, medico!=null
     * @param paciente Es el objeto paciente, paciente!=null
     */

    public void atiendePaciente(IMedico medico, Paciente paciente) {
        if (sistemaAtencion.getRegistroPaciente(paciente) == null)
            try {
                sistemaIngreso.SacarPaciente(paciente);
                System.out.print(paciente.getNombre() + " " + paciente.getApellido() + " sacado de sala de espera correctamente\n");
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

    /**
     * Proceso de internación del paciente<br>
     * <b>Precondiciones:</b>
     * <ul>
     * <li>El paciente debe haber sido atendido</li>
     * <li>La habitación debe tener lugar para poder internar al paciente</li>
     * </ul><br>
     * <b>Postcondición: </b> Comienza la internación del paciente a la habitación correspondiente<br>
     * @param paciente Es el objeto paciente, y ha sido derivado para internación, paciente!=null
     * @param habitacion La habitación a la que corresponde ser internado el paciente, habitacion != null
     */
    public void internaPaciente(Paciente paciente, Habitacion habitacion) {
        try {
            sistemaAtencion.internaPaciente(paciente, habitacion);
        }
        catch (PacienteSinAtenderExcepcion e) {
            System.out.print(e.getMessage() + "\n");
        }
        catch (InternacionCapacidadExcedidaExcepcion e) {
            System.out.print(e.getMessage() + "\n");
        }
    }

    /**
     * El paciente egresa sin haber sido internado y se comienza la facturación<br>
     * <b>Precondición: </b> El paciente debe estar en la lista de atendidos<br>
     * <b>Postcondición: </b> Se egresa al paciente generando factura y quitando de la lista de atendidos<br>
     * @param paciente El paciente que egresa, paciente != null
     * @return La factura corespondiente al paciente
     */
    public Factura egresaPaciente(Paciente paciente) {
        try {
            if (sistemaAtencion.getRegistroPaciente(paciente).getHabitacion() != null) {
                sistemaAtencion.establecerDiasInternado(paciente);
            }

            Factura factura = sistemaEgreso.egresar(paciente, sistemaAtencion.getRegistroPaciente(paciente));
            sistemaAtencion.removerRegistroPaciente(paciente);
            System.out.print(paciente.getNombre() + " " + paciente.getApellido() + " egresado correctamente\n");
            return factura;
        }
        catch (PacienteSinAtenderExcepcion | DesocupacionPacienteInexistenteExcepcion e) {
            System.out.print(e.getMessage() + "\n");
            return null;
        }
    }

    /**
     * El paciente egresa luego de haber sido internado y se comienza la facturación<br>
     * <b>Precondiciones:</b>
     * <ul>
     * <li>El paciente debe estar en la lista de atendidos</li>
     * <li>El paciente tiene que estar internado</li>
     * </ul><br>
     * <b>Postcondición: </b> Se egresa al paciente generando factura y quitando de la lista de atendidos<br>
     * @param paciente El paciente que egresa, paciente != null
     * @param cantDiasInternado Cantidad de dias que el paciente estuvo internado, cantDiasInternado > 0
     * @return La factura corespondiente al paciente
     */
    public Factura egresaPaciente(Paciente paciente, int cantDiasInternado) {
        try {
            sistemaAtencion.establecerDiasInternado(paciente, cantDiasInternado);
            Factura factura = sistemaEgreso.egresar(paciente, sistemaAtencion.getRegistroPaciente(paciente));
            sistemaAtencion.removerRegistroPaciente(paciente);
            System.out.print(paciente.getNombre() + " " + paciente.getApellido() + " egresado correctamente\n");
            return factura;
        }
        catch (PacienteSinAtenderExcepcion e) {
            System.out.print(e.getMessage() + "\n");
            return null;
        }
        catch (DesocupacionPacienteInexistenteExcepcion e) {
            System.out.print(e.getMessage() + "\n");
            return null;
        }
    }
   
    /**
     * Comienza el proceso de muestra del reporte de un médico de una fecha indicada a otra también indicada<br>
     * @param medico Medico del cual se mostrará el reporte de actividad, medico != null
     * @param fechaInicio fechaInicio != null, fechaInicio != ""
     * @param fechaFin fechaFin != null, fechaFin != ""
     * @return El reporte del medico desde una fecha a otra
     */
    public ReporteActividadMedica generarReporteActividadMedica(IMedico medico, String fechaInicio, String fechaFin) {
        List<PacienteAtendido> atenciones = sistemaAtencion.getAtencionesDelMedicoPorPeriodo(medico, fechaInicio, fechaFin);
        return new ReporteActividadMedica(medico, fechaInicio, fechaFin, atenciones);
    }

    /**
     * Agrega una habitación a la lista de habitaciones de la clínica
     * @param habitacion La habitación a agregar
     */
    public void agregarHabitacion(Habitacion habitacion) {
        sistemaAtencion.agregarHabitacion(habitacion);
    }
}
