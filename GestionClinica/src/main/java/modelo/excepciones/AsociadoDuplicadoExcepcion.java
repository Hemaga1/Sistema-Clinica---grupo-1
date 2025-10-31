package modelo.excepciones;

public class AsociadoDuplicadoExcepcion extends Exception {
    public AsociadoDuplicadoExcepcion() {
        super("El asociado ya se encuentra registrado");
    }
}
