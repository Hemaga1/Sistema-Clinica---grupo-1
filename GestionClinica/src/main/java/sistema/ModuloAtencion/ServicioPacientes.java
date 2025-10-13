package sistema.ModuloAtencion;

import excepciones.PacienteDuplicadoExcepcion;
import personas.Paciente;
import facturacion.RegistroPaciente;
import java.util.*;

/**
 * Clase que contiene operaciones del paciente
 */
public class ServicioPacientes {
    
    private final Set<Paciente> pacientesRegistrados = new HashSet<>();
    private final Map<Paciente, RegistroPaciente> pacientesAtendidos = new HashMap<>();

    /**
     * Registra un paciente<br>
     * <b>Precondición: </b> El paciente no debe haberse registrado anteriormente<br>
     * <b>Postcondición:</b>
     * <ul>
     *  <li>Si el paciente estaba registrado no se registra nuevamente y se lanza una excepción</li>
     *  <li>Si el paciente no estaba registrado se procede a registrarlo.</li>
     * </ul>
     *
     * @param paciente El objeto paciente a registrar, paciente != null
     * @throws PacienteDuplicadoExcepcion
     */
    public void registrarPaciente(Paciente paciente) throws PacienteDuplicadoExcepcion {
        if (pacientesRegistrados.contains(paciente)) {
            throw new PacienteDuplicadoExcepcion();
        }
        else pacientesRegistrados.add(paciente);
    }

    /**
     * Verifica si el paciente esta registrado o no
     * @param paciente paciente!=null
     * @return true or false
     */
    public boolean estaRegistrado(Paciente paciente) {
        return pacientesRegistrados.contains(paciente);
    }

    /**
     * Se atiende a un paciente, se ingresa a la lista de atendidos y se crea su registro
     * @param paciente Paciente a ser atendido, paciente != null
     */
    public void iniciarRegistroAtencion(Paciente paciente) {
        if (!pacientesAtendidos.containsKey(paciente)) {
            pacientesAtendidos.put(paciente, new RegistroPaciente());
        }
    }

    /**
     * Se obtiene el registro del paciente
     * @param paciente Paciente del cual se quiere obtener el registro, paciente!=null
     * @return registro del paciente indicado
     */
    public RegistroPaciente getRegistroPaciente(Paciente paciente) {
        return pacientesAtendidos.get(paciente);
    }

    /**
     * El paciente egresa entonces se lo elimina de la lista de atendidos
     * @param paciente Paciente que egresa, paciente != null
     */
    public void removerRegistroPaciente(Paciente paciente) {
        pacientesAtendidos.remove(paciente);
    }

    /**
     * Verifica si el paciente esta en atención
     * @param paciente Paciente del cual queremos saber si esta siendo atendido, paciente!=null
     * @return true or false
     */
    public boolean estaEnAtencion(Paciente paciente) {
        return pacientesAtendidos.containsKey(paciente);
    }
}
