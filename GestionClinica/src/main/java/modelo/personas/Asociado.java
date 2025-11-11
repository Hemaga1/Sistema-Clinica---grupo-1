package modelo.personas;

import modelo.ambulancia.Ambulancia;
import util.UTIL;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.util.Objects;
import java.util.Observable;
import java.util.Random;

public class Asociado extends Persona implements Runnable {
    private ObservableAsociado observableAsociado;

    public Asociado(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono) {
        super(DNI, nombre, apellido, calle, numero, ciudad, telefono);
        observableAsociado = new ObservableAsociado(nombre, apellido, DNI);
    }

    public ObservableAsociado getObservableAsociado() {
        return observableAsociado;
    }

    public void solicitaAmbulancia() {
        Random r = new Random();
        boolean domicilio = r.nextBoolean();
        String tipo = domicilio ? "Atencion domiciliaria" : "Traslado";
        observableAsociado.avisarCambio(this.getNombre() + " (" + this.getDNI() + ") solicita: " + tipo);
        System.out.println("[Asociado] " + this.getNombre() + " (" + this.getDNI() + ") solicita: " + tipo);
        if (domicilio) {
            Ambulancia.get_instance().solicitaAtencionDomicilio();
        } else {
            Ambulancia.get_instance().solicitaTraslado();
        }
    }

    public void notificarEspera() {
        observableAsociado.avisarCambio(this.getNombre() + " (" + this.getDNI() + ") esperando");
    }

    public void notificarFueAtendido() {
        observableAsociado.avisarCambio(this.getNombre() + " (" + this.getDNI() + ") ha sido atendido");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asociado asociado = (Asociado) o;
        return Objects.equals(this.getDNI(), asociado.getDNI());
    }

    @Override
    public void run() {
        this.solicitaAmbulancia();
    }

}
