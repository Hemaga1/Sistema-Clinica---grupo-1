package modelo.honorarios;

import modelo.interfaces.IMedico;

/**
 * Clase para el cálculo del honorario de especialidad: Clínica
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
		double honorarioConAumento = super.calcularHonorarios() * 1.05;

		assert honorarioConAumento >= 0 : "El honorario calculado no puede ser negativo";

		return honorarioConAumento;
	}

    @Override
    public String toString() {
        return " Especialidad Clinica" + super.toString();
    }
}