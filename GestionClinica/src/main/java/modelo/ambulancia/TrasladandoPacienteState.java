package modelo.ambulancia;

public class TrasladandoPacienteState implements IAmbulanciaState{
    private Ambulancia ambulancia;

    public TrasladandoPacienteState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {
    }

    @Override
    public void solicitaTraslado() {
    }

    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new DisponibleState(ambulancia));
    }

    @Override
    public void repararAmbulancia() {
    }

    @Override
    public boolean puedeIniciarAtencionDomicilio() {
        return false;
    }

    @Override
    public boolean puedeIniciarTraslado() {
        return false;
    }

    @Override
    public boolean puedeIniciarReparacion() {
        return false;
    }

    @Override
    public String toString() {
        return "Trasladando Paciente a Clínica";
    }
}
