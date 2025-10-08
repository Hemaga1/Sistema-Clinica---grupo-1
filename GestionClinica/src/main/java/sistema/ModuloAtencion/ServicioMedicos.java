package sistema.ModuloAtencion;

import excepciones.MedicoDuplicadoExcepcion;
import honorarios.IMedico;
import facturacion.PacienteAtendido;
import java.util.*;

public class ServicioMedicos {
    
    private final Map<IMedico, List<PacienteAtendido>> medicos = new HashMap<>();
    
    public void registrarMedico(IMedico medico) throws MedicoDuplicadoExcepcion {
        if (medicos.get(medico) != null) {
            throw new MedicoDuplicadoExcepcion();
        }
        else medicos.put(medico, new ArrayList<>());
    }

    public boolean estaRegistrado(IMedico medico) {
        return medicos.containsKey(medico);
    }
    
    public void removerMedico(IMedico medico) {
        medicos.remove(medico);
    }
    
    public void agregarAtencion(IMedico medico, PacienteAtendido atencion) {
        medicos.get(medico).add(atencion);
    }
    
//    public List<PacienteAtendido> getAtencionesDelMedico(IMedico medico) {
//        return medicos.getOrDefault(medico);
//    }
//
    public int getCantidadPacientesAtendidos(IMedico medico) {
        List<PacienteAtendido> atenciones = medicos.get(medico);
        return atenciones == null ? 0 : atenciones.size();
    }
    
    public double getHonorariosTotalesMedico(IMedico medico) {
        List<PacienteAtendido> atenciones = medicos.get(medico);
        if (atenciones == null) {
            return 0.0;
        }
        return atenciones.stream()
                .mapToDouble(PacienteAtendido::getHonorario)
                .sum();
    }

    public Set<IMedico> getMedicosRegistrados() {
        return medicos.keySet();
    }
    
}
