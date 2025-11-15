package modelo.excepciones;

/**
 * Excepción lanzada cuando se ingresa una cantidad de solicitudes
 * marcada como inválida por reglas de negocio (por ejemplo, <= 0).
 */
public class CantidadSolicitudesInvalidaExcepcion extends Exception{
    public CantidadSolicitudesInvalidaExcepcion() {
        super("El numero de solicitudes ingresado es invalido");
    }
}
