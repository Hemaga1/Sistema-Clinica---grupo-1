package honorarios;

/**
 * Clase para el calculo del honorario de Posgrado: Doctorado
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
		return super.calcularHonorarios() * 1.1;
	}

    @Override
    public String toString() {
        return " Posgrado Doctorado" + super.toString();
    }
}
