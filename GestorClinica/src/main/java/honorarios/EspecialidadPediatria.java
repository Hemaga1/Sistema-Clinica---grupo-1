package honorarios;

public class EspecialidadPediatria extends HonorarioDecorator {
	public EspecialidadPediatria(IHonorario honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.07;
	}
}
