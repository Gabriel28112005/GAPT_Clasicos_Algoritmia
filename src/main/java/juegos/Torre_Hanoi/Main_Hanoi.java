package juegos.Torre_Hanoi;

import juegos.Torre_Hanoi.Vista.Vista_Hanoi;
import juegos.Torre_Hanoi.Modelo.Modelo_Hanoi;
import juegos.Torre_Hanoi.Controlador.Controlador_Hanoi;

import javax.swing.SwingUtilities;

public class Main_Hanoi {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Vista_Hanoi vista = new Vista_Hanoi();
                Modelo_Hanoi modelo = new Modelo_Hanoi();
                new Controlador_Hanoi(vista, modelo);
                vista.setVisible(true);
            }
        });
    }
}
