package modelo.excepciones;

public class ImputVacioException extends Exception{
    public ImputVacioException(){
        super("Llene todos los datos");
    }
}
