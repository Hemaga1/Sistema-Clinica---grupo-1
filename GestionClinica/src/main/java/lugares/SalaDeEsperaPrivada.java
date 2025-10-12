package lugares;

import personas.Paciente;

/**
 * Sala de espera proivada, unicamente puede haber un paciente
 */
public class SalaDeEsperaPrivada {

    private Paciente paciente;

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public boolean Ocupado() {
        return paciente != null;
    }

}

