package controlador;

import modelo.factoria.FactoryMedico;
import modelo.factoria.FactoryPaciente;
import modelo.interfaces.IMedico;
import modelo.personas.Paciente;
import modelo.sistema.SistemaFacade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private IVista vista;
    private SistemaFacade  sistema;

    FactoryMedico factoryMedico = new FactoryMedico();
    FactoryPaciente factoryPaciente = new FactoryPaciente();

    IMedico medClinica = factoryMedico.crearMedico("10000000", "Ana", "García", "Calle 1", 1, "CABA", "111-1111", "M-123", "CLINICA", "PERMANENTE", "MASTER");
    IMedico medCirugia = factoryMedico.crearMedico("10000001", "Bruno", "Lopez", "Calle 2", 2, "CABA", "222-2222", "M-456", "CIRUGIA", "RESIDENTE", "DOCTORADO");
    IMedico medPediatra = factoryMedico.crearMedico("10000001", "Bianca", "Gonzalez", "Calle 2", 2, "CABA", "222-2222", "M-456", "PEDIATRIA", "RESIDENTE", "DOCTORADO");

    // Pacientes
    Paciente p1 = factoryPaciente.crearPaciente("20000000", "Juan", "Pérez", "Calle 10", 10, "CABA", "300-0000", 12345, "JOVEN");
    Paciente p2 = factoryPaciente.crearPaciente("20000001", "Lucía", "Suárez", "Calle 11", 11, "CABA", "300-0001", 22345, "NINIO");
    Paciente p3 = factoryPaciente.crearPaciente("20000002", "Mario", "Gómez", "Calle 12", 12, "CABA", "300-0002", 32345, "MAYOR");




    public Controlador(IVista vista, SistemaFacade sistema)
    {
        this.sistema = sistema;
        this.vista = vista;
        this.vista.addActionListener(this);
        sistema.registraMedico(medClinica);
        sistema.registraMedico(medCirugia);
        sistema.registraMedico(medPediatra);
        sistema.registraPaciente(p1);
        sistema.registraPaciente(p2);
        sistema.registraPaciente(p3);
        this.vista.actualizarIngresarPacienteLista(this.sistema.getPacientesRegistrados(), "");
        this.vista.actualizarAtenderPacienteLista(this.sistema.getListaEspera(), this.sistema.getPacientesAtendidos(), "");
        this.vista.actualizarAtenderMedicoLista(this.sistema.getMedicos(), "");
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object comando = e.getSource();
        try {
            if (comando == vista.getPacienteBotonEnviar()) {
                Paciente p = factoryPaciente.crearPaciente(this.vista.getDNIPaciente(), this.vista.getNombrePaciente(), this.vista.getApellidoPaciente(), this.vista.getCallePaciente(), this.vista.getNumPaciente(), this.vista.getCiudadPaciente(), this.vista.getTelefonoPaciente(), this.vista.getHistoriaPaciente(), this.vista.getRangoEtarioPaciente());
                this.vista.ocultarMensajeExcepcionPaciente();
                this.sistema.registraPaciente(p);
                this.vista.mostrarPacientesRegistrados(this.sistema.getPacientesRegistrados());
                this.vista.actualizarIngresarPacienteLista(this.sistema.getPacientesRegistrados(), "");
            }
            else
                if (comando == vista.getMedicoBotonEnviar()) {
                    IMedico m = factoryMedico.crearMedico(this.vista.getDNIMedico(), this.vista.getNombreMedico(), this.vista.getApellidoMedico(), this.vista.getCalleMedico(), this.vista.getNumMedico(), this.vista.getTelefonoMedico(), this.vista.getCiudadMedico(), this.vista.getMatriculaMedico(), this.vista.getEspecialidadMedico(), this.vista.getContratacionMedico(), this.vista.getPosgradoMedico());
                    this.vista.ocultarMensajeExcepcionMedico();
                    this.sistema.registraMedico(m);
                    this.vista.mostrarMedicosRegistrados(this.sistema.getMedicos());
                    this.vista.actualizarAtenderMedicoLista(this.sistema.getMedicos(), "");
                }
                else
                    if (comando == vista.getIngresarPacienteBoton()){
                        this.sistema.ingresaPaciente(this.vista.getPacienteIngresar());
                        this.vista.actualizarAtenderPacienteLista(this.sistema.getListaEspera(), this.sistema.getPacientesAtendidos(), this.vista.getAtenderPacienteBusqueda());
                    }
                    else
                        if (comando == vista.getIngresarPacienteBuscarBoton()){
                            this.vista.actualizarIngresarPacienteLista(this.sistema.getPacientesRegistrados(), this.vista.getIngresarPacienteBusqueda());
                        }
                        else
                            if (comando == vista.getAtenderPacienteBuscarBoton()) {
                                this.vista.actualizarAtenderPacienteLista(this.sistema.getListaEspera(), this.sistema.getPacientesAtendidos(), this.vista.getAtenderPacienteBusqueda());
                            }
                            else
                                if (comando == vista.getAtenderMedicoBuscarBoton()) {
                                    this.vista.actualizarAtenderMedicoLista(this.sistema.getMedicos(), this.vista.getAtenderMedicoBusqueda());
                                }
                                else
                                    if (comando == vista.getAtenderPacienteBoton()) {
                                        this.sistema.atiendePaciente(this.vista.getMedicoAtender(), this.vista.getPacienteAtender());
                                    }
        }
        catch(Exception exception) {
            if (comando == vista.getPacienteBotonEnviar())
                vista.mostrarMensajeExcepcionPaciente(exception);
            else if (comando == vista.getMedicoBotonEnviar())
                     vista.mostrarMensajeExcepcionMedico(exception);
        }

    }

}
