package modelo.ambulancia;

import controlador.Controlador;
import controlador.IVista;

import java.util.Observable;
import java.util.Observer;

public class ObservadorAmbulancia implements Observer {
    private Observable observable;
    private IVista vista;

    public ObservadorAmbulancia(Observable observable, IVista vista) {
        this.observable = observable;
        this.observable.addObserver(this);
        this.vista = vista;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.vista.cambiarEstadoAmbulancia(Ambulancia.get_instance().getEstado());
    }
}
