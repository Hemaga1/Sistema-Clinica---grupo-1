package modelo.ambulancia;

public class EnTallerState implements IAmbulanciaState{
    private Ambulancia ambulancia;

    public EnTallerState(Ambulancia ambulancia) {
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
        // No disponible en este estado
    }

    @Override
    public void repararAmbulancia() {
        ambulancia.setEstado(new RegresandoDelTallerState(ambulancia));
    }

    @Override
    public String toString() {
        return "en el Taller";
    }
}
