package modelo.prueba;

import controlador.Controlador;
import modelo.factoria.FactoryMedico;
import modelo.factoria.FactoryPaciente;
import modelo.sistema.Clinica;
import modelo.sistema.SistemaFacade;
import vista.Ventana;

public class Main2 {

    public static void main(String[] args)
    {
        Ventana v = new Ventana();
        Clinica clinica = Clinica.getInstancia("Clinica Central", "Avenida San Martin", 105, "+54 11 1234-5678", "Buenos Aires");
        Controlador controlador = new Controlador(v,SistemaFacade.getInstancia(clinica));
    }
}
