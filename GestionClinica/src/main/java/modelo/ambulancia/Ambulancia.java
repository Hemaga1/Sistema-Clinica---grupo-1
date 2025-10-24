package modelo.ambulancia;

public class Ambulancia {
    protected IAmbulanciaState estado;
    private static Ambulancia instance = null;

    private Ambulancia() {
        this.estado = new DisponibleState(this);
    }

    public static Ambulancia get_instance() {
        if (instance == null)
            instance = new Ambulancia();
        return instance;
    }

    protected void setEstado(IAmbulanciaState estado) {
        this.estado = estado;
    }

    public void solicitaAtencionDomicilio() {
        estado.solicitaAtencionDomicilio();
    }

    public void solicitaTraslado() {
        estado.solicitaTraslado();
    }

    public void repararAmbulancia() {
        estado.repararAmbulancia();
    }

    public void retornarAClinica() {
        estado.vuelveClinica();
    }

    public IAmbulanciaState getEstado() {
        return estado;
    }
}
