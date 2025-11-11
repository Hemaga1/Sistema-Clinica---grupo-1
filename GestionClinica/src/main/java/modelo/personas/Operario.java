package modelo.personas;

import modelo.ambulancia.Ambulancia;
import util.UTIL;

import java.util.Objects;
import java.util.Random;

public class Operario implements Runnable {

    public void solicitaAmbulancia() {
        System.out.println("[Operario] Solicita taller");
        Ambulancia.get_instance().repararAmbulancia();
    }


    @Override
    public void run() {
        this.solicitaAmbulancia();
    }
}
