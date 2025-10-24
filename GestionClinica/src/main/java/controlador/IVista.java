package controlador;

import modelo.excepciones.ImputVacioException;
import modelo.facturacion.PacienteAtendido;
import modelo.interfaces.IMedico;
import modelo.personas.Paciente;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IVista {

    static final String ENVIARPACIENTE = "ENVIAR PACIENTE";
    static final String ENVIARMEDICO = "ENVIAR MEDICO";

    String getDNIPaciente() throws ImputVacioException;
    String getNombrePaciente() throws ImputVacioException;
    String getApellidoPaciente() throws ImputVacioException;
    String getCallePaciente() throws ImputVacioException;
    int getNumPaciente() throws ImputVacioException;
    String getCiudadPaciente() throws ImputVacioException;
    String getTelefonoPaciente() throws ImputVacioException;
    String getRangoEtarioPaciente();
    int getHistoriaPaciente() throws ImputVacioException;
    void mostrarPacientesRegistrados(Set<Paciente> pacientesRegistrados);

    String getDNIMedico() throws ImputVacioException;
    String getNombreMedico() throws ImputVacioException;
    String getApellidoMedico() throws ImputVacioException;
    String getCalleMedico() throws ImputVacioException;
    int getNumMedico() throws ImputVacioException;
    String getCiudadMedico() throws ImputVacioException;
    String getTelefonoMedico() throws ImputVacioException;
    String getMatriculaMedico() throws ImputVacioException;
    String getEspecialidadMedico();
    String getContratacionMedico();
    String getPosgradoMedico();
    void mostrarMedicosRegistrados(Map<IMedico, List<PacienteAtendido>> medicosRegistrados);

    void mostrarMensajeExcepcionPaciente(Exception e);
    void mostrarMensajeExcepcionMedico(Exception e);
    void ocultarMensajeExcepcionPaciente();
    void ocultarMensajeExcepcionMedico();

    void addActionListener(ActionListener actionListener);
}
