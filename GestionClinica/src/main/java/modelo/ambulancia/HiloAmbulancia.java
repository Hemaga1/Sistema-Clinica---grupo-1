package modelo.ambulancia;

import util.UTIL;

import java.util.Observable;

public class HiloAmbulancia extends Thread {
    private Runnable solicitante;
    private int cantSolicitudes;
    private ObservableHilo observableHilo;

    public HiloAmbulancia(Runnable solicitante, int cantSolicitudes) {
        this.solicitante = solicitante;
        this.cantSolicitudes = cantSolicitudes;
        this.observableHilo = new ObservableHilo();
    }

    public Observable getObservableHilo() {
        return observableHilo;
    }

    @Override
    public void run() {
        int i = 1;
        while(SimulacionAmbulancia.getActivo() && (i <= this.cantSolicitudes)) {
            UTIL.tiempoMuerto();
            this.solicitante.run();
            i++;
        }
        System.out.println("Hilo finalizado\n");
        this.observableHilo.avisarFinalizacion(this);
    }
}
