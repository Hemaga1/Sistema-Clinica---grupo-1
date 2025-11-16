package controlador;

import modelo.ambulancia.*;
import modelo.factoria.FactoryMedico;
import modelo.factoria.FactoryPaciente;
import modelo.facturacion_y_registros.Factura;
import modelo.facturacion_y_registros.ReporteActividadMedica;
import modelo.interfaces.IMedico;
import modelo.lugares.Habitacion;
import modelo.lugares.HabitacionCompartida;
import modelo.lugares.HabitacionPrivada;
import modelo.lugares.HabitacionTerapiaIntensiva;
import modelo.excepciones.ErrorPersistenciaExcepcion;
import modelo.personas.Asociado;
import modelo.personas.Paciente;
import modelo.sistema.SistemaFacade;
import vista.IVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase Controlador del patrón MVC.
 * <p>
 * Se encarga de gestionar todas las acciones generadas por la vista
 * y coordinar las operaciones en el Sistema Facade. Recibe los
 * eventos de usuario, ejecuta la lógica necesaria y actualiza la vista
 * según corresponda.
 * </p>
 */

public class Controlador implements ActionListener {
    private IVista vista;
    private SistemaFacade sistema;
    private SimulacionAmbulancia simulacion;
    private FactoryMedico factoryMedico;
    private FactoryPaciente  factoryPaciente;


    /**
     * Constructor del controlador.
     * <p>
     * Inicializa la vista, registra los listeners, carga datos iniciales
     * y actualiza los listados visibles en la interfaz. También configura
     * la simulación de ambulancia.
     * </p>
     *
     * @param vista   instancia de la vista principal
     * @param sistema fachada del sistema encargada de la lógica del modelo
     */
    public Controlador(IVista vista, SistemaFacade sistema, FactoryMedico factoryMedico, FactoryPaciente factoryPaciente)
    {
        this.vista = vista;
        this.vista.addActionListener(this);
        this.sistema = sistema;
        this.simulacion = new SimulacionAmbulancia(this, vista);
        this.factoryMedico = factoryMedico;
        this.factoryPaciente = factoryPaciente;
    }

