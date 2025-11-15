package modelo.ambulancia;

import modelo.observadores_y_observables.ObservableHilo;
import util.UTIL;

import java.util.Observable;

/**
 * Hilo encargado de retornar automáticamente la ambulancia a la clínica
 * cuando ya no quedan solicitudes pendientes.
 *
 * Su función principal es garantizar que, al final de la simulación,
 * la ambulancia siempre vuelva al estado "Disponible".
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
     * Lógica principal del hilo:
     * <ul>
     *     <li>Espera hasta que no haya más hilos activos atendiendo.</li>
     *     <li>Retorna automáticamente la ambulancia a la clínica.</li>
     *     <li>Si la ambulancia está en el taller y la simulación terminó, la repara.</li>
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
