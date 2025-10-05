package honorarios;

//Clase abstracta --> Decorator
public abstract class HonorarioDecorator implements IHonorario {
	private IHonorario honorario;

	public HonorarioDecorator(IHonorario honorario) {
		this.honorario = honorario;
	}
	
	@Override
	public double calcularHonorarios() {
		return honorario.calcularHonorarios();
	}
}

