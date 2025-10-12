package personas;
import honorarios.IMedico;

import java.util.ArrayList;

/**
 * Clase extendida de Persona, correspondiente al paciente
 */
public abstract class Paciente extends Persona{
	private int historiaClinica;
	public static int siguienteNro = 0;
	private int nroOrden;

	/**
	 * Constructor del paciente, se utiliza el constructor de persona y se añade la historia clinica
	 * @param DNI dni!=null, dni!=""
	 * @param nombre nombre!=null, nombre!=""
	 * @param apellido apellido!=null, apellido!=""
	 * @param calle calle!=null, calle!=""
	 * @param numero numero>=o
	 * @param ciudad ciudad!=null, cidudad!=""
	 * @param telefono telefono!=null, telefono !=""
	 * @param historiaClinica historiaClinica>0
	 */
	public Paciente(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono, int historiaClinica) {
		super(DNI, nombre, apellido, calle, numero, ciudad, telefono);
		this.historiaClinica = historiaClinica;
		siguienteNro++;
		nroOrden=siguienteNro;
	}

	/**
	 * Teniendo el nuevo paciente, se verá si este ingresa a la sala de espera privada o si va al patio
	 * @param otroPaciente otroPaciente!=null
	 * @return true or false
	 */
	public abstract boolean reemplaza(Paciente otroPaciente);

	/**
	 * Se verifica si se reemplaza el niño que esta en la sala de espera privada segun el paciente nuevo que ingrese
	 * @return true or false
	 */
	public abstract boolean reemplazaANinio();

	/**
	 * Se verifica si se reemplaza el Joven que esta en la sala de espera privada segun el paciente nuevo que ingrese
	 * @return true or false
	 */
	public abstract boolean reemplazaAJoven();

	/**
	 * Se verifica si se reemplaza el Mayor que esta en la sala de espera privada segun el paciente nuevo que ingrese
	 * @return true or false
	 */
	public abstract boolean reemplazaAMayor();

    @Override
    public String toString() {
        return "Paciente HC: " + historiaClinica + " - " + super.toString();
    }

    public int getNroOrden() {
        return nroOrden;
    }

	
}
