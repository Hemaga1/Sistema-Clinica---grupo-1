package honorarios;

/**
 * Clase para el calculo del honorario de especialidad: Pediatría
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
		return super.calcularHonorarios() * 1.07;
	}

    @Override
    public String toString() {
        return " Especialidad Pediatria" + super.toString();
    }
}
