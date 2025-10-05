package Personas;

import honorarios.*;

public class Medico extends Persona implements IHonorario{
	private String matricula;
	private String especialidad;
	private String posgrado;
	private String contratacion;

	public Medico(String dni, String matricula, String nombre, String apellido, String domicilio, String ciudad, String telefono) {
		super(dni, nombre, apellido, domicilio, ciudad, telefono);
		this.matricula = matricula;
	}
	
	public double calcularHonorarios() {
		return 20000;
	}
	
}
