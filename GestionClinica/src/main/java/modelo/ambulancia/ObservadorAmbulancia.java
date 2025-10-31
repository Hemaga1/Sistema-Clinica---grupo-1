package modelo.ambulancia;

import controlador.Controlador;

import java.util.Observable;
import java.util.Observer;

public class ObservadorAmbulancia implements Observer {
    private Observable observable;
    private Controlador controlador;

    public ObservadorAmbulancia(Observable observable, Controlador controlador) {
        this.observable = observable;
        this.observable.addObserver(this);
        this.controlador = controlador;
    }
    @Override
    public void update(Observable o, Object arg) {
        this.controlador.modificarDisponibilidad();
    }
}
