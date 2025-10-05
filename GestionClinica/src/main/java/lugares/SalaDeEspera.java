package lugares;

import personas.Paciente;

public class SalaDeEspera {
	private Paciente paciente;
	private Patio patio;
	
	public SalaDeEspera() {
		super();
		this.patio = new Patio();
	}

	public void ingresar(Paciente otroPaciente) {
		if (paciente == null) {
			this.paciente = otroPaciente;
			System.out.println(paciente.toString() + "ingresado a Sala de espera");
		}
		else
			if (otroPaciente.reemplaza(this.paciente)) {
				patio.agregarPaciente(this.paciente);
				this.paciente = otroPaciente;
				System.out.println(paciente.toString() + "ingresado a Sala de espera");
			}
			else {
				patio.agregarPaciente(otroPaciente);
				System.out.println(paciente.toString() + "ingresado al Patio");
			}
			
	}
	
	public void sacarPaciente(Paciente otroPaciente) {
		if (otroPaciente == this.paciente) {
			this.paciente = null;
		}
		else patio.sacarPaciente(otroPaciente);
	}
}
