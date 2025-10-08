package sistema.ModuloIngreso;

import excepciones.PacienteNoEstaEsperandoExcepcion;
import excepciones.SalaEsperaVaciaExcepcion;
import personas.Paciente;
import lugares.SalaDeEspera;

public class SistemaIngreso {

    private final SalaDeEspera salaDeEspera;
    
    public SistemaIngreso() {
        this.salaDeEspera = new SalaDeEspera();
    }
    
    public void ingresarPaciente(Paciente paciente) {
        salaDeEspera.ingresar(paciente);
    }

    public void SacarPaciente(Paciente paciente) throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion {
        salaDeEspera.sacarPaciente(paciente);
    }
    
    public Paciente SacarPaciente() throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion{
        return salaDeEspera.sacarPacienteConMenorOrden();
    }

    public SalaDeEspera getSalaDeEspera() {
        return salaDeEspera;
    }

}
