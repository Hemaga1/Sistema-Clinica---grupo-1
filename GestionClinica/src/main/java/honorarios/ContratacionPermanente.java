package honorarios;

public class ContratacionPermanente extends HonorarioDecorator {
    public ContratacionPermanente(IMedico honorario) {
		super(honorario);
	}
	
	@Override
	public double calcularHonorarios() {
		return super.calcularHonorarios() * 1.1;
	}

    @Override
    public String toString() {
        return " Contratacion Permanente" + super.toString();
    }
}
