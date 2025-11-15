package modelo.observadores_y_observables;

import vista.IVista;

import java.util.Observable;
import java.util.Observer;


/**
 * Observador que escucha los cambios de un ObservableAsociado y actualiza la vista correspondiente.
 */
public class ObservadorAsociado implements Observer {
    private ObservableAsociado observableAsociado;
    private IVista vista;

    /**
     * Crea un observador asociado a un observable y una vista.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>observableAsociado != null</li>
     *   <li>vista != null</li>
     * </ul>
     */
    public ObservadorAsociado(ObservableAsociado observableAsociado, IVista vista) {
        this.vista = vista;
        this.observableAsociado = observableAsociado;
        assert observableAsociado != null;
        assert vista != null;
        observableAsociado.addObserver(this);
    }

    /**
     * Elimina este observador del observable.
     */
    public void eliminarObservable(){
        observableAsociado.deleteObserver(this);
    }

    /**
     * Devuelve los datos del asociado observado.
     */
    public String getDatosAsociado() {
        return observableAsociado.toString();
    }

    /**
     * Recibe actualizaciones del observable y las envía a la vista.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.vista.actualizarConsolaAsociado(this, (String) arg);
    }
}
