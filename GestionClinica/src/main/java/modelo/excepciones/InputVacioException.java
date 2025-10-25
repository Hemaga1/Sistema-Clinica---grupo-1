package modelo.excepciones;

public class InputVacioException extends Exception{
    public InputVacioException(){
        super("Llene todos los datos");
    }
}
