package honorarios;

public class ContratacionResidente extends HonorarioDecorator {
    public ContratacionResidente(IMedico honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.1;
	}

    @Override
    public String toString() {
        return " Contratacion Residente" + super.toString();
    }
}
