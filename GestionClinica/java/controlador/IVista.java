package controlador;

import modelo.excepciones.FechaInvalidaExcepcion;
import modelo.excepciones.InputNumeroInvalidoExcepcion;
import modelo.excepciones.InputStringInvalidoExcepcion;
import modelo.excepciones.InputVacioExcepcion;
import modelo.facturacion.Factura;
import modelo.facturacion.PacienteAtendido;
import modelo.facturacion.RegistroPaciente;
import modelo.facturacion.ReporteActividadMedica;
import modelo.interfaces.IMedico;
import modelo.lugares.Habitacion;
import modelo.personas.Asociado;
import modelo.personas.Paciente;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IVista {

    static final String ENVIARPACIENTE = "ENVIAR PACIENTE";
    static final String ENVIARMEDICO = "ENVIAR MEDICO";


    //GET BOTONES
    JButton getPacienteBotonEnviar();
    JButton getMedicoBotonEnviar();
    JButton getIngresarPacienteBoton();
    JButton getIngresarPacienteBuscarBoton();
    JButton getAtenderPacienteBuscarBoton();
    JButton getAtenderMedicoBuscarBoton();
    JButton getAtenderPacienteBoton();
    JButton getInternarPacienteBuscarBoton();
    JButton getInternarPacienteBoton();
    JButton getEgresarBoton();
    JButton getEgresarBuscarBoton();
    JButton getAsociadoBotonEnviar();
    JButton getBajaAsociadoBuscarBoton();
    JButton getBajaAsociadoBoton();
    JButton getReporteMedicoBuscarBoton();
    JButton getReporteMedicoBoton();

    //GET FIELDS PACIENTE
    String getDNIPaciente() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getNombrePaciente() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getApellidoPaciente() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getCallePaciente() throws InputVacioExcepcion;
    int getNumPaciente() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getCiudadPaciente() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getTelefonoPaciente() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getRangoEtarioPaciente();
    int getHistoriaPaciente() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;

    //GET FIELDS MEDICO
    String getDNIMedico() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getNombreMedico() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getApellidoMedico() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getCalleMedico() throws InputVacioExcepcion;
    int getNumMedico() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getCiudadMedico() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getTelefonoMedico() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getMatriculaMedico() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getEspecialidadMedico();
    String getContratacionMedico();
    String getPosgradoMedico();

    //GET FIELDS ASOCIADO
    String getDNIAsociado() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getNombreAsociado() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getApellidoAsociado() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getCalleAsociado() throws InputVacioExcepcion;
    int getNumAsociado() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;
    String getCiudadAsociado() throws InputVacioExcepcion, InputStringInvalidoExcepcion;
    String getTelefonoAsociado() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion;

    //GET FIELDS FECHAS
    String getFechaInicial() throws InputVacioExcepcion, FechaInvalidaExcepcion;
    String getFechaFinal() throws InputVacioExcepcion, FechaInvalidaExcepcion;

    //GET SELECTEDS
    Paciente getPacienteIngresar();
    Paciente getPacienteAtender();
    IMedico getMedicoAtender();
    Paciente getPacienteInternar();
    Paciente getPacienteEgresar();
    Habitacion getHabitacion();
    Asociado getAsociadoBajar();
    IMedico getMedicoReporte();

    //GET BUSQUEDAS
    String getIngresarPacienteBusqueda();
    String getAtenderPacienteBusqueda();
    String getInternarPacienteBusqueda();
    String getEgresarPacienteBusqueda();
    String getAtenderMedicoBusqueda();
    String getBajaAsociadoBusqueda();
    String getReporteMedicoBusqueda();

    //ACTUALIZAR LISTAS
    void actualizarPacientesRegistradosLista(Set<Paciente> pacientesRegistrados);
    void actualizarMedicosRegistradosLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados);
    void actualizarAsociadosRegistradosLista(Set<Asociado> asociadosRegistrados);
    void actualizarIngresarPacienteLista(Set<Paciente> pacientesRegistrados, String comparar);
    void actualizarAtenderPacienteLista(Set<Paciente> pacientesRegistrados, Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar);
    void actualizarInternarPacienteLista(Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar);
    void actualizarEgresarLista(Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar);
    void actualizarHabitaciones(List<Habitacion> habitaciones);
    void actualizarAtenderMedicoLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados, String comparar);
    void actualizarBajaAsociadosLista(Set<Asociado> asociados, String comparar);
    void actualizarReporteMedicoLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados, String comparar);



    void mostrarFactura(Factura factura);

    void mostrarReporteMedico(ReporteActividadMedica reporte);

    void cambiarEstadoAmbulancia(String estado);

    boolean confirmarBaja();
    void mostrarMensajeExcepcionPaciente(Exception e);
    void mostrarMensajeExcepcionMedico(Exception e);
    void ocultarMensajeExcepcionPaciente();
    void ocultarMensajeExcepcionMedico();
    void mostrarExcepcionVentana(Exception e);

    void addActionListener(ActionListener actionListener);
}
