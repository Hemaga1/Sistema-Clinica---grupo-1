package sistema.ModuloAtencion;

import lugares.Habitacion;
import facturacion.RegistroPaciente;
import excepciones.PacienteSinAtenderExcepcion;
import excepciones.InternacionCapacidadExcedidaExcepcion;
import personas.Paciente;

/**
 * Clase utilizada cuando el paciente debe ser internado, se lo lleva a la habitacion que corresponda y se indica la cantidad de días
 */
public class ServicioInternaciones {

    /**
     * Internacion del paciente, se lo agrega en la habitacion que corresponda y se setea esta en el registro del paciente<br>
     * <b>Precondición: </b> El paciente debe estar atendido y la habitacion no debe estar llena<br>
     * <b>Postcondición: </b> Paciente internado
     * @param paciente Paciente a ser internado, paciente != null
     * @param registro Registro del paciente, registro !=null
     * @param habitacion Habitacion donde será internado el paciente, habitacion != null
     * @throws PacienteSinAtenderExcepcion
     * @throws InternacionCapacidadExcedidaExcepcion
     */
    public void internar(Paciente paciente, RegistroPaciente registro, Habitacion habitacion) throws PacienteSinAtenderExcepcion, InternacionCapacidadExcedidaExcepcion {
        if (registro == null) {
            throw new PacienteSinAtenderExcepcion();
        }
        else{
            habitacion.ocuparHabitacion(paciente);
            registro.setHabitacion(habitacion);
        }
    }

    /**
     * Se establece en el registro del paciente la cantidad de días internado<br>
     * <b>Precondición: </b> El paciente debe estar atendido<br>
     * <b>Postcondición: </b> Se setea la cantidad de días de internación en el registro del paciente
     * @param registro Registro paciente, registro != null
     * @param cantDiasInternado canDiasInternado>=0
     * @throws PacienteSinAtenderExcepcion
     */
    public void establecerDiasInternado(RegistroPaciente registro, int cantDiasInternado) throws PacienteSinAtenderExcepcion {
        if (registro == null) {
            throw new PacienteSinAtenderExcepcion();
        }
        else registro.setCantDiasInternado(cantDiasInternado);
    }
    
    /*public boolean estaInternado(RegistroPaciente registro) {
        if (registro == null) {
            return false;
        }
        return registro.getHabitacion() != null;
    }*/
}
