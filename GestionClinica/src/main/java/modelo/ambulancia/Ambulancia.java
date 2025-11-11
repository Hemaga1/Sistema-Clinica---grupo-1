package modelo.ambulancia;

import modelo.personas.Asociado;

import java.util.Observable;

public class Ambulancia extends Observable {
    protected volatile IAmbulanciaState estado;
    private static volatile Ambulancia instance = null;

    private Ambulancia() {
        this.estado = new DisponibleState(this);
    }

    public static Ambulancia get_instance() {
        if (instance == null) {
            synchronized (Ambulancia.class) {
                if (instance == null) {
                    instance = new Ambulancia();
                }
            }
        }
        return instance;
    }

    protected synchronized void setEstado(IAmbulanciaState estado) {
        this.estado = estado;
        setChanged();
        notifyObservers();
        notifyAll(); // Notificar threads en wait()
    }

    public synchronized void solicitaAtencionDomicilio() {
        HiloAmbulancia hilo = (HiloAmbulancia) Thread.currentThread();
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

    public synchronized void solicitaTraslado() {
        HiloAmbulancia hilo = (HiloAmbulancia) Thread.currentThread();
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

    public synchronized void retornarAClinica() {
        this.estado.vuelveClinica();
    }

    public synchronized String getEstado() {
        return estado.toString();
    }
}
