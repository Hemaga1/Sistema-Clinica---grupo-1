package facturacion;

import honorarios.IMedico;
import java.util.*;

public class ReporteActividadMedica {

    private final IMedico medico;
    private final String fechaInicio;
    private final String fechaFin;
    private final List<PacienteAtendido> consultas;
    private final double totalHonorarios;

    public ReporteActividadMedica(IMedico medico, String fechaInicio, String fechaFin, List<PacienteAtendido> consultas) {
        this.medico = medico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.consultas = consultas;
        this.totalHonorarios = calcularTotalHonorarios();
    }

    private double calcularTotalHonorarios() {
        return consultas.stream()
                .mapToDouble(PacienteAtendido::getHonorario)
                .sum();
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();

        sb.append("========================================\n");
        sb.append("REPORTE DE ACTIVIDAD MÉDICA\n");
        sb.append("========================================\n");
        sb.append("Médico: ").append(medico.getNombre()).append("\n");
        sb.append("Especialidad: ").append(medico.getEspecialidad()).append("\n");
        sb.append("Período: ").append(fechaInicio).append(" - ").append(fechaFin).append("\n");
        sb.append("========================================\n\n");

        if (consultas.isEmpty()) {
            sb.append("No se encontraron consultas en el período especificado.\n");
        } else {
            sb.append("CONSULTAS REALIZADAS:\n");
            sb.append("----------------------------------------\n");
            sb.append(String.format("%-12s %-25s %-15s\n", "FECHA", "PACIENTE", "HONORARIO"));
            sb.append("----------------------------------------\n");

            for (PacienteAtendido consulta : consultas) {
                String nombreCompleto = consulta.getNombrePaciente() + " " + consulta.getApellidoPaciente();
                sb.append(String.format("%-12s %-25s $%-15.2f\n",
                        consulta.getFecha(),
                        nombreCompleto,
                        consulta.getHonorario()));
            }

            sb.append("----------------------------------------\n");
            sb.append(String.format("TOTAL HONORARIOS: $%.2f\n", totalHonorarios));
            sb.append("TOTAL CONSULTAS: ").append(consultas.size()).append("\n");
        }

        sb.append("========================================\n");

        return sb.toString();
    }
}

