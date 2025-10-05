package facturacion;

import personas.Paciente;

public class Factura {
    private Paciente paciente;
    private double monto;
    private int numeroFactura;
    private static int numeroFacturaMax;

    public Factura(Paciente paciente) {
        this.paciente = paciente;
        this.numeroFactura = this.numeroFacturaMax;
        this.numeroFacturaMax++;
    }

    public StringBuilder ImprimeFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("FACTURA\n");
        sb.append("Número de Factura: ").append(numeroFactura).append("\n");
        sb.append("Paciente: ").append(paciente.toString()).append("\n");
        sb.append("Monto: $").append(String.format("%.2f", monto)).append("\n");
        sb.append("===============\n");
        return sb;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }
}
