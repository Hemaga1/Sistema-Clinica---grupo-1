package modelo.excepciones;

public class AsociadoNoRegistradoExcepcion extends Exception {
    public AsociadoNoRegistradoExcepcion() {
        super("El asociado que intenta eliminar no existe");
    }
}
