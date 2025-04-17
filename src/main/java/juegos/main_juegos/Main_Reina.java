package juegos.main_juegos;

import juegos.vistas.Vista_Reina;
import juegos.modelos.Modelo_Reina;
import juegos.controlador.Controlador_Reina;

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