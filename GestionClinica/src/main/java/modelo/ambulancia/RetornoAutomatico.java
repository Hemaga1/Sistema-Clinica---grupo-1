package modelo.ambulancia;

import modelo.personas.Asociado;
import util.UTIL;

import java.util.Observable;

public class RetornoAutomatico extends Thread{
    private ObservableHilo observableHilo;

    RetornoAutomatico() {
        this.observableHilo = new ObservableHilo();
    }

    public Observable getObservableHilo() {
        return observableHilo;
    }

    @Override
    public void run() {
        while(SimulacionAmbulancia.getCantHilos() > 1 || !Ambulancia.get_instance().getEstado().equals("Ambulancia disponible")) {
            System.out.println(SimulacionAmbulancia.getCantHilos());
            if ((!SimulacionAmbulancia.getActivo()) && (Ambulancia.get_instance().getEstado().equals("en el Taller"))){
                Ambulancia.get_instance().repararAmbulancia();
            }
            UTIL.tiempoMuerto();
            System.out.print("Solicita retorno\n");
            Ambulancia.get_instance().retornarAClinica();
        }
        System.out.println("Hilo finalizado\n");
        this.observableHilo.avisarFinalizacion(this);
    }

}
