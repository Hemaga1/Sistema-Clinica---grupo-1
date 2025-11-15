package modelo.facturacion_y_registros;

import modelo.personas.Paciente;

/**
 * Clase de un paciente atendido que será agregado a la lista de los atendidos, incluye fecha en que es atendido el mismo
 */

public class PacienteAtendido {
    private String fecha;
    private String nombrePaciente;
    private String apellidoPaciente;
    private double honorario;

    /**
     * Constructor del paciente atendido para hacer agregado a la lista de pacientes atendidos
     * @param paciente Paciente a ser atendido, paciente!=null
     * @param fecha fecha de atencion, fecha!=null, fecha!=""
     * @param honorario honorario>0
     */
    public PacienteAtendido(Paciente paciente, String fecha, double honorario) {
        assert paciente!=null : "El paciente no puede ser null";
        assert fecha!=null && !fecha.isEmpty() : "La fecha que ingresa el paciente no puede ser null ni estar vacia";
        assert honorario>=0 : "El honoario no puede ser negativo";
        this.fecha = fecha;
        this.nombrePaciente = paciente.getNombre();
        this.apellidoPaciente = paciente.getApellido();
        this.honorario = honorario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public double getHonorario() {
        return honorario;
    }

    @Override
    public String toString() {
        return "Paciente: " + nombrePaciente + " " + apellidoPaciente + " fecha: " + fecha + " honorario: " + honorario;
    }
}