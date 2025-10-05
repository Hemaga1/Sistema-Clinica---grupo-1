package honorarios;

public class ContratacionResidente extends HonorarioDecorator {
	public ContratacionResidente(IHonorario honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.1;
	}
}
