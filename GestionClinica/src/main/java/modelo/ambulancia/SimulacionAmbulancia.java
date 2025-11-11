package modelo.ambulancia;

import controlador.Controlador;
import controlador.IVista;
import modelo.excepciones.CantidadSolicitudesInvalidaExcepcion;
import modelo.personas.Asociado;
import modelo.personas.ObservadorOperario;
import modelo.personas.Operario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimulacionAmbulancia {
        public static boolean activo;
        private List<Asociado> asociados = new ArrayList<>();
        private RetornoAutomatico retornoAutomatico;
        private ObservadorHilos observadorHilos = new ObservadorHilos(this);
        private ObservadorOperario observadorOperario = new ObservadorOperario(this);
        private Controlador controlador;
        private IVista vista;
        private static int cantHilos;
        private Operario operario = new Operario();

        public SimulacionAmbulancia(Controlador controlador, IVista vista) {
            this.controlador = controlador;
            this.vista = vista;
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
            HiloAmbulancia hilo = new HiloAmbulancia(operario, 1);
            observadorOperario.agregarOperario(hilo);
            cantHilos++;
            hilo.start();
        }

        public void eliminarHilo() {
            cantHilos--;
            if (cantHilos == 1 && !Ambulancia.get_instance().getEstado().equals("en el Taller")) {
                activo = false;
            }
            else
                if (cantHilos == 0) {
                    this.asociados.clear();
                    this.observadorHilos.eliminarObservables();
                    this.controlador.terminarSimulacionVista();
                }
        }

        public void empezarAmbulancia(ArrayList<Integer> cant) throws CantidadSolicitudesInvalidaExcepcion {
            List<Thread> hilos = new ArrayList<>();
            activo = true;
            //hilos.clear();
            for (int i = 0; i < asociados.size(); i++){
                hilos.add(new HiloAmbulancia(asociados.get(i), cant.get(i)));
            }
            retornoAutomatico = new RetornoAutomatico();
            for (int i = 0; i < asociados.size(); i++){
                hilos.get(i).start();
            }
            this.observadorHilos.agregarObservables(hilos, retornoAutomatico);
            cantHilos = hilos.size() + 1;
            retornoAutomatico.start();
        }

        public void terminarOperario(){
            this.vista.cambiarBotonTallerEnabled();
            this.observadorOperario.eliminarOperario();
            this.eliminarHilo();
        }

        public void pararSimulacion(){
            activo = false;
        }

        public static int getCantHilos() {
            return cantHilos;
        }

        public Thread getRetorno(){
            return retornoAutomatico;
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
