package modelo.personas;

/**
 * Clase que extiende de paciente si es un mayor
 */
public class Mayor extends Paciente{

	public Mayor(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono,
			int historiaClinica) {
		super(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
	}

	/**
	 * Se verifica si se reemplaza el mayor que esta en la sala de espera privada segun el paciente nuevo que ingrese
	 * @return true or false
	 */
	public boolean esReemplazado(Paciente otroPaciente) {
		assert otroPaciente!=null : "El otro paciente no debe ser null al comparar con el mayor";
        return otroPaciente.reemplazaAMayor();
	}

	/**
	 * Si hay un niño se reemplaza por el mayor
	 * @return true
	 */
	public boolean reemplazaANinio() {
		return true;
	}

	/**
	 * Si hay un joven no se reemplaza por el mayor
	 * @return false
	 */
	public boolean reemplazaAJoven() {
		return false;
	}

	/**
	 * Si hay un mayor no se reemplaza por otro mayor
	 * @return false
	 */
	public boolean reemplazaAMayor() {
		return false;
	}

    @Override
    public String toString() {
        return "Mayor: " +super.toString();
    }
}
