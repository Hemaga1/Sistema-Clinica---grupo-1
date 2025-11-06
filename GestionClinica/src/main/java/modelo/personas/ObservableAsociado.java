package modelo.personas;

import java.util.Observable;

public class ObservableAsociado extends Observable {
    public void avisarCambio(String mensaje) {
        setChanged();
        notifyObservers(mensaje);
    }
}
