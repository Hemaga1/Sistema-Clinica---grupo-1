package lugares;

public class HabitacionCompartida extends Habitacion {
    
    public HabitacionCompartida(double costoInicial) {
        super(costoInicial);
    }

    @Override
    public double calculaCosto(int cantDias) /* throws DiasInvalidosException */{
        if (cantDias <= 0) {
            // throw new DiasInvalidosException("La cantidad de días debe ser mayor a 0");
        }
        System.out.println("Calculando");
        return costoAsignacion + costoInicial * cantDias;
    }

}
