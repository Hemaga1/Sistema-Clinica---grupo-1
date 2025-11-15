package modelo.excepciones;

/**
 * Excepción lanzada cuando se intenta registrar un asociado que ya existe en el sistema.
 */
public class AsociadoDuplicadoExcepcion extends Exception {
    public AsociadoDuplicadoExcepcion() {
        super("El asociado ya se encuentra registrado");
    }
}
