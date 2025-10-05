package lugares;
import java.util.ArrayList;

import personas.Paciente;

public class Patio {
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	
	public void agregarPaciente(Paciente paciente) {
		pacientes.add(paciente);
	}

	public void sacarPaciente(Paciente paciente) {
		pacientes.remove(paciente);
	}
}
