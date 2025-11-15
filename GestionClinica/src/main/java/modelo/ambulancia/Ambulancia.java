package modelo.ambulancia;

import modelo.interfaces.IAmbulanciaState;

import java.util.Observable;

/**
 * Representa la ambulancia de la clínica, implementada como un Singleton.
 * Utiliza el patrón State para gestionar sus distintos estados operativos
 * (disponible, en atención domiciliaria, en traslado, en reparación, etc.).
 */

public class Ambulancia extends Observable {
    protected volatile IAmbulanciaState estado;
    private static volatile Ambulancia instance = null;

    /**
     * Constructor privado del Singleton.
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>El estado inicial es una instancia válida</li>
     *     <li>El atributo estado no es null.</li>
     * </ul>
     */
    private Ambulancia() {
        this.estado = new DisponibleState(this);
        assert this.estado != null : "Estado inicial de ambulancia no puede ser null";
    }

    /**
     * Obtiene la instancia Singleton de la ambulancia.
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>Se devuelve siempre la misma instancia.</li>
     *     <li>La instancia nunca es null.</li>
     * </ul>
     *
     * @return instancia única de Ambulancia
     */
    public static Ambulancia get_instance() {
        if (instance == null) {
            synchronized (Ambulancia.class) {
                if (instance == null) {
                    instance = new Ambulancia();
                    assert instance != null : "Error: no se pudo crear instancia Singleton de Ambulancia";
                }
            }
        }
        return instance;
    }

    /**
     * Cambia el estado interno de la ambulancia.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *     <li>estado no puede ser null.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>El estado de la ambulancia se actualiza correctamente.</li>
     *     <li>Se notifica a los observadores del cambio.</li>
     * </ul>
     *
     * @param estado nuevo estado != null
     */
    protected synchronized void setEstado(IAmbulanciaState estado) {
        assert estado != null : "Precondición falló: estado no puede ser null";
        this.estado = estado;
        setChanged();
        notifyObservers();
        notifyAll(); // Notificar threads en wait()
    }

    /**
     * Solicita atención domiciliaria para el asociado que ejecuta el hilo actual.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *     <li>El método debe ser invocado por un hilo de tipo HiloAmbulancia.</li>
     *     <li>El hilo debe tener asignado un asociado no null.</li>
     *     <li>El estado actual debe ser capaz de eventualmente permitir atención domiciliaria.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>El asociado es notificado como atendido.</li>
     *     <li>El estado de la ambulancia cambia al correspondiente.</li>
     * </ul>
     */
    public synchronized void solicitaAtencionDomicilio() {
        HiloAmbulancia hilo = (HiloAmbulancia) Thread.currentThread();
        assert hilo.getAsociado() != null : "Precondición falló: HiloAmbulancia debe tener un asociado asignado";
        while (!this.estado.puedeIniciarAtencionDomicilio()) {
            try {
                hilo.getAsociado().notificarEspera();
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        hilo.getAsociado().notificarFueAtendido();
        this.estado.solicitaAtencionDomicilio();
        this.setChanged();
        notifyAll();
    }

    /**
     * Solicita un traslado del asociado del hilo actual.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *     <li>El método debe ser llamado por un HiloAmbulancia.</li>
     *     <li>El hilo debe tener un asociado asignado.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>El asociado es notificado como atendido.</li>
     *     <li>La ambulancia cambia a un estado compatible con la operación.</li>
     * </ul>
     */
    public synchronized void solicitaTraslado() {
        HiloAmbulancia hilo = (HiloAmbulancia) Thread.currentThread();
        assert hilo.getAsociado() != null : "Precondición falló: el hilo debe tener un asociado";
        while (!this.estado.puedeIniciarTraslado()) {
            try {
                hilo.getAsociado().notificarEspera();
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        hilo.getAsociado().notificarFueAtendido();
        this.estado.solicitaTraslado();
        this.setChanged();
        notifyAll();
    }

    /**
     * Indica que la ambulancia entra en reparación.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *     <li>El estado actual debe permitir entrar en reparación.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>El estado de la ambulancia cambia a uno de reparación.</li>
     * </ul>
     */
    public synchronized void repararAmbulancia() {
        while (!this.estado.puedeIniciarReparacion()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        this.estado.repararAmbulancia();
    }

    /**
     * Indica que la ambulancia retorna a la clínica.
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>El estado cambia a uno compatible con estar nuevamente disponible o retornando.</li>
     * </ul>
     */
    public synchronized void retornarAClinica() {
        this.estado.vuelveClinica();
    }

    /**
     * Devuelve la representación en texto del estado actual de la ambulancia.
     *
     * <b>Invariante:</b>
     * <ul>
     *     <li>El estado no puede ser null.</li>
     * </ul>
     *
     * @return nombre del estado actual !=null
     */
    public synchronized String getEstado() {
        assert this.estado != null : "El estado jamás puede ser null";
        return estado.toString();
    }
}
