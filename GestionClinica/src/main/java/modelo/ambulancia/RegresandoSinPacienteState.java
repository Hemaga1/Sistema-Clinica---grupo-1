package modelo.ambulancia;

public class RegresandoSinPacienteState implements IAmbulanciaState {
    private Ambulancia ambulancia;

    public RegresandoSinPacienteState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.cambiaEstado();
        ambulancia.setEstado(new AtendiendoADomState(ambulancia));
    }

    @Override
    public void solicitaTraslado() {
        ambulancia.cambiaEstado();
        ambulancia.setEstado(new TrasladandoPacienteState(ambulancia));
    }

    @Override
    public void vuelveClinica() {
        ambulancia.cambiaEstado();
        ambulancia.setEstado(new DisponibleState(ambulancia));
    }

    @Override
    public void repararAmbulancia() {
        // No disponible en este estado
    }

    @Override
    public String toString() {
        return "Regresando a la clínica sin paciente";
    }
}
