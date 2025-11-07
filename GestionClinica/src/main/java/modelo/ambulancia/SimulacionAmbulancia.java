package modelo.ambulancia;

import modelo.excepciones.CantidadSolicitudesInvalidaExcepcion;
import modelo.personas.Asociado;
import modelo.personas.Operario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimulacionAmbulancia {
        public static boolean activo;
        private List<Thread> hilos = new ArrayList<>();
        private List<Asociado> asociados = new ArrayList<>();

        public SimulacionAmbulancia(){
            this.activo = false;
        }

        public void prepararAsociados(List<Asociado> asociadosAmbulancia) {
            Iterator<Asociado> iterator = asociadosAmbulancia.iterator();
            while(iterator.hasNext()){
                Asociado a = (Asociado) iterator.next();
                asociados.add(a);
            }
        }

        public void enviarATaller() {
            HiloAmbulancia hilo = new HiloAmbulancia(new Operario(), 1);
            hilos.add(hilo);
            hilo.start();
        }

        public void empezarAmbulancia(ArrayList<Integer> cant) throws CantidadSolicitudesInvalidaExcepcion {
            activo = true;
            for (int i = 0; i < asociados.size(); i++){
                hilos.add(new HiloAmbulancia(asociados.get(i), cant.get(i)));
            }
            for (int i = 0; i < asociados.size(); i++){
                hilos.get(i).start();
            }
        }

        public List<Thread> getHilos(){
            return hilos;
        }

        public void eliminarHilo(Thread hilo){
            hilos.remove(hilo);
            if (hilos.isEmpty()){
                asociados.clear();
                activo = false;
            }
        }

        public List<Asociado> getAsociadosAmbulancia(){
            return asociados;
        }

        public void eliminarAsociadosAmbulancia(){
            asociados.clear();
        }

        public static boolean getActivo(){
            return activo;
        }
}
