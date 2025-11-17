package modelo.personas;

import modelo.ambulancia.Ambulancia;
import util.UTIL;

import java.util.Objects;
import java.util.Random;

/**
 * Representa a un operario encargado de solicitar reparación de ambulancias.
 * Implementa Runnable para ser ejecutado en un hilo.
 */
public class Operario implements Runnable {

    /**
     * Solicita que la ambulancia sea reparada.
     *
     * <b>Postcondición:</b> la ambulancia pasa al estado de reparación si corresponde.
     */
    public void solicitaAmbulancia() {
        Ambulancia.get_instance().repararAmbulancia();
    }

    /**
     * Ejecuta la acción principal del operario.
     */
    @Override
    public void run() {
        this.solicitaAmbulancia();
    }
}
