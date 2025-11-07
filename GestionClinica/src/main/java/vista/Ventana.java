package vista;

import controlador.IVista;
import modelo.excepciones.*;
import modelo.facturacion.Factura;
import modelo.facturacion.PacienteAtendido;
import modelo.facturacion.RegistroPaciente;
import modelo.facturacion.ReporteActividadMedica;
import modelo.interfaces.IMedico;
import modelo.lugares.Habitacion;
import modelo.personas.Asociado;
import modelo.personas.ObservadorAsociado;
import modelo.personas.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Ventana extends JFrame implements IVista {
    private JTabbedPane PanelTabbeado;
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

    private JButton MedicoBotonEnviar;
    private JTextField DNIMedicoField;
    private JTextField NombreMedicoField;
    private JTextField ApellidoMedicoField;
    private JTextField CalleMedicoField;
    private JTextField NumMedicoField;
    private JTextField CiudadMedicoField;
    private JLabel MuestraDeExcepcionMedicoLabel;
    private JTextField MatriculaMedicoField;
    private JComboBox EspecialidadMedicoField;
    private JComboBox ContratacionMedicoField;
    private JComboBox PosgradoMedicoField;
    private JScrollPane MedicoScrollPane;
    private JTextField TelefonoMedicoField;

    private JComboBox<Paciente> IngresarPacienteField;
    private JButton IngresarPacienteBoton;
    private JTextField IngresarPacienteBuscarField;
    private JButton IngresarPacienteBuscarBoton;

    private JComboBox<Paciente> AtenderPacienteField;
    private JComboBox<IMedico> AtenderMedicoField;
    private JTextField AtenderPacienteBuscarField;
    private JTextField AtenderMedicoBuscarField;
    private JButton AtenderPacienteBuscarBoton;
    private JButton AtenderMedicoBuscarBoton;
    private JButton AtenderPacienteBoton;

    private JTextField InternarPacienteBuscarField;
    private JButton InternarPacienteBuscarBoton;
    private JComboBox<Paciente> InternarPacienteField;
    private JComboBox<Habitacion> HabitacionField;
    private JButton InternarPacienteBoton;
    private JTextField EgresarBuscarField;
    private JButton EgresarBuscarBoton;
    private JComboBox<Paciente> EgresarField;
    private JTextPane FacturaTextPane;
    private JButton EgresarBoton;

    private JPanel PanelAsociados;
    private JTabbedPane PanelTabbeadoAsociado;
    private JPanel PanelRegistroAsociado;
    private JPanel PanelBajaAsociado;
    private JLabel DNIAsociadoLabel;
    private JTextField DNIAsociadoField;
    private JTextField NombreAsociadoField;
    private JTextField ApellidoAsociadoField;
    private JTextField CalleAsociadoField;
    private JTextField CiudadAsociadoField;
    private JTextField NumAsociadoField;
    private JTextField TelefonoAsociadoField;
    private JLabel NombreAsociadoLabel;
    private JLabel ApellidoAsociadoLabel;
    private JLabel CalleAsociadoLabel;
    private JLabel CiudadAsociadoLabel;
    private JLabel NumAsociadoLabel;
    private JLabel TelefonoAsociadoLabel;
    private JButton AsociadoBotonEnviar;
    private JTabbedPane PanelPacientesTabbeado;
    private JPanel PanelRegistroPaciente;
    private JLabel DNIPacienteLabel;
    private JLabel NombrePacienteLabel;
    private JLabel ApellidoPacienteLabel;
    private JLabel CallePacienteLabel;
    private JLabel NumPacienteLabel;
    private JLabel CiudadPacienteLabel;
    private JLabel TelefonoPacienteLabel;
    private JLabel RangoPacienteLabel;
    private JLabel HistoriaPacienteLabel;
    private JPanel PanelManejoPaciente;
    private JLabel IngresarPacienteLabel;
    private JLabel AtenderPacienteLabel;
    private JLabel AtenderPacienteBuscarLabel;
    private JLabel IngresarPacienteBuscarLabel;
    private JLabel AtenderMedicoBuscarLabel;
    private JLabel InternarPacienteLabel;
    private JLabel InternarPacienteBuscarLabel;
    private JPanel PanelEgresar;
    private JLabel EgresarLabel;
    private JLabel PacienteBuscarLabel;
    private JPanel PanelPacientes;
    private JTabbedPane PanelMedicosTabbeado;
    private JPanel PanelRegistroMedico;
    private JLabel DNIMedicoLabel;
    private JLabel NombreMedicoLabel;
    private JLabel ApellidoMedicoLabel;
    private JLabel CalleMedicoLabel;
    private JLabel NumMedicoLabel;
    private JLabel CiudadMedicoLabel;
    private JLabel MatriculaMedicoLabel;
    private JLabel EspecialidadMedicoLabel;
    private JLabel ContratacionMedicoLabel;
    private JLabel PosgradoMedicoLabel;
    private JLabel TelefonoMedicoLabel;
    private JPanel PanelMedicos;
    private JPanel PanelPacientesLista;
    private JPanel PanelAtendidosMedico;
    private JPanel PanelMedicosLista;
    private JScrollPane AsociadoScrollPane;
    private JPanel PanelAsociadosLista;
    private JScrollPane FacturaScrollPane;
    private JLabel BajaAsociadoBuscarLabel;
    private JTextField BajaAsociadoBuscarField;
    private JComboBox<Asociado> BajaAsociadoField;
    private JButton BajaAsociadoBuscarBoton;
    private JButton BajaAsociadoBoton;
    private JLabel ReporteMedicoLabel;
    private JTextField ReporteMedicoBuscarField;
    private JComboBox<IMedico> ReporteMedicoField;
    private JButton ReporteMedicoBuscarBoton;
    private JButton ReporteMedicoBoton;
    private JTextField FechaInicialField;
    private JTextField FechaFinalField;
    private JLabel FechaInicialLabel;
    private JLabel FechaFinalLabel;
    private JScrollPane ReporteMedicoScrollPane;
    private JTextArea EstadoText;
    private JButton AmbulanciaAsociadosBoton;
    private JButton AmbulanciaEmpezarBoton;
    private JScrollPane AmbulanciaAsociadosScrollPane;
    private JButton AmbulanciaPararBoton;
    private JButton AmbulanciaVolverBoton;
    private JTabbedPane PanelAmbulanciaTabbeado;
    private JPanel PanelAmbulanciaSimulacion;
    private JPanel PanelAmbulancia;
    private JButton AmbulanciaTallerBoton;
    private JButton CrearTablasBoton;
    private JPanel PanelCrearTablas;
    private JPanel PanelAmbu = new JPanel();
    private ArrayList<JCheckBox> AsociadosCheckBoxes = new ArrayList<>();

    private DefaultListModel<String> modelPaciente = new DefaultListModel<>();
    private DefaultListModel<String> modelMedico = new DefaultListModel<>();
    private DefaultListModel<String> modelAsociado = new DefaultListModel<>();
    private JList<String> PacientesRegistradosList = new JList<>(modelPaciente);
    private JList<String> MedicosRegistradosList = new JList<>(modelMedico);
    private JList<String> AsociadosRegistradosList = new JList<>(modelAsociado);

    private DefaultTableModel tablaModelo = new DefaultTableModel(new String[]{"Asociado","Cantidad de Solicitudes"},0);
    private JTable tabla = new JTable(tablaModelo);

    private ArrayList<JPanel> panelesAsociados = new ArrayList<>();

    private ArrayList<ObservadorAsociado> observadoresAsociados = new ArrayList<>();

    private ArrayList<JScrollPane> scrollPaneAmbulanciaAsociado = new ArrayList<JScrollPane>();


    public Ventana() {
        setContentPane(MainPanel);
        setTitle("Sistema Clínica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600,800);
        setLocationRelativeTo(null);
        setVisible(true);
        EstadoText.setText("DISPONIBLE");
        PanelAmbu.setLayout(new BoxLayout(PanelAmbu, BoxLayout.Y_AXIS));
        AmbulanciaEmpezarBoton.setVisible(false);
        AmbulanciaPararBoton.setVisible(false);
        AmbulanciaVolverBoton.setVisible(false);
        AmbulanciaTallerBoton.setVisible(false);
        MuestraDeExcepcionPacienteLabel.setVisible(false);
        MuestraDeExcepcionMedicoLabel.setVisible(false);

    }




    //GET BOTONES
    public JButton getPacienteBotonEnviar(){
        return PacienteBotonEnviar;
    }

    public JButton getMedicoBotonEnviar(){
        return MedicoBotonEnviar;
    }

    public JButton getIngresarPacienteBoton(){
        return IngresarPacienteBoton;
    }

    public JButton getIngresarPacienteBuscarBoton(){
        return IngresarPacienteBuscarBoton;
    }

    public JButton getAtenderPacienteBuscarBoton(){
        return AtenderPacienteBuscarBoton;
    }

    public JButton getAtenderMedicoBuscarBoton(){
        return AtenderMedicoBuscarBoton;
    }

    public JButton getAtenderPacienteBoton(){
        return AtenderPacienteBoton;
    }

    public JButton getInternarPacienteBuscarBoton(){
        return InternarPacienteBuscarBoton;
    }

    public JButton getInternarPacienteBoton(){
        return InternarPacienteBoton;
    }

    public JButton getEgresarBuscarBoton() {
        return EgresarBuscarBoton;
    }

    public JButton getEgresarBoton() {
        return EgresarBoton;
    }

    public JButton getAsociadoBotonEnviar() {
        return AsociadoBotonEnviar;
    }

    public JButton getBajaAsociadoBuscarBoton() {
        return BajaAsociadoBuscarBoton;
    }

    public JButton getBajaAsociadoBoton() {
        return BajaAsociadoBoton;
    }

    public JButton getReporteMedicoBuscarBoton() {
        return ReporteMedicoBuscarBoton;
    }

    public JButton getReporteMedicoBoton() {
        return ReporteMedicoBoton;
    }

    public JButton getAmbulanciaAsociadosBoton() {
        return AmbulanciaAsociadosBoton;
    }

    public JButton getAmbulanciaEmpezarBoton() {
        return AmbulanciaEmpezarBoton;
    }

    public JButton getAmbulanciaPararBoton() {
        return AmbulanciaPararBoton;
    }

    public JButton getAmbulanciaVolverBoton() {
        return AmbulanciaVolverBoton;
    }

    public JButton getAmbulanciaTallerBoton() {
        return AmbulanciaTallerBoton;
    }

    public JButton getCrearPacienteBoton() {
        return CrearTablasBoton;
    }
    //TERMINAN ACA



    //GET FIELDS PACIENTE
    @Override
    public String getDNIPaciente() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.DNIPacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                Integer.parseInt(this.DNIPacienteField.getText());
                return this.DNIPacienteField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getNombrePaciente()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.NombrePacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else {
            if (this.NombrePacienteField.getText().matches("^[ A-Za-z]+$"))
                return this.NombrePacienteField.getText();
            else throw new InputStringInvalidoExcepcion();
        }
    }

    @Override
    public String getApellidoPaciente()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.ApellidoPacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            if (this.ApellidoPacienteField.getText().matches("^[ A-Za-z]+$"))
                return this.ApellidoPacienteField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getCallePaciente()  throws InputVacioExcepcion {
        if (this.CallePacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            return this.CallePacienteField.getText();
    }

    @Override
    public int getNumPaciente()  throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.NumPacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                return Integer.parseInt(this.NumPacienteField.getText());
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getCiudadPaciente()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.CiudadPacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            if (this.CiudadPacienteField.getText().matches("^[ A-Za-z]+$"))
                return this.CiudadPacienteField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getTelefonoPaciente()  throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.TelefonoPacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
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
    public int getHistoriaPaciente()  throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.HistoriaPacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                return Integer.parseInt(this.HistoriaPacienteField.getText());
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }
    //TERMINAN ACA




    //GET FIELDS MEDICO
    @Override
    public String getDNIMedico() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.DNIMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                Integer.parseInt(this.DNIMedicoField.getText());
                return this.DNIMedicoField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getNombreMedico()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.NombreMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            if (this.NombreMedicoField.getText().matches("^[ A-Za-z]+$"))
                return this.NombreMedicoField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getApellidoMedico()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.ApellidoMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            if (this.ApellidoMedicoField.getText().matches("^[ A-Za-z]+$"))
                return this.ApellidoMedicoField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getCalleMedico()  throws InputVacioExcepcion {
        if (this.CalleMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            return this.CalleMedicoField.getText();
    }

    @Override
    public int getNumMedico()  throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.NumMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                return Integer.parseInt(this.NumMedicoField.getText());
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getCiudadMedico()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.CiudadMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            if (this.CiudadMedicoField.getText().matches("^[ A-Za-z]+$"))
                return this.CiudadMedicoField.getText();
            else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getTelefonoMedico()  throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.TelefonoMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                Integer.parseInt(this.TelefonoMedicoField.getText());
                return this.TelefonoMedicoField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getMatriculaMedico()  throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.MatriculaMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
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
    //TERMINAN ACA




    //GET FIELDS ASOCIADO
    public String getDNIAsociado() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.DNIAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                Integer.parseInt(this.DNIAsociadoField.getText());
                return this.DNIAsociadoField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getNombreAsociado()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.NombreAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else {
            if (this.NombreAsociadoField.getText().matches("^[ A-Za-z]+$"))
                return this.NombreAsociadoField.getText();
            else throw new InputStringInvalidoExcepcion();
        }
    }

    @Override
    public String getApellidoAsociado()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.ApellidoAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
        if (this.ApellidoAsociadoField.getText().matches("^[ A-Za-z]+$"))
            return this.ApellidoAsociadoField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getCalleAsociado()  throws InputVacioExcepcion {
        if (this.CalleAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            return this.CalleAsociadoField.getText();
    }

    @Override
    public int getNumAsociado()  throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.NumAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                return Integer.parseInt(this.NumAsociadoField.getText());
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }

    @Override
    public String getCiudadAsociado()  throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.CiudadAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
        if (this.CiudadAsociadoField.getText().matches("^[ A-Za-z]+$"))
            return this.CiudadAsociadoField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getTelefonoAsociado()  throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
        if (this.TelefonoAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            try {
                Integer.parseInt(this.TelefonoAsociadoField.getText());
                return this.TelefonoAsociadoField.getText();
            } catch (NumberFormatException e) {
                throw new InputNumeroInvalidoExcepcion();
            }
    }
    //TERMINAN ACA




    //GET FECHAS
    public String getFechaInicial() throws InputVacioExcepcion, FechaInvalidaExcepcion {
        if (this.FechaInicialField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else {
            String fecha = this.FechaInicialField.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                sdf.parse(fecha);
                return fecha;
            }
            catch (Exception e) {
                throw new FechaInvalidaExcepcion();
            }
        }
    }

    public String getFechaFinal() throws InputVacioExcepcion, FechaInvalidaExcepcion {
        if (this.FechaFinalField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else {
            String fecha = this.FechaFinalField.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                sdf.parse(fecha);
                return fecha;
            }
            catch (Exception e) {
                throw new FechaInvalidaExcepcion();
            }
        }
    }
    //TERMINAN ACA




    //GET SELECTEDS
    public Paciente getPacienteIngresar() {
        return (Paciente) IngresarPacienteField.getSelectedItem();
    }

    public Paciente getPacienteAtender() {
        return (Paciente) AtenderPacienteField.getSelectedItem();
    }

    public IMedico getMedicoAtender() {
        return (IMedico) AtenderMedicoField.getSelectedItem();
    }

    public Paciente getPacienteInternar() {
        return (Paciente) InternarPacienteField.getSelectedItem();
    }

    public Paciente getPacienteEgresar() {
        return (Paciente) EgresarField.getSelectedItem();
    }

    public Habitacion getHabitacion() {
        return (Habitacion) HabitacionField.getSelectedItem();
    }

    public Asociado getAsociadoBajar() {
        return (Asociado) BajaAsociadoField.getSelectedItem();
    }

    public IMedico getMedicoReporte() {
        return (IMedico) ReporteMedicoField.getSelectedItem();
    }
    //TERMINAN ACA




    //GET BUSQUEDAS
    public String getIngresarPacienteBusqueda(){
        return IngresarPacienteBuscarField.getText();
    }

    public String getAtenderPacienteBusqueda(){
        return AtenderPacienteBuscarField.getText();
    }

    public String getAtenderMedicoBusqueda(){
        return AtenderMedicoBuscarField.getText();
    }

    public String getInternarPacienteBusqueda() {
        return InternarPacienteBuscarField.getText();
    }

    public String getEgresarPacienteBusqueda() {
        return EgresarBuscarField.getText();
    }

    public String getBajaAsociadoBusqueda() {
        return BajaAsociadoBuscarField.getText();
    }

    public String getReporteMedicoBusqueda() {
        return ReporteMedicoBuscarField.getText();
    }
    //TERMINAN ACA




    //ACTUALIZAR LISTAS
    public void actualizarPacientesRegistradosLista(Set<Paciente> pacientesRegistrados){
        modelPaciente.removeAllElements();
        Iterator<Paciente> iterator = pacientesRegistrados.iterator();
        while(iterator.hasNext()){
            modelPaciente.addElement(iterator.next().toString());
        }
        PacienteScrollPane.setViewportView(this.PacientesRegistradosList);
    }

    public void actualizarMedicosRegistradosLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados){
        modelMedico.removeAllElements();
        Iterator<Map.Entry<IMedico, List<PacienteAtendido>>> iterator = medicosRegistrados.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<IMedico, List<PacienteAtendido>> entry = iterator.next();
            modelMedico.addElement(entry.getKey().toString());
        }
        MedicoScrollPane.setViewportView(this.MedicosRegistradosList);
    }

    public void actualizarAsociadosRegistradosLista(Set<Asociado> asociadosRegistrados){
        modelAsociado.removeAllElements();
        Iterator<Asociado> iterator = asociadosRegistrados.iterator();
        while(iterator.hasNext()){
            modelAsociado.addElement(iterator.next().toString());
        }
        AsociadoScrollPane.setViewportView(this.AsociadosRegistradosList);
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

    public void actualizarAtenderMedicoLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados, String comparar){
        AtenderMedicoField.removeAllItems();
        Iterator<Map.Entry<IMedico, List<PacienteAtendido>>> iterator = medicosRegistrados.entrySet().iterator();
        while(iterator.hasNext()){
            IMedico m = iterator.next().getKey();
            if ((m.getDNI().contains(comparar)) || (m.getNombre().contains(comparar)) || (m.getApellido().contains(comparar)))
                AtenderMedicoField.addItem(m);
        }
    }

    public void actualizarInternarPacienteLista(Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar){
        InternarPacienteField.removeAllItems();
        Iterator<Map.Entry<Paciente, RegistroPaciente>> iterator = pacientesAtendidos.entrySet().iterator();
        while(iterator.hasNext()){
            Paciente p = iterator.next().getKey();
            if (pacientesAtendidos.get(p).getHabitacion() == null) {
                if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                    InternarPacienteField.addItem(p);
            }
        }
    }

    public void actualizarHabitaciones(List<Habitacion> habitaciones){
        HabitacionField.removeAllItems();
        Iterator<Habitacion> iterator = habitaciones.iterator();
        while(iterator.hasNext()){
            Habitacion h = iterator.next();
            if (!(h.estaLlena()))
                HabitacionField.addItem(h);
        }
    }

    public void actualizarEgresarLista(Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar) {
        EgresarField.removeAllItems();
        Iterator<Map.Entry<Paciente, RegistroPaciente>> iterator = pacientesAtendidos.entrySet().iterator();
        while(iterator.hasNext()){
            Paciente p = iterator.next().getKey();
            if (pacientesAtendidos.get(p).getHabitacion() == null) {
                if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                    EgresarField.addItem(p);
            }
        }
    }

    public void actualizarBajaAsociadosLista(Set<Asociado> asociados,  String comparar) {
        BajaAsociadoField.removeAllItems();
        Iterator<Asociado> iterator = asociados.iterator();
        while(iterator.hasNext()){
            Asociado p = iterator.next();
            if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                BajaAsociadoField.addItem(p);
        }
    }

    public void actualizarReporteMedicoLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados, String comparar){
        ReporteMedicoField.removeAllItems();
        Iterator<Map.Entry<IMedico, List<PacienteAtendido>>> iterator = medicosRegistrados.entrySet().iterator();
        while(iterator.hasNext()){
            IMedico m = iterator.next().getKey();
            if ((m.getDNI().contains(comparar)) || (m.getNombre().contains(comparar)) || (m.getApellido().contains(comparar)))
                ReporteMedicoField.addItem(m);
        }
    }

    public void actualizarAmbulanciaAsociadosLista(Set<Asociado> asociados){
        Iterator<Asociado> iterator = asociados.iterator();
        PanelAmbu.removeAll();
        AsociadosCheckBoxes.clear();
        while(iterator.hasNext()){
            Asociado a = iterator.next();
            JCheckBox checkBox = new JCheckBox(a.toString());
            AsociadosCheckBoxes.add(checkBox);
            PanelAmbu.add(checkBox);
        }
        AmbulanciaAsociadosScrollPane.setViewportView(PanelAmbu);
    }
    //TERMINAN ACA




    public List<Asociado> getAsociadosAmbulancia(Set<Asociado> asociadosRegistrados) throws SinAsociadosSeleccionadosExcepcion{
        List<Asociado> asos1 = new ArrayList<>();
        asos1.addAll(asociadosRegistrados);

        ArrayList<Integer> cantidades = new ArrayList<>();
        for (int i = 0; i < AsociadosCheckBoxes.size(); i++){
            if (AsociadosCheckBoxes.get(i).isSelected()){
                cantidades.add(i);
            }
        }

        if  (!cantidades.isEmpty()) {
            List<Asociado> aso = new ArrayList<>();
            for (int i = 0; i < cantidades.size(); i++) {
                aso.add(asos1.get(cantidades.get(i)));
            }
            return aso;
        }
        else throw new SinAsociadosSeleccionadosExcepcion();
    }

    public void mostrarFactura(Factura factura) {
        JTextPane f = new JTextPane();
        f.setText(factura.ImprimeFactura().toString());
        FacturaScrollPane.setViewportView(f);
    }

    public void mostrarReporteMedico(ReporteActividadMedica reporte) {
        JTextPane textoReporte = new JTextPane();
        textoReporte.setText(reporte.generarReporte());
        ReporteMedicoScrollPane.setViewportView(textoReporte);
    }

    public boolean confirmarBaja() {
        JOptionPane panel = new JOptionPane();
        int respuesta = JOptionPane.showConfirmDialog(panel, "¿Quiere eliminar el asociado seleccionado?","Confirmacion de baja", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void mostrarMensajeExcepcionPaciente(Exception e) {
        MuestraDeExcepcionPacienteLabel.setText(e.getMessage());
        MuestraDeExcepcionPacienteLabel.setVisible(true);
    }

    public void mostrarMensajeExcepcionMedico(Exception e) {
        MuestraDeExcepcionMedicoLabel.setText(e.getMessage());
        MuestraDeExcepcionMedicoLabel.setVisible(true);
    }

    public void cambiarEstadoAmbulancia(String estado) {
        EstadoText.setText(estado);
    }





    public void setBotonAmbulanciaPararNotEnabled() {
        AmbulanciaPararBoton.setEnabled(false);
        AmbulanciaTallerBoton.setEnabled(false);
    }

    public void panelAmbulanciaAsociados(Set<Asociado> asociadosRegistrados){
        AmbulanciaEmpezarBoton.setVisible(false);
        AmbulanciaVolverBoton.setVisible(false);
        AmbulanciaPararBoton.setVisible(false);
        AmbulanciaTallerBoton.setVisible(false);
        AmbulanciaAsociadosBoton.setVisible(true);

        AsociadoBotonEnviar.setEnabled(true);
        BajaAsociadoBoton.setEnabled(true);

        this.actualizarAmbulanciaAsociadosLista(asociadosRegistrados);
        for (int i=0; i<scrollPaneAmbulanciaAsociado.size(); i++){
            PanelAmbulanciaTabbeado.remove(scrollPaneAmbulanciaAsociado.get(i));
        }
        for (int i=0; i<observadoresAsociados.size(); i++){
            observadoresAsociados.get(i).eliminarObservable();
        }
        observadoresAsociados.clear();
        panelesAsociados.clear();
        scrollPaneAmbulanciaAsociado.clear();
    }

    public void panelAmbulanciaEmpezar(List<Asociado> asociadosAmbulancia){
        AmbulanciaPararBoton.setVisible(false);
        AmbulanciaAsociadosBoton.setVisible(false);
        AmbulanciaTallerBoton.setVisible(false);
        AmbulanciaEmpezarBoton.setVisible(true);
        AmbulanciaVolverBoton.setVisible(true);
        this.mostrarAmbulanciaCantidades(asociadosAmbulancia);
        panelesAsociados.clear();
        for  (int i=0; i < asociadosAmbulancia.size(); i++){
            JPanel panel = new JPanel();
            panelesAsociados.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            scrollPaneAmbulanciaAsociado.add(new JScrollPane(panel));
            ObservadorAsociado observadorAsociado = new ObservadorAsociado(asociadosAmbulancia.get(i).getObservableAsociado(),this);
            PanelAmbulanciaTabbeado.add(scrollPaneAmbulanciaAsociado.get(i),observadorAsociado.getDatosAsociado());
            observadoresAsociados.add(observadorAsociado);
        }
    }

    public void panelAmbulanciaParar(){
        AmbulanciaAsociadosBoton.setVisible(false);
        AmbulanciaEmpezarBoton.setVisible(false);
        AmbulanciaVolverBoton.setVisible(false);
        AmbulanciaPararBoton.setEnabled(true);
        AmbulanciaTallerBoton.setEnabled(true);
        AmbulanciaPararBoton.setVisible(true);
        AmbulanciaTallerBoton.setVisible(true);

        AsociadoBotonEnviar.setEnabled(false);
        BajaAsociadoBoton.setEnabled(false);
    }


    public void actualizarConsolaAsociado(ObservadorAsociado observadorAsociado, String mensaje) {
        JLabel label = new JLabel(mensaje + "\n");
        label.setHorizontalAlignment(JLabel.CENTER);
        panelesAsociados.get(observadoresAsociados.indexOf(observadorAsociado)).add(label);
    }




    public void mostrarAmbulanciaCantidades(List<Asociado> asociados){
        PanelAmbu.removeAll();
        tablaModelo.setRowCount(0);
        Iterator<Asociado> iterator = asociados.iterator();

        while(iterator.hasNext()){
            Asociado a = iterator.next();
            tablaModelo.addRow(new Object[]{a,"1"});
        }

        PanelAmbu.add(tabla);
        AmbulanciaAsociadosScrollPane.setViewportView(PanelAmbu);
    }

    public ArrayList<Integer> getCantidadSolicitudes() throws CantidadSolicitudesInvalidaExcepcion {
        ArrayList<Integer> cantidades = new ArrayList<>();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            String cantString = (String) tabla.getValueAt(i, 1);
            Integer cant = Integer.parseInt(cantString);
            if (cant < 1)
                throw new CantidadSolicitudesInvalidaExcepcion();
            else
                cantidades.add(cant);
        }
        return cantidades;
    }

    public void mostrarExcepcionVentana(Exception e) {
        JOptionPane panel = new JOptionPane();
        JOptionPane.showMessageDialog(panel, e.getMessage());
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
        InternarPacienteBoton.addActionListener(actionListener);
        InternarPacienteBuscarBoton.addActionListener(actionListener);
        EgresarBoton.addActionListener(actionListener);
        EgresarBuscarBoton.addActionListener(actionListener);
        AsociadoBotonEnviar.addActionListener(actionListener);
        BajaAsociadoBoton.addActionListener(actionListener);
        BajaAsociadoBuscarBoton.addActionListener(actionListener);
        ReporteMedicoBoton.addActionListener(actionListener);
        ReporteMedicoBuscarBoton.addActionListener(actionListener);
        AmbulanciaAsociadosBoton.addActionListener(actionListener);
        AmbulanciaEmpezarBoton.addActionListener(actionListener);
        AmbulanciaPararBoton.addActionListener(actionListener);
        AmbulanciaVolverBoton.addActionListener(actionListener);
        AmbulanciaTallerBoton.addActionListener(actionListener);
        CrearTablasBoton.addActionListener(actionListener);
    }
}
