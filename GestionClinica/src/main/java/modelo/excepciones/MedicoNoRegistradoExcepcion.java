package modelo.excepciones;

/**
 * Cuando un pociente se tiene que atender con un médico, este último debe estar registrado. En caso de no estarlo tenemos esta excepción
 */

public class MedicoNoRegistradoExcepcion extends Exception {

    public MedicoNoRegistradoExcepcion() {
        super("Medico no registrado");
    }
}
