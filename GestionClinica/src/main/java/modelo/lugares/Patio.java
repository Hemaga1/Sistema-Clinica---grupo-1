package modelo.lugares;
import java.util.ArrayList;
import java.util.List;

import modelo.excepciones.PacienteNoEstaEsperandoExcepcion;
import modelo.excepciones.SalaEsperaVaciaExcepcion;
import modelo.personas.Paciente;

/**
 * Clase patio, donde se encuentran todos los pacientes en espera excepto el que esta en la sala de espera Privada
 */
public class Patio {
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

	/**
	 * Se agrega al paciente en la lista del patio
	 * @param paciente Paciente a ser agregado
	 */
	public void agregarPaciente(Paciente paciente) {
		pacientes.add(paciente);
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
        if (pacientes.isEmpty())
            throw new SalaEsperaVaciaExcepcion();
        else {
            if (pacientes.contains(paciente)) {
                this.pacientes.remove(paciente);
            }
            else throw new PacienteNoEstaEsperandoExcepcion();
        }
	}

	
	/**
	 * Obtiene la lista de pacientes en el patio
	 * @return Lista de pacientes
	 */
	public List<Paciente> getPacientes() {
		return new ArrayList<>(pacientes);
	}
}
