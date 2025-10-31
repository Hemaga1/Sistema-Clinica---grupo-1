package modelo.ambulancia;

public interface IAmbulanciaState {

    void solicitaAtencionDomicilio();

    void solicitaTraslado();

    void vuelveClinica();

    void repararAmbulancia();

    boolean puedeIniciarAtencionDomicilio();
    boolean puedeIniciarTraslado();
    boolean puedeIniciarReparacion();

}
