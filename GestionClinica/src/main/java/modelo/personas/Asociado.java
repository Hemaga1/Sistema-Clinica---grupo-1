package modelo.personas;

import modelo.ambulancia.Ambulancia;
import modelo.observadores_y_observables.ObservableAsociado;

import java.util.Objects;
import java.util.Random;

/**
 * Representa a un asociado que puede solicitar una ambulancia.
 * Extiende Persona e implementa Runnable para permitir que sus solicitudes se ejecuten en un hilo.
 */
public class Asociado extends Persona implements Runnable {
    private ObservableAsociado observableAsociado;

    /**
     * Crea un nuevo asociado con la información personal indicada.
     *
     * <b>Precondiciones:</b>
     * <ul>
     *   <li>Ningún parámetro debe ser null.</li>
     *   <li>Los valores numéricos deben ser válidos.</li>
     * </ul>
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>El asociado queda correctamente inicializado.</li>
     *   <li>Se crea un observable asociado a esta persona.</li>
     * </ul>
     */
    public Asociado(String DNI, String nombre, String apellido, String calle, int numero, String ciudad, String telefono) {
        super(DNI, nombre, apellido, calle, numero, ciudad, telefono);
        assert DNI!=null && DNI!="": "El dni de la persona, ya sea medico o paciente no debe ser null ni estar vacío";
        assert nombre!=null && nombre!="": "El nombre de la persona, ya sea medico o paciente no debe ser null ni estar vacío";
        assert apellido!=null && apellido!="": "El apellido de la persona, ya sea medico o paciente no debe ser null ni estar vacío";
        assert calle!=null && calle!="": "La calle del domicilio de la persona, ya sea medico o paciente no debe ser null ni estar vacía";
        assert numero>=0 : "El numero del domicilio no puede ser negativo";
        assert ciudad!=null && ciudad!="": "La ciudad del domicilio de la persona, ya sea medico o paciente no debe ser null ni estar vacía";
        assert telefono!=null && telefono!="": "El telefono de la persona, ya sea medico o paciente no debe ser null ni estar vacío";
        observableAsociado = new ObservableAsociado(nombre, apellido, DNI);
    }

    /**
     * Devuelve el observable del asociado.
     *
     * @return observable asociado a este asociado
     */
    public ObservableAsociado getObservableAsociado() {
        return observableAsociado;
    }

    /**
     * El asociado solicita una ambulancia. Puede pedir atención domiciliaria o un traslado
     *
     * <b>Postcondiciones:</b>
     * <ul>
     *   <li>Se notifica el tipo de solicitud al observable.</li>
     *   <li>Se invoca el método correspondiente en Ambulancia.</li>
     * </ul>
     */
    public void solicitaAmbulancia() {
        Random r = new Random();
        boolean domicilio = r.nextBoolean();
        String tipo = domicilio ? "Atencion domiciliaria" : "Traslado";
        observableAsociado.avisarCambio(this.getNombre() + " (" + this.getDNI() + ") solicita: " + tipo);
        if (domicilio) {
            Ambulancia.get_instance().solicitaAtencionDomicilio();
        } else {
            Ambulancia.get_instance().solicitaTraslado();
        }
    }

    /**
     * Notifica que el asociado está esperando atención.
     */
    public void notificarEspera() {
        observableAsociado.avisarCambio(this.getNombre() + " (" + this.getDNI() + ") esperando");
    }

    /**
     * Notifica que el asociado fue atendido.
     */
    public void notificarFueAtendido() {
        observableAsociado.avisarCambio(this.getNombre() + " (" + this.getDNI() + ") ha sido atendido");
    }

    /**
     * Dos asociados son iguales si tienen el mismo DNI.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asociado asociado = (Asociado) o;
        return Objects.equals(this.getDNI(), asociado.getDNI());
    }

    /**
     * Ejecuta la acción del asociado cuando corre como hilo.
     *
     * <b>Postcondición:</b> se realiza una solicitud de ambulancia.
     */
    @Override
    public void run() {
        this.solicitaAmbulancia();
    }

}
