package honorarios;

import interfaces.IMedico;

/**
 * Clase para el cálculo del honorario de especialidad Cirugía
 */

public class EspecialidadCirugia extends HonorarioDecorator {
    public EspecialidadCirugia(IMedico honorario) {
		super(honorario);
	}

    /**
     * Cuando la especialidad es Cirugía, hay un 10% de aumento del valor base
     * @return honorario con el aumento de la especialidad
     */
    @Override
    public double calcularHonorarios() {
        return super.calcularHonorarios() * 1.1;
    }

    @Override
    public String toString() {
        return " Especialidad Cirugia" + super.toString();
    }
}
