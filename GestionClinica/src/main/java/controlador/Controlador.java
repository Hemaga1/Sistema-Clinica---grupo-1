package controlador;

import modelo.excepciones.ImputVacioException;
import modelo.factoria.FactoryMedico;
import modelo.factoria.FactoryPaciente;
import modelo.interfaces.IMedico;
import modelo.personas.Medico;
import modelo.personas.Paciente;
import modelo.sistema.SistemaFacade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private IVista vista;
    private SistemaFacade  sistema;

    FactoryMedico factoryMedico = new FactoryMedico();
    FactoryPaciente factoryPaciente = new FactoryPaciente();



    public Controlador(IVista vista, SistemaFacade sistema)
    {
        this.sistema = sistema;
        this.vista = vista;
        this.vista.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        String comando = e.getActionCommand();
        try {
            if (comando.equalsIgnoreCase(IVista.ENVIARPACIENTE)) {
                Paciente p = factoryPaciente.crearPaciente(this.vista.getDNIPaciente(), this.vista.getNombrePaciente(), this.vista.getApellidoPaciente(), this.vista.getCallePaciente(), this.vista.getNumPaciente(), this.vista.getTelefonoPaciente(), this.vista.getCiudadPaciente(), this.vista.getHistoriaPaciente(), this.vista.getRangoEtarioPaciente());
                this.vista.ocultarMensajeExcepcionPaciente();
                this.sistema.registraPaciente(p);
                this.vista.mostrarPacientesRegistrados(this.sistema.getPacientesRegistrados());
            }
            else if (comando.equalsIgnoreCase(IVista.ENVIARMEDICO)) {
                    IMedico m = factoryMedico.crearMedico(this.vista.getDNIMedico(), this.vista.getNombreMedico(), this.vista.getApellidoMedico(), this.vista.getCalleMedico(), this.vista.getNumMedico(), this.vista.getTelefonoMedico(), this.vista.getCiudadMedico(), this.vista.getMatriculaMedico(), this.vista.getEspecialidadMedico(), this.vista.getContratacionMedico(), this.vista.getPosgradoMedico());
                    this.vista.ocultarMensajeExcepcionMedico();
                    this.sistema.registraMedico(m);
                    this.vista.mostrarMedicosRegistrados(this.sistema.getMedicos());
                 }
        }
        catch(ImputVacioException exception) {
            if (comando.equalsIgnoreCase(IVista.ENVIARPACIENTE))
                vista.mostrarMensajeExcepcionPaciente(exception);
            else if (comando.equalsIgnoreCase(IVista.ENVIARMEDICO))
                     vista.mostrarMensajeExcepcionMedico(exception);
        }

    }

}
