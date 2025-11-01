package modelo.lugares;

/**
 * Clase de la Habitación Compartida que se extiende de Habitacion
 */

public class HabitacionCompartida extends Habitacion {

    /**
     * Constructor indicando el costo inicial y el maximo de modelo.personas en la habitacion compartida
     * @param costoInicial costoInicial>0
     * @param maximoPacientes maximoPaciente>0
     */
    public HabitacionCompartida(double costoInicial, int maximoPacientes) {
        super(costoInicial, maximoPacientes);
    }

    /**
     * Se calcula el costo de la habitacion compartida que es el costo de asignacion + el costo inicial multiplicada por la cantidad de dias internado<br>
     * <b>Preocndición: </b> La cantidad de días no puede ser negativa ni tampoco 0<br>
     * <b>Postcondición: </b> Se calcula el costo por haber estado internado el paciente en la habitacon compartida
     * @param cantDias cantDias>0
     * @return costo calculado
     */
    @Override
    public double calculaCosto(int cantDias){
        assert cantDias>0 : "La cantidad de días debe ser mayor que 0";
        System.out.println("Calculando");

        double costo = costoAsignacion + costoInicial * cantDias;

        assert costo>0 : "El costo calculado debe ser mayor a 0";

        return costo;
    }

    @Override
    public String toString() {
        return "Habitacion Nº" + getNumHabitacion() + ": Compartida";
    }

}
