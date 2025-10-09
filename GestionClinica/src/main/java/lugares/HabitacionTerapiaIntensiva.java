package lugares;

public class HabitacionTerapiaIntensiva extends Habitacion {
    
    public HabitacionTerapiaIntensiva(double costoInicial) {
        super(costoInicial, 1);
    }

    @Override
    public double calculaCosto(int cantDias) /* throws DiasInvalidosException */{
        if (cantDias <= 0) {
            // throw new DiasInvalidosException("La cantidad de días debe ser mayor a 0");
        }
        return Math.pow(costoInicial,cantDias);
    }

    @Override
    public String toString() {
        return "Terapia Intensiva";
    }

}
