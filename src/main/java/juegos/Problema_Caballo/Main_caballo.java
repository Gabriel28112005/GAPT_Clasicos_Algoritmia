package juegos.Problema_Caballo;

import juegos.Problema_Caballo.Vista.Vista_Caballo;
import juegos.Problema_Caballo.Modelo.Modelo_Caballo;
import juegos.Problema_Caballo.Controlador.Controlador_Caballo;

import javax.swing.SwingUtilities;

public class Main_caballo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Vista_Caballo vista = new Vista_Caballo();
                Modelo_Caballo modelo = new Modelo_Caballo();
                new Controlador_Caballo(vista, modelo);
                vista.setVisible(true);
            }
        });
    }
}