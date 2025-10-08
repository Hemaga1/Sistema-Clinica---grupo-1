package facturacion;

import personas.Paciente;

public class PacienteAtendido {
    private String fecha;
    private String nombrePaciente;
    private String apellidoPaciente;
    private double honorario;

    public PacienteAtendido(Paciente paciente, String fecha, double honorario) {
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