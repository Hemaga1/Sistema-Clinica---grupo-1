package modelo.excepciones;

import modelo.lugares.Habitacion;
import modelo.personas.Paciente;

/**
 * Cuando un paciente debe ser internado se verifica si hay lugar en la habitación, si se excede la capacidad tenemos esta excepción
 */

public class InternacionCapacidadExcedidaExcepcion extends Exception {
    public InternacionCapacidadExcedidaExcepcion(Habitacion habitacion, Paciente paciente) {
        super("Capacidad de la " + habitacion.toString() + " excedida; no se pudo internar al paciente: " + paciente);
    }
}




