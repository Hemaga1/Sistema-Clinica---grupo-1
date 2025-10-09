package sistema.ModuloEgreso;

import excepciones.PacienteSinAtenderExcepcion;
import excepciones.DesocupacionPacienteInexistenteExcepcion;
import facturacion.Factura;
import facturacion.RegistroPaciente;
import personas.Paciente;

public class SistemaEgreso {

    public Factura egresar(Paciente paciente, RegistroPaciente registro) throws PacienteSinAtenderExcepcion, DesocupacionPacienteInexistenteExcepcion {
        if (registro == null) {
            throw new PacienteSinAtenderExcepcion();
        }
        else {
            if (registro.getHabitacion() != null) {
                registro.getHabitacion().desocuparHabitacion(paciente);
            }
            return new Factura(paciente, registro);
        }
    }
}


