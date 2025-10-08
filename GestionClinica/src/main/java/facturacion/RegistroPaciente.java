package facturacion;

import lugares.Habitacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistroPaciente {
    private Habitacion habitacion;
    private String fechaIngreso;
    private int cantDiasInternado;
    private ArrayList<ConsultaMedica> consultasMedicas = new ArrayList<>();

    public RegistroPaciente() {
        this.habitacion = null;
        this.fechaIngreso = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
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
