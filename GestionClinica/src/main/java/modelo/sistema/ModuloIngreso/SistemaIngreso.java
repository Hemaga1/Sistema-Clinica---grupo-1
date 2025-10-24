package modelo.sistema.ModuloIngreso;

import modelo.excepciones.PacienteNoEstaEsperandoExcepcion;
import modelo.excepciones.SalaEsperaVaciaExcepcion;
import modelo.personas.Paciente;
import modelo.lugares.SalaDeEspera;

/**
 * Clase donde se ingresa y saca al paciente de la sala es espera, ya sea privada o el patio
 */
public class SistemaIngreso {

    private final SalaDeEspera salaDeEspera;
    
    public SistemaIngreso() {
        this.salaDeEspera = new SalaDeEspera();
    }

    /**
     * Se procede a ingresar al paciente a la sala de espera luego de haber sido registrado<br>
     * <b>Precondición: </b>Debe haber sido registrado anteriormente<br>
     * <b>Postcondición: </b>Se ingresa el paciente a espera
     * @param paciente Paciente que sera ingresado a la sala de espera, paciente != null
     */
    public void ingresarPaciente(Paciente paciente) {
        salaDeEspera.ingresar(paciente);
    }

    /**
     * Se saca al paciente de la sala de espera cuando va a ser atendido<br>
     * <b>Precondición: </b> La sala de espera no está vacía y el paciente indicado esta en ella<br>
     * <b>Postcondición: </bSe quita de espera, ya sea de la Sala Privada o del Patio
     * @param paciente Paciente que será sacado de espera, paciente != null
     * @throws SalaEsperaVaciaExcepcion
     * @throws PacienteNoEstaEsperandoExcepcion
     */
    public void SacarPaciente(Paciente paciente) throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion {
        salaDeEspera.sacarPaciente(paciente);
    }

    /**
     * Se saca al paciente con menor numero de orden de la sala de espera cuando va a ser atendido<br>
     * <b>Precondición: </b> La sala de espera no está vacía<br>
     * <b>Postcondición: </bSe quita de espera, ya sea de la Sala Privada o del Patio
     * @throws SalaEsperaVaciaExcepcion
     * @throws PacienteNoEstaEsperandoExcepcion
     */
    public Paciente SacarPaciente() throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion{
        return salaDeEspera.sacarPacienteConMenorOrden();
    }
}
