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
        boolean domicilio = r.nextBoolean();
        String tipo = domicilio ? "Atencion domiciliaria" : "Traslado";
        System.out.println("[Asociado] " + this.getNombre() + " (" + this.getDNI() + ") solicita: " + tipo);
        if (domicilio) {
            this.ambulancia.solicitaAtencionDomicilio();
        } else {
            this.ambulancia.solicitaTraslado();
        }
        // Mostrar estado actual de la ambulancia justo después de la solicitud
        System.out.println("[Ambulancia] estado actual: " + this.ambulancia.getEstado());
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
            System.out.println("[Asociado] " + this.getNombre() + " inicia solicitud #" + i);
            this.solicitaAmbulancia();
            UTIL.tiempoMuerto();
            this.ambulancia.retornarAClinica();
            System.out.println("[Asociado] " + this.getNombre() + " retorna a clínica después de solicitud #" + i + " — Ambulancia: " + this.ambulancia.getEstado());
            UTIL.tiempoMuerto();
            // llamada extra en el flujo original: se mantiene pero también se informa
            this.ambulancia.retornarAClinica();
            System.out.println("[Asociado] " + this.getNombre() + " (post-retorno extra) — Ambulancia: " + this.ambulancia.getEstado());
        }

    }

    public void setCantSolicitudes(int i) {
        this.cantSolicitudes = i;
    }
}
