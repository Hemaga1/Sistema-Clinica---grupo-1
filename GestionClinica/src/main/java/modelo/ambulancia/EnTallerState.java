package modelo.ambulancia;

import modelo.interfaces.IAmbulanciaState;

/**
 * Representa el estado en el cual la ambulancia se encuentra en el taller
 * recibiendo mantenimiento. En este estado no puede iniciar atenciones ni
 * traslados, pero sí puede finalizar la reparación y volver a operar.
 */
public class EnTallerState implements IAmbulanciaState {
    private Ambulancia ambulancia;

    /**
     * Crea una instancia del estado EnTallerState.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia asociada no puede ser null.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>El estado queda asociado a una ambulancia válida.</li>
     * </ul>
     *
     * @param ambulancia instancia de la ambulancia que se encuentra en reparación
     */
    public EnTallerState(Ambulancia ambulancia) {
        assert ambulancia != null : "La ambulancia no puede ser null";
        this.ambulancia = ambulancia;
    }

    /**
     * No permite iniciar atención domiciliaria mientras la ambulancia está en el taller.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia debe estar en estado EnTallerState.</li>
     * </ul>
     */
    @Override
    public void solicitaAtencionDomicilio() {
        // No disponible en este estado
    }

    /**
     * No permite iniciar traslados mientras la ambulancia está en el taller.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia debe estar en EnTallerState.</li>
     * </ul>
     */
    @Override
    public void solicitaTraslado() {
        // No disponible en este estado
    }

    /**
     * No puede volver a la clínica directamente desde el taller sin completar la reparación.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia debe estar en EnTallerState.</li>
     * </ul>
     */
    @Override
    public void vuelveClinica() {
        // No disponible en este estado
    }

    /**
     * Finaliza la reparación y cambia el estado a RegresandoDelTallerState.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>La ambulancia debe estar en EnTallerState.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>El estado cambia a RegresandoDelTallerState.</li>
     * </ul>
     */
    @Override
    public void repararAmbulancia() {
        ambulancia.setEstado(new RegresandoDelTallerState(ambulancia));
    }

    /**
     * Retorna siempre false porque en el taller no se pueden iniciar atenciones domiciliarias.
     */
    @Override
    public boolean puedeIniciarAtencionDomicilio() {
        return false;
    }

    /**
     * Retorna siempre false porque en el taller no se pueden iniciar traslados.
     */
    @Override
    public boolean puedeIniciarTraslado() {
        return false;
    }

    /**
     * Retorna siempre true porque esta en este estado.
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
        return "en el Taller";
    }
}
