package sistema.ModuloIngreso;

import excepciones.PacienteNoEstaEsperandoExcepcion;
import excepciones.SalaEsperaVaciaExcepcion;
import personas.Paciente;
import lugares.SalaDeEspera;

/**
 * Clase donde se ingresa y saca al paciente de la sala es espera, ya sea privada o el patio
 */
public class SistemaIngreso {

    private final SalaDeEspera salaDeEspera;
    
    public SistemaIngreso() {
        this.salaDeEspera = new SalaDeEspera();
    }

    /**
     * Se procede a ingresar al paciente a la sala de espera luego de haber sido registrado
     * @param paciente Paciente que sera ingresado a la sala de espera, paciente != null
     */
    public void ingresarPaciente(Paciente paciente) {
        salaDeEspera.ingresar(paciente);
    }

    /**
     * Se saca al paciente de la sala de espera cuando va a ser atendido<br>
     * <b>Precondición: </b> La sala de espera no está vacía y el paciente indicado esta en ella
     * @param paciente Paciente que será sacado de espera, paciente != null
     * @throws SalaEsperaVaciaExcepcion
     * @throws PacienteNoEstaEsperandoExcepcion
     */
    public void SacarPaciente(Paciente paciente) throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion {
        salaDeEspera.sacarPaciente(paciente);
    }
    
    public Paciente SacarPaciente() throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion{
        return salaDeEspera.sacarPacienteConMenorOrden();
    }
}
