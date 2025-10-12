package facturacion;

import honorarios.IMedico;

/**
 * Clase que especifica la consulta médica que tenga un paciente, para luego además ser agregada a todas las consultas médicas que pueda tener un paciente
 */

public class ConsultaMedica {
    private String nombreMedico;
    private double precio;
    private String especialidad;

    /**
     * Constructor de la consulta
     * @param medico
     * @param precio Precio de la consulta, precio>0
     */
    public ConsultaMedica(IMedico medico, double precio) {
        this.nombreMedico = medico.getNombre();
        this.especialidad = medico.getEspecialidad();
        this.precio = precio;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public double getPrecio() {
        return precio;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
