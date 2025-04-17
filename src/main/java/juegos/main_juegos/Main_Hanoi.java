package juegos.main_juegos;

import juegos.vistas.Vista_Hanoi;
import juegos.modelos.Modelo_Hanoi;
import juegos.controlador.Controlador_Hanoi;

import javax.swing.SwingUtilities;

public class Main_Hanoi {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Vista_Hanoi vista = new Vista_Hanoi();
                Modelo_Hanoi modelo = new Modelo_Hanoi();
                new Controlador_Hanoi(vista, modelo);
                vista.setVisible(true);
            }

        });

    }

} //Fin de la clase Main_Hanoi