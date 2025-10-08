package lugares;

import excepciones.PacienteNoEstaEsperandoExcepcion;
import excepciones.SalaEsperaVaciaExcepcion;
import personas.Paciente;

public class SalaDeEspera {
    Patio patio;
    SalaDeEsperaPrivada salaPrivada;

    public SalaDeEspera(){
        this.patio = new Patio();
        this.salaPrivada = new SalaDeEsperaPrivada();
    }

    public void ingresar(Paciente paciente) {
        Paciente pacienteSalaPrivada = salaPrivada.getPaciente();
        if (!salaPrivada.Ocupado()){
            salaPrivada.setPaciente(paciente);
        }else{
            if (paciente.reemplaza(pacienteSalaPrivada)){
                patio.agregarPaciente(pacienteSalaPrivada);
                salaPrivada.setPaciente(paciente);
            }else{
                patio.agregarPaciente(paciente);
            }
        }
    }

    public void sacarPaciente(Paciente paciente) throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion {
        if (this.salaPrivada.Ocupado() && this.salaPrivada.getPaciente().equals(paciente)){
            this.salaPrivada.setPaciente(null);
        }
        else {
            this.patio.sacarPaciente(paciente);
        }
    }

    public Paciente sacarPacienteConMenorOrden() throws SalaEsperaVaciaExcepcion, PacienteNoEstaEsperandoExcepcion{
        Paciente candidato = null;
        // Considerar sala privada
        if (this.salaPrivada.Ocupado()) {
            candidato = this.salaPrivada.getPaciente();
        }
        // Considerar patio
        for (Paciente p : this.patio.getPacientes()) {
            if (candidato == null || p.getNroOrden() < candidato.getNroOrden()) {
                candidato = p;
            }
        }
        if (candidato != null) {
            this.sacarPaciente(candidato);
        }
        return candidato;
    }
}