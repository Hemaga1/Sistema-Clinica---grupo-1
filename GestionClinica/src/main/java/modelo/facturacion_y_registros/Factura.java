package modelo.facturacion_y_registros;

import modelo.personas.Paciente;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase para generar y mostrar la factura del paciente que invoque el método ImprimeFactura
 */

public class Factura {
    private static int numeroFacturaSig = 1;
    private final int numeroFactura;
    private final String fechaEgreso;
    private final double monto;
    private final String nombrePaciente;
    private final String apellidoPaciente;
    private final String fechaIngreso;
    private final String tipoHabitacion;
    private final int cantDias;

    private final double costoHabitacion;
    private final double total;
    private final ArrayList<ConsultaMedica> consultasMedicas;

    /**
     * Constructor de la factura para luego ser mostrada
     * @param paciente Paciente del cual se quiere generar la factura, paciente!=null
     * @param registro Registro del paciente, registro!= null
     */
    public Factura(Paciente paciente, RegistroPaciente registro) {
        assert paciente!=null : "El paciente no puede ser null";
        assert registro!=null : "El registro del paciente donde se indican habitacion, ingreso, cantidad de días internados y el listado de sus consultas médicas no puede ser null a la hora de hacer la factura";

        this.nombrePaciente = paciente.getNombre();
        this.apellidoPaciente = paciente.getApellido();
        this.fechaIngreso = registro.getFechaIngreso();
        this.fechaEgreso = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.numeroFactura = numeroFacturaSig;
        numeroFacturaSig++;
        this.consultasMedicas = registro.getConsultasMedicas();

        assert nombrePaciente != null && !nombrePaciente.isEmpty() : "El nombre del paciente no debe ser null ni estar vacío";
        assert apellidoPaciente != null && !apellidoPaciente.isEmpty() : "El apellido del paciente no debe ser null ni estar vacío";
        assert fechaIngreso != null : "La fecha de ingreso no debe ser null ni estar vacía";
        assert consultasMedicas != null && !consultasMedicas.isEmpty(): "El listado de consultas médicas no debe ser null";

        if (registro.getHabitacion() != null){
            this.tipoHabitacion = registro.getHabitacion().toString();
            this.cantDias = registro.getCantDiasInternado();
            this.costoHabitacion = registro.getHabitacion().calculaCosto(cantDias);
            assert cantDias > 0 : "Si el paciente fue internado la cantidad de días internados debe ser mayor a 0";
            assert costoHabitacion >= 0 : "El costo de la habitación al ser internado no puede ser negativo";
        }
        else {
            this.tipoHabitacion = "";
            this.cantDias = 0;
            this.costoHabitacion = 0;
        }

        // Calcular totales con 20% de incremento en modelo.honorarios médicos
        double totalConsultas = 0;
        for (ConsultaMedica consulta : consultasMedicas) {
            double precioConIncremento = consulta.getPrecio() * 1.20;
            consulta.setPrecio(precioConIncremento);
            totalConsultas += precioConIncremento;
        }
        this.total = this.costoHabitacion + totalConsultas;
        this.monto = this.total;
        assert total >= 0 : "El total de la factura no puede ser negativo";
        assert monto >= 0 : "El monto de la factura no puede ser negativo";
    }

    /**
     * Muestra de la factura
     * @return factura del paciente del cual se invoque el método
     */
    public StringBuilder imprimeFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nº Factura: ").append(numeroFactura).append("\n");
        sb.append("Nombre Paciente: ").append(nombrePaciente).append("-").append(apellidoPaciente).append("\n");
        sb.append("Fecha Ingreso: ").append(fechaIngreso).append("\n");
        sb.append("Fecha Egreso: ").append(fechaEgreso).append("\n");
        
        if (!tipoHabitacion.isEmpty()) {
            sb.append("Cantidad de días: ").append(cantDias).append("\n");
            sb.append("Habitación tipo: ").append(tipoHabitacion).append("                        Costo:                 $").append(String.format("%.0f", costoHabitacion)).append("\n");
        }
        
        sb.append("\nConsultas Médicas:\n\n");
        
        for (ConsultaMedica consulta : consultasMedicas) {
            sb.append("Nombre Médico: ").append(String.format("%-20s", consulta.getNombreMedico()))
              .append("Especialidad: ").append(String.format("%-15s", consulta.getEspecialidad()))
              .append("Subtotal:         $").append(String.format("%.0f", consulta.getPrecio())).append("\n");
        }
        
        sb.append("\nTotal:                $").append(String.format("%.0f", total)).append("\n");
        assert sb.length() > 0 : "La factura generada no debe estar vacía";
        return sb;
    }
}
