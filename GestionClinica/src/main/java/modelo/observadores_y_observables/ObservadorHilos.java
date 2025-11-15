package modelo.observadores_y_observables;

import modelo.ambulancia.HiloAmbulancia;
import modelo.ambulancia.RetornoAutomatico;
import modelo.ambulancia.SimulacionAmbulancia;

import java.util.*;

/**
 * Observer encargado de monitorear la finalización de los hilos asociados a la simulación:
 * <ul>
 *     <li>Los hilos de atención a asociados (HiloAmbulancia)</li>
 *     <li>El hilo de retorno automático</li>
 * </ul>
 *
 * Cuando un hilo notifica su finalización, este observer informa a
 * SimulacionAmbulancia para que gestione la finalización global.
 */
public class ObservadorHilos implements Observer {
    private ArrayList<Observable>  observableHilos = new ArrayList<>();
    private SimulacionAmbulancia simulacion;
    private Observable observableRetornoAutomatico;

    public ObservadorHilos(SimulacionAmbulancia simulacion) {
        this.simulacion = simulacion;
    }

    /**
     * Registra los observables correspondientes a cada hilo de asociados y al hilo
     * de retorno automático.
     *
     * @param hilos Lista de hilos de asociados.
     * @param retorno Hilo de retorno automático.
     */
    public void agregarObservables(List<Thread> hilos, Thread retorno){
        Iterator<Thread> iterator = hilos.iterator();
        while (iterator.hasNext()) {
            HiloAmbulancia hilo = (HiloAmbulancia) iterator.next();
            Observable observable = hilo.getObservableHilo();
            if (!observableHilos.contains(observable)) {
                observableHilos.add(observable);
                observable.addObserver(this);
            }
        }
        RetornoAutomatico retornoAutomatico = (RetornoAutomatico) retorno;
        this.observableRetornoAutomatico = retornoAutomatico.getObservableHilo();
        observableRetornoAutomatico.addObserver(this);
    }

    /**
     * Limpia todos los observables al finalizar la simulación.
     */
    public void eliminarObservables(){
        observableHilos.clear();
        observableRetornoAutomatico = null;
    }

    /**
     * Llamado cuando un hilo notifica su finalización.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.simulacion.eliminarHilo();
    }
}
