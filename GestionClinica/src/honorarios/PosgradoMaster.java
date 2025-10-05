package honorarios;

public class PosgradoMaster extends HonorarioDecorator {
	public PosgradoMaster(IHonorario honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.05;
	}

	@Override
	public String toString() {
		return " Posgrado Master" + super.toString();
	}
	
	
}
