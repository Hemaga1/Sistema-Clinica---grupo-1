package modelo.sistema;

import modelo.excepciones.*;
import modelo.facturacion.*;
import modelo.interfaces.IMedico;
import modelo.personas.*;
import modelo.lugares.*;
import modelo.sistema.ModuloIngreso.*;
import modelo.sistema.ModuloAtencion.*;
import modelo.sistema.ModuloEgreso.*;
import persistencia.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Cumple el rol de fachada utilizando Patrón Facade, centralizando la comunicación entre: ModuloIngreso, ModuloAtencion y ModuloEgreso.
 * Implementa el patrón Singleton para garantizar que solo exista una instancia del modelo.sistema durante toda la ejecución del programa.
 */
public class SistemaFacade {
    
    private final Clinica clinica;
    
    private final SistemaAtencion sistemaAtencion;
    private final SistemaIngreso sistemaIngreso;
    private final SistemaEgreso sistemaEgreso;
    private dataBaseDAO db;
    private static SistemaFacade instancia;

    /**
     * Constructor privado para impedir la creacion de un objeto fuera de la clase
     * @param clinica clinica!=null
     */
    private SistemaFacade(Clinica clinica) {
        assert clinica!=null: "La clinica no puede ser null";
        this.clinica = clinica;
        this.sistemaIngreso = new SistemaIngreso();
        this.sistemaAtencion = new SistemaAtencion();
        this.sistemaEgreso = new SistemaEgreso();
        this.db = dataBaseDAO.getInstancia();
    }

