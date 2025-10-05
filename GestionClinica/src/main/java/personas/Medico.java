package personas;

import facturacion.Reporte;
import honorarios.*;

import java.util.ArrayList;

public class Medico extends Persona implements IMedico{
	private String matricula;
    private String especialidad;
    private ArrayList<Reporte> atendidos = new ArrayList<>();

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

    public void agregarAtendido(Reporte atendido) {
        atendidos.add(atendido);
    }

    public ArrayList<Reporte> getAtendidos(){
        return atendidos;
    }

    @Override
    public String toString() {
        return " Medico matricula: " + matricula + super.toString();
    }
	
}
