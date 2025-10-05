package paquete;

public class Mayor extends Paciente{

	public Mayor(String DNI, String nombre, String apellido, String domicilio, String ciudad, String telefono, int historiaClinica) {
		super(DNI, nombre, apellido, domicilio, ciudad, telefono, historiaClinica);
	}
	
	public boolean reemplaza(Paciente otroPaciente) {
		return otroPaciente.reemplazaAMayor();
	}
	
	public boolean reemplazaANinio() {
		return true;
	}
	
	public boolean reemplazaAJoven() {
		return false;
	}
	
	public boolean reemplazaAMayor() {
		return false;
	}
	
	@Override
	public String toString() {
		return " Mayor" +super.toString();
	}
}
