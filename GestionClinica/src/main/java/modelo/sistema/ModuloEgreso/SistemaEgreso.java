package modelo.sistema.ModuloEgreso;

import modelo.excepciones.PacienteSinAtenderExcepcion;
import modelo.excepciones.DesocupacionPacienteInexistenteExcepcion;
import modelo.facturacion_y_registros.Factura;
import modelo.facturacion_y_registros.RegistroPaciente;
import modelo.personas.Paciente;

/**
 * Clase donde se maneja el egreso del paciente, se desocupa la habitacion en caso de haber sido internado y se genera la factura
 */
public class SistemaEgreso {

    /**
     * Se egresa al paciente y se realiza su facturación correspondiente con la información sobre su actividad siendo atendido
     * @param paciente Paciente que egresa, paciente != null
     * @param registro Registro del paciente donde se tiene si estuvo internado, cantidad de días y las consultas médicas
     * @return Instanciación a la facturación
     * @throws PacienteSinAtenderExcepcion
     * @throws DesocupacionPacienteInexistenteExcepcion
     */
    public Factura egresar(Paciente paciente, RegistroPaciente registro) throws PacienteSinAtenderExcepcion, DesocupacionPacienteInexistenteExcepcion {
        assert paciente!=null : "El paciente que se quiere egresar y generar factura no puede ser null";
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


