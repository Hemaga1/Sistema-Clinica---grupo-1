package honorarios;
import java.util.ArrayList;

import facturacion.Reporte;


//Clase abstracta --> Decorator
public abstract class HonorarioDecorator implements IMedico {
    private IMedico honorario;

    public HonorarioDecorator(IMedico honorario) {
		this.honorario = honorario;
	}
	
	@Override
	public double calcularHonorarios() {
		return honorario.calcularHonorarios();
	}

    @Override
    public String toString() {
        return this.honorario.toString();
    }

    @Override
    public void agregarAtendido(Reporte atendido){
        honorario.agregarAtendido(atendido);
    }

    @Override
    public ArrayList<Reporte> getAtendidos(){
        return honorario.getAtendidos();
    }
}

