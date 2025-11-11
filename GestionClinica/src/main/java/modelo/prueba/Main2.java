package modelo.prueba;

import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import controlador.Controlador;
import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.ObservadorAmbulancia;
import modelo.factoria.FactoryMedico;
import modelo.factoria.FactoryPaciente;
import modelo.sistema.Clinica;
import modelo.sistema.SistemaFacade;
import vista.Ventana;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main2 {

    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(new MintLookAndFeel());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        Ventana v = new Ventana();
        ObservadorAmbulancia observadorAmbulancia = new ObservadorAmbulancia(Ambulancia.get_instance(), v);
        Clinica clinica = Clinica.getInstancia("Clinica Central", "Avenida San Martin", 105, "+54 11 1234-5678", "Buenos Aires");
        Controlador controlador = new Controlador(v,SistemaFacade.getInstancia(clinica));
    }
}
