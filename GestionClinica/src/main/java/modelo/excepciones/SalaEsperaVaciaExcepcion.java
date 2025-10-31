package modelo.excepciones;

/**
 * Cuando se quiere sacar un paciente de espera, esta no puede estar vacía. Si lo está tenemos esta excepción.
 */

public class SalaEsperaVaciaExcepcion extends Exception {

    public SalaEsperaVaciaExcepcion(){
        super("Sala de espera vacia");
    }
}
