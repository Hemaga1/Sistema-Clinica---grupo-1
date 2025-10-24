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
		return super.calcularHonorarios() * 1.1;
	}

    @Override
    public String toString() {
        return " Contratacion Permanente" + super.toString();
    }
}
