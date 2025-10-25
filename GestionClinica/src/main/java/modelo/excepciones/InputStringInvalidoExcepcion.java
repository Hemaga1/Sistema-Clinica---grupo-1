package modelo.excepciones;

public class InputStringInvalidoExcepcion extends Exception{
    public InputStringInvalidoExcepcion(){
        super("Nombre, Apellido y Ciudad deben contener solamente letras");
    }
}
