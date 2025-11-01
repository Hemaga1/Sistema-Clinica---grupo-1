package modelo.prueba;

import modelo.ambulancia.Ambulancia;
import modelo.personas.Asociado;

import java.util.ArrayList;
import java.util.List;


public class Simulador {

    private static final int NUM_SOLICITUDES = 5;

    public static void main(String[] args) {
        List<Thread> hilos = new ArrayList<>();
        List<Asociado> asociados = new ArrayList<>();

        System.out.println("Simulador: creando " + NUM_SOLICITUDES + " asociados y lanzando solicitudes...");
        System.out.println("Estado inicial de la ambulancia: " + Ambulancia.get_instance().getEstado());

      
        for (int i = 1; i <= NUM_SOLICITUDES; i++) {
            Asociado a = new Asociado("DNI-" + i, "Nombre" + i, "Apellido" + i, "Calle" + i, i, "Ciudad" + i, "000-" + i);
           
            a.setCantSolicitudes(1);
            asociados.add(a);

            Thread t = new Thread(a);
            hilos.add(t);
            t.start();
        }

        // Esperar a que terminen
        for (Thread t : hilos) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Simulador: espera interrumpida");
            }
        }

        System.out.println("--- Simulación finalizada ---");
        System.out.println("Estado final de la ambulancia: " + Ambulancia.get_instance().getEstado());
        System.out.println("Situación de cada asociado:");
        for (Asociado a : asociados) {
            System.out.println(" - " + a.getNombre() + " (" + a.getDNI() + ") solicitudes: " + a.getCantSolicitudes() + " — ambulancia: " + Ambulancia.get_instance().getEstado());
        }
    }
}
