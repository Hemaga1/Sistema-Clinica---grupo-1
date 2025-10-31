package modelo.excepciones;

public class FechaInvalidaExcepcion extends Exception {
    public FechaInvalidaExcepcion() {
        super("La fecha que ingresó es inválida");
    }
}
