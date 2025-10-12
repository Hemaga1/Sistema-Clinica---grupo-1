package honorarios;
import java.util.ArrayList;

/**
 * Clase abstracta que implementa el patrón Decorator para agregar responsabilidades de cálculo de honorarios de manera dinámica.<br>
 * El patrón Decorator permite "envolver" un objeto IMedico base y añadirle nuevos cálculos sin modificar la estructura del objeto base.
 */
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

    public String getNombre(){
        return honorario.getNombre();
    }

    public String getEspecialidad(){
        return honorario.getEspecialidad();
    }

    @Override
    public String toString() {
        return this.honorario.toString();
    }
}

