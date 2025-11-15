package modelo.excepciones;
/**
 * Excepción lanzada cuando un campo que debe contener únicamente números recibe caracteres no válidos.
 */
public class InputNumeroInvalidoExcepcion extends Exception{
    public InputNumeroInvalidoExcepcion() {
        super("El DNI, Numero de Paciente, Telefono e Historia Clinica deben contener solo numeros ");
    }
}
