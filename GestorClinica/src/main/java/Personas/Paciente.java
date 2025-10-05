package Personas;

public abstract class Paciente extends Persona{
	private int historiaClinica;

	public Paciente(String DNI, String nombre, String apellido, String domicilio, String ciudad, String telefono, int historiaClinica) {
		super(DNI, nombre, apellido, domicilio, ciudad, telefono);
		this.historiaClinica = historiaClinica;
	}
	
	public abstract boolean reemplaza(Paciente otroPaciente);
	
	public abstract boolean reemplazaANinio();
	
	public abstract boolean reemplazaAJoven();
	
	public abstract boolean reemplazaAMayor();

	
}
