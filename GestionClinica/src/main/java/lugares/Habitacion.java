package lugares;

public abstract class Habitacion {
	
	protected double costoInicial;
    protected double costoAsignacion = 15000;

    public Habitacion(double costoInicial) {
        this.costoInicial = costoInicial;
    }

    public abstract double calculaCosto(int cantDias);

    public double getCostoAsignacion() {
        return costoAsignacion;
    }

}
