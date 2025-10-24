package modelo.excepciones;

import modelo.personas.Paciente;

/**
 * Cuando un paciente internado egresa se debe sacar de la habitación, si no se encuentra internado tenemos esta excepción
 */

public class DesocupacionPacienteInexistenteExcepcion extends Exception {
    public DesocupacionPacienteInexistenteExcepcion(Paciente paciente) {
        super("No se puede desocupar la habitación: el paciente no está internado: " + paciente);
    }
}




