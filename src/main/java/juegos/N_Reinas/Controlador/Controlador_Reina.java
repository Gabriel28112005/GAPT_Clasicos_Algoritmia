package juegos.N_Reinas.Controlador;

import juegos.N_Reinas.Modelo.Modelo_Reina;
import juegos.N_Reinas.Vista.Vista_Reina;
import juegos.Matriz;
import juegos.Figura;

import javax.swing.*;

public class Controlador_Reina {
    private Vista_Reina vista;
    private Modelo_Reina modelo;
    private boolean solucionesCalculadas = false;
    private int ultimoTamaño = -1;

    public Controlador_Reina(Vista_Reina vista, Modelo_Reina modelo) {
        this.vista = vista;
        this.modelo = modelo;
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBotonResolver().addActionListener(e -> {
            try {
                int n = Integer.parseInt(vista.getCampoDimension().getText());
                if (n <= 0) throw new NumberFormatException();

                // Si es un nuevo valor de tablero, volver a resolver
                if (!solucionesCalculadas || n != ultimoTamaño) {
                    boolean exito = modelo.resolver(n);

                    if (exito) {
                        vista.getEtiquetaResultado().setText("Total de soluciones posibles: " + modelo.numeroDeCombinaciones);
                        vista.mostrarTablero(modelo.obtenerSolucionAleatoria(), modelo.getDimension());
                        solucionesCalculadas = true;
                        ultimoTamaño = n;
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se encontró solución.");
                        solucionesCalculadas = false;
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese un número entero válido y mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        vista.getBotonMostrarOtra().addActionListener(e -> {
            if (solucionesCalculadas) {
                vista.mostrarTablero(modelo.obtenerSolucionAleatoria(), modelo.getDimension());
            }
        });

        vista.getBotonVolver().addActionListener(e -> {
            vista.dispose();
            juegos.Main_Juegos.main(null);
        });
    }
}
