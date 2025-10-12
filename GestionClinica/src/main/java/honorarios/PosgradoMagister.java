package honorarios;

import interfaces.IMedico;

/**
 * Clase para el calculo del honorario de Posgrado: Magister
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
		return super.calcularHonorarios() * 1.05;
	}

    @Override
    public String toString() {
        return " Posgrado Master" + super.toString();
    }
}
