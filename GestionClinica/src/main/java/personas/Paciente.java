package personas;
import honorarios.IMedico;

import java.util.ArrayList;

public abstract class Paciente extends Persona{
	private int historiaClinica;
	public static int siguienteNro = 0;
	private int nroOrden;

	public Paciente(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono, int historiaClinica) {
		super(DNI, nombre, apellido, calle, numero, ciudad, telefono);
		this.historiaClinica = historiaClinica;
		siguienteNro++;
		nroOrden=siguienteNro;
	}
	
	public abstract boolean reemplaza(Paciente otroPaciente);
	
	public abstract boolean reemplazaANinio();
	
	public abstract boolean reemplazaAJoven();
	
	public abstract boolean reemplazaAMayor();

    @Override
    public String toString() {
        return "Paciente HC: " + historiaClinica + " - " + super.toString();
    }

    public int getNroOrden() {
        return nroOrden;
    }

	
}
