package modelo.prueba;

import modelo.ambulancia.Ambulancia;

/**
 * Clase de prueba simplificada para demostrar el funcionamiento del patrón State en la ambulancia
 */
public class TestAmbulancia {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DEL SISTEMA DE AMBULANCIA ===");
        System.out.println();
        
        // Obtener la instancia única de la ambulancia
        Ambulancia ambulancia = Ambulancia.get_instance();
        
        // Estado inicial
        System.out.println("Estado inicial: " + ambulancia.getEstado());
        System.out.println();
        
        // Solicitar atención a domicilio
        System.out.println("--- Solicitando atención a domicilio ---");
        ambulancia.solicitaAtencionDomicilio();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Regresar a la clínica
        System.out.println("--- Regresando a la clínica ---");
        ambulancia.retornarAClinica();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Llegar a la clínica (volver a disponible)
        System.out.println("--- Llegando a la clínica ---");
        ambulancia.retornarAClinica();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Solicitar traslado
        System.out.println("--- Solicitando traslado ---");
        ambulancia.solicitaTraslado();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Llegar a la clínica (volver a disponible)
        System.out.println("--- Llegando a la clínica ---");
        ambulancia.retornarAClinica();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Solicitar reparación
        System.out.println("--- Solicitando reparación ---");
        ambulancia.repararAmbulancia();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Regresar del taller
        System.out.println("--- Regresando del taller ---");
        ambulancia.repararAmbulancia();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Llegar a la clínica (volver a disponible)
        System.out.println("--- Llegando a la clínica ---");
        ambulancia.retornarAClinica();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // PRUEBAS DE OPERACIONES INVÁLIDAS
        // Ir a estado de atención a domicilio
        System.out.println("--- Yendo a atención a domicilio ---");
        ambulancia.solicitaAtencionDomicilio();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Intentar operaciones no válidas en este estado
        System.out.println("--- Intentando solicitar atención a domicilio (ya está atendiendo) ---");
        ambulancia.solicitaAtencionDomicilio();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        System.out.println("--- Intentando solicitar traslado (no disponible) ---");
        ambulancia.solicitaTraslado();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        System.out.println("--- Intentando solicitar reparación (no disponible) ---");
        ambulancia.repararAmbulancia();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        // Ir a estado de taller
        System.out.println("--- Yendo a taller ---");
        ambulancia.retornarAClinica(); // Regresando a la clínica
        ambulancia.retornarAClinica(); // Disponible
        ambulancia.repararAmbulancia(); // En taller
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        System.out.println("--- Intentando solicitar atención a domicilio (en taller) ---");
        ambulancia.solicitaAtencionDomicilio();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();
        
        System.out.println("--- Intentando solicitar traslado (en taller) ---");
        ambulancia.solicitaTraslado();
        System.out.println("Estado: " + ambulancia.getEstado());
        System.out.println();

        
        System.out.println("=== FIN DE LA PRUEBA ===");
    }
}