package modelo.lugares;

import modelo.excepciones.DesocupacionPacienteInexistenteExcepcion;
import modelo.excepciones.InternacionCapacidadExcedidaExcepcion;
import modelo.personas.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta habitacion que será extendida de los distintos tipos de habitaciones
 */
public abstract class Habitacion {
    public static int sigNumHabitacion = 0;
    private int numHabitacion;
    private int maximoPacientes;
    private int cantidadPacientes;
	protected double costoInicial;
    protected static double costoAsignacion = 15000;
    private List<Paciente> pacientes;

    /**
     * Constructor para los diferentes tipos de habitacion
     * @param costoInicial Costo inicial de internacion en la habitacion, costoInicial >= 0
     * @param maximoPacientes Maximo de pacientes que puede haber en la habitacionn maximoPacientes>0
     */
    public Habitacion(double costoInicial, int maximoPacientes) {
        sigNumHabitacion++;
        numHabitacion = sigNumHabitacion;
        this.costoInicial = costoInicial;
        this.cantidadPacientes = 0;
        this.maximoPacientes = maximoPacientes;
        this.pacientes = new ArrayList<>();
    }

    /**
     * Método abstracto del cálculo de costos segun la cantidad de días de internacion
     * @param cantDias cantDias>0
     */
    public abstract double calculaCosto(int cantDias);

    public double getCostoAsignacion() {
        return costoAsignacion;
    }

    /**
     * Si hay lugar en la habitacion se ingresa al paciente>br>
     * <b>Precondición: </b> Debe haber lugar en la habitacion para poder ingresar al paciente<br>
     * <b>Postcondición: </b> Se ingresa al paciente en la habitacion y se actualiza la cantidad de paciente dentro de la misma
     * @param paciente paciente!=null, paciente!=""
     * @throws InternacionCapacidadExcedidaExcepcion
     */
    public void ocuparHabitacion(Paciente paciente) throws InternacionCapacidadExcedidaExcepcion {
        if (cantidadPacientes >= maximoPacientes)
            throw new InternacionCapacidadExcedidaExcepcion(this, paciente);
        pacientes.add(paciente);
        cantidadPacientes += 1;
    }

    /**
     * El paciente egresa entonces desocupa la habitación<br>
     * <b>Precondiciones: </b>
     * <ul>
     *     <li>La habitación no debe estar vacía</li>
     *     <li>El paciente debe estar en la habitación</li>
     * </ul>
     * <b>Postcondición: </b> Se saca al paciente de la habitación, actualizando el la cantidad de pacientes en la misma
     * @param paciente
     * @throws DesocupacionPacienteInexistenteExcepcion
     */
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

    public boolean estaLlena() {
        return cantidadPacientes == maximoPacientes;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    @Override
    public String toString() {
        return "Habitacion " + numHabitacion;
    }
}
