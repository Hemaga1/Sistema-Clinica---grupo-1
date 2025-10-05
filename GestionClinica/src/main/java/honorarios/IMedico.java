package honorarios;
import java.util.ArrayList;

import facturacion.Reporte;

//interface Medico
public interface IMedico {
    public double calcularHonorarios();
    public void agregarAtendido(Reporte atendido);
    public ArrayList<Reporte> getAtendidos();
}


