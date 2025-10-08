package excepciones;

public class PacienteDuplicadoExcepcion extends Exception {

    public PacienteDuplicadoExcepcion() {
        super("El paciente que intenta registrar ya se encuentra registrado");
    }
}
