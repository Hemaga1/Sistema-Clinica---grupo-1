package modelo.sistema;

import modelo.excepciones.*;
import modelo.facturacion_y_registros.*;
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

    /**
     * Registra un asociado en el sistema y en la base de datos.
     * 
     * @param asociado el asociado a registrar
     * @throws AsociadoDuplicadoExcepcion si el asociado ya está registrado
     * @throws ErrorPersistenciaExcepcion si ocurre un error al guardar en la base de datos
     */
    public void registraAsociado(Asociado asociado) throws AsociadoDuplicadoExcepcion, ErrorPersistenciaExcepcion {
        sistemaAtencion.registrarAsociado(asociado);
        db.agregarAsociado(AsociadoMapper.toDTO(asociado));
    }

    /**
     * Elimina un asociado del sistema y de la base de datos.
     * 
     * @param asociado el asociado a eliminar
     * @throws AsociadoNoRegistradoExcepcion si el asociado no está registrado
     * @throws ErrorPersistenciaExcepcion si ocurre un error al eliminar de la base de datos
     */
    public void eliminarAsociado(Asociado asociado) throws AsociadoNoRegistradoExcepcion, ErrorPersistenciaExcepcion {
        sistemaAtencion.eliminarAsociado(asociado);
        db.eliminarAsociado(asociado.getDNI());
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

    public void atiendePaciente(IMedico medico, Paciente paciente) throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion, PacienteNoRegistradoExcepcion, MedicoNoRegistradoExcepcion{
        assert paciente!=null : "El paciente a ser atendido debe ser distinto a null";
        assert medico!=null : "El medico a atender debe ser distinto a null";
        if (sistemaAtencion.getRegistroPaciente(paciente) == null)
            sistemaIngreso.sacarPaciente(paciente);
        sistemaAtencion.atender(medico, paciente);

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
    public void internaPaciente(Paciente paciente, Habitacion habitacion) throws PacienteSinAtenderExcepcion, InternacionCapacidadExcedidaExcepcion {
        assert paciente!=null : "El paciente a internar debe ser distinto a null";
        assert habitacion!=null : "La habitacion en la cula se internará al paciente no puede ser null";
        sistemaAtencion.internaPaciente(paciente, habitacion);
    }

    /**
     * El paciente egresa sin haber sido internado y se comienza la facturación<br>
     * <b>Precondición: </b> El paciente debe estar en la lista de atendidos<br>
     * <b>Postcondición: </b> Se egresa al paciente generando factura y quitando de la lista de atendidos<br>
     * @param paciente El paciente que egresa, paciente != null
     * @return La factura corespondiente al paciente
     */
    public Factura egresaPaciente(Paciente paciente) throws PacienteSinAtenderExcepcion, DesocupacionPacienteInexistenteExcepcion{
        assert paciente!=null : "El paciente a egresar no puede ser null";
        if (sistemaAtencion.getRegistroPaciente(paciente).getHabitacion() != null) {
            sistemaAtencion.establecerDiasInternado(paciente);
        }

        Factura factura = sistemaEgreso.egresar(paciente, sistemaAtencion.getRegistroPaciente(paciente));
        sistemaAtencion.removerRegistroPaciente(paciente);
        return factura;
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


    /**
     * Crea las tablas en la base de datos e inicializa con datos de ejemplo.
     * Si ocurre un error, se propaga como ErrorPersistenciaExcepcion.
     * 
     * @throws ErrorPersistenciaExcepcion si ocurre un error al crear las tablas o guardar datos
     */
    public void crearTablas() throws ErrorPersistenciaExcepcion {
        Set<Asociado> asociados = getAsociados();

        if (asociados.isEmpty()) {
                db.abrirConexion();
                db.crearTablaAsociados();

                try {
                    this.registraAsociado(new Asociado("30000000", "Camilo", "Fernández", "Calle 15", 12, "CABA", "100-0001"));
                    this.registraAsociado(new Asociado("30000001", "Paola", "Benítez", "Calle 20", 100, "CABA", "100-0002"));
                    this.registraAsociado(new Asociado("30000002", "Mariano", "Martínez", "Calle 37", 230, "CABA", "100-0003"));
                } catch (AsociadoDuplicadoExcepcion e) {
                    // Si ya existen, no es un error crítico, continuar
                }

                // Guardar todos los asociados en la BD
                for (Asociado asociado : getAsociados()) {
                    db.agregarAsociado(AsociadoMapper.toDTO(asociado));
                }
        }
    }

    /**
     * Carga los asociados desde la base de datos al sistema.
     * Si un asociado ya existe en el sistema, se omite (no es un error).
     * 
     * @throws ErrorPersistenciaExcepcion si ocurre un error al acceder a la base de datos
     */
    public void cargarDesdeBD() throws ErrorPersistenciaExcepcion,AsociadoDuplicadoExcepcion {
        db.abrirConexion();
        for (AsociadoDTO asociadoDTO : db.traerAsociados()) {
            sistemaAtencion.registrarAsociado(AsociadoMapper.fromDTO(asociadoDTO));
        }
    }
}
