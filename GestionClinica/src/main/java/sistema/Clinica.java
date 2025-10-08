package sistema;

public class Clinica {

    private String nombre;
    private String direccion;
    private String telefono;
    private String ciudad;

    // Instancia estática privada (única instancia)
    private static Clinica instancia;

    private Clinica(String nombre, String direccion, String telefono, String ciudad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }
    public static Clinica getInstancia(String nombre, String direccion, String telefono, String ciudad) {
        if (instancia == null) {
            instancia = new Clinica(nombre, direccion, telefono, ciudad);
        }
        return instancia;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    @Override
    public String toString() {
        return "Clinica nombre: " + nombre + " direccion: " + direccion + " telefono: " + telefono + " ciudad: " + ciudad;
    }
}