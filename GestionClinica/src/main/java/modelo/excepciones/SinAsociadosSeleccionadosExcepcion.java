package modelo.excepciones;

public class SinAsociadosSeleccionadosExcepcion extends Exception {
    public SinAsociadosSeleccionadosExcepcion() {
        super("Seleccione al menos un asociado");
    }
}
