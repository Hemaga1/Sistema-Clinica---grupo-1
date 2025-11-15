package modelo.ambulancia;

import modelo.interfaces.IAmbulanciaState;

/**
 * Representa el estado de la ambulancia cuando está atendiendo a un paciente a domicilio.
 * Forma parte del patrón State.
 */
public class AtendiendoADomState implements IAmbulanciaState {
    private Ambulancia ambulancia;


    /**
     * Crea un estado donde la ambulancia se encuentra atendiendo un paciente a domicilio.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia no puede ser null.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>Se crea correctamente un estado asociado a una ambulancia válida.</li>
     * </ul>
     *
     * @param ambulancia instancia de la ambulancia asociada al estado
     */
    public AtendiendoADomState(Ambulancia ambulancia) {
        assert ambulancia != null : "La ambulancia no puede ser null";
        this.ambulancia = ambulancia;
    }

    /**
     * Solicita atención domiciliaria mientras ya se está atendiendo un domicilio.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia se encuentra en este estado.</li>
     * </ul>
     */
    @Override
    public void solicitaAtencionDomicilio() {
        // Ya está atendiendo a domicilio
    }

    /**
     * Solicita un traslado mientras se está atendiendo un domicilio.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia está en este estado.</li>
     * </ul>
     */
    @Override
    public void solicitaTraslado() {
        // No disponible en este estado
    }

    /**
     * Indica que la ambulancia finaliza la atención domiciliaria y retorna a la clínica
     * sin paciente.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia se encuentra en este estado.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>El estado de la ambulancia pasa a RegresandoSinPacienteState.</li>
     * </ul>
     */
    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new RegresandoSinPacienteState(ambulancia));
    }

    /**
     * Intenta reparar la ambulancia mientras atiende un domicilio.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia está en este estado.</li>
     * </ul>
     */
    @Override
    public void repararAmbulancia() {
        // No disponible en este estado
    }

    /**
     * Indica si puede iniciar una atención domiciliaria.
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>Devuelve siempre false porque ya está atendiendo uno.</li>
     * </ul>
     */
    @Override
    public boolean puedeIniciarAtencionDomicilio() {
        return false;
    }

    /**
     * Indica si puede iniciar un traslado.
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>Devuelve siempre false porque está atendiendo un domicilio.</li>
     * </ul>
     */
    @Override
    public boolean puedeIniciarTraslado() {
        return false;
    }

    /**
     * Indica si puede iniciar una reparación.
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>Devuelve false porque una ambulancia no puede ser reparada mientras atiende un domicilio.</li>
     * </ul>
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
        return "Atendiendo paciente a domicilio";
    }
}
