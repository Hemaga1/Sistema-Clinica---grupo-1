package honorarios;

import interfaces.IMedico;

/**
 * Clase para el calculo del honorario de especialidad: Clínica
 */

public class EspecialidadClinica extends HonorarioDecorator {
    public EspecialidadClinica(IMedico honorario) {
		super(honorario);
	}

	/**
	 * Cuando la especialidad es clínica, hay un 5% de aumento del valor base
	 * @return honorario con el aumento de la especialidad
	 */
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.05;
	}

    @Override
    public String toString() {
        return " Especialidad Clinica" + super.toString();
    }
}