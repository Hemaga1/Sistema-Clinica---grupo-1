package modelo.ambulancia;

import modelo.interfaces.IAmbulanciaState;

/**
 * Representa el estado en el cual la ambulancia ya terminó su reparación
 * en el taller y está regresando a la clínica. En este estado no puede
 * atender domicilios ni realizar traslados hasta que vuelva y quede disponible.
 */
public class RegresandoDelTallerState implements IAmbulanciaState {
    private Ambulancia ambulancia;


    /**
     * Crea una instancia del estado RegresandoDelTallerState.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia no debe ser null.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>El estado queda asociado a una ambulancia no nula.</li>
     * </ul>
     *
     * @param ambulancia instancia de la ambulancia asociada al estado
     */
    public RegresandoDelTallerState(Ambulancia ambulancia) {
        assert ambulancia != null : "La ambulancia no puede ser null";
        this.ambulancia = ambulancia;
    }

    /**
     * No permite iniciar atención domiciliaria mientras la ambulancia está regresando del taller.
     *
     * <b>Precondición:</b> la ambulancia debe estar en RegresandoDelTallerState.
     */
    @Override
    public void solicitaAtencionDomicilio() {
        // No disponible en este estado
    }

    /**
     * No permite iniciar traslado mientras la ambulancia está regresando del taller.
     *
     * <b>Precondición:</b> la ambulancia debe estar en RegresandoDelTallerState.
     */
    @Override
    public void solicitaTraslado() {
        // No disponible en este estado
    }

    /**
     * Vuelve a la clínica, cambiando el estado a DisponibleState.
     *
     * <b>Precondición:</b>
     * <ul>
     *   <li>La ambulancia debe estar en RegresandoDelTallerState.</li>
     * </ul>
     *
     * <b>Postcondición:</b>
     * <ul>
     *   <li>El estado cambia a DisponibleState.</li>
     * </ul>
     */
    @Override
    public void vuelveClinica() {
        ambulancia.setEstado(new DisponibleState(ambulancia));
    }


    /**
     * No permite reparar mientras la ambulancia está regresando del taller,
     * ya que la reparación ya fue completada.
     *
     * <b>Precondición:</b> la ambulancia debe estar en RegresandoDelTallerState.
     */
    @Override
    public void repararAmbulancia() {
        // No disponible en este estado
    }

    /**
     * Cuando esta regresando del taller no se puede iniciar atención domiciliaria, por eso siempre false.
     */
    @Override
    public boolean puedeIniciarAtencionDomicilio() {
        return false;
    }

    /**
     * Cuando esta regresando del taller no puede iniciar traslado, por eso siempre false.
     */
    @Override
    public boolean puedeIniciarTraslado() {
        return false;
    }

    /**
     * Cuando esta regresando del taller no se puede iniciar reparacion, por eso siempre false.
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
        return "Regresando del Taller";
    }
}
