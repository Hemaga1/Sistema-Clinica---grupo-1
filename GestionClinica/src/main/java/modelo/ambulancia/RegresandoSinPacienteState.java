package modelo.ambulancia;

import modelo.interfaces.IAmbulanciaState;

/**
 * Estado en el que la ambulancia está regresando a la clínica sin paciente.
 * En este estado puede iniciar una atención a domicilio o un traslado, o bien
 * completar el regreso a la clínica y quedar disponible.
 */
public class RegresandoSinPacienteState implements IAmbulanciaState {
    private Ambulancia ambulancia;

    /**
     * Crea el estado RegresandoSinPacienteState.
     *
     * @param ambulancia la ambulancia cuyo estado se representa !=null.
     */
    public RegresandoSinPacienteState(Ambulancia ambulancia) {
        assert ambulancia != null : "La ambulancia no puede ser null";
        this.ambulancia = ambulancia;
    }

    /**
     * La ambulancia inicia una atención a domicilio.
     * <b>Pre:</b> La ambulancia debe estar regresando sin paciente.<br>
     * <b>Post:</b> La ambulancia queda en estado AtendiendoADomState.
     */
    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.setEstado(new AtendiendoADomState(ambulancia));
    }

    /**
     * La ambulancia inicia un traslado.
     * <b>Pre:</b> La ambulancia debe estar regresando sin paciente.<br>
     * <b>Post:</b> Pasa a estado TrasladandoPacienteState.
     */
    @Override
    public void solicitaTraslado() {
        ambulancia.setEstado(new TrasladandoPacienteState(ambulancia));
    }

    /**
     * La ambulancia completa el regreso a la clínica.
     * <b>Post:</b> Pasa al estado DisponibleState.
     */
    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new DisponibleState(ambulancia));
    }

    /**
     * No se puede reparar si esta regresando sin paciente.
     */
    @Override
    public void repararAmbulancia() {
        // No disponible en este estado
    }

    /**
     * Puede iniciar atencion a domicilio si esta regresando sin paciente.
     */
    @Override
    public boolean puedeIniciarAtencionDomicilio() {
        return true;
    }

    /**
     * Puede iniciartraslado si esta regresando sin paciente.
     */
    @Override
    public boolean puedeIniciarTraslado() {
        return true;
    }

    /**
     * No se puede iniciar reparación si esta regresando sin paciente.
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
        return "Regresando a la clínica sin paciente";
    }
}
