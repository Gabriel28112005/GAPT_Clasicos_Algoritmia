package juegos.main_juegos;

import juegos.vistas.Vista_Caballo;
import juegos.modelos.Modelo_Caballo;
import juegos.controlador.Controlador_Caballo;

import javax.swing.SwingUtilities;

public class Main_Caballo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Vista_Caballo vista = new Vista_Caballo();
                Modelo_Caballo modelo = new Modelo_Caballo();
                new Controlador_Caballo(vista, modelo);
                vista.setVisible(true);
            }

        });

    }

} //Fin de la clase Main_Caballo