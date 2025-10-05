package honorarios;

public class ContratacionPermanente extends HonorarioDecorator {
	public ContratacionPermanente(IHonorario honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.1;
	}
}
