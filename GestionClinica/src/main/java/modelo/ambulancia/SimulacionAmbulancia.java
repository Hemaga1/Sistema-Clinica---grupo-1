package modelo.ambulancia;

import controlador.Controlador;
import vista.IVista;
import modelo.excepciones.CantidadSolicitudesInvalidaExcepcion;
import modelo.observadores_y_observables.ObservadorHilos;
import modelo.personas.Asociado;
import modelo.observadores_y_observables.ObservadorOperario;
import modelo.personas.Operario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase principal encargada de coordinar y ejecutar la simulación de solicitudes
 * Utiliza un contador de hilos activos para determinar cuándo la simulación debe finalizar.
 */
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


    /**
     * @param controlador Controlador general del sistema.
     * @param vista Vista para actualizar interfaz durante la simulación.
     */
        public SimulacionAmbulancia(Controlador controlador, IVista vista) {
            this.controlador = controlador;
            this.vista = vista;
            this.activo = false;
        }

    /**
     * Prepara la lista de asociados que participarán en la simulación.
     *
     * @param asociadosAmbulancia lista de asociados seleccionados.
     */
        public void prepararAsociados(List<Asociado> asociadosAmbulancia) {
            Iterator<Asociado> iterator = asociadosAmbulancia.iterator();
            while(iterator.hasNext()){
                Asociado a = (Asociado) iterator.next();
                asociados.add(a);
            }
        }

    /**
     * Envía la ambulancia al taller, generando un hilo especial.
     */
        public void enviarATaller() {
            HiloAmbulancia hilo = new HiloAmbulancia(operario, 1);
            observadorOperario.agregarOperario(hilo);
            cantHilos++;
            hilo.start();
        }

    /**
     * Reduce la cantidad de hilos activos y decide si la simulación debe finalizar.
     */
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

    /**
     * Inicia la simulación de la ambulancia.
     *
     * @param cant Cantidad de solicitudes por asociado.
     * @throws CantidadSolicitudesInvalidaExcepcion si los valores son inválidos.
     */
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
