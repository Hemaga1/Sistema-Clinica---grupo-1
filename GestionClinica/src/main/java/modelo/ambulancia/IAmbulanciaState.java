package modelo.ambulancia;

public interface IAmbulanciaState {

    void solicitaAtencionDomicilio();

    void solicitaTraslado();

    void vuelveClinica();

    void repararAmbulancia();

}
