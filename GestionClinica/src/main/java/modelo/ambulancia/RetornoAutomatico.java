package modelo.ambulancia;

import modelo.observadores_y_observables.ObservableHilo;
import util.UTIL;

import java.util.Observable;

/**
 * Hilo supervisor encargado de solicitar cada cierto tiempo que la ambulancia
 * retorne a la clínica, sin importar en qué estado se encuentre.
 *
 * <p>Su función no es únicamente actuar al finalizar la simulación, sino que
 * opera de forma continua mientras la simulación esté activa. Esto permite:
 *
 * <ul>
 *     <li>Forzar el retorno a la clínica durante la simulación, lo cual puede
 *     provocar que ciertos estados finalicen o que la ambulancia pase al estado
 *     disponible cuando corresponde.</li>
 *
 *     <li>Garantizar que, cuando la simulación termine, la ambulancia no quede
 *     bloqueada en estados como por ejemplo “En atención”.</li>
 *
 *     <li>Si la simulación finaliza y la ambulancia está en el taller,
 *     el hilo solicita automáticamente la reparación para permitir que
 *     todos los hilos cierren correctamente.</li>
 * </ul>
 */
public class RetornoAutomatico extends Thread{
    private ObservableHilo observableHilo;

    /**
     * Constructor. Crea un observable asociado a este hilo.
     */
    RetornoAutomatico() {
        this.observableHilo = new ObservableHilo();
    }

    /**
     * @return Observable asociado para notificar finalización.
     */
    public Observable getObservableHilo() {
        return observableHilo;
    }

    /**
     * Lógica principal del hilo.
     *
     * <p>El hilo funciona de la siguiente manera:
     * <ul>
     *     <li>Mientras la simulación siga activa o existan hilos de atención,
     *     solicita cada cierto tiempo que la ambulancia retorne a la clínica.</li>
     *
     *     <li>Si la simulación terminó y la ambulancia se encuentra en el taller,
     *     se solicita automáticamente su reparación para permitir un cierre limpio.</li>
     *
     *     <li>Realiza pausas simuladas utilizando util.UTIL#tiempoMuerto().</li>
     * </ul>
     */
    @Override
    public void run() {
        while(SimulacionAmbulancia.getCantHilos() > 1 || !Ambulancia.get_instance().getEstado().equals("Ambulancia disponible")) {
            if ((!SimulacionAmbulancia.getActivo()) && (Ambulancia.get_instance().getEstado().equals("en el Taller"))){
                Ambulancia.get_instance().repararAmbulancia();
            }
            UTIL.tiempoMuerto();
            Ambulancia.get_instance().retornarAClinica();
        }
        this.observableHilo.avisarFinalizacion(this);
    }

}
