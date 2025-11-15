package modelo.excepciones;
/**
 * Excepción lanzada cuando se intenta realizar una operación
 * para la cual se requiere seleccionar al menos un asociado y no se seleccionó ninguno.
 */
public class SinAsociadosSeleccionadosExcepcion extends Exception {
    public SinAsociadosSeleccionadosExcepcion() {
        super("Seleccione al menos un asociado");
    }
}
