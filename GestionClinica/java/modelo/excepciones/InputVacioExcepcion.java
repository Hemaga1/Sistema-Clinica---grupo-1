package modelo.excepciones;

public class InputVacioExcepcion extends Exception{
    public InputVacioExcepcion(){
        super("Llene todos los datos");
    }
}
