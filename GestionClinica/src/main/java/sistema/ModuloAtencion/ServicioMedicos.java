package sistema.ModuloAtencion;

import excepciones.MedicoDuplicadoExcepcion;
import interfaces.IMedico;
import facturacion.PacienteAtendido;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
// imports de java.time y streams removidos para simplificar la lectura

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
        if (medicos.get(medico) != null) {
            throw new MedicoDuplicadoExcepcion();
        }
        else medicos.put(medico, new ArrayList<>());
    }

    /**
     * Verifica si el médico esta registrado o no
     * @param medico medico!=null
     * @return true or false
     */
    public boolean estaRegistrado(IMedico medico) {
        return medicos.containsKey(medico);
    }

    /**
     * En el caso de que se quiera eliminar un médico<br>
     * <b>Precondición: </b> El médico debe haber sido registrado anteriormente<br>
     * <b>Postcondición:</b> Médico eliminado
     * @param medico Médico a remover, medico!=null
     */
    public void removerMedico(IMedico medico) {
        medicos.remove(medico);
    }

    /**
     * Se agrega un la atencion de un paciente a la lista de atencion que tiene un médico
     * @param medico Médico al cual se le agrega la atención, médico != null
     * @param atencion Donde se encuentran los datos de dicha atención de un paciente
     */
    public void agregarAtencion(IMedico medico, PacienteAtendido atencion) {
        medicos.get(medico).add(atencion);
    }

    /*public int getCantidadPacientesAtendidos(IMedico medico) {
        List<PacienteAtendido> atenciones = medicos.get(medico);
        return atenciones == null ? 0 : atenciones.size();
    }*/

    public Set<IMedico> getMedicosRegistrados() {
        return medicos.keySet();
    }

    /**
     * Muestra de atenciones del médico
     * @param medico Médico del cual se mostrarán las atenciones, medico != null
     * @return Se retorna el médico con su lista de atenciones
     */
    public List<PacienteAtendido> getAtencionesDelMedico(IMedico medico) {
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
                    // si una fecha individual es inválida, se omite esa entrada
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
            throw new IllegalArgumentException("Formato de fecha inválido. Use dd/MM/yyyy");
        }
    }
    
}
