package controlador;

import modelo.ambulancia.*;
import modelo.excepciones.CantidadSolicitudesInvalidaExcepcion;
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
import util.UTIL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Controlador implements ActionListener {
    private IVista vista;
    private SistemaFacade  sistema;
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
            sistema.registraAsociado(new Asociado("30000000", "Camilo", "Fernández", "Calle 15", 12, "CABA", "100-0001"));
            sistema.registraAsociado(new Asociado("30000001", "Paola", "Benítez", "Calle 20", 100, "CABA", "100-0002"));
            sistema.registraAsociado(new Asociado("30000002", "Mariano", "Martínez", "Calle 37", 230, "CABA", "100-0003"));
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
            Object comando = e.getSource();
            if (comando == vista.getPacienteBotonEnviar()) {
                try {
                    String rango_etario = this.vista.getRangoEtarioPaciente();
                    if (rango_etario.equalsIgnoreCase("NIÑO")) {
                        rango_etario = "NINIO";
                    }
                    Paciente p = factoryPaciente.crearPaciente(this.vista.getDNIPaciente(), this.vista.getNombrePaciente(), this.vista.getApellidoPaciente(), this.vista.getCallePaciente(), this.vista.getNumPaciente(), this.vista.getCiudadPaciente(), this.vista.getTelefonoPaciente(), this.vista.getHistoriaPaciente(), rango_etario);
                    this.vista.ocultarMensajeExcepcionPaciente();
                    this.sistema.registraPaciente(p);
                    this.vista.actualizarPacientesRegistradosLista(this.sistema.getPacientesRegistrados());
                    this.vista.actualizarIngresarPacienteLista(this.sistema.getPacientesRegistrados(), "");
                }
                catch (Exception ex) {
                    this.vista.mostrarExcepcionVentana(ex);
                }
            }
            else
                if (comando == vista.getMedicoBotonEnviar()) {
                    try {
                        IMedico m = factoryMedico.crearMedico(this.vista.getDNIMedico(), this.vista.getNombreMedico(), this.vista.getApellidoMedico(), this.vista.getCalleMedico(), this.vista.getNumMedico(), this.vista.getTelefonoMedico(), this.vista.getCiudadMedico(), this.vista.getMatriculaMedico(), this.vista.getEspecialidadMedico(), this.vista.getContratacionMedico(), this.vista.getPosgradoMedico());
                        this.vista.ocultarMensajeExcepcionMedico();
                        this.sistema.registraMedico(m);
                        this.vista.actualizarMedicosRegistradosLista(this.sistema.getMedicos());
                        this.vista.actualizarAtenderMedicoLista(this.sistema.getMedicos(), "");
                        this.vista.actualizarReporteMedicoLista(this.sistema.getMedicos(), "");
                    }
                    catch (Exception ex) {
                        this.vista.mostrarExcepcionVentana(ex);
                    }
                }
                else
                    if (comando == vista.getAsociadoBotonEnviar()){
                        try {
                            Asociado a = new Asociado(this.vista.getDNIAsociado(), this.vista.getNombreAsociado(), this.vista.getApellidoAsociado(), this.vista.getCalleAsociado(), this.vista.getNumAsociado(), this.vista.getCiudadAsociado(), this.vista.getTelefonoAsociado());
                            this.sistema.registraAsociado(a);
                            this.vista.actualizarAsociadosRegistradosLista(this.sistema.getAsociados());
                            this.vista.actualizarAmbulanciaAsociadosLista(this.sistema.getAsociados());
                            this.vista.actualizarBajaAsociadosLista(this.sistema.getAsociados(),  this.vista.getBajaAsociadoBusqueda());
                        }
                        catch (Exception ex) {
                            this.vista.mostrarExcepcionVentana(ex);
                        }
                    }
                    else
                        if (comando == vista.getIngresarPacienteBoton()){
                            Paciente paciente = this.vista.getPacienteIngresar();
                            if (paciente != null){
                                this.sistema.ingresaPaciente(this.vista.getPacienteIngresar());
                                this.vista.actualizarAtenderPacienteLista(this.sistema.getListaEspera(), this.sistema.getPacientesAtendidos(), this.vista.getAtenderPacienteBusqueda());
                                this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
                            }
                        }
                        else
                            if (comando == vista.getIngresarPacienteBuscarBoton()){
                                this.vista.actualizarIngresarPacienteLista(this.sistema.getPacientesRegistrados(), this.vista.getIngresarPacienteBusqueda());
                            }
                            else
                                if (comando == vista.getAtenderPacienteBuscarBoton()) {
                                    this.vista.actualizarAtenderPacienteLista(this.sistema.getListaEspera(), this.sistema.getPacientesAtendidos(), this.vista.getAtenderPacienteBusqueda());
                                }
                                else
                                    if (comando == vista.getAtenderMedicoBuscarBoton()) {
                                        this.vista.actualizarAtenderMedicoLista(this.sistema.getMedicos(), this.vista.getAtenderMedicoBusqueda());
                                    }
                                    else
                                        if (comando == vista.getAtenderPacienteBoton()) {
                                            Paciente paciente = this.vista.getPacienteAtender();
                                            if (paciente != null) {
                                                this.sistema.atiendePaciente(this.vista.getMedicoAtender(), paciente);
                                                this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
                                                this.vista.actualizarEgresarLista(this.sistema.getPacientesAtendidos(), "");
                                            }
                                        }
                                        else
                                            if (comando == vista.getInternarPacienteBuscarBoton()) {
                                                this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
                                            }
                                            else
                                                if (comando == vista.getInternarPacienteBoton()) {
                                                    Paciente paciente = this.vista.getPacienteInternar();
                                                    if (paciente != null) {
                                                        this.sistema.internaPaciente(paciente, this.vista.getHabitacion());
                                                        this.vista.actualizarInternarPacienteLista(this.sistema.getPacientesAtendidos(), this.vista.getInternarPacienteBusqueda());
                                                        this.vista.actualizarHabitaciones(this.sistema.getHabitaciones());
                                                    }
                                                }
                                                else
                                                    if (comando == vista.getEgresarBuscarBoton()) {
                                                        this.vista.actualizarEgresarLista(this.sistema.getPacientesAtendidos(),  this.vista.getEgresarPacienteBusqueda());
                                                    }
                                                    else
                                                        if (comando == vista.getEgresarBoton()) {
                                                            Paciente paciente = this.vista.getPacienteEgresar();
                                                            if (paciente != null) {
                                                                Factura f = this.sistema.egresaPaciente(paciente);
                                                                this.vista.mostrarFactura(f);
                                                            }
                                                        }
                                                        else
                                                            if (comando == vista.getBajaAsociadoBuscarBoton()) {
                                                                this.vista.actualizarBajaAsociadosLista(this.sistema.getAsociados(),  this.vista.getBajaAsociadoBusqueda());
                                                            }
                                                            else
                                                                if (comando == vista.getBajaAsociadoBoton()) {
                                                                    Asociado asociado = this.vista.getAsociadoBajar();
                                                                    if (asociado != null) {
                                                                        if (this.vista.confirmarBaja()) {
                                                                            try {
                                                                                this.sistema.eliminarAsociado(this.vista.getAsociadoBajar());
                                                                                this.vista.actualizarAsociadosRegistradosLista(this.sistema.getAsociados());
                                                                                this.vista.actualizarAmbulanciaAsociadosLista(this.sistema.getAsociados());
                                                                                this.vista.actualizarBajaAsociadosLista(this.sistema.getAsociados(), this.vista.getBajaAsociadoBusqueda());
                                                                            } catch (Exception ex) {
                                                                                this.vista.mostrarExcepcionVentana(ex);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                else
                                                                    if (comando == vista.getReporteMedicoBuscarBoton()){
                                                                        this.vista.actualizarReporteMedicoLista(this.sistema.getMedicos(),  this.vista.getReporteMedicoBusqueda());
                                                                    }
                                                                    else
                                                                        if  (comando == vista.getReporteMedicoBoton()){
                                                                            IMedico medico = this.vista.getMedicoReporte();
                                                                            try {
                                                                                ReporteActividadMedica reporte = this.sistema.generarReporteActividadMedica(medico, this.vista.getFechaInicial(), this.vista.getFechaFinal());
                                                                                this.vista.mostrarReporteMedico(reporte);
                                                                            }
                                                                            catch (Exception ex) {
                                                                                this.vista.mostrarExcepcionVentana(ex);
                                                                            }
                                                                        }
                                                                        else
                                                                            if (comando == vista.getAmbulanciaAsociadosBoton()){
                                                                                try {
                                                                                    this.simulacion.prepararAsociados(this.vista.getAsociadosAmbulancia(this.sistema.getAsociados()));
                                                                                    this.vista.panelAmbulanciaEmpezar(this.simulacion.getAsociadosAmbulancia());
                                                                                }
                                                                                catch (Exception ex) {
                                                                                    this.vista.mostrarExcepcionVentana(ex);
                                                                                }

                                                                            }
                                                                            else
                                                                                if (comando == vista.getAmbulanciaEmpezarBoton()){
                                                                                    try {
                                                                                        this.simulacion.empezarAmbulancia(this.vista.getCantidadSolicitudes());
                                                                                        this.observadorHilos.agregarObservables(this.simulacion.getHilos());
                                                                                    }
                                                                                    catch (Exception ex) {
                                                                                        this.vista.mostrarExcepcionVentana(ex);
                                                                                    }
                                                                                    this.vista.panelAmbulanciaParar();
                                                                                }
                                                                                else
                                                                                    if  (comando == vista.getAmbulanciaVolverBoton()){
                                                                                        this.simulacion.eliminarAsociadosAmbulancia();
                                                                                        this.vista.panelAmbulanciaAsociados(this.sistema.getAsociados());
                                                                                    }
                                                                                    else
                                                                                        if (comando == vista.getAmbulanciaPararBoton()){
                                                                                            SimulacionAmbulancia.activo = false;
                                                                                            this.vista.setBotonAmbulanciaPararNotEnabled();
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
