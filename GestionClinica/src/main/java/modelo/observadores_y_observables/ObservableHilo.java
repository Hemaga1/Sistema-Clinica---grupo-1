package modelo.observadores_y_observables;

import java.util.Observable;

/**
 * Representa un Observable asociado a un hilo específico (HiloAmbulancia o RetornoAutomatico).
 * <p>
 * Su única responsabilidad es notificar a los observers cuando el hilo finaliza su ejecución.
 * Se utiliza dentro del patrón Observer para comunicar eventos relacionados al ciclo de vida
 * de los hilos de la simulación.
 * </p>
 */
public class ObservableHilo extends Observable {
    /**
     * Notifica a todos los observers que el hilo asociado ha finalizado su ejecución.
     *
     * @param hilo Hilo que terminó su ejecución. Precondición: no debe ser null.
     */
    public void avisarFinalizacion(Thread hilo){
        assert hilo != null : "El hilo que finaliza no puede ser null";
        setChanged();
        notifyObservers(hilo);
    }
}
