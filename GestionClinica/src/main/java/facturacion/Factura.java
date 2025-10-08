package facturacion;

import honorarios.IMedico;
import personas.Paciente;

import java.util.ArrayList;

public class Factura {
    private double monto;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String fechaIngreso;
    private String tipoHabitacion;
    private int cantDias;
    private int numeroFactura;
    private static int numeroFacturaSig = 1;
    private ArrayList<ConsultaMedica> consultasMedicas;

    public Factura(Paciente paciente, RegistroPaciente registro) {
        this.nombrePaciente = paciente.getNombre();
        this.apellidoPaciente = paciente.getApellido();
        this.fechaIngreso = registro.getFechaIngreso();
        this.numeroFactura = numeroFacturaSig;
        numeroFacturaSig++;
        this.consultasMedicas = registro.getConsultasMedicas();
        if (registro.getHabitacion() != null){
            this.tipoHabitacion = registro.getHabitacion().toString();
            this.cantDias = registro.getCantDiasInternado();
        }
        else {
            this.tipoHabitacion = "";
            this.cantDias = 0;
        }
    }

    public StringBuilder ImprimeFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("FACTURA\n");
        sb.append("Número de Factura: ").append(numeroFactura).append("\n");
        sb.append("Paciente: ").append(nombrePaciente + " " + apellidoPaciente + "\n"); //paciente.toString()).append("\n");
        sb.append("Fecha de Ingreso: ").append(fechaIngreso).append("\n");
        if (tipoHabitacion != "") {
            sb.append("Cantidad de dias: ").append(cantDias).append("\n");
            sb.append("Tipo de Habitacion: ").append(tipoHabitacion).append("\n");
        }
        sb.append("Monto: $").append(String.format("%.2f", monto)).append("\n");
        sb.append("===============\n");
        return sb;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }
}
