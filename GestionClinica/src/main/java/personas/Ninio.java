package personas;

/**
 * Clase que extiende de paciente si es un niño
 */
public class Ninio extends Paciente{

	public Ninio(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono,
			int historiaClinica) {
		super(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
	}

	/**
	 * Segun los rangos etarios se evalua si será reemplazado para ingresar a la sala de espera privada o si va al patio
	 * @param otroPaciente
	 * @return true or false
	 */
	public boolean esReemplazado(Paciente otroPaciente) {
		return otroPaciente.reemplazaANinio();
	}

	/**
	 * Si hay un niño no se reemplaza por otro niño
	 * @return false
	 */
	public boolean reemplazaANinio() {
		return false;
	}

	/**
	 * Si hay un joven, se reemplaza por el niño
	 * @return true
	 */
	public boolean reemplazaAJoven() {
		return true;
	}

	/**
	 * Si hay un mayor, no se reemplaza por un niño
	 * @return false
	 */
	public boolean reemplazaAMayor() {
		return false;
	}

    @Override
    public String toString() {
        return "Ninio: " +super.toString();
    }
}
