package paquete;

public class Ninio extends Paciente{

	public Ninio(String DNI, String nombre, String apellido, String domicilio, String ciudad, String telefono, int historiaClinica) {
		super(DNI, nombre, apellido, domicilio, ciudad, telefono, historiaClinica);
	}
	
	public boolean reemplaza(Paciente otroPaciente) {
		if (otroPaciente.reemplazaANinio())
			return false;
		else return true;
	}
	
	public boolean reemplazaANinio() {
		return false;
	}
	
	public boolean reemplazaAJoven() {
		return true;
	}
	
	public boolean reemplazaAMayor() {
		return false;
	}

	@Override
	public String toString() {
		return " Ninio" +super.toString();
	}
	
	
}
