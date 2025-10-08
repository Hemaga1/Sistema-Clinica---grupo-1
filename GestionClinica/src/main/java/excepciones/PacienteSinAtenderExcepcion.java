package excepciones;

public class PacienteSinAtenderExcepcion extends Exception {

    public PacienteSinAtenderExcepcion() {
        super("El paciente no esta en la lista de atendidos");
    }
}
