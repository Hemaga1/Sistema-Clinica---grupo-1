package modelo.excepciones;

/**
 * Excepción que se lanza cuando ocurre un error en la capa de persistencia.
 * Encapsula errores de base de datos (SQLException) para que no se propaguen
 * directamente a las capas superiores del sistema.
 */
public class ErrorPersistenciaExcepcion extends Exception {
    
    /**
     * Constructor que crea una excepción con un mensaje descriptivo.
     * 
     * @param mensaje mensaje que describe el error de persistencia
     */
    public ErrorPersistenciaExcepcion(String mensaje) {
        super(mensaje);
    }
    
    /**
     * Constructor que crea una excepción con un mensaje y la causa original.
     * 
     * @param mensaje mensaje que describe el error de persistencia
     * @param causa la excepción original que causó este error
     */
    public ErrorPersistenciaExcepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

