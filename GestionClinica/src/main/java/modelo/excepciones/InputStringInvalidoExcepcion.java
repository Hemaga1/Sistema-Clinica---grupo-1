package modelo.excepciones;
/**
 * Excepción lanzada cuando un campo que debe contener solo letras
 * recibe números u otros caracteres inválidos.
 */
public class InputStringInvalidoExcepcion extends Exception{
    public InputStringInvalidoExcepcion(){
        super("Nombre, Apellido y Ciudad deben contener solamente letras");
    }
}
