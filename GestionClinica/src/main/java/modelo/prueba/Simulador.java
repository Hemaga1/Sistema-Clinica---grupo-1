package modelo.prueba;

import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.HiloAmbulancia;
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


        Asociado a1 = new Asociado("25847188","Marcelo","Perez" ,"Independencia",24,"Mar del Plata","2234324432");
        Asociado a2 = new Asociado("43242113","Daniela","Gomez" ,"Colón",300,"Mar del Plata","2235345435");
        Asociado a3 = new Asociado("31273724","Miriam","Gonzalez" ,"Pedro Luro",127,"Mar del Plata","2237637265");
        Asociado a4 = new Asociado("32183984","Teodoro","Fernandez" ,"Juan B Justo",732,"Mar del Plata","2235682654");

        asociados.add(a1);
        asociados.add(a2);
        asociados.add(a3);
        asociados.add(a4);

        hilos.add(new HiloAmbulancia(a1, 5));
        hilos.add(new HiloAmbulancia(a2, 5));
        hilos.add(new HiloAmbulancia(a3, 5));
        hilos.add(new HiloAmbulancia(a4, 5));
        hilos.get(0).start();
        hilos.get(1).start();
        hilos.get(2).start();
        hilos.get(3).start();

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
    }
}
