package controlador;

import modelo.excepciones.InputNumeroInvalidoExcepcion;
import modelo.excepciones.InputStringInvalidoExcepcion;
import modelo.excepciones.InputVacioException;
import modelo.facturacion.PacienteAtendido;
import modelo.facturacion.RegistroPaciente;
import modelo.interfaces.IMedico;
import modelo.personas.Paciente;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IVista {

    static final String ENVIARPACIENTE = "ENVIAR PACIENTE";
    static final String ENVIARMEDICO = "ENVIAR MEDICO";

    JButton getPacienteBotonEnviar();
    JButton getMedicoBotonEnviar();
    JButton getIngresarPacienteBoton();
    JButton getIngresarPacienteBuscarBoton();
    JButton getAtenderPacienteBuscarBoton();
    JButton getAtenderMedicoBuscarBoton();
    JButton getAtenderPacienteBoton();

    String getDNIPaciente() throws InputVacioException, InputNumeroInvalidoExcepcion;
    String getNombrePaciente() throws InputVacioException, InputStringInvalidoExcepcion;
    String getApellidoPaciente() throws InputVacioException, InputStringInvalidoExcepcion;
    String getCallePaciente() throws InputVacioException;
    int getNumPaciente() throws InputVacioException, InputNumeroInvalidoExcepcion;
    String getCiudadPaciente() throws InputVacioException, InputStringInvalidoExcepcion;
    String getTelefonoPaciente() throws InputVacioException, InputNumeroInvalidoExcepcion;
    String getRangoEtarioPaciente();
    int getHistoriaPaciente() throws InputVacioException, InputNumeroInvalidoExcepcion;
    void mostrarPacientesRegistrados(Set<Paciente> pacientesRegistrados);
    void actualizarIngresarPacienteLista(Set<Paciente> pacientesRegistrados, String comparar);
    void actualizarAtenderPacienteLista(Set<Paciente> pacientesRegistrados, Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar);
    String getIngresarPacienteBusqueda();
    String getAtenderPacienteBusqueda();

    String getDNIMedico() throws InputVacioException, InputNumeroInvalidoExcepcion;
    String getNombreMedico() throws InputVacioException, InputStringInvalidoExcepcion;
    String getApellidoMedico() throws InputVacioException, InputStringInvalidoExcepcion;
    String getCalleMedico() throws InputVacioException;
    int getNumMedico() throws InputVacioException, InputNumeroInvalidoExcepcion;
    String getCiudadMedico() throws InputVacioException, InputStringInvalidoExcepcion;
    String getTelefonoMedico() throws InputVacioException, InputNumeroInvalidoExcepcion;
    String getMatriculaMedico() throws InputVacioException, InputNumeroInvalidoExcepcion;
    String getEspecialidadMedico();
    String getContratacionMedico();
    String getPosgradoMedico();
    void mostrarMedicosRegistrados(Map<IMedico, List<PacienteAtendido>> medicosRegistrados);
    void actualizarAtenderMedicoLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados, String comparar);
    String getAtenderMedicoBusqueda();

    Paciente getPacienteIngresar();
    Paciente getPacienteAtender();
    IMedico getMedicoAtender();

    void mostrarMensajeExcepcionPaciente(Exception e);
    void mostrarMensajeExcepcionMedico(Exception e);
    void ocultarMensajeExcepcionPaciente();
    void ocultarMensajeExcepcionMedico();

    void addActionListener(ActionListener actionListener);
}
