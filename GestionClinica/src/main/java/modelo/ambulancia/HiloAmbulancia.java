package modelo.ambulancia;

import modelo.observadores_y_observables.ObservableHilo;
import modelo.personas.Asociado;
import util.UTIL;

import java.util.Observable;

/**
 * Hilo que simula la ejecución de solicitudes realizadas por un {@link Asociado}.
 * Cada hilo ejecutará una cantidad fija de solicitudes, siempre y cuando la
 * simulación siga activa.
 *
 * El hilo notifica su finalización mediante un ObservableHilo.
 */
public class HiloAmbulancia extends Thread {
    private Runnable solicitante;
    private int cantSolicitudes;
    private ObservableHilo observableHilo;

    /**
     * Crea un nuevo hilo de ambulancia.
     *
     * <b>Precondiciones:</b><br>
     * - solicitante no debe ser null.<br>
     * - cantSolicitudes debe ser mayor a 0.<br><br>
     *
     * <b>Postcondiciones:</b><br>
     * - Se crea un ObservableHilo asociado al hilo.<br>
     *
     * @param solicitante objeto que representa la acción que debe ejecutar el hilo.
     * @param cantSolicitudes cantidad de veces que debe ejecutarse la acción.
     */
    public HiloAmbulancia(Runnable solicitante, int cantSolicitudes) {
        assert solicitante != null : "El solicitante no puede ser null";
        assert cantSolicitudes > 0 : "La cantidad de solicitudes debe ser mayor a 0";
        this.solicitante = solicitante;
        this.cantSolicitudes = cantSolicitudes;
        this.observableHilo = new ObservableHilo();
    }

    /**
     * Devuelve el observable que permite notificar la finalización del hilo.
     *
     * @return instancia de ObservableHilo.
     */
    public Observable getObservableHilo() {
        return observableHilo;
    }

    /**
     * Devuelve el asociado dueño de este hilo.
     * @return asociado que solicita el servicio.
     */
    public Asociado getAsociado() {
        return (Asociado) solicitante;
    }

    /**
     * Ejecuta el ciclo de solicitudes mientras la simulación permanezca activa
     * y no se haya superado la cantidad máxima.
     */
    @Override
    public void run() {
        int i = 1;
        while(SimulacionAmbulancia.getActivo() && (i <= this.cantSolicitudes)) {
            UTIL.tiempoMuerto();
            this.solicitante.run();
            i++;
        }
        this.observableHilo.avisarFinalizacion(this);
    }
}
