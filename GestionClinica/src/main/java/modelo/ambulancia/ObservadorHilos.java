package modelo.ambulancia;

import controlador.Controlador;

import java.util.*;

public class ObservadorHilos implements Observer {
    private ArrayList<Observable>  observableHilos = new ArrayList<>();
    private Controlador controlador;

    public ObservadorHilos(List<Thread> hilos, Controlador controlador) {
        this.controlador = controlador;
        Iterator<Thread> iterator = hilos.iterator();
        while (iterator.hasNext()) {
            HiloAmbulancia hilo = (HiloAmbulancia) iterator.next();
            Observable observable = hilo.getObservableHilo();
            observableHilos.add(observable);
            observable.addObserver(this);
        }
    }

    public ObservadorHilos(Controlador controlador) {
        this.controlador = controlador;
    }

    public void agregarObservables(List<Thread> hilos){
        Iterator<Thread> iterator = hilos.iterator();
        while (iterator.hasNext()) {
            HiloAmbulancia hilo = (HiloAmbulancia) iterator.next();
            Observable observable = hilo.getObservableHilo();
            if (!observableHilos.contains(observable)) {
                observableHilos.add(observable);
                observable.addObserver(this);
            }
        }
    }

    public void eliminarObservables(){
        observableHilos.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.controlador.eliminarHilo((Thread) arg);
    }
}
