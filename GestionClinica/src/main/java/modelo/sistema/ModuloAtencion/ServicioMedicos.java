package modelo.sistema.ModuloAtencion;

import modelo.excepciones.MedicoDuplicadoExcepcion;
import modelo.interfaces.IMedico;
import modelo.facturacion_y_registros.PacienteAtendido;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
// imports de java.time y streams removidos para simplificar la lectura

/**
 * Clase que contiene operaciones del médico
 */
public class ServicioMedicos {
    
    private final Map<IMedico, List<PacienteAtendido>> medicos = new HashMap<>();

    /**
     * Registra un médico<br>
     * <b>Precondición: </b> El médico no debe haberse registrado anteriormente<br>
     * <b>Postcondición:</b>
     * <ul>
     *  <li>Si el médico estaba registrado no se registra nuevamente y se lanza una excepción</li>
     *  <li>Si el médico no estaba registrado se procede a registrarlo.</li>
     * </ul>
     *
     * @param medico El objeto medico a registrar, medico != null
     * @throws MedicoDuplicadoExcepcion
     */
    public void registrarMedico(IMedico medico) throws MedicoDuplicadoExcepcion {
        assert medico!=null : "El medico a registrar no puede ser null";
        if (medicos.get(medico) != null) {
            throw new MedicoDuplicadoExcepcion();
        }
        else medicos.put(medico, new ArrayList<>());
        assert medicos.get(medico).isEmpty() : "El listado de consultas del médico debe estar vacío tras registrarlo";
    }

    /**
     * Verifica si el médico esta registrado o no
     * @param medico medico!=null
     * @return true or false
     */
    public boolean estaRegistrado(IMedico medico) {
        assert medico!=null : "El medico a ver si esta registrado no puede ser null";
        return medicos.containsKey(medico);
    }

    /**
     * En el caso de que se quiera eliminar un médico<br>
     * <b>Precondición: </b> El médico debe haber sido registrado anteriormente<br>
     * <b>Postcondición:</b> Médico eliminado
     * @param medico Médico a remover, medico!=null
     */
    public void removerMedico(IMedico medico) {
        assert medico!=null : "El medico a remover no puede ser null";
        medicos.remove(medico);
    }

    /**
     * Se agrega un la atencion de un paciente a la lista de atencion que tiene un médico
     * @param medico Médico al cual se le agrega la atención, médico != null
     * @param atencion Donde se encuentran los datos de dicha atención de un paciente
     */
    public void agregarAtencion(IMedico medico, PacienteAtendido atencion) {
        assert medico!=null : "El medico a agregar atencion no puede ser null";
        assert atencion!=null : "La atencion a ser agregada a las atenciones del medico no puede ser null";
        medicos.get(medico).add(atencion);
    }

    /**
     * Muestra de atenciones del médico
     * @param medico Médico del cual se mostrarán las atenciones, medico != null
     * @return Se retorna el médico con su lista de atenciones
     */
    public List<PacienteAtendido> getAtencionesDelMedico(IMedico medico) {
        assert medico!=null : "El medico del cual se quieren traer las atenciones no debe ser null";
        return medicos.getOrDefault(medico, new ArrayList<>());
    }

    /**
     * Muestra de atenciones del médico de cierta fecha a otra fecha
     * @param medico Médico del cual se mostrarán las atenciones, medico != null
     * @param fechaInicio Fecha de inicio, fechaInicio!=null, fechaInicio!=""
     * @param fechaFin Fecha de fin, fechaFin!=null, fechaFin!=""
     * @return Se retorna el médico con su lista de atenciones
     */
    public List<PacienteAtendido> getAtencionesDelMedicoPorPeriodo(IMedico medico, String fechaInicio, String fechaFin) {
        assert medico!=null : "El medico del cual se quieren traer las atenciones no debe ser null";
        assert fechaFin!=null && fechaFin!="" : "La fecha limite de querer ver atenciones no puede ser null";
        assert fechaInicio!=null && fechaInicio!="" : "La fecha inicio de querer ver atenciones no puede ser null";
        List<PacienteAtendido> todas = getAtencionesDelMedico(medico);
        List<PacienteAtendido> resultado = new ArrayList<>();
        if (todas == null || todas.isEmpty()) {
            return resultado;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date inicio = sdf.parse(fechaInicio);
            Date fin = sdf.parse(fechaFin);

            for (PacienteAtendido atencion : todas) {
                try {
                    Date fechaConsulta = sdf.parse(atencion.getFecha());
                    if (!fechaConsulta.before(inicio) && !fechaConsulta.after(fin)) {
                        resultado.add(atencion);
                    }
                } catch (ParseException ignored) {
                    return null;
                }
            }

            Collections.sort(resultado, new Comparator<PacienteAtendido>() {
                @Override
                public int compare(PacienteAtendido a1, PacienteAtendido a2) {
                    try {
                        Date d1 = sdf.parse(a1.getFecha());
                        Date d2 = sdf.parse(a2.getFecha());
                        return d1.compareTo(d2);
                    } catch (ParseException e) {
                        return 0;
                    }
                }
            });

            return resultado;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Obtiene todos los médicos registrados con sus atenciones
     * @return Map de médicos con sus listas de atenciones
     */
    public Map<IMedico, List<PacienteAtendido>> getMedicos() {
        return medicos;
    }
    
}