    /**
     * Maneja todos los eventos generados desde la vista.
     * <p>
     * Dependiendo del comando recibido, se ejecuta una acción específica
     * sobre el modelo (registrar pacientes, médicos, asociados, ingresar
     * pacientes, internación, egreso, generación de facturas, reportes,
     * manejo de ambulancias, etc).
     * </p>
     *
     * @param e evento recibido desde la interfaz gráfica
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String comando = e.getActionCommand();
        if (comando.equals("PacienteBotonEnviar")) {
            try {
                String rango_etario = this.vista.getRangoEtarioPaciente();
                if (rango_etario.equalsIgnoreCase("NIÑO")) {
                    rango_etario = "NINIO";
                }
                Paciente p = factoryPaciente.crearPaciente(this.vista.getDNIPaciente(), this.vista.getNombrePaciente(), this.vista.getApellidoPaciente(), this.vista.getCallePaciente(), this.vista.getNumPaciente(), this.vista.getCiudadPaciente(), this.vista.getTelefonoPaciente(), this.vista.getHistoriaPaciente(), rango_etario);
                this.sistema.registraPaciente(p);
                this.vista.mostrarMensajeVentana("Paciente registrado correctamente");
                this.vista.actualizarPacientesRegistradosLista(this.sistema.getPacientesRegistrados());
                this.vista.actualizarIngresarPacienteLista(this.sistema.getPacientesRegistrados(), "");
            }
            catch (Exception ex) {
                this.vista.mostrarMensajeVentana(ex.getMessage());
            }
        }
        else
        if (comando.equals("MedicoBotonEnviar")) {
            try {
                IMedico m = factoryMedico.crearMedico(this.vista.getDNIMedico(), this.vista.getNombreMedico(), this.vista.getApellidoMedico(), this.vista.getCalleMedico(), this.vista.getNumMedico(), this.vista.getTelefonoMedico(), this.vista.getCiudadMedico(), this.vista.getMatriculaMedico(), this.vista.getEspecialidadMedico(), this.vista.getContratacionMedico(), this.vista.getPosgradoMedico());
                this.sistema.registraMedico(m);
                this.vista.mostrarMensajeVentana("Medico registrado correctamente");
                this.vista.actualizarMedicosRegistradosLista(this.sistema.getMedicos());
                this.vista.actualizarAtenderMedicoLista(this.sistema.getMedicos(), "");
                this.vista.actualizarReporteMedicoLista(this.sistema.getMedicos(), "");
            }
            catch (Exception ex) {
                this.vista.mostrarMensajeVentana(ex.getMessage());
            }
        }
        else
        if (comando.equals("AsociadoBotonEnviar")){
            try {
                Asociado a = new Asociado(this.vista.getDNIAsociado(), this.vista.getNombreAsociado(), this.vista.getApellidoAsociado(), this.vista.getCalleAsociado(), this.vista.getNumAsociado(), this.vista.getCiudadAsociado(), this.vista.getTelefonoAsociado());
                this.sistema.registraAsociado(a);
                this.vista.mostrarMensajeVentana("Asociado registrado correctamente");
                this.vista.actualizarAsociadosRegistradosLista(this.sistema.getAsociados());
                this.vista.actualizarAmbulanciaAsociadosLista(this.sistema.getAsociados());
                this.vista.actualizarBajaAsociadosLista(this.sistema.getAsociados(),  this.vista.getBajaAsociadoBusqueda());
            }
            catch (ErrorPersistenciaExcepcion ex) {
                this.vista.mostrarMensajeVentana("Error de base de datos: " + ex.getMessage());
            }
            catch (Exception ex) {
                this.vista.mostrarMensajeVentana(ex.getMessage());
            }
        }
        else
        if (comando.equals("IngresarPacienteBoton")){
            Paciente paciente = this.vista.getPacienteIngresar();
            if (paciente != null){
                this.sistema.ingresaPaciente(this.vista.getPacienteIngresar());
                this.vista.actualizarAtenderPacienteLista(this.sistema.getListaEspera(), this.sistema.getPacientesAtendidos(), this.vista.getAtenderPacienteBusqueda());
                this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
            }
        }
        else
        if (comando.equals("IngresarPacienteBuscarBoton")){
            this.vista.actualizarIngresarPacienteLista(this.sistema.getPacientesRegistrados(), this.vista.getIngresarPacienteBusqueda());
        }
        else
        if (comando.equals("AtenderPacienteBuscarBoton")) {
            this.vista.actualizarAtenderPacienteLista(this.sistema.getListaEspera(), this.sistema.getPacientesAtendidos(), this.vista.getAtenderPacienteBusqueda());
        }
        else
        if (comando.equals("AtenderMedicoBuscarBoton")) {
            this.vista.actualizarAtenderMedicoLista(this.sistema.getMedicos(), this.vista.getAtenderMedicoBusqueda());
        }
        else
        if (comando.equals("AtenderPacienteBoton")) {
            Paciente paciente = this.vista.getPacienteAtender();
            if (paciente != null) {
                try {
                    this.sistema.atiendePaciente(this.vista.getMedicoAtender(), paciente);
                    this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
                    this.vista.actualizarEgresarLista(this.sistema.getPacientesAtendidos(), "");
                }
                catch (Exception ex) {
                    this.vista.mostrarMensajeVentana(ex.getMessage());
                }
            }
        }
        else
        if (comando.equals("InternarPacienteBuscarBoton")) {
            this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
        }
        else
        if (comando.equals("InternarPacienteBoton")) {
            Paciente paciente = this.vista.getPacienteInternar();
            if (paciente != null) {
                try {
                    this.sistema.internaPaciente(paciente, this.vista.getHabitacion());
                    this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
                    this.vista.actualizarHabitaciones(this.sistema.getHabitaciones());
                }
                catch (Exception ex) {
                    this.vista.mostrarMensajeVentana(ex.getMessage());
                }
            }
        }
        else
        if (comando.equals("EgresarBuscarBoton")) {
            this.vista.actualizarEgresarLista(this.sistema.getPacientesAtendidos(),  this.vista.getEgresarPacienteBusqueda());
        }
        else
        if (comando.equals("EgresarBoton")) {
            Paciente paciente = this.vista.getPacienteEgresar();
            if (paciente != null) {
                try {
                    Factura f = this.sistema.egresaPaciente(paciente);
                    this.vista.mostrarFactura(f);
                }
                catch (Exception ex){
                    this.vista.mostrarMensajeVentana(ex.getMessage());
                }
            }
        }
        else
        if (comando.equals("BajaAsociadoBuscarBoton")) {
            this.vista.actualizarBajaAsociadosLista(this.sistema.getAsociados(),  this.vista.getBajaAsociadoBusqueda());
        }
        else
        if (comando.equals("BajaAsociadoBoton")) {
            Asociado asociado = this.vista.getAsociadoBajar();
            if (asociado != null) {
                if (this.vista.confirmarBaja()) {
                    try {
                        this.sistema.eliminarAsociado(this.vista.getAsociadoBajar());
                        this.vista.mostrarMensajeVentana("Asociado eliminado correctamente");
                        this.vista.actualizarAsociadosRegistradosLista(this.sistema.getAsociados());
                        this.vista.actualizarAmbulanciaAsociadosLista(this.sistema.getAsociados());
                        this.vista.actualizarBajaAsociadosLista(this.sistema.getAsociados(), this.vista.getBajaAsociadoBusqueda());
                    } catch (ErrorPersistenciaExcepcion ex) {
                        this.vista.mostrarMensajeVentana("Error de base de datos: " + ex.getMessage());
                    } catch (Exception ex) {
                        this.vista.mostrarMensajeVentana(ex.getMessage());
                    }
                }
            }
        }
        else
        if (comando.equals("ReporteMedicoBuscarBoton")){
            this.vista.actualizarReporteMedicoLista(this.sistema.getMedicos(),  this.vista.getReporteMedicoBusqueda());
        }
        else
        if  (comando.equals("ReporteMedicoBoton")){
            IMedico medico = this.vista.getMedicoReporte();
            try {
                ReporteActividadMedica reporte = this.sistema.generarReporteActividadMedica(medico, this.vista.getFechaInicial(), this.vista.getFechaFinal());
                this.vista.mostrarReporteMedico(reporte);
            }
            catch (Exception ex) {
                this.vista.mostrarMensajeVentana(ex.getMessage());
            }
        }
        else
        if (comando.equals("AmbulanciaAsociadosBoton")){
            try {
                this.simulacion.prepararAsociados(this.vista.getAsociadosAmbulancia(this.sistema.getAsociados()));
                this.vista.panelAmbulanciaEmpezar(this.simulacion.getAsociadosAmbulancia());
            }
            catch (Exception ex) {
                this.vista.mostrarMensajeVentana(ex.getMessage());
            }

        }
        else
        if (comando.equals("AmbulanciaEmpezarBoton")){
            try {
                this.simulacion.empezarAmbulancia(this.vista.getCantidadSolicitudes());
                this.vista.panelAmbulanciaParar();
            }
            catch (Exception ex) {
                this.vista.mostrarMensajeVentana(ex.getMessage());
            }
        }
        else
        if  (comando.equals("AmbulanciaVolverBoton")){
            this.simulacion.eliminarAsociadosAmbulancia();
            this.vista.panelAmbulanciaAsociados(this.sistema.getAsociados());
        }
        else
        if (comando.equals("AmbulanciaPararBoton")){
            this.simulacion.pararSimulacion();
            this.vista.setBotonAmbulanciaPararNotEnabled();
        }
        else
        if (comando.equals("AmbulanciaTallerBoton")) {
            this.simulacion.enviarATaller();
            this.vista.cambiarBotonTallerEnabled();
            this.vista.cambiarBotonTallerTexto();
        }
        else
        if (comando.equals("CrearTablasBoton")) {
            try {
                this.sistema.crearTablas();
                this.vista.mostrarMensajeVentana("Tablas creadas correctamente");
            } catch (ErrorPersistenciaExcepcion ex) {
                this.vista.mostrarMensajeVentana("Error al crear las tablas: " + ex.getMessage());
            }
        }

    }
    /**
     * <p>
     * Restaura la vista al panel principal de selección de asociados.
     * </p>
     */
    public void terminarSimulacionVista() {
        this.vista.panelAmbulanciaAsociados(this.sistema.getAsociados());
    }

}
