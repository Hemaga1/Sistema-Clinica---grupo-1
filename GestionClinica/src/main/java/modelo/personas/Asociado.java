package modelo.personas;

import modelo.ambulancia.Ambulancia;
import util.UTIL;

import java.util.Objects;
import java.util.Random;

public class Asociado extends Persona implements Runnable{

    private int cantSolicitudes;
    private Ambulancia ambulancia;

    public Asociado(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono) {
        super(DNI, nombre, apellido, calle, numero, ciudad, telefono);
        this.cantSolicitudes = 0;
        this.ambulancia = Ambulancia.get_instance();
    }


    public void solicitaAmbulancia() {
        Random r = new Random();
        if (r.nextBoolean()) {
            this.ambulancia.solicitaAtencionDomicilio();

        } else {
            this.ambulancia.solicitaTraslado();
        }
        UTIL.tiempoMuerto();
    }

    public int getCantSolicitudes() {
        return cantSolicitudes;
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
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
        for (int i = 1; i <= this.cantSolicitudes; i++) {
            this.solicitaAmbulancia();
            UTIL.tiempoMuerto();
            this.ambulancia.retornarAClinica();
            UTIL.tiempoMuerto();
            this.ambulancia.retornarAClinica();
        }

    }

}
