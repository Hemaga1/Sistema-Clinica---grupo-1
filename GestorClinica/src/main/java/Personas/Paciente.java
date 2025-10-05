package Personas;

public abstract class Paciente extends Persona{
	private int historiaClinica;
	public static int siguienteNro = 0;
	private int nroOrden;


	public Paciente(String DNI, String nombre, String apellido, String domicilio, String ciudad, String telefono, int historiaClinica) {
		super(DNI, nombre, apellido, domicilio, ciudad, telefono);
		this.historiaClinica = historiaClinica;
		siguienteNro++;
		nroOrden=siguienteNro;
	}
	
	public abstract boolean reemplaza(Paciente otroPaciente);
	
	public abstract boolean reemplazaANinio();
	
	public abstract boolean reemplazaAJoven();
	
	public abstract boolean reemplazaAMayor();

	
}
