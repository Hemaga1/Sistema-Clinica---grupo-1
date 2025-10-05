package facturacion;

import personas.Paciente;

public class Reporte {
    private String fecha;
    private Paciente paciente;
    private double honorario;

    public Reporte(Paciente paciente, String fecha, double honorario) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.honorario = honorario;
    }

    @Override
    public String toString() {
        return "PacienteFecha " + "fecha: " + fecha + ", paciente: " + paciente.toString() ;
    }
}
