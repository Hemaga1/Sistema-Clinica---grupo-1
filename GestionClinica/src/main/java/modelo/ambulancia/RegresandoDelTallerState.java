package modelo.ambulancia;

public class RegresandoDelTallerState implements IAmbulanciaState {
    private Ambulancia ambulancia;

    public RegresandoDelTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {
        // No disponible en este estado
    }

    @Override
    public void solicitaTraslado() {
        // No disponible en este estado
    }

    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new DisponibleState(ambulancia));
    }

    @Override
    public void repararAmbulancia() {
        // No disponible en este estado
    }

    @Override
    public String toString() {
        return "Regresando del Taller";
    }
}
