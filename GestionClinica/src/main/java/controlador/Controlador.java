package controlador;

import modelo.ambulancia.*;
import modelo.factoria.FactoryMedico;
import modelo.factoria.FactoryPaciente;
import modelo.facturacion.Factura;
import modelo.facturacion.ReporteActividadMedica;
import modelo.interfaces.IMedico;
import modelo.lugares.Habitacion;
import modelo.lugares.HabitacionCompartida;
import modelo.lugares.HabitacionPrivada;
import modelo.lugares.HabitacionTerapiaIntensiva;
import modelo.personas.Asociado;
import modelo.personas.Paciente;
import modelo.sistema.SistemaFacade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private IVista vista;
    private SistemaFacade sistema;
    private ObservadorAmbulancia observadorAmbulancia;
    private ObservadorHilos observadorHilos;
    private SimulacionAmbulancia simulacion = new SimulacionAmbulancia();

    FactoryMedico factoryMedico = new FactoryMedico();
    FactoryPaciente factoryPaciente = new FactoryPaciente();

    IMedico medClinica = factoryMedico.crearMedico("10000000", "Ana", "García", "Calle 1", 1, "CABA", "111-1111", "M-123", "CLINICA", "PERMANENTE", "MASTER");
    IMedico medCirugia = factoryMedico.crearMedico("10000001", "Bruno", "Lopez", "Calle 2", 2, "CABA", "222-2222", "M-456", "CIRUGIA", "RESIDENTE", "DOCTORADO");
    IMedico medPediatra = factoryMedico.crearMedico("10000002", "Bianca", "Gonzalez", "Calle 2", 2, "CABA", "222-2222", "M-456", "PEDIATRIA", "RESIDENTE", "DOCTORADO");

    // Pacientes
    Paciente p1 = factoryPaciente.crearPaciente("20000000", "Juan", "Pérez", "Calle 10", 10, "CABA", "300-0000", 12345, "JOVEN");
    Paciente p2 = factoryPaciente.crearPaciente("20000001", "Lucía", "Suárez", "Calle 11", 11, "CABA", "300-0001", 22345, "NINIO");
    Paciente p3 = factoryPaciente.crearPaciente("20000002", "Mario", "Gómez", "Calle 12", 12, "CABA", "300-0002", 32345, "MAYOR");

    Habitacion habPrivada1 = new HabitacionPrivada(2000);
    Habitacion habPrivada2 = new HabitacionPrivada(3000);
    Habitacion habCompartida1 = new HabitacionCompartida(1500, 2);
    Habitacion habTerapiaIntensiva1 = new HabitacionTerapiaIntensiva(2000);


    public Controlador(IVista vista, SistemaFacade sistema)
    {
        this.sistema = sistema;
        this.vista = vista;
        this.vista.addActionListener(this);
        try {
            sistema.registraMedico(medClinica);
            sistema.registraMedico(medCirugia);
            sistema.registraMedico(medPediatra);
            sistema.registraPaciente(p1);
            sistema.registraPaciente(p2);
            sistema.registraPaciente(p3);
            this.sistema.cargarDesdeBD();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sistema.agregarHabitacion(habPrivada1);
        sistema.agregarHabitacion(habPrivada2);
        sistema.agregarHabitacion(habCompartida1);
        sistema.agregarHabitacion(habTerapiaIntensiva1);
        this.vista.actualizarPacientesRegistradosLista(this.sistema.getPacientesRegistrados());
        this.vista.actualizarMedicosRegistradosLista(this.sistema.getMedicos());
        this.vista.actualizarAsociadosRegistradosLista(this.sistema.getAsociados());
        this.vista.actualizarIngresarPacienteLista(this.sistema.getPacientesRegistrados(), "");
        this.vista.actualizarAtenderPacienteLista(this.sistema.getListaEspera(), this.sistema.getPacientesAtendidos(), "");
        this.vista.actualizarAtenderMedicoLista(this.sistema.getMedicos(), "");
        this.vista.actualizarBajaAsociadosLista(this.sistema.getAsociados(),  this.vista.getBajaAsociadoBusqueda());
        this.vista.actualizarHabitaciones(this.sistema.getHabitaciones());
        this.vista.actualizarReporteMedicoLista(this.sistema.getMedicos(), "");
        this.vista.actualizarAmbulanciaAsociadosLista(this.sistema.getAsociados());

        observadorAmbulancia = new ObservadorAmbulancia(Ambulancia.get_instance(),this);
        observadorHilos = new ObservadorHilos(this);
    }


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
                this.sistema.atiendePaciente(this.vista.getMedicoAtender(), paciente);
                this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
                this.vista.actualizarEgresarLista(this.sistema.getPacientesAtendidos(), "");
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
                this.sistema.internaPaciente(paciente, this.vista.getHabitacion());
                this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
                this.vista.actualizarHabitaciones(this.sistema.getHabitaciones());
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
                Factura f = this.sistema.egresaPaciente(paciente);
                this.vista.mostrarFactura(f);
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
                this.observadorHilos.agregarObservables(this.simulacion.getHilos());
            }
            catch (Exception ex) {
                this.vista.mostrarMensajeVentana(ex.getMessage());
            }
            this.vista.panelAmbulanciaParar();
        }
        else
        if  (comando.equals("AmbulanciaVolverBoton")){
            this.simulacion.eliminarAsociadosAmbulancia();
            this.vista.panelAmbulanciaAsociados(this.sistema.getAsociados());
        }
        else
        if (comando.equals("AmbulanciaPararBoton")){
            SimulacionAmbulancia.activo = false;
            this.vista.setBotonAmbulanciaPararNotEnabled();
        }
        else
        if (comando.equals("AmbulanciaTallerBoton")) {
            this.simulacion.enviarATaller();
            this.observadorHilos.agregarObservables(this.simulacion.getHilos());
        }
        else
        if (comando.equals("CrearTablasBoton")) {
            this.sistema.crearTablas();
        }

    }

    public void eliminarHilo(Thread t) {
        this.simulacion.eliminarHilo(t);
        if (simulacion.getHilos().isEmpty()){
            this.observadorHilos.eliminarObservables();
            this.vista.panelAmbulanciaAsociados(this.sistema.getAsociados());
        }

    }

    public void modificarDisponibilidad() {
        this.vista.cambiarEstadoAmbulancia(Ambulancia.get_instance().getEstado());
    }
}
