package modelo.honorarios;

import modelo.interfaces.IMedico;

/**
 * Clase para el calculo del honorario de contratación: Residente
 */

public class ContratacionResidente extends HonorarioDecorator {
    public ContratacionResidente(IMedico honorario) {
		super(honorario);
	}

	/**
	 * Cuando contratacion es Residente, hay un 5% de aumento sobre el honorario que incluye la especialidad y el posgrado
	 * @return honorario con el aumento de la contratación
	 */
	@Override
	public double calcularHonorarios() {
		double honorarioConAumento = super.calcularHonorarios() * 1.05;

		assert honorarioConAumento >= 0 : "El honorario calculado no puede ser negativo";

		return honorarioConAumento;
	}

    @Override
    public String toString() {
        return " Contratacion Residente" + super.toString();
    }
}
