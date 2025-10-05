package honorarios;

//Clase abstracta --> Decorator
public abstract class HonorarioDecorator implements IHonorario {
	private IHonorario honorario;

	public HonorarioDecorator(IHonorario honorario) {
		this.honorario = honorario;
	}
	
	@Override
	public double calcularHonorarios() {
		return this.honorario.calcularHonorarios();
	}

	@Override
	public String toString() {
		return this.honorario.toString();
	}
	
	
}

