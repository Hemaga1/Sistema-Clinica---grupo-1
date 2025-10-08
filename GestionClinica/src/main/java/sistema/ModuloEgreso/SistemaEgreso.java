package sistema.ModuloEgreso;

import excepciones.PacienteSinAtenderExcepcion;
import facturacion.Factura;
import facturacion.RegistroPaciente;
import personas.Paciente;

public class SistemaEgreso {

    public Factura egresar(Paciente paciente, RegistroPaciente registro) throws PacienteSinAtenderExcepcion {
        if (registro == null) {
            throw new PacienteSinAtenderExcepcion();
        }
        else return new Factura(paciente, registro);
    }
}


