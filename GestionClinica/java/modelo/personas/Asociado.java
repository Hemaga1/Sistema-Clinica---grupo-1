package modelo.personas;

import modelo.ambulancia.Ambulancia;

import java.util.Objects;

public class Asociado extends Persona {

    private int cantSolicitudes;
    private Ambulancia ambulancia;

    public Asociado(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono) {
        super(DNI, nombre, apellido, calle, numero, ciudad, telefono);
        this.cantSolicitudes = 0;
        this.ambulancia = Ambulancia.get_instance();
    }


    public void solicitaAmbulancia() {
        ambulancia.solicitaAtencionDomicilio();
        cantSolicitudes++;
    }

    public int getCantSolicitudes() {
        return cantSolicitudes;
    }

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asociado asociado = (Asociado) o;
        return Objects.equals(this.getDNI(), asociado.getDNI());
    }


}
