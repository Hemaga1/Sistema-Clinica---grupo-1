package paquete;

public class SalaDeEspera {
	private Paciente paciente;
	private Patio patio;
	
	public void ingresar(Paciente otroPaciente) {
		if (paciente == null)
			this.paciente = otroPaciente;
		else
			if (otroPaciente.reemplaza(this.paciente)) {
				patio.agregarPaciente(this.paciente);
				this.paciente = otroPaciente;
			}
			else patio.agregarPaciente(otroPaciente);
	}
	
	public void sacarPaciente(Paciente otroPaciente) {
		if (otroPaciente == this.paciente) {
			this.paciente = null;
		}
		else patio.sacarPaciente(otroPaciente);
	}
}
