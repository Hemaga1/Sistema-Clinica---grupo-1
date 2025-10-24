package vista;

import controlador.IVista;
import modelo.excepciones.ImputVacioException;
import modelo.facturacion.PacienteAtendido;
import modelo.interfaces.IMedico;
import modelo.personas.Paciente;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ventana extends JFrame implements IVista {
    private JTabbedPane PanelTabbeado;
    private JPanel PanelRegistroPaciente;
    private JPanel PanelRegistroMedico;
    private JTextField DNIPacienteField;
    private JButton PacienteBotonEnviar;
    private JPanel MainPanel;
    private JTextField NombrePacienteField;
    private JTextField ApellidoPacienteField;
    private JTextField CallePacienteField;
    private JTextField NumPacienteField;
    private JTextField CiudadPacienteField;
    private JTextField TelefonoPacienteField;
    private JComboBox RangoPacienteField;
    private JTextField HistoriaPacienteField;
    private JScrollPane PacienteScrollPane;
    private JLabel MuestraDeExcepcionPacienteLabel;
    private JLabel DNIPacienteLabel;
    private JLabel NombrePacienteLabel;
    private JLabel ApellidoPacienteLabel;
    private JLabel CallePacienteLabel;
    private JLabel NumPacienteLabel;
    private JLabel CiudadPacienteLabel;
    private JLabel TelefonoPacienteLabel;
    private JLabel RangoPacienteLabel;
    private JLabel HistoriaPacienteLabel;
    private JLabel DNIMedicoLabel;
    private JButton MedicoBotonEnviar;
    private JTextField DNIMedicoField;
    private JLabel NombreMedicoLabel;
    private JTextField NombreMedicoField;
    private JLabel ApellidoMedicoLabel;
    private JTextField ApellidoMedicoField;
    private JLabel CalleMedicoLabel;
    private JTextField CalleMedicoField;
    private JLabel NumMedicoLabel;
    private JTextField NumMedicoField;
    private JLabel CiudadMedicoLabel;
    private JTextField CiudadMedicoField;
    private JLabel MuestraDeExcepcionMedicoLabel;
    private JLabel MatriculaMedicoLabel;
    private JTextField MatriculaMedicoField;
    private JLabel EspecialidadMedicoLabel;
    private JComboBox EspecialidadMedicoField;
    private JLabel ContratacionMedicoLabel;
    private JComboBox ContratacionMedicoField;
    private JLabel PosgradoMedicoLabel;
    private JComboBox PosgradoMedicoField;
    private JScrollPane MedicoScrollPane;
    private JLabel TelefonoMedicoLabel;
    private JTextField TelefonoMedicoField;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> PacientesRegistradosList = new JList<>(model);
    private JList<String> MedicosRegistradosList = new JList<>(model);

    public Ventana() {
        setContentPane(MainPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);
        MuestraDeExcepcionPacienteLabel.setVisible(false);
        MuestraDeExcepcionMedicoLabel.setVisible(false);
    }

    @Override
    public String getDNIPaciente() throws ImputVacioException {
        if (this.DNIPacienteField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.DNIPacienteField.getText();
    }

    @Override
    public String getNombrePaciente()  throws ImputVacioException {
        if (this.NombrePacienteField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.NombrePacienteField.getText();
    }

    @Override
    public String getApellidoPaciente()  throws ImputVacioException {
        if (this.ApellidoPacienteField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.ApellidoPacienteField.getText();
    }

    @Override
    public String getCallePaciente()  throws ImputVacioException {
        if (this.CallePacienteField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.CallePacienteField.getText();
    }

    @Override
    public int getNumPaciente()  throws ImputVacioException {
        if (this.NumPacienteField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return Integer.parseInt(this.NumPacienteField.getText());
    }

    @Override
    public String getCiudadPaciente()  throws ImputVacioException {
        if (this.CiudadPacienteField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.CiudadPacienteField.getText();
    }

    @Override
    public String getTelefonoPaciente()  throws ImputVacioException {
        if (this.TelefonoPacienteField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.TelefonoPacienteField.getText();
    }

    @Override
    public String getRangoEtarioPaciente() {
        return (String) this.RangoPacienteField.getSelectedItem();
    }

    @Override
    public int getHistoriaPaciente()  throws ImputVacioException {
        if (this.HistoriaPacienteField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return Integer.parseInt(this.HistoriaPacienteField.getText());
    }


    public void mostrarPacientesRegistrados(Set<Paciente> pacientesRegistrados){
        model.removeAllElements();
        Iterator<Paciente> iterator = pacientesRegistrados.iterator();
        while(iterator.hasNext()){
            model.addElement(iterator.next().toString());
        }
        PacienteScrollPane.setViewportView(this.PacientesRegistradosList);
    }


    @Override
    public String getDNIMedico() throws ImputVacioException {
        if (this.DNIMedicoField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.DNIMedicoField.getText();
    }

    @Override
    public String getNombreMedico()  throws ImputVacioException {
        if (this.NombreMedicoField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.NombreMedicoField.getText();
    }

    @Override
    public String getApellidoMedico()  throws ImputVacioException {
        if (this.ApellidoMedicoField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.ApellidoMedicoField.getText();
    }

    @Override
    public String getCalleMedico()  throws ImputVacioException {
        if (this.CalleMedicoField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.CalleMedicoField.getText();
    }

    @Override
    public int getNumMedico()  throws ImputVacioException {
        if (this.NumMedicoField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return Integer.parseInt(this.NumMedicoField.getText());
    }

    @Override
    public String getCiudadMedico()  throws ImputVacioException {
        if (this.CiudadMedicoField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.CiudadMedicoField.getText();
    }

    @Override
    public String getTelefonoMedico()  throws ImputVacioException {
        if (this.TelefonoMedicoField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.TelefonoMedicoField.getText();
    }

    @Override
    public String getMatriculaMedico()  throws ImputVacioException {
        if (this.MatriculaMedicoField.getText().isEmpty())
            throw new ImputVacioException();
        else
            return this.MatriculaMedicoField.getText();
    }

    @Override
    public String getEspecialidadMedico() {
        return (String) this.EspecialidadMedicoField.getSelectedItem();
    }

    @Override
    public String getContratacionMedico(){
        return (String) this.ContratacionMedicoField.getSelectedItem();
    }

    @Override
    public String getPosgradoMedico(){
        return (String) this.PosgradoMedicoField.getSelectedItem();
    }

    public void mostrarMedicosRegistrados(Map<IMedico, List<PacienteAtendido>> medicosRegistrados){
        model.removeAllElements();
        Iterator<Map.Entry<IMedico, List<PacienteAtendido>>> iterator = medicosRegistrados.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<IMedico, List<PacienteAtendido>> entry = iterator.next();
            model.addElement(entry.getKey().toString());
        }
        MedicoScrollPane.setViewportView(this.MedicosRegistradosList);
    }

    public void mostrarMensajeExcepcionPaciente(Exception e){
        MuestraDeExcepcionPacienteLabel.setText(e.getMessage());
        MuestraDeExcepcionPacienteLabel.setVisible(true);
    }

    public void mostrarMensajeExcepcionMedico(Exception e){
        MuestraDeExcepcionMedicoLabel.setText(e.getMessage());
        MuestraDeExcepcionMedicoLabel.setVisible(true);
    }

    public void ocultarMensajeExcepcionPaciente(){
        MuestraDeExcepcionPacienteLabel.setVisible(false);
    }

    public void ocultarMensajeExcepcionMedico(){
        MuestraDeExcepcionMedicoLabel.setVisible(false);
    }

    public void addActionListener(ActionListener actionListener) {
        PacienteBotonEnviar.addActionListener(actionListener);
        MedicoBotonEnviar.addActionListener(actionListener);
    }
}
