package honorarios;

public class EspecialidadClinica extends HonorarioDecorator {
	public EspecialidadClinica(IHonorario honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.05;
	}
}