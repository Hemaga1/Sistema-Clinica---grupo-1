package honorarios;

public class EspecialidadClinica extends HonorarioDecorator {
    public EspecialidadClinica(IMedico honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.05;
	}

    @Override
    public String toString() {
        return " Especialidad Clinica" + super.toString();
    }
}