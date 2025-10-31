package modelo.ambulancia;

public class DisponibleState implements IAmbulanciaState{
    private Ambulancia ambulancia;

    public DisponibleState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.setEstado(new AtendiendoADomState(ambulancia));
    }

    @Override
    public void solicitaTraslado() {
        ambulancia.setEstado(new TrasladandoPacienteState(ambulancia));
    }

    @Override
    public void vuelveClinica() {
    }

    @Override
    public void repararAmbulancia() {
        ambulancia.setEstado(new EnTallerState(ambulancia));
    }

    @Override
    public boolean puedeIniciarAtencionDomicilio() {
        return true;
    }

    @Override
    public boolean puedeIniciarTraslado() {
        return true;
    }

    @Override
    public boolean puedeIniciarReparacion() {
        return true;
    }

    @Override
    public String toString() {
        return "Ambulancia disponible";
    }
}
