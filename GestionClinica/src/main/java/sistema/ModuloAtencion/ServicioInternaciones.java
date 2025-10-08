package sistema.ModuloAtencion;

import lugares.Habitacion;
import facturacion.RegistroPaciente;
import excepciones.PacienteSinAtenderExcepcion;

public class ServicioInternaciones {

    public void internar(RegistroPaciente registro, Habitacion habitacion) throws PacienteSinAtenderExcepcion {
        if (registro == null) {
            throw new PacienteSinAtenderExcepcion();
        }
        else registro.setHabitacion(habitacion);
    }
    
    public void establecerDiasInternado(RegistroPaciente registro, int cantDiasInternado) throws PacienteSinAtenderExcepcion {
        if (registro == null) {
            throw new PacienteSinAtenderExcepcion();
        }
        else registro.setCantDiasInternado(cantDiasInternado);
    }
    
    public boolean estaInternado(RegistroPaciente registro) {
        if (registro == null) {
            return false;
        }
        return registro.getHabitacion() != null;
    }
}
