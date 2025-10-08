package excepciones;

public class MedicoDuplicadoExcepcion extends Exception {

    public MedicoDuplicadoExcepcion() {
        super("El medico que intenta registrar ya se encuentra registrado");
    }
}
