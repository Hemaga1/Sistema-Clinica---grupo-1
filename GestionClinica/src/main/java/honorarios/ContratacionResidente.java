package honorarios;

import interfaces.IMedico;

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
		return super.calcularHonorarios() * 1.1;
	}

    @Override
    public String toString() {
        return " Contratacion Residente" + super.toString();
    }
}
