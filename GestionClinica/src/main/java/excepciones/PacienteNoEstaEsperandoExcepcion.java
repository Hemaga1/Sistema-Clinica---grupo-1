package excepciones;

/**
 * Cuando un paciente va a ser atendido, se saca de espera. Si al querer sacarlo no está tenemos esta excepción
 */

public class PacienteNoEstaEsperandoExcepcion extends Exception {

    public PacienteNoEstaEsperandoExcepcion(){
        super("El paciente que desea atender no se encuentra en la sala de espera");
    }
}
