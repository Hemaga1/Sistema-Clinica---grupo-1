package facturacion;

import lugares.Habitacion;

import java.util.ArrayList;

public class RegistroPaciente {
    private Habitacion habitacion;
    private String fechaIngreso;
    private int cantDiasInternado;
    private ArrayList<ConsultaMedica> consultasMedicas = new ArrayList<>();

    public RegistroPaciente() {
        this.habitacion = null;
        this.fechaIngreso = "99/99/9999";
        this.cantDiasInternado = 0;
    }

    public ArrayList<ConsultaMedica> getConsultasMedicas(){
        return consultasMedicas;
    }

    public void agregarConsultaMedica(ConsultaMedica consultaMedica){
        consultasMedicas.add(consultaMedica);
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setCantDiasInternado(int cantDiasInternado) {
        this.cantDiasInternado = cantDiasInternado;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public int getCantDiasInternado() {
        return cantDiasInternado;
    }
}
