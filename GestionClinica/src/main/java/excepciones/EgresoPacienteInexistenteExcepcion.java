package excepciones;

import personas.Paciente;

public class EgresoPacienteInexistenteExcepcion extends Exception {
    public EgresoPacienteInexistenteExcepcion(Paciente paciente) {
        super("El paciente no está internado en la habitación: " + paciente);
    }
}




