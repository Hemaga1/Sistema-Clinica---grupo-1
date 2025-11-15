package modelo.observadores_y_observables;

import java.util.Observable;

/**
 * Observable asociado a un Asociado. Permite notificar cambios
 * a los observadores, como solicitudes de ambulancia o estados.
 */
public class ObservableAsociado extends Observable {
    private String nombreAsociado;
    private String apellidoAsociado;
    private String DNIAsociado;

    /**
     * Crea un observable asociado a un asociado.
     *
     * <b>Precondición:</b> ningún parámetro debe ser null.
     * @param nombre nombre!=null, nombre!=""
     * @param apellido apellido!=null, apellido!=""
     * @param DNI dni!=null, dni!=""
     */
    public ObservableAsociado(String nombre, String apellido, String DNI) {
        assert nombre != null;
        assert apellido != null;
        assert DNI != null;
        this.nombreAsociado = nombre;
        this.apellidoAsociado = apellido;
        this.DNIAsociado = DNI;
    }

    /**
     * Notifica un mensaje a los observadores.
     *
     * @param mensaje mensaje enviado != null
     *
     * <b>Precondición:</b> mensaje != null
     * <b>Postcondición:</b> todos los observadores son notificados.
     */
    public void avisarCambio(String mensaje) {
        assert mensaje != null : "El mensaje no puede ser null";
        setChanged();
        notifyObservers(mensaje);
    }

    @Override
    public String toString() {
        return  nombreAsociado + " (" + DNIAsociado +") " ;
    }
}
