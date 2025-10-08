package sistema.ModuloEgreso;

import personas.Paciente;
import facturacion.Factura;
import facturacion.RegistroPaciente;

public class ServicioFacturacion {
    
    public Factura generarFactura(Paciente paciente, RegistroPaciente registro) {
        if (registro == null) {
            throw new IllegalArgumentException("No hay registro de atención para el paciente");
        }
        
        Factura factura = new Factura(paciente, registro);
        System.out.println(factura.ImprimeFactura());
        return factura;
    }
    
    public Factura generarFacturaConInternacion(Paciente paciente, RegistroPaciente registro, int cantDiasInternado) {
        if (registro == null) {
            throw new IllegalArgumentException("No hay registro de atención para el paciente");
        }
        
        registro.setCantDiasInternado(cantDiasInternado);
        Factura factura = new Factura(paciente, registro);
        System.out.println(factura.ImprimeFactura());
        return factura;
    }
}
