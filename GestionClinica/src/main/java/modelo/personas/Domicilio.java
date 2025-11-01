package modelo.personas;

public class Domicilio {
    private String calle;
    private int numero;
    private String ciudad;

    /**
     * Constructor para una dirección, utilizado para la clínica, pacientes y médicos
     * @param calle calle!=null, calle!=""
     * @param numero numero>=o
     * @param ciudad ciudad!=null, cidudad!=""
     */
    public Domicilio(String calle, int numero, String ciudad) {
        assert calle!= null && calle!="" : "La calle no puede ser null ni tampoco estar vacía";
        assert numero>=0 : "El numero de domicilio no puede ser negativo";
        assert ciudad!= null && ciudad!="" : "La ciudad no puede ser null ni tampoco estar vacía";
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
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

    @Override
    public String toString() {
        return calle + " " + numero + ", " + ciudad;
    }
}




