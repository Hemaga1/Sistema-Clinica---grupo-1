package modelo.prueba;

import com.jtattoo.plaf.mint.MintLookAndFeel;
import controlador.Controlador;
import modelo.ambulancia.Ambulancia;
import modelo.factoria.FactoryMedico;
import modelo.factoria.FactoryPaciente;
import modelo.interfaces.IMedico;
import modelo.lugares.Habitacion;
import modelo.lugares.HabitacionCompartida;
import modelo.lugares.HabitacionPrivada;
import modelo.lugares.HabitacionTerapiaIntensiva;
import modelo.observadores_y_observables.ObservadorAmbulancia;
import modelo.personas.Paciente;
import modelo.sistema.Clinica;
import modelo.sistema.SistemaFacade;
import vista.Ventana;

import javax.swing.*;

public class MainVentana {

    public static void main(String[] args)
    {
        FactoryMedico factoryMedico = new FactoryMedico();
        FactoryPaciente factoryPaciente = new FactoryPaciente();


        Clinica clinica = Clinica.getInstancia("Clinica Central", "Avenida San Martin", 105, "+54 11 1234-5678", "Buenos Aires");
        SistemaFacade sistemaFacade = SistemaFacade.getInstancia(clinica);



        //Algunos medicos y pacientes registrados para iniciar el programa
        //Medicos
        IMedico med1 = factoryMedico.crearMedico("10000000", "Ana", "García", "Calle 1", 1, "CABA", "111-1111", "M-123", "CLINICA", "PERMANENTE", "MASTER");
        IMedico med2 = factoryMedico.crearMedico("10000001", "Bruno", "López", "Calle 2", 2, "CABA", "222-2222", "M-456", "CIRUGIA", "RESIDENTE", "DOCTORADO");
        IMedico med3 = factoryMedico.crearMedico("10000002", "Bianca", "González", "Calle 3", 3, "CABA", "333-3333", "M-534", "PEDIATRIA", "PERMANENTE", "DOCTORADO");
        IMedico med4 = factoryMedico.crearMedico("10000003", "Roque", "Pérez", "Calle 4", 4, "CABA", "444-4444", "M-765", "CIRUGIA", "RESIDENTE", "MASTER");
        IMedico med5 = factoryMedico.crearMedico("10000004", "Oscar", "Rodríguez", "Calle 5", 5, "CABA", "555-5555", "M-876", "CLINICA", "PERMANENTE", "NINGUNO");
        IMedico med6 = factoryMedico.crearMedico("10000005", "Pedro", "Fernández", "Calle 6", 6, "CABA", "666-6666", "M-234", "CIRUGIA", "RESIDENTE", "MASTER");
        IMedico med7 = factoryMedico.crearMedico("10000006", "Andres", "Gutiérrez", "Calle 7", 7, "CABA", "777-7777", "M-543", "PEDIATRIA", "PERMANENTE", "NINGUNO");

        //Pacientes
        Paciente p1 = factoryPaciente.crearPaciente("20000000", "Juan", "Pérez", "Calle 10", 10, "CABA", "300-0000", 12345, "JOVEN");
        Paciente p2 = factoryPaciente.crearPaciente("20000001", "Lucía", "Suárez", "Calle 11", 11, "CABA", "300-0001", 22345, "NINIO");
        Paciente p3 = factoryPaciente.crearPaciente("20000002", "Mario", "Gómez", "Calle 12", 12, "CABA", "300-0002", 32345, "MAYOR");
        Paciente p4 = factoryPaciente.crearPaciente("20000003", "Paulo", "Moreno", "Calle 13", 13, "CABA", "300-0003", 42345, "JOVEN");
        Paciente p5 = factoryPaciente.crearPaciente("20000004", "Rocío", "Flores", "Calle 14", 14, "CABA", "300-0004", 52345, "NINIO");
        Paciente p6 = factoryPaciente.crearPaciente("20000005", "Martín", "Torres", "Calle 15", 15, "CABA", "300-0005", 62345, "MAYOR");

        //Habitaciones
        Habitacion habPrivada1 = new HabitacionPrivada(2000);
        Habitacion habPrivada2 = new HabitacionPrivada(3000);
        Habitacion habCompartida1 = new HabitacionCompartida(1500, 2);
        Habitacion habTerapiaIntensiva1 = new HabitacionTerapiaIntensiva(2000);



        //Agregar los medicos, pacientes y habitaciones al sistema
        try {
            sistemaFacade.registraMedico(med1);
            sistemaFacade.registraMedico(med2);
            sistemaFacade.registraMedico(med3);
            sistemaFacade.registraMedico(med4);
            sistemaFacade.registraMedico(med5);
            sistemaFacade.registraMedico(med6);
            sistemaFacade.registraMedico(med7);
            sistemaFacade.registraPaciente(p1);
            sistemaFacade.registraPaciente(p2);
            sistemaFacade.registraPaciente(p3);
            sistemaFacade.registraPaciente(p4);
            sistemaFacade.registraPaciente(p5);
            sistemaFacade.registraPaciente(p6);
            sistemaFacade.cargarDesdeBD();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sistemaFacade.agregarHabitacion(habPrivada1);
        sistemaFacade.agregarHabitacion(habPrivada2);
        sistemaFacade.agregarHabitacion(habCompartida1);
        sistemaFacade.agregarHabitacion(habTerapiaIntensiva1);



        //Libreria para cambiarle el estilo a la interfaz grafica
        try {
            UIManager.setLookAndFeel(new MintLookAndFeel());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        //Creacion de ventana e inicializacion de listas
        Ventana v = new Ventana();
        v.actualizarPacientesRegistradosLista(sistemaFacade.getPacientesRegistrados());
        v.actualizarMedicosRegistradosLista(sistemaFacade.getMedicos());
        v.actualizarAsociadosRegistradosLista(sistemaFacade.getAsociados());
        v.actualizarIngresarPacienteLista(sistemaFacade.getPacientesRegistrados(), "");
        v.actualizarAtenderPacienteLista(sistemaFacade.getListaEspera(), sistemaFacade.getPacientesAtendidos(), "");
        v.actualizarAtenderMedicoLista(sistemaFacade.getMedicos(), "");
        v.actualizarBajaAsociadosLista(sistemaFacade.getAsociados(),  v.getBajaAsociadoBusqueda());
        v.actualizarHabitaciones(sistemaFacade.getHabitaciones());
        v.actualizarReporteMedicoLista(sistemaFacade.getMedicos(), "");
        v.actualizarAmbulanciaAsociadosLista(sistemaFacade.getAsociados());

        ObservadorAmbulancia observadorAmbulancia = new ObservadorAmbulancia(Ambulancia.get_instance(), v);

        Controlador controlador = new Controlador(v,sistemaFacade,factoryMedico,factoryPaciente);
    }
}
