package lugares;
import java.util.ArrayList;
import java.util.List;

import excepciones.PacienteNoEstaEsperandoExcepcion;
import excepciones.SalaEsperaVaciaExcepcion;
import personas.Paciente;

public class Patio {
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	
	public void agregarPaciente(Paciente paciente) {
		pacientes.add(paciente);
	}

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
