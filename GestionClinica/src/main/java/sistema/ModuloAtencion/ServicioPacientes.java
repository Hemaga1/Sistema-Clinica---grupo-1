package sistema.ModuloAtencion;

import excepciones.PacienteDuplicadoExcepcion;
import personas.Paciente;
import facturacion.RegistroPaciente;
import java.util.*;

public class ServicioPacientes {
    
    private final Set<Paciente> pacientesRegistrados = new HashSet<>();
    private final Map<Paciente, RegistroPaciente> pacientesAtendidos = new HashMap<>();
    
    public void registrarPaciente(Paciente paciente) throws PacienteDuplicadoExcepcion {
        if (pacientesRegistrados.contains(paciente)) {
            throw new PacienteDuplicadoExcepcion();
        }
        else pacientesRegistrados.add(paciente);
    }
    
    public boolean estaRegistrado(Paciente paciente) {
        return pacientesRegistrados.contains(paciente);
    }
    
    public void iniciarRegistroAtencion(Paciente paciente) {
        if (!pacientesAtendidos.containsKey(paciente)) {
            pacientesAtendidos.put(paciente, new RegistroPaciente());
        }
    }
    
    public RegistroPaciente getRegistroPaciente(Paciente paciente) {
        return pacientesAtendidos.get(paciente);
    }
    
    public void removerRegistroPaciente(Paciente paciente) {
        pacientesAtendidos.remove(paciente);
    }

    
    public boolean estaEnAtencion(Paciente paciente) {
        return pacientesAtendidos.containsKey(paciente);
    }
}
