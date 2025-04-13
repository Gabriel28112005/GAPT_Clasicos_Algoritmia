package juegos.N_Reinas;

import juegos.N_Reinas.Vista.Vista_Reina;
import juegos.N_Reinas.Modelo.Modelo_Reina;
import juegos.N_Reinas.Controlador.Controlador_Reina;

import javax.swing.*;

public class Main_Reina {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vista_Reina vista = new Vista_Reina();
            Modelo_Reina modelo = new Modelo_Reina();
            new Controlador_Reina(vista, modelo);
            vista.setVisible(true);
        });
    }
}//Fin del Main_Reina