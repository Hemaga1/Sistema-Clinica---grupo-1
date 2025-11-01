package modelo.honorarios;

import modelo.interfaces.IMedico;

/**
 * Clase para el cálculo del honorario de Posgrado: Doctorado
 */

public class PosgradoDoctorado extends HonorarioDecorator {
    public PosgradoDoctorado(IMedico honorario) {
		super(honorario);
	}

	/**
	 * Cuando posgrado es Doctorado, hay un 10% de aumento sobre el honorario que incluye la especialidad
	 * @return honorario con el aumento del posgrado
	 */
	@Override
	public double calcularHonorarios() {
		double honorarioConAumento = super.calcularHonorarios() * 1.1;

		assert honorarioConAumento >= 0 : "El honorario calculado no puede ser negativo";

		return honorarioConAumento;
	}

    @Override
    public String toString() {
        return " Posgrado Doctorado" + super.toString();
    }
}
