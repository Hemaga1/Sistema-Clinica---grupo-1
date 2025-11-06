package modelo.ambulancia;

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
        System.out.println("[Ambulancia] estado actual: " + this.getEstado());
        setChanged();
        notifyObservers();
        notifyAll(); // Notificar threads en wait()
    }

    public synchronized void solicitaAtencionDomicilio() {
        while (!this.estado.puedeIniciarAtencionDomicilio()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        this.estado.solicitaAtencionDomicilio();
    }

    public synchronized void solicitaTraslado() {
        while (!this.estado.puedeIniciarTraslado()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        this.estado.solicitaTraslado();
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
