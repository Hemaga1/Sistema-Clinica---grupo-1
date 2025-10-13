package personas;

import interfaces.IMedico;

/**
 * Clase extendida de Persona, correspondiente al médico
 */
public class Medico extends Persona implements IMedico {
	private String matricula;
    private String especialidad;

    /**
     * Constructor del médico, se utiliza el constructor de persona y se añade la matrícula y su especialidad
     * @param DNI dni!=null, dni!=""
     * @param nombre nombre!=null, nombre!=""
     * @param apellido apellido!=null, apellido!=""
     * @param calle calle!=null, calle!=""
     * @param numero numero>=o
     * @param ciudad ciudad!=null, cidudad!=""
     * @param telefono telefono!=null, telefono !=""
     * @param matricula matricula!=null, matricula !=""
     * @param especialidad especialidad!=null, especialidad !=""
     */
	public Medico(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono, String matricula, String especialidad) {
		super(DNI, nombre, apellido, calle, numero, ciudad, telefono);
		this.matricula = matricula;
        this.especialidad = especialidad;
	}

    /**
     * Se retorna la especialidad de un médico
     * @return string de la especialidad
     */
    public String getEspecialidad(){
        return this.especialidad;
    }

    /**
     * Método para comenzar a calcular honorarios
     * @return valor base de los honorarios: 20000
     */
	public double calcularHonorarios() {
		return 20000;
	}

    @Override
    public String toString() {
        return " Medico matricula: " + matricula + super.toString();
    }
	
}
