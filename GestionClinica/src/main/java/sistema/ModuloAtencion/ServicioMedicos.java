package sistema.ModuloAtencion;

import excepciones.MedicoDuplicadoExcepcion;
import honorarios.IMedico;
import facturacion.PacienteAtendido;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
// imports de java.time y streams removidos para simplificar la lectura

public class ServicioMedicos {
    
    private final Map<IMedico, List<PacienteAtendido>> medicos = new HashMap<>();
    
    public void registrarMedico(IMedico medico) throws MedicoDuplicadoExcepcion {
        if (medicos.get(medico) != null) {
            throw new MedicoDuplicadoExcepcion();
        }
        else medicos.put(medico, new ArrayList<>());
    }

    public boolean estaRegistrado(IMedico medico) {
        return medicos.containsKey(medico);
    }
    
    public void removerMedico(IMedico medico) {
        medicos.remove(medico);
    }
    
    public void agregarAtencion(IMedico medico, PacienteAtendido atencion) {
        medicos.get(medico).add(atencion);
    }
    
//    public List<PacienteAtendido> getAtencionesDelMedico(IMedico medico) {
//        return medicos.getOrDefault(medico);
//    }
//
    public int getCantidadPacientesAtendidos(IMedico medico) {
        List<PacienteAtendido> atenciones = medicos.get(medico);
        return atenciones == null ? 0 : atenciones.size();
    }
    
    public double getHonorariosTotalesMedico(IMedico medico) {
        List<PacienteAtendido> atenciones = medicos.get(medico);
        if (atenciones == null) {
            return 0.0;
        }
        return atenciones.stream()
                .mapToDouble(PacienteAtendido::getHonorario)
                .sum();
    }

    public Set<IMedico> getMedicosRegistrados() {
        return medicos.keySet();
    }
    
    public List<PacienteAtendido> getAtencionesDelMedico(IMedico medico) {
        return medicos.getOrDefault(medico, new ArrayList<>());
    }

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
