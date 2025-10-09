package lugares;

public class HabitacionPrivada extends Habitacion {
    
    public HabitacionPrivada(double costoInicial) {
        super(costoInicial, 1);
    }

    @Override
    public double calculaCosto(int cantDias) /* throws DiasInvalidosException */{
        if (cantDias <= 0) {
            // throw new DiasInvalidosException("La cantidad de días debe ser mayor a 0");
        }
        
        
        if(cantDias == 1) {
        	return costoAsignacion + costoInicial * cantDias;
        }
        else if(cantDias < 6) {
        	return costoAsignacion + costoInicial * cantDias * 1.3;
        }
        else {
        	return costoAsignacion + costoInicial * cantDias * 2;
        }

    }

    @Override
    public String toString() {
        return "Habitacion Privada";
    }

}
