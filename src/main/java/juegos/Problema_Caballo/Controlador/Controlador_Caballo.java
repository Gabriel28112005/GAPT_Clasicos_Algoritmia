package juegos.Problema_Caballo.Controlador;

import juegos.Problema_Caballo.Modelo.Modelo_Caballo;
import juegos.Problema_Caballo.Vista.Vista_Caballo;

import javax.swing.*;

public class Controlador_Caballo {
    private Vista_Caballo vista;
    private Modelo_Caballo modelo;

    public Controlador_Caballo(Vista_Caballo vista, Modelo_Caballo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBotonResolver().addActionListener(e -> {
            try {
                int n = Integer.parseInt(vista.getCampoTamaño().getText());
                int fila = Integer.parseInt(vista.getCampoFila().getText());
                int col = Integer.parseInt(vista.getCampoColumna().getText());

                if (n <= 0 || fila < 0 || col < 0 || fila >= n || col >= n) {
                    throw new NumberFormatException();
                }

                modelo.resolver(n, fila, col);
                vista.getEtiquetaResultado().setText("Movimientos realizados: " + modelo.numeroDeMovimientos);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese valores válidos dentro del tablero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
} //Fin de la clase Controlador Caballo