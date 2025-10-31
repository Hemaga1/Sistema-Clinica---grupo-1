package modelo.excepciones;

/**
 * Al registrar un médico, este no pudo estar registrado antes, si lo estuvo tenemos esta excepción
 */

public class MedicoDuplicadoExcepcion extends Exception {

    public MedicoDuplicadoExcepcion() {
        super("El medico que intenta registrar ya se encuentra registrado");
    }
}
