package modelo.excepciones;

public class InputNumeroInvalidoExcepcion extends Exception{
    public InputNumeroInvalidoExcepcion() {
        super("El DNI, Numero de Paciente, Telefono e Historia Clinica deben contener solo numeros ");
    }
}
