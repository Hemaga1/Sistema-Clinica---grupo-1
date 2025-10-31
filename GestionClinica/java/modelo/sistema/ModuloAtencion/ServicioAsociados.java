package modelo.sistema.ModuloAtencion;

import modelo.excepciones.AsociadoDuplicadoExcepcion;
import modelo.excepciones.AsociadoNoRegistradoExcepcion;
import modelo.personas.Asociado;

import java.util.HashSet;
import java.util.Set;

public class ServicioAsociados {

    private final Set<Asociado> asociadosRegistrados = new HashSet<>();

    public void registrarAsociado(Asociado asociado) throws AsociadoDuplicadoExcepcion {
        if (asociadosRegistrados.contains(asociado)) {
            throw new AsociadoDuplicadoExcepcion();
        }
        else asociadosRegistrados.add(asociado);
    }

    public void eliminarAsociado(Asociado asociado) throws AsociadoNoRegistradoExcepcion {
        if (asociadosRegistrados.contains(asociado)) {
            asociadosRegistrados.remove(asociado);
        }
        else throw new AsociadoNoRegistradoExcepcion();
    }

    public Set<Asociado> getAsociadosRegistrados() {
        return asociadosRegistrados;
    }
}
