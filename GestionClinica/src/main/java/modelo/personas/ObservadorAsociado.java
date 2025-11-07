package modelo.personas;

import controlador.IVista;

import java.util.Observable;
import java.util.Observer;

public class ObservadorAsociado implements Observer {
    private ObservableAsociado observableAsociado;
    private IVista vista;

    public ObservadorAsociado(ObservableAsociado observableAsociado, IVista vista) {
        this.vista = vista;
        this.observableAsociado = observableAsociado;
        observableAsociado.addObserver(this);
    }

    public void eliminarObservable(){
        observableAsociado.deleteObserver(this);
    }

    public String getDatosAsociado() {
        return observableAsociado.toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.vista.actualizarConsolaAsociado(this, (String) arg);
    }
}
