package lugares;

import excepciones.DesocupacionPacienteInexistenteExcepcion;
import excepciones.InternacionCapacidadExcedidaExcepcion;
import personas.Paciente;
import java.util.ArrayList;
import java.util.List;

public abstract class Habitacion {

    private int maximoPacientes;
    private int cantidadPacientes;
	protected double costoInicial;
    protected static double costoAsignacion = 15000;
    private List<Paciente> pacientes;

    public Habitacion(double costoInicial, int maximoPacientes) {
        this.costoInicial = costoInicial;
        this.cantidadPacientes = 0;
        this.maximoPacientes = maximoPacientes;
        this.pacientes = new ArrayList<>();
    }

    public abstract double calculaCosto(int cantDias);

    public double getCostoAsignacion() {
        return costoAsignacion;
    }

    public void ocuparHabitacion(Paciente paciente) throws InternacionCapacidadExcedidaExcepcion {
        if (cantidadPacientes >= maximoPacientes)
            throw new InternacionCapacidadExcedidaExcepcion(this, paciente);
        pacientes.add(paciente);
        cantidadPacientes += 1;
    }

    public void desocuparHabitacion(Paciente paciente) throws DesocupacionPacienteInexistenteExcepcion {
        if (!pacientes.remove(paciente))
            throw new DesocupacionPacienteInexistenteExcepcion(paciente);
        cantidadPacientes -= 1;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public int getMaximoPacientes() { return maximoPacientes; }
    public int getCantidadPacientes() { return cantidadPacientes; }

    @Override
    public String toString() {
        return "Habitacion";
    }
}
