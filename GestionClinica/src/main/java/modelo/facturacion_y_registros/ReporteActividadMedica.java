package modelo.facturacion_y_registros;

import modelo.interfaces.IMedico;
import java.util.*;

/**
 * Clase con el reporte de la actividad Medica de un médico en un rango de fechas, con el listado de pacientes atendidos
 */

public class ReporteActividadMedica {

    private final IMedico medico;
    private final String fechaInicio;
    private final String fechaFin;
    private final List<PacienteAtendido> consultas;
    private final double totalHonorarios;

    public ReporteActividadMedica(IMedico medico, String fechaInicio, String fechaFin, List<PacienteAtendido> consultas) {
        assert medico!=null : "El médico al cual se le hará el reporte de actividad médica de tal fecha a tal otro no puede ser null";
        assert fechaInicio!=null : "Debe haber una fecha de inicio que no sea null";
        assert fechaFin!=null : "Debe haber una fecha de fin que no sea null";
        assert consultas!=null && !consultas.isEmpty() : "La lista de consultas no debe ser null ni estar vacía para hacer el reporte de actividades del médico";
        this.medico = medico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.consultas = consultas;
        this.totalHonorarios = calcularTotalHonorarios();
    }

    /**
     * Cálculo del honorario total por cada médico
     * @return honorario total del médico
     */
    private double calcularTotalHonorarios() {
        for (PacienteAtendido consulta : consultas) {
            assert consulta != null : "Cada consulta no puede ser null";
            assert consulta.getHonorario() >= 0 : "El honorario de cada consulta no puede ser negativo";
        }

        double total = consultas.stream()
                .mapToDouble(PacienteAtendido::getHonorario)
                .sum();

        assert total >= 0 : "El total de honorarios no puede ser negativo";
        return total;
    }

    /**
     * Muestra del reporte
     * @return reporte del médico
     */
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
        assert sb.length() > 0 : "El reporte generado no puede estar vacío";
        return sb.toString();
    }
}

