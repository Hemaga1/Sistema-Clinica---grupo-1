package facturacion;

import honorarios.IMedico;

public class ConsultaMedica {
    private String nombreMedico;
    private double precio;
    private String especialidad;

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
}
