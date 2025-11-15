package modelo.observadores_y_observables;

import modelo.ambulancia.HiloAmbulancia;
import modelo.ambulancia.SimulacionAmbulancia;

import java.util.Observable;
import java.util.Observer;

/**
 * Observador encargado de chequear la finalización de hilos de operarios.
 */
public class ObservadorOperario implements Observer {
    private SimulacionAmbulancia simulacion;
    private Observable observableOperario;


    /**
     * Crea el observador que controla la simulación.
     *
     * <b>Precondición:</b> simulacion != null
     * @param simulacion
     */
    public ObservadorOperario(SimulacionAmbulancia simulacion) {
        this.simulacion = simulacion;
        assert simulacion != null;
    }

    /**
     * Agrega un operario para ser observado.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>hiloOperario debe ser instancia de HiloAmbulancia</li>
     * </ul>
     */
    public void agregarOperario(Thread hiloOperario){
        HiloAmbulancia hiloAmbulancia = (HiloAmbulancia) hiloOperario;
        this.observableOperario = hiloAmbulancia.getObservableHilo();
        this.observableOperario.addObserver(this);
    }

    /**
     * Elimina el operario observado actual.
     */
    public void eliminarOperario(){
        this.observableOperario = null;
    }

    /**
     * Se ejecuta cuando el hilo observado finaliza.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.simulacion.terminarOperario();
    }
}
