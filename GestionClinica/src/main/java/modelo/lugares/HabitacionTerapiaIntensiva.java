package modelo.lugares;

/**
 * Clase de la Habitación de Terapia Intensiva que se extiende de Habitacion
 */

public class HabitacionTerapiaIntensiva extends Habitacion {

    /**
     * Constructor indicando el costo inicial de la habitacion de terapia intensiva
     * @param costoInicial costoInicial>0
     */
    public HabitacionTerapiaIntensiva(double costoInicial) {
        super(costoInicial, 1);
    }

    /**
     * Se calcula el costo de la habitacion de Terapia Intensiva que es el costo de asignacion + el costo inicial elevado a la cantidad de dias internado<br>
     * <b>Preocndición: </b> La cantidad de días no puede ser negativa ni tampoco 0<br>
     * <b>Postcondición: </b> Se calcula el costo por haber estado internado el paciente en la habitacon de Terapia Intensiva
     * @param cantDias cantDias>0
     * @return costo calculado
     */
    @Override
    public double calculaCosto(int cantDias){
        assert cantDias>0 : "La cantidad de días debe ser mayor que 0";
        double costo = Math.pow(costoInicial,cantDias);

        assert costo>0 : "El costo calculado debe ser mayor a 0";

        return costo;
    }

    @Override
    public String toString() {
        return "Habitacion Nº" + getNumHabitacion() + ": Terapia Intensiva";
    }

}
