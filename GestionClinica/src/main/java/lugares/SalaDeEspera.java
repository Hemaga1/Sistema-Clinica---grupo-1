package lugares;

import excepciones.PacienteNoEstaEsperandoExcepcion;
import excepciones.SalaEsperaVaciaExcepcion;
import personas.Paciente;

/**
 * Clase utilizada para la sela de espera en general, incluye la sala de espera privada y el patio
 */
public class SalaDeEspera {
    private Patio patio;
    private SalaDeEsperaPrivada salaPrivada;

    /**
     * Constructor de la sala de espera que vendría a ser genérica: incluye la Sala de Espera Privada y el Patio
     */
    public SalaDeEspera(){
        this.patio = new Patio();
        this.salaPrivada = new SalaDeEsperaPrivada();
    }

    /**
     * Se ingresa al paciente segun corresponda en la sala de espera privada o en el patio<br>
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>Si la sala Privada esta vacia se ingresa al paciente</li>
     *     <li>Si la sala Privada no esta vacía, se verifica segun rangos etarios si se reemplaza o se ingresa al patio</li>
     * </ul>
     * @param paciente Paciente a ingresar, paciente!=null, paciente!=""
     */
    public void ingresar(Paciente paciente) {
        Paciente pacienteSalaPrivada = salaPrivada.getPaciente();
        if (!salaPrivada.Ocupado()){
            salaPrivada.setPaciente(paciente);
        }else{
            if (pacienteSalaPrivada.esReemplazado(paciente)){
                patio.agregarPaciente(pacienteSalaPrivada);
                salaPrivada.setPaciente(paciente);
            }else{
                patio.agregarPaciente(paciente);
            }
        }
    }

    /**
     * El paciente será atendido entonces se quita de espera<br>
     * <b>Precondición: </b>
     * <ul>
     *     <li>La sala de espera no debe estar vacía</li>
     *     <li>El paciente debe encontrarse en la sala de espera</li>
     * </ul><br>
     * <b>Postcondición: </b> Se quita al paciente de la sala de espera para ser atendido
     * @param paciente Paciente que será atendido, paciente!=null, paciente!=""
     * @throws SalaEsperaVaciaExcepcion
     * @throws PacienteNoEstaEsperandoExcepcion
     */
    public void sacarPaciente(Paciente paciente) throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion {
        if (this.salaPrivada.Ocupado() && this.salaPrivada.getPaciente().equals(paciente)){
            this.salaPrivada.setPaciente(null);
        }
        else {
            this.patio.sacarPaciente(paciente);
        }
    }

    public Paciente sacarPacienteConMenorOrden() throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion{
        Paciente candidato = null;
        if (this.salaPrivada.Ocupado()) {
            candidato = this.salaPrivada.getPaciente();
        }
        for (Paciente p : this.patio.getPacientes()) {
            if (candidato == null || p.getNroOrden() < candidato.getNroOrden()) {
                candidato = p;
            }
        }
        if (candidato != null) {
            this.sacarPaciente(candidato);
        }
        return candidato;
    }
}