    /**
     * Metodo público estático que retorna la única instancia existente, creándola en el caso de que no exista.
     * @param clinica clinica!=null
     * @return
     */
    public static SistemaFacade getInstancia(Clinica clinica) {
        assert clinica!=null: "La clinica no puede ser null";
        if (instancia == null) {
            instancia = new SistemaFacade(clinica);
        }
        assert instancia!= null : "Si no existia la instancia se creo una entonces de ninguna forma puede ser null";
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
    public void registraMedico(IMedico medico) throws MedicoDuplicadoExcepcion {
        assert medico!=null : "El medico  que se quiere registrar no puede ser null";
        sistemaAtencion.registrarMedico(medico);
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
    public void registraPaciente(Paciente paciente) throws PacienteDuplicadoExcepcion{
        assert paciente!=null : "El paciente a registrar no puede ser null";
        sistemaAtencion.registrarPaciente(paciente);
    }

    public void registraAsociado(Asociado asociado) throws AsociadoDuplicadoExcepcion {
        sistemaAtencion.registrarAsociado(asociado);
    }

    public void eliminarAsociado(Asociado asociado) throws AsociadoNoRegistradoExcepcion {
        sistemaAtencion.eliminarAsociado(asociado);
    }

    /**
     * Intenta ingresar un paciente al modelo.sistema de atención, moviéndolo a la sala de espera si ya estaba registrado.<br>
     * <b>Precondición: </b> El paciente debe haberse registrado previamente.<br>
     * <b>Postcondiciones:</b>
     * <ul>
     * <li>Si el paciente estaba registrado se ingresa</li>
     * <li>Si el paciente no está registrado en el modelo.sistema la operación de ingreso no se realiza.</li>
     * </ul><br>
     *
     * @param paciente El objeto Paciente que intenta ingresar al modelo.sistema de atención, paciente != null
     */
    public void ingresaPaciente(Paciente paciente) {
        assert paciente!=null : "El paciente a ingresar debe ser distinto a null";
        assert sistemaAtencion.estaRegistrado(paciente) : "El paciente a ingresar debe estar anteriormente registrado";
        sistemaIngreso.ingresarPaciente(paciente);
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
        assert paciente!=null : "El paciente a ser atendido debe ser distinto a null";
        assert medico!=null : "El medico a atender debe ser distinto a null";
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
     * @param paciente Es el objeto paciente que ha sido derivado para internación, paciente!=null
     * @param habitacion La habitación a la que corresponde ser internado el paciente, habitacion != null
     */
    public void internaPaciente(Paciente paciente, Habitacion habitacion) {
        assert paciente!=null : "El paciente a internar debe ser distinto a null";
        assert habitacion!=null : "La habitacion en la cula se internará al paciente no puede ser null";
        try {
            sistemaAtencion.internaPaciente(paciente, habitacion);
            System.out.print("Internado\n");
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
        assert paciente!=null : "El paciente a engresar no puede ser null";
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
    public Factura egresaPaciente(Paciente paciente, int cantDiasInternado) throws PacienteSinAtenderExcepcion, DesocupacionPacienteInexistenteExcepcion {
        assert paciente!=null : "El paciente a engresar no puede ser null";
        assert cantDiasInternado>0 : "La cantidad de dias internado el paciente debe ser mayor a cero ya que sino no estaría siendo internado";
        sistemaAtencion.establecerDiasInternado(paciente, cantDiasInternado);
        Factura factura = sistemaEgreso.egresar(paciente, sistemaAtencion.getRegistroPaciente(paciente));
        sistemaAtencion.removerRegistroPaciente(paciente);
        System.out.print(paciente.getNombre() + " " + paciente.getApellido() + " egresado correctamente\n");
        return factura;
    }
   
    /**
     * Comienza el proceso de muestra del reporte de un médico de una fecha a otra ya indicadas<br>
     * @param medico Medico del cual se mostrará el reporte de actividad, medico != null
     * @param fechaInicio fechaInicio != null, fechaInicio != ""
     * @param fechaFin fechaFin != null, fechaFin != ""
     * @return El reporte del medico desde una fecha a otra
     */
    public ReporteActividadMedica generarReporteActividadMedica(IMedico medico, String fechaInicio, String fechaFin) {
        assert medico!=null : "El medico del cual se quieren ver las atenciones no puede ser null";
        assert fechaInicio!=null && fechaInicio!="" : "La fecha inicial desde la cual se quieren ver las atenciones del medico no debe ser null";
        assert fechaFin!=null && fechaFin!="" : "La fecha final limite hasta la cual se quieren ver las atenciones del medico no debe ser null";
        List<PacienteAtendido> atenciones = sistemaAtencion.getAtencionesDelMedicoPorPeriodo(medico, fechaInicio, fechaFin);
        return new ReporteActividadMedica(medico, fechaInicio, fechaFin, atenciones);
    }

    /**
     * Agrega una habitación a la lista de habitaciones de la clínica
     * @param habitacion La habitación a agregar
     */
    public void agregarHabitacion(Habitacion habitacion) {
        assert habitacion!=null : "La habitacion que se quiere agregar debe ser distinta de null";
        sistemaAtencion.agregarHabitacion(habitacion);
    }

    public Set<Paciente> getPacientesRegistrados() {
        return sistemaAtencion.getPacientesRegistrados();
    }

    public Map<IMedico, List<PacienteAtendido>> getMedicos() {
        return sistemaAtencion.getMedicos();
    }

    public Set<Paciente> getListaEspera() {
        return sistemaIngreso.getListaEspera();
    }

    public Map<Paciente, RegistroPaciente> getPacientesAtendidos() {
        return sistemaAtencion.getPacientesAtendidos();
    }

    public List<Habitacion> getHabitaciones() {
        return sistemaAtencion.getHabitaciones();
    }

    public Set<Asociado> getAsociados() {
        return sistemaAtencion.getAsociados();
    }


    public void crearTablas() {
        try {
            db.abrirConexion();
            db.crearTablaAsociados();
            for (Asociado asociado : getAsociados()) {
                // AsociadoDTO constructor: (String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono)
                String calle = asociado.getDomicilio() != null ? asociado.getDomicilio().getCalle() : "";
                int numero = asociado.getDomicilio() != null ? asociado.getDomicilio().getNumero() : 0;
                String ciudad = asociado.getDomicilio() != null ? asociado.getDomicilio().getCiudad() : "";
                String telefono = asociado.getTelefono() != null ? asociado.getTelefono() : "";
                db.agregarAsociado(new AsociadoDTO(asociado.getDNI(), asociado.getNombre(), asociado.getApellido(), calle, numero, ciudad, telefono));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                db.cerrarConexion();
            } catch (SQLException ignored) {
            }
        }

    }
}
