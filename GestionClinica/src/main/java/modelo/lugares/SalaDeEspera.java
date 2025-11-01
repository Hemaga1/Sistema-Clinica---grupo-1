package modelo.lugares;

import modelo.excepciones.PacienteNoEstaEsperandoExcepcion;
import modelo.excepciones.SalaEsperaVaciaExcepcion;
import modelo.personas.Paciente;

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
        assert paciente!=null : "El paciente a ingresar en una sala no debe ser null";
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
        assert salaPrivada.getPaciente() != null : "La sala privada no debe quedar vacía tras el ingreso";
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
        assert paciente!=null : "El paciente a sacar de una sala no debe ser null";
        if (this.salaPrivada.Ocupado() && this.salaPrivada.getPaciente().equals(paciente)){
            this.salaPrivada.setPaciente(null);
        }
        else {
            this.patio.sacarPaciente(paciente);
        }
    }

    /**
     * Saca al paciente con el menor número de orden de atención, ya sea de la sala privada o del patio.<br>
     * <b>Precondición:</b> Debe haber al menos un paciente esperando (en la sala privada o en el patio).<br>
     * <b>Postcondición:</b>
     * <ul>
     *     <li>Se devuelve el paciente con el menor número de orden.</li>
     *     <li>El paciente removido ya no se encuentra en la sala privada ni en el patio.</li>
     * </ul>
     *
     * @return el paciente con menor número de orden que fue removido
     * @throws SalaEsperaVaciaExcepcion si no hay pacientes en la sala de espera
     * @throws PacienteNoEstaEsperandoExcepcion si ocurre un error al intentar remover el paciente
     */
    public Paciente sacarPacienteConMenorOrden() throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion{
        Paciente candidato = null;
        boolean hayPacientes = salaPrivada.Ocupado() || !patio.getPacientes().isEmpty();
        assert hayPacientes : "No se puede sacar un paciente si la sala de espera está vacía";
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
        assert candidato == null || (!salaPrivada.Ocupado() || !candidato.equals(salaPrivada.getPaciente())) : "El paciente removido no debe seguir en la sala privada";
        assert candidato == null || !patio.getPacientes().contains(candidato) : "El paciente removido no debe seguir en el patio";
        return candidato;
    }
}