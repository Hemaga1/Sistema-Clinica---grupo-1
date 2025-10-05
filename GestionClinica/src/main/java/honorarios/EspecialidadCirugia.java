package honorarios;

public class EspecialidadCirugia extends HonorarioDecorator {
    public EspecialidadCirugia(IMedico honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.1;
	}

    @Override
    public String toString() {
        return " Especialidad Cirugia" + super.toString();
    }
}
