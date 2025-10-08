package excepciones;

public class PacienteNoEstaEsperandoExcepcion extends Exception {

    public PacienteNoEstaEsperandoExcepcion(){
        super("El paciente que desea atender no se encuentra en la sala de espera");
    }
}
