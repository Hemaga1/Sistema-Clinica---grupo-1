package honorarios;

public class PosgradoDoctorado extends HonorarioDecorator {
    public PosgradoDoctorado(IMedico honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.1;
	}

    @Override
    public String toString() {
        return " Posgrado Doctorado" + super.toString();
    }
}
