package vista;

import vista.componentes_vista.GridConstraints;
import vista.componentes_vista.GridLayoutManager;
import vista.componentes_vista.Spacer;
import modelo.excepciones.*;
import modelo.facturacion_y_registros.Factura;
import modelo.facturacion_y_registros.PacienteAtendido;
import modelo.facturacion_y_registros.RegistroPaciente;
import modelo.facturacion_y_registros.ReporteActividadMedica;
import modelo.interfaces.IMedico;
import modelo.lugares.Habitacion;
import modelo.personas.Asociado;
import modelo.observadores_y_observables.ObservadorAsociado;
import modelo.personas.Paciente;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Ventana extends JFrame implements IVista {
    private JTextField DNIPacienteField;
    private JButton PacienteBotonEnviar;
    private JPanel MainPanel;

    private JTextField NombrePacienteField;
    private JTextField ApellidoPacienteField;
    private JTextField CallePacienteField;
    private JTextField NumPacienteField;
    private JTextField CiudadPacienteField;
    private JTextField TelefonoPacienteField;
    private JComboBox<String> RangoPacienteField;
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
    private JComboBox<String> EspecialidadMedicoField;
    private JComboBox<String> ContratacionMedicoField;
    private JComboBox<String> PosgradoMedicoField;
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

    private JTextField DNIAsociadoField;
    private JTextField NombreAsociadoField;
    private JTextField ApellidoAsociadoField;
    private JTextField CalleAsociadoField;
    private JTextField CiudadAsociadoField;
    private JTextField NumAsociadoField;
    private JTextField TelefonoAsociadoField;
    private JButton AsociadoBotonEnviar;
    private JScrollPane AsociadoScrollPane;
    private JScrollPane FacturaScrollPane;
    private JTextField BajaAsociadoBuscarField;
    private JComboBox<Asociado> BajaAsociadoField;
    private JButton BajaAsociadoBuscarBoton;
    private JButton BajaAsociadoBoton;
    private JTextField ReporteMedicoBuscarField;
    private JComboBox<IMedico> ReporteMedicoField;
    private JButton ReporteMedicoBuscarBoton;
    private JButton ReporteMedicoBoton;
    private JTextField FechaInicialField;
    private JTextField FechaFinalField;
    private JScrollPane ReporteMedicoScrollPane;
    private JTextArea EstadoText;
    private JButton AmbulanciaAsociadosBoton;
    private JButton AmbulanciaEmpezarBoton;
    private JScrollPane AmbulanciaAsociadosScrollPane;
    private JButton AmbulanciaPararBoton;
    private JButton AmbulanciaVolverBoton;
    private JTabbedPane PanelAmbulanciaTabbeado;
    private JButton AmbulanciaTallerBoton;
    private JButton CrearTablasBoton;
    private JPanel PanelAmbu = new JPanel();
    private ArrayList<JCheckBox> AsociadosCheckBoxes = new ArrayList<>();

    private DefaultListModel<String> modelPaciente = new DefaultListModel<>();
    private DefaultListModel<String> modelMedico = new DefaultListModel<>();
    private DefaultListModel<String> modelAsociado = new DefaultListModel<>();
    private JList<String> PacientesRegistradosList = new JList<>(modelPaciente);
    private JList<String> MedicosRegistradosList = new JList<>(modelMedico);
    private JList<String> AsociadosRegistradosList = new JList<>(modelAsociado);

    private DefaultTableModel tablaModelo = new DefaultTableModel(new String[]{"Asociado", "Cantidad de Solicitudes"}, 0);
    private JTable tabla = new JTable(tablaModelo);

    private ArrayList<JPanel> panelesAsociados = new ArrayList<>();

    private ArrayList<ObservadorAsociado> observadoresAsociados = new ArrayList<>();

    private ArrayList<JScrollPane> scrollPaneAmbulanciaAsociado = new ArrayList<JScrollPane>();


    public Ventana() {
        setContentPane(MainPanel);
        setTitle("Sistema Clínica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 800);
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
    public String getNombrePaciente() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.NombrePacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else {
            if (this.NombrePacienteField.getText().matches("^[ A-Za-z]+$"))
                return this.NombrePacienteField.getText();
            else throw new InputStringInvalidoExcepcion();
        }
    }

    @Override
    public String getApellidoPaciente() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.ApellidoPacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else if (this.ApellidoPacienteField.getText().matches("^[ A-Za-z]+$"))
            return this.ApellidoPacienteField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getCallePaciente() throws InputVacioExcepcion {
        if (this.CallePacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            return this.CallePacienteField.getText();
    }

    @Override
    public int getNumPaciente() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
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
    public String getCiudadPaciente() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.CiudadPacienteField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else if (this.CiudadPacienteField.getText().matches("^[ A-Za-z]+$"))
            return this.CiudadPacienteField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getTelefonoPaciente() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
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
    public int getHistoriaPaciente() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
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
    public String getNombreMedico() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.NombreMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else if (this.NombreMedicoField.getText().matches("^[ A-Za-z]+$"))
            return this.NombreMedicoField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getApellidoMedico() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.ApellidoMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else if (this.ApellidoMedicoField.getText().matches("^[ A-Za-z]+$"))
            return this.ApellidoMedicoField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getCalleMedico() throws InputVacioExcepcion {
        if (this.CalleMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            return this.CalleMedicoField.getText();
    }

    @Override
    public int getNumMedico() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
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
    public String getCiudadMedico() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.CiudadMedicoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else if (this.CiudadMedicoField.getText().matches("^[ A-Za-z]+$"))
            return this.CiudadMedicoField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getTelefonoMedico() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
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
    public String getMatriculaMedico() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
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
    public String getContratacionMedico() {
        return (String) this.ContratacionMedicoField.getSelectedItem();
    }

    @Override
    public String getPosgradoMedico() {
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
    public String getNombreAsociado() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.NombreAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else {
            if (this.NombreAsociadoField.getText().matches("^[ A-Za-z]+$"))
                return this.NombreAsociadoField.getText();
            else throw new InputStringInvalidoExcepcion();
        }
    }

    @Override
    public String getApellidoAsociado() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.ApellidoAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else if (this.ApellidoAsociadoField.getText().matches("^[ A-Za-z]+$"))
            return this.ApellidoAsociadoField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getCalleAsociado() throws InputVacioExcepcion {
        if (this.CalleAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else
            return this.CalleAsociadoField.getText();
    }

    @Override
    public int getNumAsociado() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
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
    public String getCiudadAsociado() throws InputVacioExcepcion, InputStringInvalidoExcepcion {
        if (this.CiudadAsociadoField.getText().isEmpty())
            throw new InputVacioExcepcion();
        else if (this.CiudadAsociadoField.getText().matches("^[ A-Za-z]+$"))
            return this.CiudadAsociadoField.getText();
        else throw new InputStringInvalidoExcepcion();
    }

    @Override
    public String getTelefonoAsociado() throws InputVacioExcepcion, InputNumeroInvalidoExcepcion {
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
    //TERMINA ACA


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
            } catch (Exception e) {
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
            } catch (Exception e) {
                throw new FechaInvalidaExcepcion();
            }
        }
    }
    //TERMINA ACA


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
    //TERMINA ACA


    //GET BUSQUEDAS
    public String getIngresarPacienteBusqueda() {
        return IngresarPacienteBuscarField.getText();
    }

    public String getAtenderPacienteBusqueda() {
        return AtenderPacienteBuscarField.getText();
    }

    public String getAtenderMedicoBusqueda() {
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
    //TERMINA ACA


    //ACTUALIZAR LISTAS
    public void actualizarPacientesRegistradosLista(Set<Paciente> pacientesRegistrados) {
        modelPaciente.removeAllElements();
        for (Paciente pacientesRegistrado : pacientesRegistrados) {
            modelPaciente.addElement(pacientesRegistrado.toString());
        }
        PacienteScrollPane.setViewportView(this.PacientesRegistradosList);
    }

    public void actualizarMedicosRegistradosLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados) {
        modelMedico.removeAllElements();
        for (Map.Entry<IMedico, List<PacienteAtendido>> entry : medicosRegistrados.entrySet()) {
            modelMedico.addElement(entry.getKey().toString());
        }
        MedicoScrollPane.setViewportView(this.MedicosRegistradosList);
    }

    public void actualizarAsociadosRegistradosLista(Set<Asociado> asociadosRegistrados) {
        modelAsociado.removeAllElements();
        for (Asociado asociadosRegistrado : asociadosRegistrados) {
            modelAsociado.addElement(asociadosRegistrado.toString());
        }
        AsociadoScrollPane.setViewportView(this.AsociadosRegistradosList);
    }

    public void actualizarIngresarPacienteLista(Set<Paciente> pacientesRegistrados, String comparar) {
        IngresarPacienteField.removeAllItems();
        for (Paciente p : pacientesRegistrados) {
            if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                IngresarPacienteField.addItem(p);
        }
    }

    public void actualizarAtenderPacienteLista(Set<Paciente> pacientesEspera, Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar) {
        AtenderPacienteField.removeAllItems();
        for (Paciente p : pacientesEspera) {
            if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                AtenderPacienteField.addItem(p);
        }
        for (Map.Entry<Paciente, RegistroPaciente> pacienteRegistroPacienteEntry : pacientesAtendidos.entrySet()) {
            Paciente p = pacienteRegistroPacienteEntry.getKey();
            if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                AtenderPacienteField.addItem(p);
        }
    }

    public void actualizarAtenderMedicoLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados, String comparar) {
        AtenderMedicoField.removeAllItems();
        for (Map.Entry<IMedico, List<PacienteAtendido>> iMedicoListEntry : medicosRegistrados.entrySet()) {
            IMedico m = iMedicoListEntry.getKey();
            if ((m.getDNI().contains(comparar)) || (m.getNombre().contains(comparar)) || (m.getApellido().contains(comparar)))
                AtenderMedicoField.addItem(m);
        }
    }

    public void actualizarInternarPacienteLista(Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar) {
        InternarPacienteField.removeAllItems();
        for (Map.Entry<Paciente, RegistroPaciente> pacienteRegistroPacienteEntry : pacientesAtendidos.entrySet()) {
            Paciente p = pacienteRegistroPacienteEntry.getKey();
            if (pacientesAtendidos.get(p).getHabitacion() == null) {
                if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                    InternarPacienteField.addItem(p);
            }
        }
    }

    public void actualizarHabitaciones(List<Habitacion> habitaciones) {
        HabitacionField.removeAllItems();
        for (Habitacion h : habitaciones) {
            if (!(h.estaLlena()))
                HabitacionField.addItem(h);
        }
    }

    public void actualizarEgresarLista(Map<Paciente, RegistroPaciente> pacientesAtendidos, String comparar) {
        EgresarField.removeAllItems();
        for (Map.Entry<Paciente, RegistroPaciente> pacienteRegistroPacienteEntry : pacientesAtendidos.entrySet()) {
            Paciente p = pacienteRegistroPacienteEntry.getKey();
            if (pacientesAtendidos.get(p).getHabitacion() == null) {
                if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                    EgresarField.addItem(p);
            }
        }
    }

    public void actualizarBajaAsociadosLista(Set<Asociado> asociados, String comparar) {
        BajaAsociadoField.removeAllItems();
        for (Asociado p : asociados) {
            if ((p.getDNI().contains(comparar)) || (p.getNombre().contains(comparar)) || (p.getApellido().contains(comparar)))
                BajaAsociadoField.addItem(p);
        }
    }

    public void actualizarReporteMedicoLista(Map<IMedico, List<PacienteAtendido>> medicosRegistrados, String comparar) {
        ReporteMedicoField.removeAllItems();
        for (Map.Entry<IMedico, List<PacienteAtendido>> iMedicoListEntry : medicosRegistrados.entrySet()) {
            IMedico m = iMedicoListEntry.getKey();
            if ((m.getDNI().contains(comparar)) || (m.getNombre().contains(comparar)) || (m.getApellido().contains(comparar)))
                ReporteMedicoField.addItem(m);
        }
    }

    public void actualizarAmbulanciaAsociadosLista(Set<Asociado> asociados) {
        Iterator<Asociado> iterator = asociados.iterator();
        PanelAmbu.removeAll();
        AsociadosCheckBoxes.clear();
        while (iterator.hasNext()) {
            Asociado a = iterator.next();
            JCheckBox checkBox = new JCheckBox(a.toString());
            AsociadosCheckBoxes.add(checkBox);
            PanelAmbu.add(checkBox);
        }
        AmbulanciaAsociadosScrollPane.setViewportView(PanelAmbu);
    }
    //TERMINA ACA



    //PANEL AMBULANCIA
    public List<Asociado> getAsociadosAmbulancia(Set<Asociado> asociadosRegistrados) throws SinAsociadosSeleccionadosExcepcion {
        List<Asociado> asos1 = new ArrayList<>(asociadosRegistrados);

        ArrayList<Integer> cantidades = new ArrayList<>();
        for (int i = 0; i < AsociadosCheckBoxes.size(); i++) {
            if (AsociadosCheckBoxes.get(i).isSelected()) {
                cantidades.add(i);
            }
        }

        if (!cantidades.isEmpty()) {
            List<Asociado> aso = new ArrayList<>();
            for (int i = 0; i < cantidades.size(); i++) {
                aso.add(asos1.get(cantidades.get(i)));
            }
            return aso;
        } else throw new SinAsociadosSeleccionadosExcepcion();
    }

    public void cambiarEstadoAmbulancia(String estado) {
        EstadoText.setText(estado);
    }

    public void setBotonAmbulanciaPararNotEnabled() {
        AmbulanciaPararBoton.setEnabled(false);
        AmbulanciaTallerBoton.setEnabled(false);
    }

    public void panelAmbulanciaAsociados(Set<Asociado> asociadosRegistrados) {
        AmbulanciaEmpezarBoton.setVisible(false);
        AmbulanciaVolverBoton.setVisible(false);
        AmbulanciaPararBoton.setVisible(false);
        AmbulanciaTallerBoton.setVisible(false);
        AmbulanciaAsociadosBoton.setVisible(true);

        AsociadoBotonEnviar.setEnabled(true);
        BajaAsociadoBoton.setEnabled(true);

        this.actualizarAmbulanciaAsociadosLista(asociadosRegistrados);
    }

    public void panelAmbulanciaEmpezar(List<Asociado> asociadosAmbulancia) {
        AmbulanciaPararBoton.setVisible(false);
        AmbulanciaAsociadosBoton.setVisible(false);
        AmbulanciaTallerBoton.setVisible(false);
        AmbulanciaEmpezarBoton.setVisible(true);
        AmbulanciaVolverBoton.setVisible(true);

        for (JScrollPane jScrollPane : scrollPaneAmbulanciaAsociado) {
            PanelAmbulanciaTabbeado.remove(jScrollPane);
        }
        for (ObservadorAsociado observadoresAsociado : observadoresAsociados) {
            observadoresAsociado.eliminarObservable();
        }
        observadoresAsociados.clear();
        panelesAsociados.clear();
        scrollPaneAmbulanciaAsociado.clear();


        this.mostrarAmbulanciaCantidades(asociadosAmbulancia);
        panelesAsociados.clear();


        for (int i = 0; i < asociadosAmbulancia.size(); i++) {
            JPanel panel = new JPanel();
            panelesAsociados.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            scrollPaneAmbulanciaAsociado.add(new JScrollPane(panel));
            ObservadorAsociado observadorAsociado = new ObservadorAsociado(asociadosAmbulancia.get(i).getObservableAsociado(), this);
            PanelAmbulanciaTabbeado.add(scrollPaneAmbulanciaAsociado.get(i), observadorAsociado.getDatosAsociado());
            observadoresAsociados.add(observadorAsociado);
        }
    }

    public void panelAmbulanciaParar() {
        AmbulanciaAsociadosBoton.setVisible(false);
        AmbulanciaEmpezarBoton.setVisible(false);
        AmbulanciaVolverBoton.setVisible(false);
        AmbulanciaPararBoton.setEnabled(true);
        AmbulanciaTallerBoton.setEnabled(true);
        AmbulanciaTallerBoton.setText("Enviar a Taller");
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

    public void mostrarAmbulanciaCantidades(List<Asociado> asociados) {
        PanelAmbu.removeAll();
        tablaModelo.setRowCount(0);

        for (Asociado a : asociados) {
            tablaModelo.addRow(new Object[]{a, "1"});
        }

        PanelAmbu.add(new JLabel("Ingrese para cada asociado la cantidad de solicitudes:"));
        PanelAmbu.add(tabla);
        AmbulanciaAsociadosScrollPane.setViewportView(PanelAmbu);
    }

    public ArrayList<Integer> getCantidadSolicitudes() throws CantidadSolicitudesInvalidaExcepcion {
        ArrayList<Integer> cantidades = new ArrayList<>();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            String cantString = (String) tabla.getValueAt(i, 1);
            int cant = Integer.parseInt(cantString);
            if (cant < 1)
                throw new CantidadSolicitudesInvalidaExcepcion();
            else
                cantidades.add(cant);
        }
        return cantidades;
    }

    public void cambiarBotonTallerEnabled() {
        if (AmbulanciaPararBoton.isEnabled())
            AmbulanciaTallerBoton.setEnabled(!AmbulanciaTallerBoton.isEnabled());
    }

    public void cambiarBotonTallerTexto() {
        if (AmbulanciaTallerBoton.getText().equals("Enviar a Taller")) {
            AmbulanciaTallerBoton.setText("Retornar a Clinica");
        } else AmbulanciaTallerBoton.setText("Enviar a Taller");
    }
    //TERMINA ACA



    public void mostrarFactura(Factura factura) {
        JTextPane f = new JTextPane();
        f.setText(factura.imprimeFactura().toString());
        FacturaScrollPane.setViewportView(f);
    }

    public void mostrarReporteMedico(ReporteActividadMedica reporte) {
        JTextPane textoReporte = new JTextPane();
        textoReporte.setText(reporte.generarReporte());
        ReporteMedicoScrollPane.setViewportView(textoReporte);
    }

    public boolean confirmarBaja() {
        JOptionPane panel = new JOptionPane();
        int respuesta = JOptionPane.showConfirmDialog(panel, "¿Quiere eliminar el asociado seleccionado?", "Confirmacion de baja", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public void mostrarMensajeVentana(String mensaje) {
        JOptionPane panel = new JOptionPane();
        JOptionPane.showMessageDialog(panel, mensaje);
    }

    public void addActionListener(ActionListener actionListener) {
        PacienteBotonEnviar.addActionListener(actionListener);
        PacienteBotonEnviar.setActionCommand("PacienteBotonEnviar");

        MedicoBotonEnviar.addActionListener(actionListener);
        MedicoBotonEnviar.setActionCommand("MedicoBotonEnviar");

        IngresarPacienteBoton.addActionListener(actionListener);
        IngresarPacienteBoton.setActionCommand("IngresarPacienteBoton");

        IngresarPacienteBuscarBoton.addActionListener(actionListener);
        InternarPacienteBuscarBoton.setActionCommand("IngresarPacienteBuscarBoton");

        AtenderPacienteBuscarBoton.addActionListener(actionListener);
        AtenderPacienteBuscarBoton.setActionCommand("AtenderPacienteBuscarBoton");

        AtenderMedicoBuscarBoton.addActionListener(actionListener);
        AtenderMedicoBuscarBoton.setActionCommand("AtenderMedicoBuscarBoton");

        AtenderPacienteBoton.addActionListener(actionListener);
        AtenderPacienteBoton.setActionCommand("AtenderPacienteBoton");

        InternarPacienteBoton.addActionListener(actionListener);
        InternarPacienteBoton.setActionCommand("InternarPacienteBoton");

        InternarPacienteBuscarBoton.addActionListener(actionListener);
        InternarPacienteBuscarBoton.setActionCommand("InternarPacienteBuscarBoton");

        EgresarBoton.addActionListener(actionListener);
        EgresarBoton.setActionCommand("EgresarBoton");

        EgresarBuscarBoton.addActionListener(actionListener);
        EgresarBuscarBoton.setActionCommand("EgresarBuscarBoton");

        AsociadoBotonEnviar.addActionListener(actionListener);
        AsociadoBotonEnviar.setActionCommand("AsociadoBotonEnviar");

        BajaAsociadoBoton.addActionListener(actionListener);
        BajaAsociadoBoton.setActionCommand("BajaAsociadoBoton");

        BajaAsociadoBuscarBoton.addActionListener(actionListener);
        BajaAsociadoBuscarBoton.setActionCommand("BajaAsociadoBuscarBoton");

        ReporteMedicoBoton.addActionListener(actionListener);
        ReporteMedicoBoton.setActionCommand("ReporteMedicoBoton");

        ReporteMedicoBuscarBoton.addActionListener(actionListener);
        ReporteMedicoBuscarBoton.setActionCommand("ReporteMedicoBuscarBoton");

        AmbulanciaAsociadosBoton.addActionListener(actionListener);
        AmbulanciaAsociadosBoton.setActionCommand("AmbulanciaAsociadosBoton");

        AmbulanciaEmpezarBoton.addActionListener(actionListener);
        AmbulanciaEmpezarBoton.setActionCommand("AmbulanciaEmpezarBoton");

        AmbulanciaPararBoton.addActionListener(actionListener);
        AmbulanciaPararBoton.setActionCommand("AmbulanciaPararBoton");

        AmbulanciaVolverBoton.addActionListener(actionListener);
        AmbulanciaVolverBoton.setActionCommand("AmbulanciaVolverBoton");

        AmbulanciaTallerBoton.addActionListener(actionListener);
        AmbulanciaTallerBoton.setActionCommand("AmbulanciaTallerBoton");

        CrearTablasBoton.addActionListener(actionListener);
        CrearTablasBoton.setActionCommand("CrearTablasBoton");
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 200, 0, 200), -1, -1, false, true));
        MainPanel.setAlignmentX(0.5f);
        MainPanel.setDoubleBuffered(false);
        MainPanel.putClientProperty("html.disable", Boolean.FALSE);
        JTabbedPane panelTabbeado = new JTabbedPane();
        panelTabbeado.setBackground(new Color(-1579804));
        panelTabbeado.setDoubleBuffered(false);
        Font PanelTabbeadoFont = this.$$$getFont$$$("Roboto Light", -1, -1, panelTabbeado.getFont());
        if (PanelTabbeadoFont != null) panelTabbeado.setFont(PanelTabbeadoFont);
        panelTabbeado.setOpaque(false);
        panelTabbeado.setVerifyInputWhenFocusTarget(true);
        MainPanel.add(panelTabbeado, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 200), null, 0, false));
        JPanel panelPacientes = new JPanel();
        panelPacientes.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelPacientes.setDoubleBuffered(false);
        panelTabbeado.addTab("Pacientes", panelPacientes);
        JTabbedPane panelPacientesTabbeado = new JTabbedPane();
        Font PanelPacientesTabbeadoFont = this.$$$getFont$$$(null, -1, -1, panelPacientesTabbeado.getFont());
        if (PanelPacientesTabbeadoFont != null) panelPacientesTabbeado.setFont(PanelPacientesTabbeadoFont);
        panelPacientes.add(panelPacientesTabbeado, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        JPanel panelRegistroPaciente = new JPanel();
        panelRegistroPaciente.setLayout(new GridLayoutManager(20, 2, new Insets(0, 100, 0, 100), -1, -1));
        panelPacientesTabbeado.addTab("Registro", panelRegistroPaciente);
        JLabel DNIPacienteLabel = new JLabel();
        DNIPacienteLabel.setText("DNI");
        panelRegistroPaciente.add(DNIPacienteLabel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelRegistroPaciente.add(spacer1, new GridConstraints(2, 1, 18, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        DNIPacienteField = new JTextField();
        DNIPacienteField.setText("");
        panelRegistroPaciente.add(DNIPacienteField, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel nombrePacienteLabel = new JLabel();
        nombrePacienteLabel.setText("Nombre");
        panelRegistroPaciente.add(nombrePacienteLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        NombrePacienteField = new JTextField();
        NombrePacienteField.setText("");
        panelRegistroPaciente.add(NombrePacienteField, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel apellidoPacienteLabel = new JLabel();
        apellidoPacienteLabel.setText("Apellido");
        panelRegistroPaciente.add(apellidoPacienteLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ApellidoPacienteField = new JTextField();
        ApellidoPacienteField.setText("");
        panelRegistroPaciente.add(ApellidoPacienteField, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel callePacienteLabel = new JLabel();
        callePacienteLabel.setText("Calle");
        panelRegistroPaciente.add(callePacienteLabel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CallePacienteField = new JTextField();
        panelRegistroPaciente.add(CallePacienteField, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel numPacienteLabel = new JLabel();
        numPacienteLabel.setText("Numero");
        panelRegistroPaciente.add(numPacienteLabel, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        NumPacienteField = new JTextField();
        panelRegistroPaciente.add(NumPacienteField, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel ciudadPacienteLabel = new JLabel();
        ciudadPacienteLabel.setText("Ciudad");
        panelRegistroPaciente.add(ciudadPacienteLabel, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CiudadPacienteField = new JTextField();
        panelRegistroPaciente.add(CiudadPacienteField, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel telefonoPacienteLabel = new JLabel();
        telefonoPacienteLabel.setText("Telefono");
        panelRegistroPaciente.add(telefonoPacienteLabel, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TelefonoPacienteField = new JTextField();
        panelRegistroPaciente.add(TelefonoPacienteField, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel rangoPacienteLabel = new JLabel();
        rangoPacienteLabel.setText("Rango Etario");
        panelRegistroPaciente.add(rangoPacienteLabel, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RangoPacienteField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("NIÑO");
        defaultComboBoxModel1.addElement("JOVEN");
        defaultComboBoxModel1.addElement("MAYOR");
        RangoPacienteField.setModel(defaultComboBoxModel1);
        panelRegistroPaciente.add(RangoPacienteField, new GridConstraints(16, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel historiaPacienteLabel = new JLabel();
        historiaPacienteLabel.setText("Historia Clinica");
        panelRegistroPaciente.add(historiaPacienteLabel, new GridConstraints(17, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        HistoriaPacienteField = new JTextField();
        panelRegistroPaciente.add(HistoriaPacienteField, new GridConstraints(18, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        MuestraDeExcepcionPacienteLabel = new JLabel();
        MuestraDeExcepcionPacienteLabel.setText("Label");
        panelRegistroPaciente.add(MuestraDeExcepcionPacienteLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PacienteBotonEnviar = new JButton();
        Font PacienteBotonEnviarFont = this.$$$getFont$$$(null, -1, 14, PacienteBotonEnviar.getFont());
        if (PacienteBotonEnviarFont != null) PacienteBotonEnviar.setFont(PacienteBotonEnviarFont);
        PacienteBotonEnviar.setIconTextGap(4);
        PacienteBotonEnviar.setMargin(new Insets(0, 0, 0, 0));
        PacienteBotonEnviar.setText("Enviar");
        panelRegistroPaciente.add(PacienteBotonEnviar, new GridConstraints(19, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 40), null, 0, false));
        JPanel panelManejoPaciente = new JPanel();
        panelManejoPaciente.setLayout(new GridLayoutManager(16, 3, new Insets(0, 100, 0, 100), -1, -1));
        panelPacientesTabbeado.addTab("Manejo", panelManejoPaciente);
        JLabel ingresarPacienteLabel = new JLabel();
        ingresarPacienteLabel.setText("Elija el paciente que quiera ingresar");
        panelManejoPaciente.add(ingresarPacienteLabel, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        IngresarPacienteField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        IngresarPacienteField.setModel(defaultComboBoxModel2);
        panelManejoPaciente.add(IngresarPacienteField, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        IngresarPacienteBuscarField = new JTextField();
        panelManejoPaciente.add(IngresarPacienteBuscarField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        IngresarPacienteBuscarBoton = new JButton();
        IngresarPacienteBuscarBoton.setText("Buscar");
        panelManejoPaciente.add(IngresarPacienteBuscarBoton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel atenderPacienteLabel = new JLabel();
        atenderPacienteLabel.setText("Elija el paciente a atender y el medico correspondiente");
        panelManejoPaciente.add(atenderPacienteLabel, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AtenderPacienteField = new JComboBox();
        panelManejoPaciente.add(AtenderPacienteField, new GridConstraints(7, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AtenderMedicoField = new JComboBox();
        panelManejoPaciente.add(AtenderMedicoField, new GridConstraints(9, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AtenderPacienteBuscarField = new JTextField();
        panelManejoPaciente.add(AtenderPacienteBuscarField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AtenderMedicoBuscarField = new JTextField();
        panelManejoPaciente.add(AtenderMedicoBuscarField, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AtenderPacienteBuscarBoton = new JButton();
        AtenderPacienteBuscarBoton.setText("Buscar");
        panelManejoPaciente.add(AtenderPacienteBuscarBoton, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AtenderMedicoBuscarBoton = new JButton();
        AtenderMedicoBuscarBoton.setText("Buscar");
        panelManejoPaciente.add(AtenderMedicoBuscarBoton, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AtenderPacienteBoton = new JButton();
        Font AtenderPacienteBotonFont = this.$$$getFont$$$(null, -1, 14, AtenderPacienteBoton.getFont());
        if (AtenderPacienteBotonFont != null) AtenderPacienteBoton.setFont(AtenderPacienteBotonFont);
        AtenderPacienteBoton.setText("Atender Paciente");
        panelManejoPaciente.add(AtenderPacienteBoton, new GridConstraints(10, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        IngresarPacienteBoton = new JButton();
        Font IngresarPacienteBotonFont = this.$$$getFont$$$(null, -1, 14, IngresarPacienteBoton.getFont());
        if (IngresarPacienteBotonFont != null) IngresarPacienteBoton.setFont(IngresarPacienteBotonFont);
        IngresarPacienteBoton.setMargin(new Insets(0, 0, 0, 0));
        IngresarPacienteBoton.setText("Ingresar Paciente");
        panelManejoPaciente.add(IngresarPacienteBoton, new GridConstraints(3, 0, 2, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        JLabel atenderPacienteBuscarLabel = new JLabel();
        atenderPacienteBuscarLabel.setText("Paciente:");
        panelManejoPaciente.add(atenderPacienteBuscarLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel ingresarPacienteBuscarLabel = new JLabel();
        ingresarPacienteBuscarLabel.setText("Paciente:");
        panelManejoPaciente.add(ingresarPacienteBuscarLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel atenderMedicoBuscarLabel = new JLabel();
        atenderMedicoBuscarLabel.setText("Medico:");
        panelManejoPaciente.add(atenderMedicoBuscarLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel internarPacienteLabel = new JLabel();
        internarPacienteLabel.setText("Elija el paciente a internar y la habitacion");
        panelManejoPaciente.add(internarPacienteLabel, new GridConstraints(11, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InternarPacienteBuscarField = new JTextField();
        panelManejoPaciente.add(InternarPacienteBuscarField, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel internarPacienteBuscarLabel = new JLabel();
        internarPacienteBuscarLabel.setText("Paciente:");
        panelManejoPaciente.add(internarPacienteBuscarLabel, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InternarPacienteBuscarBoton = new JButton();
        InternarPacienteBuscarBoton.setText("Buscar");
        panelManejoPaciente.add(InternarPacienteBuscarBoton, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InternarPacienteField = new JComboBox();
        panelManejoPaciente.add(InternarPacienteField, new GridConstraints(13, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        HabitacionField = new JComboBox();
        panelManejoPaciente.add(HabitacionField, new GridConstraints(14, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        InternarPacienteBoton = new JButton();
        Font InternarPacienteBotonFont = this.$$$getFont$$$(null, -1, 14, InternarPacienteBoton.getFont());
        if (InternarPacienteBotonFont != null) InternarPacienteBoton.setFont(InternarPacienteBotonFont);
        InternarPacienteBoton.setText("Internar Paciente");
        panelManejoPaciente.add(InternarPacienteBoton, new GridConstraints(15, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        JPanel panelEgresar = new JPanel();
        panelEgresar.setLayout(new GridLayoutManager(5, 3, new Insets(0, 100, 0, 100), -1, -1));
        panelPacientesTabbeado.addTab("Egreso", panelEgresar);
        JLabel egresarLabel = new JLabel();
        egresarLabel.setText("Elija el paciente a egresar");
        panelEgresar.add(egresarLabel, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        EgresarBuscarField = new JTextField();
        panelEgresar.add(EgresarBuscarField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        EgresarBuscarBoton = new JButton();
        EgresarBuscarBoton.setText("Buscar");
        panelEgresar.add(EgresarBuscarBoton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel pacienteBuscarLabel = new JLabel();
        pacienteBuscarLabel.setText("Paciente:");
        panelEgresar.add(pacienteBuscarLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        EgresarField = new JComboBox();
        panelEgresar.add(EgresarField, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        EgresarBoton = new JButton();
        Font EgresarBotonFont = this.$$$getFont$$$(null, -1, 14, EgresarBoton.getFont());
        if (EgresarBotonFont != null) EgresarBoton.setFont(EgresarBotonFont);
        EgresarBoton.setText("Egresar Paciente");
        panelEgresar.add(EgresarBoton, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        FacturaScrollPane = new JScrollPane();
        panelEgresar.add(FacturaScrollPane, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JPanel panelPacientesLista = new JPanel();
        panelPacientesLista.setLayout(new GridLayoutManager(1, 1, new Insets(0, 100, 0, 100), -1, -1));
        panelPacientesTabbeado.addTab("Lista de Pacientes Registrados", panelPacientesLista);
        PacienteScrollPane = new JScrollPane();
        panelPacientesLista.add(PacienteScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JPanel panelMedicos = new JPanel();
        panelMedicos.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabbeado.addTab("Medicos", panelMedicos);
        JTabbedPane panelMedicosTabbeado = new JTabbedPane();
        panelMedicos.add(panelMedicosTabbeado, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        JPanel panelRegistroMedico = new JPanel();
        panelRegistroMedico.setLayout(new GridLayoutManager(24, 2, new Insets(0, 100, 0, 100), -1, -1));
        panelMedicosTabbeado.addTab("Registro", panelRegistroMedico);
        JLabel DNIMedicoLabel = new JLabel();
        DNIMedicoLabel.setText("DNI");
        panelRegistroMedico.add(DNIMedicoLabel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelRegistroMedico.add(spacer2, new GridConstraints(2, 1, 22, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        MedicoBotonEnviar = new JButton();
        Font MedicoBotonEnviarFont = this.$$$getFont$$$(null, -1, 14, MedicoBotonEnviar.getFont());
        if (MedicoBotonEnviarFont != null) MedicoBotonEnviar.setFont(MedicoBotonEnviarFont);
        MedicoBotonEnviar.setText("Enviar");
        panelRegistroMedico.add(MedicoBotonEnviar, new GridConstraints(23, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 40), null, 0, false));
        JLabel nombreMedicoLabel = new JLabel();
        nombreMedicoLabel.setText("Nombre");
        panelRegistroMedico.add(nombreMedicoLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        NombreMedicoField = new JTextField();
        NombreMedicoField.setText("");
        panelRegistroMedico.add(NombreMedicoField, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel apellidoMedicoLabel = new JLabel();
        apellidoMedicoLabel.setText("Apellido");
        panelRegistroMedico.add(apellidoMedicoLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ApellidoMedicoField = new JTextField();
        ApellidoMedicoField.setText("");
        panelRegistroMedico.add(ApellidoMedicoField, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel calleMedicoLabel = new JLabel();
        calleMedicoLabel.setText("Calle");
        panelRegistroMedico.add(calleMedicoLabel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CalleMedicoField = new JTextField();
        panelRegistroMedico.add(CalleMedicoField, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel numMedicoLabel = new JLabel();
        numMedicoLabel.setText("Numero");
        panelRegistroMedico.add(numMedicoLabel, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        NumMedicoField = new JTextField();
        panelRegistroMedico.add(NumMedicoField, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel ciudadMedicoLabel = new JLabel();
        ciudadMedicoLabel.setText("Ciudad");
        panelRegistroMedico.add(ciudadMedicoLabel, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CiudadMedicoField = new JTextField();
        panelRegistroMedico.add(CiudadMedicoField, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        MuestraDeExcepcionMedicoLabel = new JLabel();
        MuestraDeExcepcionMedicoLabel.setText("Label");
        panelRegistroMedico.add(MuestraDeExcepcionMedicoLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel matriculaMedicoLabel = new JLabel();
        matriculaMedicoLabel.setText("Matricula");
        panelRegistroMedico.add(matriculaMedicoLabel, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MatriculaMedicoField = new JTextField();
        panelRegistroMedico.add(MatriculaMedicoField, new GridConstraints(16, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel especialidadMedicoLabel = new JLabel();
        especialidadMedicoLabel.setText("Especialidad");
        panelRegistroMedico.add(especialidadMedicoLabel, new GridConstraints(17, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        EspecialidadMedicoField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("CIRUGIA");
        defaultComboBoxModel3.addElement("CLINICA");
        defaultComboBoxModel3.addElement("PEDIATRIA");
        EspecialidadMedicoField.setModel(defaultComboBoxModel3);
        panelRegistroMedico.add(EspecialidadMedicoField, new GridConstraints(18, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel contratacionMedicoLabel = new JLabel();
        contratacionMedicoLabel.setText("Contratacion");
        panelRegistroMedico.add(contratacionMedicoLabel, new GridConstraints(19, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ContratacionMedicoField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("PERMANENTE");
        defaultComboBoxModel4.addElement("RESIDENTE");
        ContratacionMedicoField.setModel(defaultComboBoxModel4);
        panelRegistroMedico.add(ContratacionMedicoField, new GridConstraints(20, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel posgradoMedicoLabel = new JLabel();
        posgradoMedicoLabel.setText("Posgrado");
        panelRegistroMedico.add(posgradoMedicoLabel, new GridConstraints(21, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PosgradoMedicoField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel5 = new DefaultComboBoxModel();
        defaultComboBoxModel5.addElement("NINGUNO");
        defaultComboBoxModel5.addElement("MAGISTER");
        defaultComboBoxModel5.addElement("DOCTORADO");
        PosgradoMedicoField.setModel(defaultComboBoxModel5);
        panelRegistroMedico.add(PosgradoMedicoField, new GridConstraints(22, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel telefonoMedicoLabel = new JLabel();
        telefonoMedicoLabel.setText("Telefono");
        panelRegistroMedico.add(telefonoMedicoLabel, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TelefonoMedicoField = new JTextField();
        panelRegistroMedico.add(TelefonoMedicoField, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        DNIMedicoField = new JTextField();
        panelRegistroMedico.add(DNIMedicoField, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JPanel panelAtendidosMedico = new JPanel();
        panelAtendidosMedico.setLayout(new GridLayoutManager(9, 2, new Insets(0, 100, 0, 100), -1, -1));
        panelMedicosTabbeado.addTab("Reporte", panelAtendidosMedico);
        JLabel reporteMedicoLabel = new JLabel();
        reporteMedicoLabel.setText("Elija el medico");
        panelAtendidosMedico.add(reporteMedicoLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ReporteMedicoBuscarField = new JTextField();
        ReporteMedicoBuscarField.setText("");
        panelAtendidosMedico.add(ReporteMedicoBuscarField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ReporteMedicoField = new JComboBox();
        panelAtendidosMedico.add(ReporteMedicoField, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ReporteMedicoBuscarBoton = new JButton();
        ReporteMedicoBuscarBoton.setText("Buscar");
        panelAtendidosMedico.add(ReporteMedicoBuscarBoton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ReporteMedicoBoton = new JButton();
        Font ReporteMedicoBotonFont = this.$$$getFont$$$(null, -1, 14, ReporteMedicoBoton.getFont());
        if (ReporteMedicoBotonFont != null) ReporteMedicoBoton.setFont(ReporteMedicoBotonFont);
        ReporteMedicoBoton.setText("Mostrar Reporte");
        panelAtendidosMedico.add(ReporteMedicoBoton, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 40), null, 0, false));
        FechaInicialField = new JTextField();
        FechaInicialField.setText("");
        FechaInicialField.setToolTipText("");
        panelAtendidosMedico.add(FechaInicialField, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FechaFinalField = new JTextField();
        FechaFinalField.setText("");
        panelAtendidosMedico.add(FechaFinalField, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel fechaInicialLabel = new JLabel();
        fechaInicialLabel.setText("Ingrese la fecha inicial - formato: DD/MM/AAAA");
        panelAtendidosMedico.add(fechaInicialLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabel fechaFinalLabel = new JLabel();
        fechaFinalLabel.setText("Ingrese la fecha final - formato: DD/MM/AAAA");
        panelAtendidosMedico.add(fechaFinalLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ReporteMedicoScrollPane = new JScrollPane();
        panelAtendidosMedico.add(ReporteMedicoScrollPane, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JPanel panelMedicosLista = new JPanel();
        panelMedicosLista.setLayout(new GridLayoutManager(1, 1, new Insets(0, 100, 0, 100), -1, -1));
        panelMedicosTabbeado.addTab("Lista de Medicos", panelMedicosLista);
        MedicoScrollPane = new JScrollPane();
        panelMedicosLista.add(MedicoScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JPanel panelAsociados = new JPanel();
        panelAsociados.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabbeado.addTab("Asociados", panelAsociados);
        JTabbedPane panelTabbeadoAsociado = new JTabbedPane();
        panelAsociados.add(panelTabbeadoAsociado, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 558), null, 0, false));
        JPanel panelRegistroAsociado = new JPanel();
        panelRegistroAsociado.setLayout(new GridLayoutManager(15, 1, new Insets(0, 100, 0, 100), -1, -1));
        panelTabbeadoAsociado.addTab("Registro", panelRegistroAsociado);
        JLabel DNIAsociadoLabel = new JLabel();
        DNIAsociadoLabel.setText("DNI");
        panelRegistroAsociado.add(DNIAsociadoLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DNIAsociadoField = new JTextField();
        panelRegistroAsociado.add(DNIAsociadoField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel nombreAsociadoLabel = new JLabel();
        nombreAsociadoLabel.setText("Nombre");
        panelRegistroAsociado.add(nombreAsociadoLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        NombreAsociadoField = new JTextField();
        panelRegistroAsociado.add(NombreAsociadoField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel apellidoAsociadoLabel = new JLabel();
        apellidoAsociadoLabel.setText("Apellido");
        panelRegistroAsociado.add(apellidoAsociadoLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ApellidoAsociadoField = new JTextField();
        panelRegistroAsociado.add(ApellidoAsociadoField, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel calleAsociadoLabel = new JLabel();
        calleAsociadoLabel.setText("Calle");
        panelRegistroAsociado.add(calleAsociadoLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CalleAsociadoField = new JTextField();
        panelRegistroAsociado.add(CalleAsociadoField, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel ciudadAsociadoLabel = new JLabel();
        ciudadAsociadoLabel.setText("Ciudad");
        panelRegistroAsociado.add(ciudadAsociadoLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CiudadAsociadoField = new JTextField();
        panelRegistroAsociado.add(CiudadAsociadoField, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel numAsociadoLabel = new JLabel();
        numAsociadoLabel.setText("Numero");
        panelRegistroAsociado.add(numAsociadoLabel, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        NumAsociadoField = new JTextField();
        panelRegistroAsociado.add(NumAsociadoField, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        JLabel telefonoAsociadoLabel = new JLabel();
        telefonoAsociadoLabel.setText("Telefono");
        panelRegistroAsociado.add(telefonoAsociadoLabel, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TelefonoAsociadoField = new JTextField();
        panelRegistroAsociado.add(TelefonoAsociadoField, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AsociadoBotonEnviar = new JButton();
        Font AsociadoBotonEnviarFont = this.$$$getFont$$$(null, -1, 14, AsociadoBotonEnviar.getFont());
        if (AsociadoBotonEnviarFont != null) AsociadoBotonEnviar.setFont(AsociadoBotonEnviarFont);
        AsociadoBotonEnviar.setText("Enviar");
        panelRegistroAsociado.add(AsociadoBotonEnviar, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 40), null, 0, false));
        JPanel panelBajaAsociado = new JPanel();
        panelBajaAsociado.setLayout(new GridLayoutManager(4, 2, new Insets(0, 100, 0, 100), -1, -1));
        panelTabbeadoAsociado.addTab("Baja", panelBajaAsociado);
        JLabel bajaAsociadoBuscarLabel = new JLabel();
        bajaAsociadoBuscarLabel.setText("Elija el Asociado");
        panelBajaAsociado.add(bajaAsociadoBuscarLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BajaAsociadoBuscarField = new JTextField();
        panelBajaAsociado.add(BajaAsociadoBuscarField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        BajaAsociadoField = new JComboBox();
        panelBajaAsociado.add(BajaAsociadoField, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BajaAsociadoBuscarBoton = new JButton();
        BajaAsociadoBuscarBoton.setText("Buscar");
        panelBajaAsociado.add(BajaAsociadoBuscarBoton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BajaAsociadoBoton = new JButton();
        Font BajaAsociadoBotonFont = this.$$$getFont$$$(null, -1, 14, BajaAsociadoBoton.getFont());
        if (BajaAsociadoBotonFont != null) BajaAsociadoBoton.setFont(BajaAsociadoBotonFont);
        BajaAsociadoBoton.setText("Bajar");
        panelBajaAsociado.add(BajaAsociadoBoton, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 40), null, 0, false));
        JPanel panelAsociadosLista = new JPanel();
        panelAsociadosLista.setLayout(new GridLayoutManager(1, 1, new Insets(0, 100, 0, 100), -1, -1));
        panelTabbeadoAsociado.addTab("Lista de Asociados", panelAsociadosLista);
        AsociadoScrollPane = new JScrollPane();
        panelAsociadosLista.add(AsociadoScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JPanel panelAmbulancia = new JPanel();
        panelAmbulancia.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabbeado.addTab("Ambulancia", panelAmbulancia);
        PanelAmbulanciaTabbeado = new JTabbedPane();
        panelAmbulancia.add(PanelAmbulanciaTabbeado, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        JPanel panelAmbulanciaSimulacion = new JPanel();
        panelAmbulanciaSimulacion.setLayout(new GridLayoutManager(5, 2, new Insets(0, 100, 0, 100), -1, -1));
        PanelAmbulanciaTabbeado.addTab("Simulacion", panelAmbulanciaSimulacion);
        EstadoText = new JTextArea();
        EstadoText.setAlignmentX(0.3f);
        EstadoText.setEditable(false);
        Font EstadoTextFont = this.$$$getFont$$$(null, -1, 16, EstadoText.getFont());
        if (EstadoTextFont != null) EstadoText.setFont(EstadoTextFont);
        EstadoText.setLineWrap(false);
        EstadoText.setMargin(new Insets(100, 100, 100, 100));
        EstadoText.setTabSize(8);
        panelAmbulanciaSimulacion.add(EstadoText, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(536, 50), null, 0, false));
        AmbulanciaAsociadosBoton = new JButton();
        Font AmbulanciaAsociadosBotonFont = this.$$$getFont$$$(null, -1, 14, AmbulanciaAsociadosBoton.getFont());
        if (AmbulanciaAsociadosBotonFont != null) AmbulanciaAsociadosBoton.setFont(AmbulanciaAsociadosBotonFont);
        AmbulanciaAsociadosBoton.setHorizontalTextPosition(0);
        AmbulanciaAsociadosBoton.setMargin(new Insets(0, 0, 0, 0));
        AmbulanciaAsociadosBoton.setText("Confirmar Asociados");
        panelAmbulanciaSimulacion.add(AmbulanciaAsociadosBoton, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), null, 0, false));
        AmbulanciaAsociadosScrollPane = new JScrollPane();
        AmbulanciaAsociadosScrollPane.setHorizontalScrollBarPolicy(30);
        panelAmbulanciaSimulacion.add(AmbulanciaAsociadosScrollPane, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AmbulanciaEmpezarBoton = new JButton();
        Font AmbulanciaEmpezarBotonFont = this.$$$getFont$$$(null, -1, 14, AmbulanciaEmpezarBoton.getFont());
        if (AmbulanciaEmpezarBotonFont != null) AmbulanciaEmpezarBoton.setFont(AmbulanciaEmpezarBotonFont);
        AmbulanciaEmpezarBoton.setText("Empezar Simulación");
        panelAmbulanciaSimulacion.add(AmbulanciaEmpezarBoton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), null, 0, false));
        AmbulanciaVolverBoton = new JButton();
        Font AmbulanciaVolverBotonFont = this.$$$getFont$$$(null, -1, 14, AmbulanciaVolverBoton.getFont());
        if (AmbulanciaVolverBotonFont != null) AmbulanciaVolverBoton.setFont(AmbulanciaVolverBotonFont);
        AmbulanciaVolverBoton.setText("Volver Atras");
        panelAmbulanciaSimulacion.add(AmbulanciaVolverBoton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 50), null, 0, false));
        AmbulanciaPararBoton = new JButton();
        Font AmbulanciaPararBotonFont = this.$$$getFont$$$(null, -1, 14, AmbulanciaPararBoton.getFont());
        if (AmbulanciaPararBotonFont != null) AmbulanciaPararBoton.setFont(AmbulanciaPararBotonFont);
        AmbulanciaPararBoton.setText("Parar Simulación");
        panelAmbulanciaSimulacion.add(AmbulanciaPararBoton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), null, 0, false));
        AmbulanciaTallerBoton = new JButton();
        Font AmbulanciaTallerBotonFont = this.$$$getFont$$$(null, -1, 14, AmbulanciaTallerBoton.getFont());
        if (AmbulanciaTallerBotonFont != null) AmbulanciaTallerBoton.setFont(AmbulanciaTallerBotonFont);
        AmbulanciaTallerBoton.setText("Enviar a Taller");
        panelAmbulanciaSimulacion.add(AmbulanciaTallerBoton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), null, 0, false));
        JPanel panelCrearTablas = new JPanel();
        panelCrearTablas.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabbeado.addTab("Creacion de Tablas", panelCrearTablas);
        CrearTablasBoton = new JButton();
        Font CrearTablasBotonFont = this.$$$getFont$$$(null, -1, 20, CrearTablasBoton.getFont());
        if (CrearTablasBotonFont != null) CrearTablasBoton.setFont(CrearTablasBotonFont);
        CrearTablasBoton.setText("Crear Tablas");
        panelCrearTablas.add(CrearTablasBoton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 100), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }

}