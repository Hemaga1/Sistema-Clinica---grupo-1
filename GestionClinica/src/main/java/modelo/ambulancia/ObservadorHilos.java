package modelo.ambulancia;

import controlador.Controlador;

import java.util.*;

public class ObservadorHilos implements Observer {
    private ArrayList<Observable>  observableHilos = new ArrayList<>();
    private SimulacionAmbulancia simulacion;
    private Observable observableRetornoAutomatico;

    public ObservadorHilos(SimulacionAmbulancia simulacion) {
        this.simulacion = simulacion;
    }

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

    public void eliminarObservables(){
        observableHilos.clear();
        observableRetornoAutomatico = null;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.simulacion.eliminarHilo();
    }
}
