package modelo.observadores_y_observables;

import vista.IVista;
import modelo.ambulancia.Ambulancia;

import java.util.Observable;
import java.util.Observer;

/**
 * Observer encargado de actualizar la vista cuando cambia el estado de la ambulancia.
 * <p>
 * Se registra sobre la instancia singleton de Ambulancia y cada vez que esta cambia de estado
 * </p>
 */
public class ObservadorAmbulancia implements Observer {
    private Observable observable;
    private IVista vista;

    /**
     * Crea un nuevo observer para la ambulancia.
     *
     * @param observable Observable al cual se suscribe.
     * @param vista Vista que debe actualizarse ante cambios.
     */
    public ObservadorAmbulancia(Observable observable, IVista vista) {
        this.observable = observable;
        this.observable.addObserver(this);
        this.vista = vista;
    }

    /**
     * Se ejecuta cuando la ambulancia cambia de estado.
     *
     * @param o   Observable que generó la notificación.
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        this.vista.cambiarEstadoAmbulancia(Ambulancia.get_instance().getEstado());
    }
}
