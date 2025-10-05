package honorarios;

public class EspecialidadCirugia extends HonorarioDecorator {
	public EspecialidadCirugia(IHonorario honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.1;
	}
}
