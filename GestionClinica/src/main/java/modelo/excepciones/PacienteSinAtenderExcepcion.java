package modelo.excepciones;

/**
 * Para internar un paciente o para que pueda egresar, previamente tuvo que ser atendido, sino tenemos esta excepción.
 */

public class PacienteSinAtenderExcepcion extends Exception {

    public PacienteSinAtenderExcepcion() {
        super("El paciente no esta en la lista de atendidos");
    }
}
