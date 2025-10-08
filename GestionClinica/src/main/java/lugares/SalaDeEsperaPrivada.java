package lugares;

import personas.Paciente;

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

