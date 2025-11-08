package persistencia;

public class AsociadoDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String calle;
    private int numero;
    private String ciudad;


    public AsociadoDTO(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = DNI;
        this.telefono = telefono;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;  
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
