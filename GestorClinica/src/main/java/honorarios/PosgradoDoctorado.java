package honorarios;

public class PosgradoDoctorado extends HonorarioDecorator {
	public PosgradoDoctorado(IHonorario honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.1;
	}
}
