package modelo.interfaces;

import java.nio.charset.Charset;

/**
 * Componente a ser envuelto luego, utilizada para patrón decorator
 */
public interface IMedico {
    public double calcularHonorarios();
    public String getDNI();
    public String getNombre();
    public String getApellido();
    public String getEspecialidad();
    public boolean equals(Object o);
    public int hashCode();

}


