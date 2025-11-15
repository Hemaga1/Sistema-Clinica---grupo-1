package modelo.excepciones;
/**
 * Excepción lanzada cuando una fecha ingresada por el usuario
 * no respeta el formato válido o resulta imposible.
 */
public class FechaInvalidaExcepcion extends Exception {
    public FechaInvalidaExcepcion() {
        super("La fecha que ingresó es inválida");
    }
}
