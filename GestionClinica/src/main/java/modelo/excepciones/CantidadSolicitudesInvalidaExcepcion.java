package modelo.excepciones;

public class CantidadSolicitudesInvalidaExcepcion extends Exception{
    public CantidadSolicitudesInvalidaExcepcion() {
        super("El numero de solicitudes ingresado es invalido");
    }
}
