package excepciones;

import lugares.Habitacion;
import personas.Paciente;

public class InternacionCapacidadExcedidaExcepcion extends Exception {
    public InternacionCapacidadExcedidaExcepcion(Habitacion habitacion, Paciente paciente) {
        super("Capacidad de la " + habitacion.toString() + " excedida; no se pudo internar al paciente: " + paciente);
    }
}




