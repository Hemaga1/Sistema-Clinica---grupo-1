package modelo.sistema.ModuloAtencion;

import modelo.lugares.Habitacion;
import modelo.facturacion.RegistroPaciente;
import modelo.excepciones.PacienteSinAtenderExcepcion;
import modelo.excepciones.InternacionCapacidadExcedidaExcepcion;
import modelo.personas.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilizada cuando el paciente debe ser internado, se lo lleva a la habitacion que corresponda y se indica la cantidad de días
 */
public class ServicioInternaciones {

    private List<Habitacion> habitaciones;

    /**
     * Constructor del servicio de internaciones
     */
    public ServicioInternaciones() {
        this.habitaciones = new ArrayList<>();
    }

    /**
     * Internacion del paciente, se lo agrega en la habitacion que corresponda y se setea esta en el registro del paciente<br>
     * <b>Precondición: </b> El paciente debe estar atendido y la habitacion no debe estar llena<br>
     * <b>Postcondición: </b> Paciente internado
     * @param paciente Paciente a ser internado, paciente != null
     * @param registro Registro del paciente, registro !=null
     * @param habitacion Habitacion donde será internado el paciente, habitacion != null
     * @throws PacienteSinAtenderExcepcion
     * @throws InternacionCapacidadExcedidaExcepcion
     */
    public void internar(Paciente paciente, RegistroPaciente registro, Habitacion habitacion) throws PacienteSinAtenderExcepcion, InternacionCapacidadExcedidaExcepcion {
        assert paciente!=null : "El paciente a internar no pouede ser null";
        assert registro!=null : "El registro del paciente no puede ser null";
        assert habitacion!=null : "La habitacion en la que se internará el paciente no puede ser null";
        if (registro == null) {
            throw new PacienteSinAtenderExcepcion();
        }
        else{
            habitacion.ocuparHabitacion(paciente);
            registro.setHabitacion(habitacion);
        }
        assert registro.getHabitacion() == habitacion : "El registro debe tener la habitación asignada";
        assert habitacion.getPacientes().contains(paciente) : "El paciente debe estar internado en la habitación";
    }

    /**
     * Se establece en el registro del paciente la cantidad de días internado<br>
     * <b>Precondición: </b> El paciente debe estar atendido<br>
     * <b>Postcondición: </b> Se setea la cantidad de días de internación en el registro del paciente
     * @param registro Registro paciente, registro != null
     * @param cantDiasInternado canDiasInternado>=0
     * @throws PacienteSinAtenderExcepcion
     */
    public void establecerDiasInternado(RegistroPaciente registro, int cantDiasInternado) throws PacienteSinAtenderExcepcion {
        assert cantDiasInternado>0 : "La cantidad de dias internado no puede ser negativa o 0";
        if (registro == null) {
            throw new PacienteSinAtenderExcepcion();
        }
        else registro.setCantDiasInternado(cantDiasInternado);
    }
    
    /**
     * Agrega una habitación a la lista de habitaciones de la clínica
     * @param habitacion La habitación a agregar
     */
    public void agregarHabitacion(Habitacion habitacion) {
        assert habitacion!=null : "La habitacion no puede ser null";
        habitaciones.add(habitacion);
    }

    /**
     * Quita una habitación de la lista de habitaciones de la clínica
     * @param habitacion La habitación a quitar
     * @return true si la habitación fue removida, false si no estaba en la lista
     */
    public boolean quitarHabitacion(Habitacion habitacion) {
        assert habitacion!=null : "La habitacion a eliminar no debe ser null";
        assert !habitaciones.isEmpty() : "Si vamos a quitar una habitacion, habitaciones no puede ser negativa";
        return habitaciones.remove(habitacion);
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }
}
