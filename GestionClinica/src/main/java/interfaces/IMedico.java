package interfaces;

/**
 * Componente a ser envuelto luego, utilizada para patrón decorator
 */
public interface IMedico {
    public double calcularHonorarios();
    public String getNombre();
    public String getEspecialidad();
}


