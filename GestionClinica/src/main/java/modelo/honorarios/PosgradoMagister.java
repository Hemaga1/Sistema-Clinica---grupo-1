package modelo.honorarios;

import modelo.interfaces.IMedico;

/**
 * Clase para el cálculo del honorario de Posgrado: Magister
 */

public class PosgradoMagister extends HonorarioDecorator {
    public PosgradoMagister(IMedico honorario) {
		super(honorario);
	}

	/**
	 * Cuando posgrado es Magister, hay un 5% de aumento sobre el honorario que incluye la especialidad
	 * @return honorario con el aumento del posgrado
	 */
	@Override
	public double calcularHonorarios() {
		double honorarioConAumento = super.calcularHonorarios() * 1.05;

		assert honorarioConAumento >= 0 : "El honorario calculado no puede ser negativo";

		return honorarioConAumento;
	}

    @Override
    public String toString() {
        return " Posgrado Master" + super.toString();
    }
}
