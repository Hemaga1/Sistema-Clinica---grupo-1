package lugares;

import personas.Paciente;

/**
 * Sala de espera privada, unicamente puede haber un paciente
 */
public class SalaDeEsperaPrivada {

    private Paciente paciente;

    /**
     * Se setea el paciente, ya sea porque la sala estaba vacía o por un reemplazo
     * @param paciente Paciente a ingresar en la sala de espera privada, paciente!=null
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Se verifica si la sala de espera privada esta ocupada o vacía
     * @return true or false
     */
    public boolean Ocupado() {
        return paciente != null;
    }

}

