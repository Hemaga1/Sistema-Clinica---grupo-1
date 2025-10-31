package modelo.ambulancia;

public class AtendiendoADomState implements IAmbulanciaState {
    private Ambulancia ambulancia;

    public AtendiendoADomState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitaAtencionDomicilio() {
        // Ya está atendiendo a domicilio
    }

    @Override
    public void solicitaTraslado() {
        // No disponible en este estado
    }

    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new RegresandoSinPacienteState(ambulancia));
    }

    @Override
    public void repararAmbulancia() {
        // No disponible en este estado
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
        return "Atendiendo paciente a domicilio";
    }
}
