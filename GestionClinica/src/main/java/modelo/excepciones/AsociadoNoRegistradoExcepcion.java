package modelo.excepciones;

/**
 * Excepción lanzada cuando se intenta eliminar o acceder a un asociado
 * que no se encuentra registrado en el sistema.
 */
public class AsociadoNoRegistradoExcepcion extends Exception {
    public AsociadoNoRegistradoExcepcion() {
        super("El asociado que intenta eliminar no existe");
    }
}
