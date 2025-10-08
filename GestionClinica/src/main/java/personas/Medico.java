package personas;

import honorarios.*;

import java.util.ArrayList;

public class Medico extends Persona implements IMedico{
	private String matricula;
    private String especialidad;

	public Medico(String DNI, String nombre, String apellido, String domicilio, String ciudad, String telefono, String matricula, String especialidad) {
		super(DNI, nombre, apellido, domicilio, ciudad, telefono);
		this.matricula = matricula;
        this.especialidad = especialidad;
	}

    public String getEspecialidad(){
        return this.especialidad;
    }

	public double calcularHonorarios() {
		return 20000;
	}

    @Override
    public String toString() {
        return " Medico matricula: " + matricula + super.toString();
    }
	
}
