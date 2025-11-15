package modelo.excepciones;
/**
 * Excepción lanzada cuando uno o varios campos obligatorios
 * fueron dejados vacíos por el usuario.
 */
public class InputVacioExcepcion extends Exception{
    public InputVacioExcepcion(){
        super("Llene todos los datos");
    }
}
