package juegos.Problema_Caballo.Controlador;

import juegos.Problema_Caballo.Modelo.Modelo_Caballo;
import juegos.Problema_Caballo.Vista.Vista_Caballo;
import juegos.Matriz;

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
        vista.getBotonResolver().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    // Leer valores
                    int n = Integer.parseInt(vista.getCampoTamaño().getText());
                    int fila = Integer.parseInt(vista.getCampoFila().getText()) - 1;
                    int col = Integer.parseInt(vista.getCampoColumna().getText()) - 1;

                    // Validar valores fuera de rango
                    if (fila < 0 || col < 0 || fila >= n || col >= n) {
                        JOptionPane.showMessageDialog(vista,
                                "Ingrese posiciones válidas dentro del tablero.",
                                "Error de posición", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Validar tamaño mínimo
                    if (n < 5) {
                        JOptionPane.showMessageDialog(vista,
                                "El tamaño mínimo del tablero para encontrar solución es 5x5.",
                                "Tamaño insuficiente", JOptionPane.WARNING_MESSAGE);
                        vista.actualizarTablero(null, 0);
                        return;
                    }

                    // Intentar resolver
                    boolean exito = modelo.resolver(n, fila, col);

                    if (!exito) {
                        JOptionPane.showMessageDialog(vista,
                                "No hay solución para la posición inicial que has ingresado.",
                                "Sin solución", JOptionPane.WARNING_MESSAGE);
                        vista.actualizarTablero(null, 0);

                        // Limpiar los campos para permitir nuevo intento
                        vista.getCampoFila().setText("");
                        vista.getCampoColumna().setText("");
                        return;
                    }

                    // Si hay solución, animar el recorrido
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Matriz<Integer> tablero = modelo.getTablero();

                            for (int paso = 1; paso <= n * n; paso++) {
                                Integer[][] visibles = new Integer[n][n];

                                for (int i = 0; i < n; i++) {
                                    for (int j = 0; j < n; j++) {
                                        Integer valor = (Integer) tablero.datos[i][j];
                                        if (valor != null && valor <= paso) {
                                            visibles[i][j] = valor;
                                        }
                                    }
                                }

                                vista.actualizarTablero(visibles, n);

                                try {
                                    Thread.sleep(200); // velocidad de la animación
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }).start();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista,
                            "Ingrese números válidos (enteros positivos).",
                            "Error de entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}