package modelo.personas;

import java.util.Observable;

public class ObservableAsociado extends Observable {
    private String nombreAsociado;
    private String apellidoAsociado;
    private String DNIAsociado;

    public ObservableAsociado(String nombre, String apellido, String DNI) {
        this.nombreAsociado = nombre;
        this.apellidoAsociado = apellido;
        this.DNIAsociado = DNI;
    }

    public void avisarCambio(String mensaje) {
        setChanged();
        notifyObservers(mensaje);
    }

    @Override
    public String toString() {
        return  nombreAsociado + " (" + DNIAsociado +") " ;
    }
}
