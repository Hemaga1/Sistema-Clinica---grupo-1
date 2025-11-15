package modelo.excepciones;

/**
 * Excepción lanzada cuando alguno de los campos de entrada
 * requeridos por el sistema se encuentra vacío.
 */
public class ImputVacioException extends Exception{
    public ImputVacioException(){
        super("Llene todos los datos");
    }
}
