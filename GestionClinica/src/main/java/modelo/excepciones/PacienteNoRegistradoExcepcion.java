package modelo.excepciones;

/**
 * Cuando un pociente se tiene que atender debe estar registrado. En caso de no estarlo tenemos esta excepción
 */

public class PacienteNoRegistradoExcepcion extends Exception {

    public PacienteNoRegistradoExcepcion() {
        super("Paciente no registrado");
    }
}
