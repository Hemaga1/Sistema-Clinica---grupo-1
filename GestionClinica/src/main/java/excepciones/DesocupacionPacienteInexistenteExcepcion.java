package excepciones;

import personas.Paciente;

public class DesocupacionPacienteInexistenteExcepcion extends Exception {
    public DesocupacionPacienteInexistenteExcepcion(Paciente paciente) {
        super("No se puede desocupar la habitación: el paciente no está internado: " + paciente);
    }
}




