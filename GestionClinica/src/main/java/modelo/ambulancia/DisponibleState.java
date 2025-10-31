package modelo.ambulancia;

public class DisponibleState implements IAmbulanciaState{
    private Ambulancia ambulancia;

    public DisponibleState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.cambiaDisponibilidad();
        ambulancia.setEstado(new AtendiendoADomState(ambulancia));
    }

    @Override
    public void solicitaTraslado() {
        ambulancia.cambiaDisponibilidad();
        ambulancia.setEstado(new TrasladandoPacienteState(ambulancia));
    }

    @Override
    public void vuelveClinica() {
    }

    @Override
    public void repararAmbulancia() {
        ambulancia.cambiaDisponibilidad();
        ambulancia.setEstado(new EnTallerState(ambulancia));
    }

    @Override
    public String toString() {
        return "Ambulancia disponible";
    }
}
