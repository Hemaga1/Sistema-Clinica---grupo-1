package modelo.honorarios;
import modelo.interfaces.IMedico;
import modelo.personas.Medico;

/**
 * Clase abstracta que implementa el patrón Decorator para agregar responsabilidades de cálculo de modelo.honorarios de manera dinámica.<br>
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

    public String getDNI(){
        return honorario.getDNI();
    }

    public String getNombre(){
        return honorario.getNombre();
    }

    public String getApellido(){
        return honorario.getApellido();
    }

    public String getEspecialidad(){
        return honorario.getEspecialidad();
    }

    @Override
    public String toString() {
        return this.honorario.toString();
    }

    public boolean equals(Object o) {
        return honorario.equals(o);
    };

    public int hashCode(){
        return honorario.hashCode();
    };
}

