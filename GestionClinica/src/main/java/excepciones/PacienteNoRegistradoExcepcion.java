package excepciones;

public class PacienteNoRegistradoExcepcion extends Exception {

    public PacienteNoRegistradoExcepcion() {
        super("Paciente no registrado");
    }
}
