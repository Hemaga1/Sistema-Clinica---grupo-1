package excepciones;

/**
 * Al registrar un paciente, este no pudo estar registrado antes, si lo estuvo tenemos esta excepción
 */

public class PacienteDuplicadoExcepcion extends Exception {

    public PacienteDuplicadoExcepcion() {
        super("El paciente que intenta registrar ya se encuentra registrado");
    }
}
