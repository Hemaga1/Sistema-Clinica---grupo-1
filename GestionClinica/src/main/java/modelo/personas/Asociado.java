package modelo.personas;

import modelo.ambulancia.Ambulancia;
import util.UTIL;

import java.util.Objects;
import java.util.Random;

public class Asociado extends Persona implements Runnable {

    //private Ambulancia ambulancia;

    public Asociado(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono) {
        super(DNI, nombre, apellido, calle, numero, ciudad, telefono);
        //this.ambulancia = Ambulancia.get_instance();
    }

    public void solicitaAmbulancia() {
        Random r = new Random();
        boolean domicilio = r.nextBoolean();
        String tipo = domicilio ? "Atencion domiciliaria" : "Traslado";
        System.out.println("[Asociado] " + this.getNombre() + " (" + this.getDNI() + ") solicita: " + tipo);
        if (domicilio) {
            Ambulancia.get_instance().solicitaAtencionDomicilio();
        } else {
            Ambulancia.get_instance().solicitaTraslado();
        }
        UTIL.tiempoMuerto();
        Ambulancia.get_instance().retornarAClinica();
        UTIL.tiempoMuerto();
        Ambulancia.get_instance().retornarAClinica();
    }

    /*public Ambulancia getAmbulancia() {
        return Ambulancia.get_instance();
    }*/

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
