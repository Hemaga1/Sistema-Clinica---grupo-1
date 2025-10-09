package personas;

public class Joven extends Paciente{

	public Joven(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono,
			int historiaClinica) {
		super(DNI, nombre, apellido, calle, numero, ciudad, telefono, historiaClinica);
	}
	
	public boolean reemplaza(Paciente otroPaciente) {
		return otroPaciente.reemplazaAJoven();
	}
	
	public boolean reemplazaANinio() {
		return false;
	}
	
	public boolean reemplazaAJoven() {
		return false;
	}
	
	public boolean reemplazaAMayor() {
		return true;
	}

    @Override
    public String toString() {
        return "Joven: " +super.toString();
    }
	
}
