package modelo.ambulancia;

import modelo.interfaces.IAmbulanciaState;

/**
 * Representa el estado en el cual la ambulancia está disponible para
 * atender un domicilio, realizar un traslado o ingresar a reparación.
 */
public class DisponibleState implements IAmbulanciaState {
    private Ambulancia ambulancia;


    /**
     * Crea una instancia del estado Disponible.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia no puede ser null.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>Se asocia correctamente el estado con una ambulancia válida.</li>
     * </ul>
     *
     * @param ambulancia instancia de la ambulancia asociada al estado
     */
    public DisponibleState(Ambulancia ambulancia) {
        assert ambulancia != null : "La ambulancia no puede ser null";
        this.ambulancia = ambulancia;
    }

    /**
     * Inicia una atención domiciliaria desde el estado Disponible.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia debe estar en estado Disponible.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>El estado cambia a AtendiendoADomState.</li>
     * </ul>
     */
    @Override
    public void solicitaAtencionDomicilio() {
        ambulancia.setEstado(new AtendiendoADomState(ambulancia));
    }

    /**
     * Inicia un traslado desde el estado Disponible.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia debe estar en estado Disponible.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>El estado cambia a TrasladandoPacienteState.</li>
     * </ul>
     */
    @Override
    public void solicitaTraslado() {
        ambulancia.setEstado(new TrasladandoPacienteState(ambulancia));
    }

    /**
     * Si está disponible y vuelve a la clínica, no se produce ningún cambio:
     * la ambulancia ya está en la clínica y disponible.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia debe estar en DisponibleState.</li>
     * </ul>
     */
    @Override
    public void vuelveClinica() {
        // No hay efecto: ya está disponible
    }

    /**
     * Inicia el proceso de reparación desde el estado Disponible.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia debe estar en estado Disponible.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>El estado cambia a EnTallerState.</li>
     * </ul>
     */
    @Override
    public void repararAmbulancia() {
        ambulancia.setEstado(new EnTallerState(ambulancia));
    }

    /**
     * Devuelve siempre true porque una ambulancia disponible siempre puede iniciar atención domiciliaria.
     */
    @Override
    public boolean puedeIniciarAtencionDomicilio() {
        return true;
    }

    /**
     * Devuelve siempre true porque una ambulancia disponible siempre puede hacer traslados.
     */
    @Override
    public boolean puedeIniciarTraslado() {
        return true;
    }

    /**
     * Devuelve siempre true porque una ambulancia disponible siempre puede ir a reparacion.
     */
    @Override
    public boolean puedeIniciarReparacion() {
        return true;
    }

    /**
     * Devuelve el estado.
     */
    @Override
    public String toString() {
        return "Ambulancia disponible";
    }
}
