package personas;

/**
 * Clase que extiende de paciente si es un joven
 */
public class Joven extends Paciente{

	public Joven(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono,
			int historiaClinica) {
		super(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
	}

	/**
	 * Se verifica si se reemplaza el joven que esta en la sala de espera privada segun el paciente nuevo que ingrese
	 * @return true or false
	 */
	public boolean reemplaza(Paciente otroPaciente) {
		return otroPaciente.reemplazaAJoven();
	}

	/**
	 * Si hay un niño no se reemplaza por el joven
	 * @return false
	 */
	public boolean reemplazaANinio() {
		return false;
	}

	/**
	 * Si hay un joven no se reemplaza por otro joven
	 * @return false
	 */
	public boolean reemplazaAJoven() {
		return false;
	}

	/**
	 * Si hay un mayor se reemplaza por el joven
	 * @return true
	 */
	public boolean reemplazaAMayor() {
		return true;
	}

    @Override
    public String toString() {
        return "Joven: " +super.toString();
    }
	
}
