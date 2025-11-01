package modelo.lugares;

/**
 * Clase de la Habitación Privada que se extiende de Habitacion
 */

public class HabitacionPrivada extends Habitacion {

    /**
     * Constructor indicando el costo inicial de la habitacion Privada
     * @param costoInicial costoInicial>0
     */
    public HabitacionPrivada(double costoInicial) {
        super(costoInicial, 1);
    }

    /**
     * Se calcula el costo de la habitacion Privada que es el costo de asignacion + el costo inicial multiplicada por la cantidad de dias internado y dependiendo la cantidad de dias se aplica un porcentaje de interes<br>
     * <b>Preocndición: </b> La cantidad de días no puede ser negativa ni tampoco 0<br>
     * <b>Postcondición: </b> Se calcula el costo por haber estado internado el paciente en la habitacon privada
     * @param cantDias cantDias>0
     * @return costo calculado
     */
    @Override
    public double calculaCosto(int cantDias){
        assert cantDias>0 : "La cantidad de días debe ser mayor que 0";
        double costo;
        if(cantDias == 1) {
        	costo = costoAsignacion + costoInicial * cantDias;
        }
        else if(cantDias < 6) {
        	costo = costoAsignacion + costoInicial * cantDias * 1.3;
        }
        else {
        	costo = costoAsignacion + costoInicial * cantDias * 2;
        }
        assert costo>0 : "El costo calculado debe ser mayor a 0";

        return costo;
    }

    @Override
    public String toString() {
        return "Habitacion Nº" + getNumHabitacion() + ": Privada";
    }

}
