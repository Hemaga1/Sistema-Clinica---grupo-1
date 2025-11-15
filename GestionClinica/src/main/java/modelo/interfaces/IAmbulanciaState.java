package modelo.interfaces;

/**
 * Interfaz que define el comportamiento de los distintos estados de una ambulancia.
 * Cada estado define qué operaciones están permitidas y cuáles no.
 */
public interface IAmbulanciaState {

    /**
     * Solicita iniciar una atención a domicilio.
     */
    void solicitaAtencionDomicilio();

    /**
     * Solicita iniciar un traslado.
     */
    void solicitaTraslado();

    /**
     * Indica que la ambulancia regresa a la clínica.
     */
    void vuelveClinica();


    /**
     * Inicia la reparación de la ambulancia.
     */
    void repararAmbulancia();

    /**
     * Indica si la ambulancia puede iniciar atención a domicilio.
     *
     * @return true si está habilitado para comenzar una atención a domicilio.
     */
    boolean puedeIniciarAtencionDomicilio();

    /**
     * Indica si la ambulancia puede iniciar un traslado.
     *
     * @return true si está habilitado para comenzar un traslado.
     */
    boolean puedeIniciarTraslado();


    /**
     * Indica si la ambulancia puede entrar en reparación.
     *
     * @return true si puede iniciar reparación.
     */
    boolean puedeIniciarReparacion();

}
