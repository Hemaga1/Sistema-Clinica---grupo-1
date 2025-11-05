package modelo.ambulancia;

import java.util.Observable;

public class ObservableHilo extends Observable {
    public void avisarFinalizacion(Thread hilo){
        setChanged();
        notifyObservers(hilo);
    }
}
