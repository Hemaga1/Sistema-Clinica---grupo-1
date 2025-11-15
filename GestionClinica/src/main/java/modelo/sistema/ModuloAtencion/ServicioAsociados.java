package modelo.sistema.ModuloAtencion;

import modelo.excepciones.AsociadoDuplicadoExcepcion;
import modelo.excepciones.AsociadoNoRegistradoExcepcion;
import modelo.personas.Asociado;

import java.util.HashSet;
import java.util.Set;

/**
 * Servicio encargado de gestionar el registro y eliminación de  Asociados.
 * Funciona como un módulo interno del sistema que mantiene un conjunto sin duplicados.
 */
public class ServicioAsociados {

    private final Set<Asociado> asociadosRegistrados = new HashSet<>();

    /**
     * Registra un asociado en el sistema.
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>Si el asociado no estaba registrado, queda agregado al conjunto.</li>
     *     <li>Si ya estaba registrado, se lanza una excepción y no se modifica el conjunto.</li>
     * </ul>
     *
     * @param asociado asociado a registrar, asociado !=null
     * @throws AsociadoDuplicadoExcepcion si el asociado ya existía en el sistema
     */
    public void registrarAsociado(Asociado asociado) throws AsociadoDuplicadoExcepcion {
        assert asociado != null : "El asociado no puede ser null";
        if (asociadosRegistrados.contains(asociado)) {
            throw new AsociadoDuplicadoExcepcion();
        }
        else asociadosRegistrados.add(asociado);
    }

    /**
     * Elimina un asociado del sistema.
     *
     * <b>Precondiciones:</b> asociado != null
     * <b>Postcondiciones:</b>
     * <ul>
     *     <li>Si el asociado estaba registrado, deja de estarlo.</li>
     *     <li>Si no estaba registrado, se lanza una excepcion.</li>
     * </ul>
     *
     * @param asociado asociado a eliminar, asociado != null
     * @throws AsociadoNoRegistradoExcepcion si el asociado no estaba en el sistema
     */
    public void eliminarAsociado(Asociado asociado) throws AsociadoNoRegistradoExcepcion {
        assert asociado != null : "El asociado no puede ser null";
        if (asociadosRegistrados.contains(asociado)) {
            asociadosRegistrados.remove(asociado);
            assert !asociadosRegistrados.contains(asociado) : "El asociado debería haber sido eliminado";
        }
        else throw new AsociadoNoRegistradoExcepcion();
    }

    /**
     * Devuelve el conjunto de asociados registrados.
     *
     * <b>Postcondición:</b> siempre devuelve una referencia válida.
     *
     * @return conjunto de asociados registrados
     */
    public Set<Asociado> getAsociadosRegistrados() {
        assert asociadosRegistrados != null : "El conjunto nunca debe ser null";
        return asociadosRegistrados;
    }
}
