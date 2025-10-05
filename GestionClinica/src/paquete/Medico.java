package paquete;

import honorarios.*;

public class Medico extends Persona implements IHonorario{
	private String matricula;

	public Medico(String dni, String nombre, String apellido, String domicilio, String ciudad, String telefono, String matricula) {
		super(dni, nombre, apellido, domicilio, ciudad, telefono);
		this.matricula = matricula;
	}
	
	public double calcularHonorarios() {
		return 20000;
	}

	@Override
	public String toString() {
		return " Medico matricula: " + matricula + super.toString();
	}
	
	
	
}