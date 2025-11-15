package modelo.ambulancia;

import modelo.interfaces.IAmbulanciaState;

/**
 * Estado en el que la ambulancia está trasladando un paciente hacia la clínica.
 * En este estado no puede iniciar nuevos traslados ni atenciones.
 */
public class TrasladandoPacienteState implements IAmbulanciaState {
    private Ambulancia ambulancia;

    /**
     * Crea el estado TrasladandoPacienteState.
     *
     * @param ambulancia la ambulancia a la que pertenece este estado.
     */
    public TrasladandoPacienteState(Ambulancia ambulancia) {
        assert ambulancia != null : "La ambulancia no puede ser null";
        this.ambulancia = ambulancia;
    }

    /**
     * No se puede iniciar atención a domicilio durante un traslado con paciente.
     */
    @Override
    public void solicitaAtencionDomicilio() {
    }

    /**
     * No se puede solicitar un traslado durante un traslado con paciente.
     */
    @Override
    public void solicitaTraslado() {
    }

    /**
     * Completa el traslado y vuelve a la clínica.
     * <b>Post:</b> La ambulancia pasa a DisponibleState.
     */
    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new DisponibleState(ambulancia));
    }

    /**
     * No se puede reparar durante un traslado.
     */
    @Override
    public void repararAmbulancia() {
    }

    /**
     * No se puede iniciar atencion a domicilio durante un traslado.
     */
    @Override
    public boolean puedeIniciarAtencionDomicilio() {
        return false;
    }

    /**
     * No se puede iniciar un traslado durante un traslado.
     */
    @Override
    public boolean puedeIniciarTraslado() {
        return false;
    }

    /**
     * No se puede iniciar reparacion durante un traslado.
     */
    @Override
    public boolean puedeIniciarReparacion() {
        return false;
    }

    /**
     * Devuelve el estado.
     */
    @Override
    public String toString() {
        return "Trasladando Paciente a Clínica";
    }
}
