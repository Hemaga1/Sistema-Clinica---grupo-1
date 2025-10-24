package modelo.sistema;

import modelo.personas.Domicilio;

/**
 * Representa la entidad principal del modelo.sistema: la clínica.<br>
 * Implementa el patrón Singleton para garantizar que solo exista una instancia de la clínica durante toda la ejecución del programa.
 */
public class Clinica {
    private String nombre;
    private Domicilio direccion;
    private String telefono;

    private static Clinica instancia;

    /**
     * Constructor privado para impedir la creacion de un objeto fuera de la clase
     * @param nombre El nombre de la clínica, nombre != null
     * @param calle La calle que se incluirá en el domicilio de la clínica, calle != null
     * @param numero Numero de domicilio, domicilio >= 0
     * @param telefono El teléfono de la clínica, teléfono != null
     * @param ciudad Ciudad donde se encuentra ubicada la clínica, clínica != null
     */
    private Clinica(String nombre, String calle, int numero, String telefono, String ciudad) {
        this.nombre = nombre;
        this.direccion = new Domicilio(calle,numero,ciudad);
        this.telefono = telefono;
    }

    /**
     * Metodo público estático que retorna la única instancia existente, creándola en el caso de que no exista.
     * @param nombre El nombre de la clínica, nombre != null
     * @param calle La calle que se incluirá en el domicilio de la clínica, calle != null
     * @param numero Numero de domicilio, domicilio >= 0
     * @param telefono El teléfono de la clínica, teléfono != null
     * @param ciudad Ciudad donde se encuentra ubicada la clínica, clínica != null
     * @return la unica instancia de Clinica
     */
    public static Clinica getInstancia(String nombre, String calle, int numero,  String telefono, String ciudad) {
        if (instancia == null) {
            instancia = new Clinica(nombre, calle, numero, telefono, ciudad);
        }
        return instancia;
    }

    @Override
    public String toString() {
        return "Clinica nombre: " + nombre + " direccion: " + direccion + " telefono: " + telefono;
    }
}