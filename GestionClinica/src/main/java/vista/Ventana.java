package vista;

import controlador.IVista;
import modelo.excepciones.InputNumeroInvalidoExcepcion;
import modelo.excepciones.InputStringInvalidoExcepcion;
import modelo.excepciones.InputVacioException;
import modelo.facturacion.PacienteAtendido;
import modelo.facturacion.RegistroPaciente;
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
    private JPanel PanelManejoPaciente;
    private JLabel IngresarPacienteLabel;
    private JComboBox<Paciente> IngresarPacienteField;
    private JButton IngresarPacienteBoton;
    private JTextField IngresarPacienteBuscarField;
    private JButton IngresarPacienteBuscarBoton;
    private JLabel AtenderPacienteLabel;
    private JComboBox<Paciente> AtenderPacienteField;
    private JComboBox<IMedico> AtenderMedicoField;
    private JTextField AtenderPacienteBuscarField;
    private JTextField AtenderMedicoBuscarField;
    private JButton AtenderPacienteBuscarBoton;
    private JButton AtenderMedicoBuscarBoton;
    private JButton AtenderPacienteBoton;
    private JLabel IngresarPacienteBuscarLabel;
    private JLabel AtenderPacienteBuscarLabel;
    private JLabel AtenderMedicoBuscarLabel;
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

    public JButton getPacienteBotonEnviar (){
        return PacienteBotonEnviar;
    }

    public JButton getMedicoBotonEnviar (){
        return MedicoBotonEnviar;
    }

    public JButton getIngresarPacienteBoton (){
        return IngresarPacienteBoton;
    }

    public JButton getIngresarPacienteBuscarBoton (){
        return IngresarPacienteBuscarBoton;
    }

    public JButton getAtenderPacienteBuscarBoton (){
        return AtenderPacienteBuscarBoton;
    }

    public JButton getAtenderMedicoBuscarBoton (){
        return AtenderMedicoBuscarBoton;
    }

    public JButton getAtenderPacienteBoton (){
        return AtenderPacienteBoton;
    }

    @Override
    public String getDNIPaciente() throws InputVacioException, InputNumeroInvalidoExcepcion {
        if (this.DNIPacienteField.getText().isEmpty())
            throw new InputVacioException();
        else
            try {
                Integer.parseInt(this.DNIPacienteField.getText());
                return this.DNIPacienteField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getNombrePaciente()  throws InputVacioException, InputStringInvalidoExcepcion {
        if (this.NombrePacienteField.getText().isEmpty())
            throw new InputVacioException();
        else {
            if (this.NombrePacienteField.getText().matches("^[ A-Za-z]+$"))
                return this.NombrePacienteField.getText();
            else throw new InputStringInvalidoExcepcion();
        }
    }

    @Override
    public String getApellidoPaciente()  throws InputVacioException, InputStringInvalidoExcepcion {
        if (this.ApellidoPacienteField.getText().isEmpty())
            throw new InputVacioException();
        else
            if (this.ApellidoPacienteField.getText().matches("^[ A-Za-z]+$"))
                return this.ApellidoPacienteField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getCallePaciente()  throws InputVacioException {
        if (this.CallePacienteField.getText().isEmpty())
            throw new InputVacioException();
        else
            return this.CallePacienteField.getText();
    }

    @Override
    public int getNumPaciente()  throws InputVacioException, InputNumeroInvalidoExcepcion {
        if (this.NumPacienteField.getText().isEmpty())
            throw new InputVacioException();
        else
            try {
                return Integer.parseInt(this.NumPacienteField.getText());
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getCiudadPaciente()  throws InputVacioException, InputStringInvalidoExcepcion {
        if (this.CiudadPacienteField.getText().isEmpty())
            throw new InputVacioException();
        else
            if (this.CiudadPacienteField.getText().matches("^[ A-Za-z]+$"))
                return this.CiudadPacienteField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getTelefonoPaciente()  throws InputVacioException, InputNumeroInvalidoExcepcion {
        if (this.TelefonoPacienteField.getText().isEmpty())
            throw new InputVacioException();
        else
            try {
                Integer.parseInt(this.TelefonoPacienteField.getText());
                return this.TelefonoPacienteField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getRangoEtarioPaciente() {
        return (String) this.RangoPacienteField.getSelectedItem();
    }

    @Override
    public int getHistoriaPaciente()  throws InputVacioException, InputNumeroInvalidoExcepcion {
        if (this.HistoriaPacienteField.getText().isEmpty())
            throw new InputVacioException();
        else
            try {
                return Integer.parseInt(this.HistoriaPacienteField.getText());
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }


    public void mostrarPacientesRegistrados(Set<Paciente> pacientesRegistrados){
        model.removeAllElements();
        Iterator<Paciente> iterator = pacientesRegistrados.iterator();
        while(iterator.hasNext()){
            model.addElement(iterator.next().toString());
        }
        PacienteScrollPane.setViewportView(this.PacientesRegistradosList);
    }

    public String getIngresarPacienteBusqueda(){
        return IngresarPacienteBuscarField.getText();
    }

    public void actualizarIngresarPacienteLista(Set<Paciente> pacientesRegistrados, String comparar){
        IngresarPacienteField.removeAllItems();
        Iterator<Paciente> iterator = pacientesRegistrados.iterator();
        while(iterator.hasNext()){
            Paciente p = iterator.next();
            if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                IngresarPacienteField.addItem(p);
        }
    }

    public String getAtenderPacienteBusqueda(){
        return AtenderPacienteBuscarField.getText();
    }

    public String getAtenderMedicoBusqueda(){
        return AtenderMedicoBuscarField.getText();
    }

    public void actualizarAtenderPacienteLista(Set<Paciente> pacientesEspera, Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar){
        AtenderPacienteField.removeAllItems();
        Iterator<Paciente> iterator = pacientesEspera.iterator();
        while(iterator.hasNext()){
            Paciente p = iterator.next();
            if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                AtenderPacienteField.addItem(p);
        }
        Iterator<Map.Entry<Paciente, RegistroPaciente>> iterator2 = pacientesAtendidos.entrySet().iterator();
        while(iterator2.hasNext()){
            Paciente p = iterator2.next().getKey();
            if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                AtenderPacienteField.addItem(p);
        }
    }

    @Override
    public String getDNIMedico() throws InputVacioException, InputNumeroInvalidoExcepcion {
        if (this.DNIMedicoField.getText().isEmpty())
            throw new InputVacioException();
        else
            try {
                Integer.parseInt(this.DNIMedicoField.getText());
                return this.DNIMedicoField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getNombreMedico()  throws InputVacioException, InputStringInvalidoExcepcion {
        if (this.NombreMedicoField.getText().isEmpty())
            throw new InputVacioException();
        else
            if (this.NombreMedicoField.getText().matches("^[ A-Za-z]+$"))
                return this.NombreMedicoField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getApellidoMedico()  throws InputVacioException, InputStringInvalidoExcepcion {
        if (this.ApellidoMedicoField.getText().isEmpty())
            throw new InputVacioException();
        else
            if (this.ApellidoMedicoField.getText().matches("^[ A-Za-z]+$"))
                return this.ApellidoMedicoField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getCalleMedico()  throws InputVacioException {
        if (this.CalleMedicoField.getText().isEmpty())
            throw new InputVacioException();
        else
            return this.CalleMedicoField.getText();
    }

    @Override
    public int getNumMedico()  throws InputVacioException, InputNumeroInvalidoExcepcion {
        if (this.NumMedicoField.getText().isEmpty())
            throw new InputVacioException();
        else
            try {
                return Integer.parseInt(this.NumMedicoField.getText());
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getCiudadMedico()  throws InputVacioException, InputStringInvalidoExcepcion {
        if (this.CiudadMedicoField.getText().isEmpty())
            throw new InputVacioException();
        else
            if (this.CiudadMedicoField.getText().matches("^[ A-Za-z]+$"))
                return this.CiudadMedicoField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getTelefonoMedico()  throws InputVacioException, InputNumeroInvalidoExcepcion {
        if (this.TelefonoMedicoField.getText().isEmpty())
            throw new InputVacioException();
        else
            try {
                Integer.parseInt(this.TelefonoMedicoField.getText());
                return this.TelefonoMedicoField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getMatriculaMedico()  throws InputVacioException, InputNumeroInvalidoExcepcion {
        if (this.MatriculaMedicoField.getText().isEmpty())
            throw new InputVacioException();
        else
            try {
                Integer.parseInt(this.MatriculaMedicoField.getText());
                return this.MatriculaMedicoField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
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

    public void actualizarAtenderMedicoLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados, String comparar){
        AtenderMedicoField.removeAllItems();
        Iterator<Map.Entry<IMedico, List<PacienteAtendido>>> iterator2 = medicosRegistrados.entrySet().iterator();
        while(iterator2.hasNext()){
            IMedico m = iterator2.next().getKey();
            if ((m.getDNI().contains(comparar)) || (m.getNombre().contains(comparar)) || (m.getApellido().contains(comparar)))
                AtenderMedicoField.addItem(m);
        }
    }

    public Paciente getPacienteIngresar() {
        return (Paciente) IngresarPacienteField.getSelectedItem();
    }

    public Paciente getPacienteAtender() {
        return (Paciente) AtenderPacienteField.getSelectedItem();
    }

    public IMedico getMedicoAtender() {
        return (IMedico) AtenderMedicoField.getSelectedItem();
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
        IngresarPacienteBoton.addActionListener(actionListener);
        IngresarPacienteBuscarBoton.addActionListener(actionListener);
        AtenderPacienteBuscarBoton.addActionListener(actionListener);
        AtenderMedicoBuscarBoton.addActionListener(actionListener);
        AtenderPacienteBoton.addActionListener(actionListener);
    }
}
