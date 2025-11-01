package modelo.honorarios;

import modelo.interfaces.IMedico;

/**
 * Clase para el calculo del honorario de Contratación: Permanente
 */

public class ContratacionPermanente extends HonorarioDecorator {
    public ContratacionPermanente(IMedico honorario) {
		super(honorario);
	}

	/**
	 * Cuando contratacion es Permanente, hay un 10% de aumento sobre el honorario que incluye la especialidad y el posgrado
	 * @return honorario con el aumento de la contratación
	 */
	@Override
	public double calcularHonorarios() {
		double honorarioConAumento = super.calcularHonorarios() * 1.1;

		assert honorarioConAumento >= 0 : "El honorario calculado no puede ser negativo";

		return honorarioConAumento;
	}

    @Override
    public String toString() {
        return " Contratacion Permanente" + super.toString();
    }
}
