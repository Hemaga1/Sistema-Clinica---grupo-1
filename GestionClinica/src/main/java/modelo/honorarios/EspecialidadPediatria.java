package modelo.honorarios;

import modelo.interfaces.IMedico;

/**
 * Clase para el caálculo del honorario de especialidad: Pediatría
 */

public class EspecialidadPediatria extends HonorarioDecorator {
    public EspecialidadPediatria(IMedico honorario) {
		super(honorario);
	}

	/**
	 * Cuando la especialidad es Pediatría, hay un 7% de aumento del valor base
	 * @return honorario con el aumento de la especialidad
	 */
	@Override
	public double calcularHonorarios() {
		double honorarioConAumento = super.calcularHonorarios() * 1.07;

		assert honorarioConAumento >= 0 : "El honorario calculado no puede ser negativo";

		return honorarioConAumento;	}

    @Override
    public String toString() {
        return " Especialidad Pediatria" + super.toString();
    }
}
