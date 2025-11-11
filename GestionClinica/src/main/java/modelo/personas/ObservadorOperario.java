package modelo.personas;

import controlador.Controlador;
import modelo.ambulancia.HiloAmbulancia;
import modelo.ambulancia.SimulacionAmbulancia;

import java.util.Observable;
import java.util.Observer;

public class ObservadorOperario implements Observer {
    private SimulacionAmbulancia simulacion;
    private Observable observableOperario;

    public ObservadorOperario(SimulacionAmbulancia simulacion) {
        this.simulacion = simulacion;
    }

    public void agregarOperario(Thread hiloOperario){
        HiloAmbulancia hiloAmbulancia = (HiloAmbulancia) hiloOperario;
        this.observableOperario = hiloAmbulancia.getObservableHilo();
        this.observableOperario.addObserver(this);
    }

    public void eliminarOperario(){
        this.observableOperario = null;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.simulacion.terminarOperario();
    }
}
